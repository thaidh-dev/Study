db.persons.find()

db.persons.aggregate([
    {
      $project: {
        _id: 0,
        name: 1,
        email: 1,
        birthdate: { $toDate: '$dob.date' },
        age: "$dob.age",
        location: {
          type: 'Point',
          coordinates: [
            {
              $convert: {
                input: '$location.coordinates.longitude',
                to: 'double',
                onError: 0.0,
                onNull: 0.0
              }
            },
            {
              $convert: {
                input: '$location.coordinates.latitude',
                to: 'double',
                onError: 0.0,
                onNull: 0.0
              }
            }
          ]
        }
      }
    },
    {
      $project: {
        gender: 1,
        email: 1,
        location: 1,
        birthdate: 1,
        age: 1,
        fullName: {
          $concat: [
            { $toUpper: { $substrCP: ['$name.first', 0, 1] } },
            {
              $substrCP: [
                '$name.first',
                1,
                { $subtract: [{ $strLenCP: '$name.first' }, 1] }
              ]
            },
            ' ',
            { $toUpper: { $substrCP: ['$name.last', 0, 1] } },
            {
              $substrCP: [
                '$name.last',
                1,
                { $subtract: [{ $strLenCP: '$name.last' }, 1] }
              ]
            }
          ]
        }
      }
    },
    { $group: { _id: { birthYear: { $isoWeekYear: "$birthdate" } }, numPersons: { $sum: 1 } } },
    { $sort: { numPersons: -1 } }
  ])
    

db.friends.find()
    
db.friends.aggregate([
{
    $group: {
        _id: {age: "$age"},
        allHobbies: {$push: "$hobbies"}
    }
}
])

// tach array hobbies thanh cac ban ghi khac nhau
db.friends.aggregate([
{$unwind: "$hobbies"}
])

db.friends.aggregate([
{$unwind: "$hobbies"},
{
    $group: {
        _id: {age: "$age"},
        allHobbies: {$push: "$hobbies"}
    }
}
])

// gia tri cua array khong trung nhau
db.friends.aggregate([
{$unwind: "$hobbies"},
{
    $group: {
        _id: {age: "$age"},
        allHobbies: {$addToSet: "$hobbies"}
    }
}
])

db.friends.find()

// lay truong examScores o ban ghi thu 2 va chi lay 2 gia tri cua array
db.friends.aggregate([
{$project: {
    _id: 0, 
    examScore: {$slice: ["$examScores", 2, 1]}}
}
])

db.friends.aggregate([
{$project: {
    _id: 0, 
    numScores: {$size: "$examScores"}}
}
])

db.friends.aggregate([
{$project: {
    _id: 0, 
    numScores: {$size: "$examScores"}}
}
])

db.friends.aggregate([
{
    $project: {
        _id: 0,
        scores: {
            $filter: {
                input: "$examScores",
                as: "sc",
                cond: {$gt: ["$$sc.score", 60]}
            }
        }
    }
}
])

db.friends.find()

db.friends.aggregate([
{$unwind: "$examScores"},
{$project: {_id: 1, name: 1, age: 1, score: "$examScores.score"}},
{$sort: {score: -1}},
{$group: {_id: "$_id", name: {$first: "$name"}, maxScore: {$max: "$score"}}},
{$sort: {maxScore: -1}}
])

db.friends.aggregate([
{
    $project: {
        discountedPrice: {
            $reduce: {
                input: [
                    {"x": 10, "y": 1310616760.0}, 
                    {"x": 11, "y": 1075507291.0}, 
                    {"x": 12, "y": 1048739601.0}, 
                    {"x": 13, "y": 1137725136.0}, 
                    {"x": 14, "y": 795580315.0}, 
                    {"x": 15, "y": 1154983623.0}, 
                    {"x": 16, "y": 1149331428.0}, 
                    {"x": 17, "y": 1075414530.0}, 
                    {"x": 18, "y": 898245444.0}, 
                    {"x": 19, "y": 851289665.0}, 
                    {"x": 20, "y": 929310851.0}
                ],
                initialValue: 1,
                in: {$sum: ["$$value", "$$this.x"]}
            }
        }
    }
}])


db.persons.find()
db.persons.aggregate([
{
    $bucket: {
        groupBy: "$dob.age",
        boundaries: [0, 18, 30, 50, 80, 120],
        output: {
            numPersons: {$sum: 1},
            averageAge: {$avg: "$dob.age"},
            names: {$push: "$name.first"}
        }
    }
}
])

// gop tuoi va auto phan thanh 5 nhom tuoi
db.persons.aggregate([
{
    $bucketAuto: {
        groupBy: "$dob.age",
        buckets: 5,
        output: {
            numPersons: {$sum: 1},
            averageAge: {$avg: "$dob.age"},
            names: {$push: "$name.first"}
        }
    }
}
])

db.persons.aggregate([
{$match: {gender: "male"}},
{
    $project: {
        _id: 0,
        name: {$concat: ["$name.first", " ", "$name.last"]},
        birthdate: {$toDate: "$dob.date"}
    }
},
{$sort: {birthdate: 1}},
{$skip: 10},
{$limit: 10}
])

// toi da 3 record
db.createCollection("capped", {capped: true, size: 10000, max: 3})

db.capped.insertOne({name: "Max"})
db.capped.insertOne({name: "Manu"})
db.capped.insertOne({name: "Anna"})
db.capped.insertOne({name: "Maria"})

// auto sort by id
db.capped.find().sort({$natural: 1})
db.capped.find().sort({_id: 1})
// 5ce00: -1
// 95cdfe

db.persons.find().sort({$natural: -1})
db.persons.find().sort({_id: -1})


db.persons.aggregate([ { 
    $group: { 
        _id: null, 
        total: { 
            $sum: "$dob.age" 
        } 
    } 
} ] )



db.temp.insertMany([
{
 _id: ObjectId('4f442120eb03305789000000'),
 time: ISODate("2013-10-10T20:55:36Z"),
 value:1
},
{
 _id: ObjectId('4f442120eb03305789000001'),
 time: ISODate("2013-10-10T28:43:16Z"),
 value:2
},
{
 _id: ObjectId('4f442120eb03305789000002'),
 time: ISODate("2013-10-11T27:12:66Z"),
 value:3
},
{
 _id: ObjectId('4f442120eb03305789000003'),
 time: ISODate("2013-10-11T10:15:38Z"),
 value:4
},
{
 _id: ObjectId('4f442120eb03305789000004'),
 time: ISODate("2013-10-12T26:15:38Z"),
 value:5
}
])
  
db.temp.aggregate(
    [
        {
            '$group': {
                '_id': '$time',
                'total': { '$sum': '$value' }
            }
        },
        {
            '$sort': {
                 '_id': 1
            }
        },
        {
            '$group': {
                '_id': 0,
                'time': { '$push': '$_id' },
                'totals': { '$push': '$total' }
            }
        },
        {
            '$unwind': {
                'path' : '$time',
                'includeArrayIndex' : 'index'
            }
        },
        {
            '$project': {
                '_id': 0,
                'time': { '$dateToString': { 'format': '%Y-%m-%d', 'date': '$time' }  },
                'total': { '$arrayElemAt': [ '$totals', '$index' ] },
                'runningTotal': {
                    '$sum': {
                        '$slice': ['$totals', {'$add': ['$index', 1]}]
                    }
                },
            }
        },
    ]
);



db.temp.deleteMany({})

    
    
    
    
    
    
    