const mongodb = require('mongodb');
const MongoClient = mongodb.MongoClient;
const mongoDbUrl = "mongodb+srv://thaidh:dht24111997@cluster0-p8yjh.mongodb.net/shop?retryWrites=true&w=majority";
let _db;

const initDb = callback => {
    if (_db) {
        console.log('Database is alreadly initialized');
        return callback(null, _db);
    }

    MongoClient.connect(mongoDbUrl).then(client => {
        _db = client;
        callback(null, _db);
    }).catch(err => {
        callback(err);
    })
}

const getDb = () => {
    if (!_db)
        throw Error('Database not initialzed');

    return _db;
}

module.exports = {
    initDb,
    getDb
}