db.products.findOne({price: {$gt: 10}})

db.products.find({price: {$gt: 10}})

db.products.find()

db.products.updateOne({_id: "txl-lhr-1"}, {$set: {delayed: true, name: "Bút bi"}})

db.products.updateMany({_id: ObjectId("5e5a15856e1f420591cc03c3")}, {delayed: false})

db.products.replaceOne({_id: "txl-lhr-1"}, {name: "Bút chì", marker: "toDelete"})

db.products.find().forEach((x) => {printjson(x)})

db.products.findOne()

db.products.find({}, {name: 1, _id: 0})

db.products.updateMany({}, {$set: {status: {moTa: "on-time", lastUpdated: "1h ago"}}})


db.products.find()


db.products.updateOne(
{_id: "txl-lhr-1"}, {$set: {hobbies: ["sport", "cookings"]}}
)

// tìm mà d? li?u l?i có m?ng
db.products.find({hobbies: "sport"})

db.products.find({"status.moTa": "on-time"})

db.thai.insertMany([{name: "long"}, {name: "cu?ng", tu?i: 6746574657578485746375848375}, {timestamp: new Timestamp(), time: new Date()}])
db.thai.update(
{_id: ObjectId("5e5a356189ae5ffd8ac71825")}, {tu?i: 22345678901234567890}
)
db.thai.deleteMany({})
db.thai.find()

db.stats()
typeof db.thai.findOne({_id: ObjectId("5e5a356189ae5ffd8ac71825")}).tu?i




















