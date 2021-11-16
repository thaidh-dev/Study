let a = [
    { id: 'horizontal-e1-2', source: 'horizontal-1', type: 'smoothstep', target: 'horizontal-2', animated: true, },
    { id: 'horizontal-1', sourcePosition: 'right', type: 'input', className: 'dark-node', data: { label: 'Input' }, position: { x: 0, y: 80 }, },
    { id: 'horizontal-e6-8', source: 'horizontal-6', type: 'smoothstep', target: 'horizontal-8', animated: true, },
    { id: 'horizontal-8', sourcePosition: 'right', targetPosition: 'left', data: { label: 'Node 8' }, position: { x: 750, y: 300 }, },
    { id: 'horizontal-2', sourcePosition: 'right', targetPosition: 'left', data: { label: 'A Node' }, position: { x: 250, y: 0 }, },
    { id: 'horizontal-e3-6', source: 'horizontal-3', type: 'smoothstep', target: 'horizontal-6', animated: true, },
    { id: 'horizontal-3', sourcePosition: 'right', targetPosition: 'left', data: { label: 'Node 3' }, position: { x: 250, y: 160 }, },
    { id: 'horizontal-e5-7', source: 'horizontal-5', type: 'smoothstep', target: 'horizontal-7', animated: true, },
    { id: 'horizontal-4', sourcePosition: 'right', targetPosition: 'left', data: { label: 'Node 4' }, position: { x: 500, y: 0 }, },
    { id: 'horizontal-5', sourcePosition: 'top', targetPosition: 'bottom', data: { label: 'Node 5' }, position: { x: 500, y: 100 }, },
    { id: 'horizontal-e1-4', source: 'horizontal-2', type: 'smoothstep', target: 'horizontal-4', label: 'edge label', },
    { id: 'horizontal-6', sourcePosition: 'bottom', targetPosition: 'top', data: { label: 'Node 6' }, position: { x: 500, y: 230 }, },
    { id: 'horizontal-e3-5', source: 'horizontal-3', type: 'smoothstep', target: 'horizontal-5', animated: true, },
    { id: 'horizontal-e1-3', source: 'horizontal-1', type: 'smoothstep', target: 'horizontal-3', animated: true, },
    { id: 'horizontal-7', sourcePosition: 'right', targetPosition: 'left', data: { label: 'Node 7' }, position: { x: 750, y: 50 }, },

];

console.log(JSON.stringify(a));


function dummyData() {
    let data = [
        {
            id: 'a1',
            data: {
                driver: 'mysql',
                host: 'localhost',
                port: 3306,
                user: 'root',
                password: '&Dht24111997',
                database: 'world',
                schema: 'world',
                table: 'city',
                columnsToRead: ["id", "name", "countrycode", "district", "population"]
            },
            type: 'connectDB'
        },
        {
            id: 'a2',
            data: {
                driver: 'mysql',
                host: 'localhost',
                port: 3306,
                user: 'root',
                password: '&Dht24111997',
                database: 'world',
                schema: 'world',
                table: 'country',
                columnsToRead: ["code", "name"]
            },
            type: 'connectDB'
        },
        {
            id: 'a3',
            data: {
                driver: 'mysql',
                host: 'localhost',
                port: 3306,
                user: 'root',
                password: '&Dht24111997',
                database: 'world',
                schema: 'world',
                table: 'countrylanguage',
                columnsToRead: ["countrycode", "language", "percentage"]
            },
            type: 'connectDB'
        },
        {
            id: 'b1',
            data: {
                aggregateFunction: [
                    {
                        "aggregateType": "count",
                        "outputField": "countcity"
                    },
                    {
                        "aggregateType": "sum",
                        "aggregateField": "population",
                        "outputField": "sumpopulation"
                    }
                ],
                groupByField: 'countrycode',
            },
            type: 'aggregate',
        },
        {
            id: 'b2',
            data: {
                aggregateFunction: [
                    {
                        "aggregateType": "max",
                        "aggregateField": "percentage",
                        "outputField": "maxpercentage"
                    }
                ],
                groupByField: 'countrycode',
            },
            type: 'aggregate',
        },
        {
            id: 'c1',
            data: {
                matchingFields: [
                    'countrycode',
                    'code',
                    'countrycode'
                ],
                joinType: 'rightJoin'
            },
            type: 'join',
        },
        {
            id: 'd1',
            data: {
                fields: [
                    "code",
                ],
                action: 'remove'
            },
            type: 'fieldRemover',
        },
        {
            id: 'e1',
            data: {
                driver: 'postgresql',
                host: 'localhost',
                port: 5433,
                user: 'postgres',
                password: 'postgres',
                database: 'TestDS',
                schema: 'public',
                table: 'thaidh'
            },
            type: 'insertDB',
        },
        { id: 'd1-e1', source: 'd1', target: 'e1' },
        { id: 'a3-b2', source: 'a3', target: 'b2' },
        { id: 'b1-c1', source: 'b1', target: 'c1' },
        { id: 'a1-b1', source: 'a1', target: 'b1' },
        { id: 'a2-c1', source: 'a2', target: 'c1' },
        { id: 'c1-d1', source: 'c1', target: 'd1' },
        { id: 'b2-c1', source: 'b2', target: 'c1' },
    ]
}










