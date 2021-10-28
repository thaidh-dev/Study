const express = require("express");
const cors = require("cors");
const transform = require('./dataTransformation');

const app = express();
var corsOptions = {
    origin: "http://localhost:8088"
};
app.use(cors(corsOptions));
// parse requests of content-type - application/json
app.use(express.json());
// parse requests of content-type - application/x-www-form-urlencoded
app.use(express.urlencoded({ extended: true }));

// simple route  
app.get("/api/execute", async (req, res) => {
    let response = await execute(req.body);
    res.json(response);
});

// set port, listen for requests
const PORT = process.env.PORT || 8888;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}.`);
});

async function execute(initialElements) {
    let levels = divideLevel(initialElements);
    // return levels;
    let nodes = {};

    // for loop từng level
    loop: for (let i = 0; i < levels.length; i++) {
        let level = levels[i];
        // for loop từng node trong level
        for (let j = 0; j < level.length; j++) {
            let node = level[j];
            let paramInput = node.data;
            let result;

            try {
                // thực hiện ETL
                switch (node.type) {
                    case 'connectDB': {
                        result = await transform.connectDB(paramInput.driver, paramInput.host, paramInput.port, paramInput.user, paramInput.password, paramInput.database, paramInput.schema, paramInput.table, paramInput.columnsToRead);
                        break;
                    }
                    case 'aggregate': {
                        result = transform.aggregate(nodes[node.sourceID], paramInput.aggregateFunction, paramInput.groupByField);
                        break;
                    }
                    case 'join': {
                        let nodesToJoin = [];
                        for (let i = 0; i < node.sourceID.length; i++) {
                            nodesToJoin.push(nodes[node.sourceID[i]]);
                        }
                        result = transform.join(paramInput.joinType, nodesToJoin, paramInput.matchingFields);
                        break;
                    }
                    case 'fieldRemover': {
                        result = transform.fieldRemover(paramInput.action, paramInput.fields, nodes[node.sourceID]);
                        break;
                    }
                    case 'insertDB': {
                        await transform.insertDB(paramInput.driver, paramInput.host, paramInput.port, paramInput.user, paramInput.password, paramInput.database, paramInput.schema, paramInput.table, nodes[node.sourceID]);
                        break;
                    }
                }
            } catch (error) {
                console.log(error);
                nodes.error = error;
                break loop;
            }

            nodes[node.id] = result;
        }
    }

    return nodes;
}

function divideLevel(initialElements) {
    let levels = [];
    let nodes = readFlow(initialElements);

    // sắp xếp các node theo từng level tương ứng
    for (let [nodeID, value] of Object.entries(nodes)) {
        if (levels[value.level] === undefined) {
            levels[value.level] = [];
        }
        // những node mà ko được nối với bất kì node nào khác thì sẽ ko xử lý
        if (value.sourceID === undefined && value.targetID === undefined) {
            continue;
        }
        levels[value.level].push(value);
    }

    return levels;
}

// phân chia các node thành từng level tương ứng
function readFlow(initialElements) {
    let nodesAndFlow = classifyBetweenNodeAndFlow(initialElements);
    let nodes = nodesAndFlow.nodes;
    let flow = nodesAndFlow.flow;

    for (let i = 0; i < flow.length; i++) {
        let edge = flow[i];
        let source = nodes[edge.source];
        let target = nodes[edge.target];
        let sourceID = target.sourceID;
        let targetID = source.targetID;

        setLevelNode(nodes, source, target);
        if (targetID === undefined) {
            // set targetID cho source node
            source.targetID = target.id;
        } else {
            // nếu source node có nhiều target node thì sẽ là 1 array targetID
            if (Array.isArray(targetID)) {
                source.targetID.push(target.id);
            } else {
                // tạo 1 array targetID
                source.targetID = [];
                source.targetID.push(targetID);
                source.targetID.push(target.id);
            }
        }
        if (sourceID === undefined) {
            // set sourceID cho target node
            target.sourceID = source.id;
        } else {
            // nếu target node có nhiều source node thì sẽ là 1 array sourceID
            if (Array.isArray(sourceID)) {
                target.sourceID.push(source.id);
            } else {
                // tạo 1 array sourceID
                target.sourceID = [];
                target.sourceID.push(sourceID);
                target.sourceID.push(source.id);
            }
        }
    }

    return nodes;
}

function classifyBetweenNodeAndFlow(initialElements) {
    let nodes = {};
    let flow = [];
    for (let i = 0; i < initialElements.length; i++) {
        let element = initialElements[i];
        if (element.source === undefined) {
            // set level = 0 cho từng node
            element.level = 0;
            nodes[element.id] = element;
        } else {
            flow.push(element);
        }
    }

    return {
        nodes: nodes,
        flow: flow
    }
}

function setLevelNode(nodes, source, target) {
    let childNodeID = target.targetID;

    // thì level của node target = level của node source + 1
    if (source.level + 1 > target.level) {
        target.level = source.level + 1;
    }
    if (childNodeID !== undefined) {
        if (Array.isArray(childNodeID)) {
            // xử lý set level cho target node có nhiều child node
            for (let i = 0; i < childNodeID.length; i++) {
                // set level cho tất cả các child node của target node
                if (target.level + 1 > nodes[childNodeID[i]].level) {
                    nodes[childNodeID[i]].level = target.level + 1;
                }
                setLevelNode(nodes, target, nodes[childNodeID[i]]);
            }
        } else {
            if (target.level + 1 > nodes[childNodeID].level) {
                // set level cho tất cả các child node của target node
                nodes[childNodeID].level = target.level + 1;
            }
            setLevelNode(nodes, target, nodes[childNodeID]);
        }
    }
}









