const Knex = require('knex');
const Enumerable = require('linq');

module.exports = {
    connectDB: (driver, host, port, user, password, database, schema, table, columnsToRead) => {
        let knex = Knex({
            client: driver,
            connection: {
                host: host,
                port: port,
                user: user,
                password: password,
                database: database
            }
        });

        return knex(`${schema}.${table}`)
            .select(columnsToRead)
            .then(rows => {
                return rows;
            })
            .catch(error => {
                throw error;
            })
            .finally(() => {
                knex.destroy();
            });
    },

    insertDB: (driver, host, port, user, password, database, schema, table, dataInsert) => {
        let knex = Knex({
            client: driver,
            connection: {
                host: host,
                port: port,
                user: user,
                password: password,
                database: database
            },
            // useNullAsDefault: true
        });

        return knex.transaction(trx => {
			// `${schema}.${table}` => 'dbo.table'
            knex(`${schema}.${table}`).transacting(trx)
                .insert(dataInsert)
                .then(trx.commit)
                .catch(trx.rollback);
        })
            .then(resp => {
                console.log('Transaction complete.');
                return resp;
            })
            .catch(error => {
                throw error;
            })
            .finally(() => {
                knex.destroy();
            });
    },

    aggregate: (input, aggregateFunction, groupByField) => {
        return Enumerable.from(input).groupBy(
            `$.${groupByField}`,
            null,
            (key, group) => {
                let resultObject = {
                    [groupByField]: key
                };
                for (let i = 0; i < aggregateFunction.length; i++) {
                    let func = aggregateFunction[i];
                    let aggregateType = func.aggregateType;
                    let aggregateField = func.aggregateField;
                    if (aggregateField === undefined) {
                        // tính count
                        // VD: resultObject.countAll = group.count()
						// 	group[aggregateType]() => group.count()
                        resultObject[func.outputField] = group[aggregateType]();
                    } else {
                        // tính max hoặc min, average, sum
                        // VD: resultObject.sumField = group.sum('$.fieldToSum')
						// 	resultObject[func.outputField] => resultObject['sumField'] => resultObject.sumField
						// 	group[aggregateType](`$.${aggregateField}`) => group.sum('$.fieldToSum')
                        resultObject[func.outputField] = group[aggregateType](`$.${aggregateField}`);
                    }
                }
                return resultObject;
            })
            .toArray();
    },

    join: (joinType, inputs, matchingFields) => {
        switch (joinType) {
            case 'innerJoin': {
                return innerJoin(inputs[0], 1, inputs, matchingFields);
            }
            case 'leftJoin': {
                return leftOrRightJoin(inputs[0], 1, false, inputs, matchingFields);
            }
            case 'rightJoin': {
                return leftOrRightJoin(inputs[0], 1, true, inputs, matchingFields);
            }
        }
    },

    fieldRemover: (action, fields, objs) => {
        return objs.map(obj => {
            switch (action) {
                case 'remove': {
                    return fields.reduce((newObj, field) => {
                        delete newObj[field];
                        return newObj;
                    }, obj);
                }
                case 'keep': {
                    return fields.reduce((newObj, field) => {
                        newObj[field] = obj[field];
                        return newObj;
                    }, {});
                }
            }
        });
    }
};

function innerJoin(input1, index, inputs, matchingFields) {
    let input2 = inputs[index];
    let matchingField1 = matchingFields[index - 1];
    let matchingField2 = matchingFields[index];
    let dataJoined = Enumerable.from(input1).join(
        input2,
        input1 => input1[matchingField1],
        input2 => input2[matchingField2],
        (input1, input2) => {
            // merge 2 object
			// var obj1 = { food: 'pizza', car: 'ford' }
			// var obj2 = { animal: 'dog' }
			// let merged = { ...obj1, ...obj2 };
			// obj1 now has three properties: food, car, and animal
            return { ...input1, ...input2 };
        }
    ).toArray();

    index++;
    if (index === inputs.length) {
        return dataJoined;
    }
    return innerJoin(dataJoined, index, inputs, matchingFields);
}

function leftOrRightJoin(input1, index, isRightJoin, inputs, matchingFields) {
    let input2 = inputs[index];
    let matchingField1 = matchingFields[index - 1];
    let matchingField2 = matchingFields[index];

    if (isRightJoin) {
        input2 = input1;
        input1 = inputs[index];
        matchingField1 = matchingFields[index];
        matchingField2 = matchingFields[index - 1];
    }
    let dataJoined = Enumerable.from(input1)
        .groupJoin(
            input2,
            input1 => input1[matchingField1],
            input2 => input2[matchingField2],
            (input1, input2) => { return { input1, input2 } }
        )
        .selectMany(
            joined => joined.input2.defaultIfEmpty(),
            (input1, input2) => {
                // merge 2 object
                return { ...input1.input1, ...input2 };
            }
        )
        .toArray();

    index++;
    if (index === inputs.length) {
        return dataJoined;
    }
    return leftOrRightJoin(dataJoined, index, isRightJoin, inputs, matchingFields);
}