let data = [
    {
        id: 'a1',
        data: {
            driver: 'mssql',
            host: 'localhost',
            port: 1433,
            user: 'ThaiDH',
            password: '&Dht24111997',
            database: 'AdventureWorks2019',
            schema: 'HumanResources',
            table: 'Employee',
            columnsToRead: ["BusinessEntityID", "LoginID", "JobTitle"]
        },
        type: 'connectDB'
    },
    {
        id: 'a2',
        data: {
            driver: 'mssql',
            host: 'localhost',
            port: 1433,
            user: 'ThaiDH',
            password: '&Dht24111997',
            database: 'AdventureWorks2019',
            schema: 'HumanResources',
            table: 'EmployeeDepartmentHistory',
            columnsToRead: ["BusinessEntityID", "DepartmentID"]
        },
        type: 'connectDB'
    },
    {
        id: 'a3',
        data: {
            driver: 'mssql',
            host: 'localhost',
            port: 1433,
            user: 'ThaiDH',
            password: '&Dht24111997',
            database: 'AdventureWorks2019',
            schema: 'HumanResources',
            table: 'Department',
            columnsToRead: ["DepartmentID", "Name"]
        },
        type: 'connectDB'
    },
    {
        id: 'b1',
        data: {
            aggregateFunction: [
                {
                    "aggregateType": "count",
                    "outputField": "countcity"
                },
                {
                    "aggregateType": "sum",
                    "aggregateField": "population",
                    "outputField": "sumpopulation"
                }
            ],
            groupByField: 'countrycode',
        },
        type: 'aggregate',
    },
    {
        id: 'b2',
        data: {
            aggregateFunction: [
                {
                    "aggregateType": "max",
                    "aggregateField": "percentage",
                    "outputField": "maxpercentage"
                }
            ],
            groupByField: 'countrycode',
        },
        type: 'aggregate',
    },
    {
        id: 'c1',
        data: {
            matchingFields: [
                'BusinessEntityID',
                'BusinessEntityID',
            ],
            joinType: 'leftJoin'
        },
        type: 'join',
    },
    {
        id: 'c2',
        data: {
            matchingFields: [
                'DepartmentID',
                'DepartmentID',
            ],
            joinType: 'leftJoin'
        },
        type: 'join',
    },    
    {
        id: 'd1',
        data: {
            fields: [
                "loginid",
            ],
            action: 'remove'
        },
        type: 'fieldRemover',
    },
    {
        id: 'e1',
        data: {
            driver: 'postgresql',
            host: 'localhost',
            port: 5433,
            user: 'postgres',
            password: 'postgres',
            database: 'TestDS',
            schema: 'public',
            table: 'thaidh'
        },
        type: 'insertDB',
    },
    { source: 'a1', target: 'c1' },
    { source: 'c2', target: 'd1' },
    { source: 'a2', target: 'c1' },
    { source: 'c1', target: 'c2' },
    { source: 'a3', target: 'c2' },
]

console.log(JSON.stringify(a));