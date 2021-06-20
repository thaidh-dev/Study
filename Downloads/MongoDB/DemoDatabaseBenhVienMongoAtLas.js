use benhVien

db.benhNhan.find()

db.benh.insertOne(
{_id: "corona", trieuChung: ["dau chân", "bu?n"]}
)
db.benhNhan.insertOne(
{tên: "Tr?n Ð?c Nam", tu?i: 29, b?nh: "corona"}
)
db.benhNhan.insertOne(
{tên: "Duong H?ng Thái", tu?i: 17, b?nh: "Cúm gia c?m"}
)


db.benhNhan.deleteMany({})
db.benh.deleteMany({})
db.benh.find()
db.benhNhan.find()

var dsid = db.benhNhan.findOne().b?nh
db.benh.findOne({_id: dsid})

db.benhNhan.insertOne(
{tên: "Nguy?n Van Cu?ng", tu?i: 24, b?nh: "Lu?i"}
)


db.questionThreads.find()
db.answers.find()
db.questionThreads.insertOne(
{creator: "Max", question: "Tôi nó d?ng bào có nghe rõ không", answers: ["q1a1", "q1a2"]}
)

db.answers.insertMany(
[{_id: "q1a1", text: "Có"}, {_id: "q1a2", text: "Không"}]
)

db.thanhPho.insertOne(
{name: "Vu Hán", toaDo: {x: 21, y: 55}}
)

db.nguoiDan.insertMany([
{name: "Sing Sing", cityId: ObjectId("5e5b749b614c5f26c488e1ca")},
{name: "Fly", cityId: ObjectId("5e5b749b614c5f26c488e1ca")}
])

db.thanhPho.find()
db.nguoiDan.find()


db.products.insertOne(
{title: "a book", price: 12.99}
)

db.customers.insertOne(
{name: "Max", age: 29, order: [{productId: ObjectId("5e5b76df614c5f26c488e1cd"), quantity: 2}]}
)

db.products.find()
db.customers.find()

db.books.insertOne(
{name: "Toán", authors: [{name: "Thái", age: 25}, {name: "Nam", age: 25}]}
)

db.authors.insertMany([
{name: "Thái", age: 29, address: "Hà N?i"},
{name: "Nam", age: 25, address: "Hà Nam"}
])

db.books.find()
db.authors.find()

db.books.updateOne(
{}, {$set: {authors: [ObjectId("5e5b79d2614c5f26c488e1d0"), ObjectId("5e5b79d2614c5f26c488e1d1"), "DHT"]}}
)

db.books.aggregate(
[{$lookup: {from: "authors", localField: "authors", foreignField: "_id", as: "creators"}}]
)

db.users.insertMany([
{name: "Thái", age: 24, email: "thaidh@gmail.com"},
{name: "Long", age: 24, email: "longnh@gmail.com"}
])

db.posts.insertOne(
{
    title: "my first post",
    text: "dây là post",
    tags: ["new", "tech"],
    creator: ObjectId("5e5b7da2614c5f26c488e1d2"),
    comments: [{
        text: "dây là comment",
        author: ObjectId("5e5b7da2614c5f26c488e1d3")
    }]
})


db.users.find()
db.posts.find()
db.posts.drop()

db.createCollection('posts', {
  validator: {
    $jsonSchema: {
      bsonType: 'object',
      required: ['title', 'text', 'creator', 'comments'],
      properties: {
        title: {
          bsonType: 'string',
          description: 'must be a string and is required'
        },
        text: {
          bsonType: 'string',
          description: 'must be a string and is required'
        },
        creator: {
          bsonType: 'objectId',
          description: 'must be an objectid and is required'
        },
        comments: {
          bsonType: 'array',
          description: 'must be an array and is required',
          items: {
            bsonType: 'object',
            required: ['text', 'author'],
            properties: {
              text: {
                bsonType: 'string',
                description: 'must be a string and is required'
              },
              author: {
                bsonType: 'objectId',
                description: 'must be an objectid and is required'
              }
            }
          }
        }
      }
    }
  }
})


db.runCommand({
  collMod: 'posts',
  validator: {
    $jsonSchema: {
      bsonType: 'object',
      required: ['title', 'text', 'creator', 'comments'],
      properties: {
        title: {
          bsonType: 'string',
          description: 'must be a string and is required'
        },
        text: {
          bsonType: 'string',
          description: 'must be a string and is required'
        },
        creator: {
          bsonType: 'objectId',
          description: 'must be an objectid and is required'
        },
        comments: {
          bsonType: 'array',
          description: 'must be an array and is required',
          items: {
            bsonType: 'object',
            required: ['text', 'author'],
            properties: {
              text: {
                bsonType: 'string',
                description: 'must be a string and is required'
              },
              author: {
                bsonType: 'objectId',
                description: 'must be an objectid and is required'
              }
            }
          }
        }
      }
    }
  },
  validationAction: 'warn'
});
    


db.users.find()
db.users.insertMany([
{
    _id: 7,
    name: "Trang",
    age: 24,
    email: "trangnv@gmail.com"
},
{
    _id: 6,
    name: "Vân",
    age: 24,
    email: "vannv@gmail.com"
},
{
    _id: 8,
    name: "Linh",
    age: 24,
    email: "linhnv@gmail.com"
}
], {ordered: false})

db.users.deleteOne({_id: 1})


db.movies.find({runtime: {$gte: 60}})

db.movies.find({"rating.average": {$gt: 7}})
db.movies.find({genres: "Drama"})
db.movies.find({runtime: {$nin: [30, 42]}})

// select count(average) from movies where average < 5
db.movies.find({"rating.average": {$gt: 5}}).count()
db.movies.find({"rating.average": {$gt: 9.3}}).count()
db.movies.find().count()

db.movies.find({$or: [{"rating.average": {$lt: 5}}, {"rating.average": {$gt: 9.3}}]}).count()
db.movies.find({$and: [{"rating.average": {$gt: 9}}, {genres: "Drama"}]})
// nhu nay thi mac dinh la $and 
db.movies.find({"rating.average": {$gt: 9}, genres: "Drama"})
db.movies.find({$and: [{"rating.average": {$gt: 9}}, {genres: "Drama"}]})

// bat buoc trong genres phai co ca drama va horror
db.movies.find({$and: [{genres: "Drama"}, {genres: "Horror"}]})
// chi can co 1 trong 2
db.movies.find({genres: "Drama", genres: "Horror"})

db.movies.find({runtime: {$not: {$eq: 60}}}).count()
// =
db.movies.find({runtime: {$ne: 60}}).count()

db.users.find()
db.users.insertOne(
{
    _id: 100,
    name: "Hùng",
    age: 30,
    email: "hungnv@gmail.com"
})

db.users.find({job: {$exists: true}})
db.users.find({age: {$type: "number"}})
db.users.find({age: {$type: ["number", "string"]}})

db.movies.find({summary: {$regex: /musical/}})
db.users.find({$expr: {$gt: ["$_id", "$age"]}})
db.users.find()

db.movies.find()

db.empdetails.find()
db.empdetails.insertMany([
{
    "_id" : ObjectId("56750affdaac17575df77f3e"),
    "emp_code" : "E005",
    "emp_name" : "Alan Hogg",
    "date_of_join" : "15/09/2013",
    "salary" : 19000,
    "gross" : {
            "basic" : 12000,
            "da" : 5000,
            "hra" : 2000
    },
    "deduction" : {
            "pf" : 2000,
            "pt" : 300,
            "it" : 200
    }
},
{
        "_id" : ObjectId("567532f0f61afaaed2aae48c"),
        "emp_code" : "E006",
        "emp_name" : "Karlos Mint",
        "date_of_join" : "23/05/2010",
        "salary" : 17000,
        "gross" : {
                "basic" : 11000,
                "da" : 4500,
                "hra" : 1500
        },
        "deduction" : {
                "pf" : 3000,
                "pt" : 300,
                "it" : 400
        }
},
{
        "_id" : ObjectId("567532fbf61afaaed2aae48d"),
        "emp_code" : "E007",
        "emp_name" : "Burg Hence",
        "date_of_join" : "27/08/2011",
        "salary" : 20000,
        "gross" : {
                "basic" : 14000,
                "da" : 5000,
                "hra" : 1000
        },
        "deduction" : {
                "pf" : 2500,
                "pt" : 300,
                "it" : 200
        }
},
{
        "_id" : ObjectId("56753307f61afaaed2aae48e"),
        "emp_code" : "E004",
        "emp_name" : "Kim Nail",
        "date_of_join" : "16/10/2010",
        "salary" : 17000,
        "gross" : {
                "basic" : 14000,
                "da" : 3000,
                "hra" : 0
        },
        "deduction" : {
                "pf" : 2000,
                "pt" : 300,
                "it" : 200
        }
}
])
   
db.empdetails.find()

db.sales.insertMany([
  { "_id" : 1, "item" : "abc", "price" : NumberDecimal("10"), "quantity" : NumberInt("2"), "date" : ISODate("2014-03-01T08:00:00Z") },
  { "_id" : 2, "item" : "jkl", "price" : NumberDecimal("20"), "quantity" : NumberInt("1"), "date" : ISODate("2014-03-01T09:00:00Z") },
  { "_id" : 3, "item" : "xyz", "price" : NumberDecimal("5"), "quantity" : NumberInt( "10"), "date" : ISODate("2014-03-15T09:00:00Z") },
  { "_id" : 4, "item" : "xyz", "price" : NumberDecimal("5"), "quantity" :  NumberInt("20") , "date" : ISODate("2014-04-04T11:21:39.736Z") },
  { "_id" : 5, "item" : "abc", "price" : NumberDecimal("10"), "quantity" : NumberInt("10") , "date" : ISODate("2014-04-04T21:23:13.331Z") },
  { "_id" : 6, "item" : "def", "price" : NumberDecimal("7.5"), "quantity": NumberInt("5" ) , "date" : ISODate("2015-06-04T05:08:13Z") },
  { "_id" : 7, "item" : "def", "price" : NumberDecimal("7.5"), "quantity": NumberInt("10") , "date" : ISODate("2015-09-10T08:43:00Z") },
  { "_id" : 8, "item" : "abc", "price" : NumberDecimal("10"), "quantity" : NumberInt("5" ) , "date" : ISODate("2016-02-06T20:20:13Z") },
])

db.sales.aggregate( [
  {
    $group: {
       _id: null,
       count: { $sum: 1 }
    }
  }
] )

db.sales.aggregate(
  [
    // First Stage
    {
      $group :
        {
          _id : "$item",
          totalSaleAmount: { $sum: { $multiply: [ "$price", "$quantity" ] } }
        }
     },
     // Second Stage
     {
       $match: { "totalSaleAmount": { $gte: 100 } }
     }
   ]
 )

db.sales.aggregate([
{$group: {_id: {item: "$item"}, quantityMax: {$max: "$quantity"}}}
])

db.sales.aggregate([
{$group: {_id: "$item", id: {$first: "$_id"}, quantity: {$cond}}},
])

db.sales.aggregate([
{$group: {_id: {item: "$item", quantity: "$quantity"}}},
{$match: {"_id.quantity": 5}}
])

db.sales.find()

db.sales.find({
    $expr: {
        $gt: [{
            $cond: [
                {$gte: ["$volume", 190]},
                {$subtract: ["$volume", 10]},
                "$volume"
            ]
        }, "$target"]
    }
})

db.users.find()
db.users.find({job: {$all: ["Th? xây"]}})

db.users.find({$and: [{name: "Thái"}, {age: 24}]})

// $elemMatch chi choi dc vs array

db.movies.find({_id: ObjectId("5e5bc374614c5f26c488e1de")})
db.movies.find()
var cursor = db.movies.find();
cursor.next()
cursor

var cursor = db.movies.find();
var tong = 0
cursor.forEach(doc => {
    tong += doc.id
    print(tong)
})


cursor.forEach(doc => {print(doc.id)})
cursor.hasNext()

db.sales.find()
db.sales.aggregate([
// {$match: {quantity: {$gte: 5}}},
{$group: {_id: null, tong: {$sum: {$cond: [{$gte: ["$quantity", 5]}, "$quantity", 0]}}}}
])

db.sales.aggregate([
// {$match: {quantity: {$gte: 5}}},
{$group: {_id: "$item", tong: {$sum: 1}}}
])


db.movies.find().sort({"rating.average": 1, "runtime": -1}).skip(100).limit(10)

db.users.find().skip(5).limit(7)

// lay nhung ban ghi co genres(cai nay la array) chua "Drama", nhung ma khi show ra thi no chi ko show het, chi show dung "Drama thoi"
db.movies.find({genres: "Drama"}, {"genres.$": 1})
db.movies.find({genres: {$all: ["Drama", "Horror"]}}, {"genres.$": 1})
db.movies.find({genres: {$all: ["Drama", "Horror"]}}, {"genres.$": 1}).count()
db.movies.find({genres: {$all: ["Drama"]}}, {"genres.$": 1}).count()

db.movies.find({genres: "Drama"}, {genres: {$elemMatch: {$eq: "Horror"}}})
db.movies.find({"rating.average": {$gt: 9}}, {genres: {$elemMatch: {$eq: "Horror"}}})

// gioi han ket qua cua array genres(chi dc show ra 2 thanh phan cua mang)
db.movies.find({"rating.average": {$gt: 9}}, {genres: {$slice: 2}, name: 1})
// bo 1 ban ghi dau cua genres va lay 2 ban ghi sau
db.movies.find({"rating.average": {$gt: 9}}, {genres: {$slice: [1, 2]}, name: 1})
db.movies.find({"rating.average": {$gt: 9}})

db.movies.find({_id: ObjectId("5e5bc374614c5f26c488e1f7")}, {genres: 1})

db.users.find()
// tang gia tri cua 1 truong
db.users.updateOne({name: "Thái"}, {$inc: {age: 2}, $set: {isSporty: false}})

// age < 20 thi update thanh 20 (max thi cung the)
db.users.updateOne({name: "Thái"}, {$min: {age: 20}})

// age * 1.1
db.users.updateOne({name: "Thái"}, {$mul: {age: 1.1}})

db.users.updateOne(
{_id: ObjectId("5e5b7da2614c5f26c488e1d2")}, 
{$set: {nhung: "alo alo"}}
)
db.users.find()
// xoa truong dc chi dinh
db.users.updateMany({_id: ObjectId("5e5b7da2614c5f26c488e1d2")}, {$unset: {nhung: ""}})

// doi ten truong
db.users.updateMany({}, {$rename: {isSporty: "reNamed"}})

// khong tim thay thang "Tran Duc Nam" thi ko update nua ma them me no ban ghi moi luon
db.users.updateOne(
{
    name: "Tr?n Ð?c Nam"}, 
    {$set: {age: 7}
},
{upsert: true}
)

db.users.find()
// update 1 frequency cua array hobbies
db.users.updateOne({name: "Thái"}, {$inc: {"hobbies.$[].frequency": -1}})
db.users.update(
{name: "Thái"}, 
{
    $set: {
        hobbies: [
            {
                title: "Good food", 
                frequency: 7, 
                goodFrequency: false
            }
        ]
    }
}
)

db.users.updateOne(
{name: "Thái"}, 
{$set: {"hobbies.$[el].goodFrequency": true}},
{arrayFilters: [{"el.frequency": {$eq: 6}}]}
)

db.users.update(
{name: "Thái"}, 
{
    $push: {
        hobbies: {
            title: "Sports", 
            frequency: 3, 
        }
    }
}
)

db.users.updateOne(
{name: "Thái"}, 
{$pull: {hobbies: {}}}
)

db.users.find()

// xoa ban ghi co hobbies.title = "Sports"
db.users.updateOne(
{name: "Thái"}, 
{$pull: {hobbies: {title: "Sports"}}}
)

// xoa ban ghi dau tien hoac cuoi cung cua array
db.users.updateOne(
{name: "Thái"}, 
{$pop: {hobbies: 1}}
)

// them gia tri vao mang, neu mang da co gia tri tuong tu thi deo them
db.users.updateOne(
{name: "Thái"}, 
{$addToSet: {hobbies: {title: "Hiking", frequency: 111}}}
)




// bat mongo shell len thi moi query dc
db.persons.explain().find({"dob.age": {$gt: 6}})
db.persons.explain("executionStats").find({"dob.age": {$gt: 6}})

db.persons.find({"dob.age": {$gt: 6}})

db.persons.createIndex({"dob.age": 1})
db.persons.dropIndex({"dob.age": 1})

db.persons.getIndexes()
db.persons.createIndex({"dob.age": {$gt: 70}}) // cau nay sai
db.persons.find({email: "abigail.clark@example.com"})
db.persons.aggregate([
{
    $group: {
        _id: "$email",
        tong: {$sum: 1}
    }
},
{
    $match: {
        tong: {$gt: 1}
    }
},
{
    $sort: {_id: 1}
}
])
db.persons.find()
db.persons.getIndexes()

// Partial indexes only index the documents in a collection that meet a specified filter expression. 
// By indexing a subset of the documents in a collection, partial indexes have lower storage requirements and reduced performance costs for index creation and maintenance.
db.persons.createIndex({"dob.age": 1}, {partialFilterExpression: {"dob.age": {$gt: 60}}})
db.persons.createIndex({"dob.age": 1}, {unique: true, partialFilterExpression: {"dob.age": {$gt: 60}}})

// create index va bien cai email thanh unique
db.persons.createIndex({email: 1}, {unique: true})

db.persons.find({"dob.age": {$gt: 60}})


db.sessions.insertOne({data: "Haizzz", createdAt: new Date()})
db.sessions.find()
// xoa luon het cac ban ghi trong bang sau 10s
db.sessions.createIndex({createdAt: 1}, {expireAfterSeconds: 10})
db.sessions.getIndexes()


db.products.find()
db.products.insertMany([
{title: "A leg", description: "Chân to"},
{title: "A t-shirst", description: "Áo màu d?"}
])
db.products.createIndex({description: "text"})
db.products.getIndexes()
// co "do" hoac "chan" la ok
db.products.find({$text: {$search: "d? chân"}})

// phai match vs "d? chân"
db.products.find({$text: {$search: "\"d? chân\""}})

// diem danh gia xem doan text cua $search khop bao nhieu so vs du lieu
db.products.find(
{$text: {$search: "\"màu\""}}, 
{score: {$meta: "textScore"}}
).sort(
{score: {$meta: "textScore"}}
)









    