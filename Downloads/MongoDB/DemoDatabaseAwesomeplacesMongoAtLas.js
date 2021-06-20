use awesomeplaces
db.places.find()
db.places.insertOne(
{
    name: "Nopa",
    location: {
        type: "Point",
        coordinates: [-122.4389058, 37.7747415]
    }
}
) 

db.places.createIndex({location: "2dsphere"})

db.places.find(
{
    location: {
        $near: {
            $geometry: {
                type: "Point", 
                coordinates: [-122.47114, 37.771104]
            },
            $maxDistance: 500,
            $minDistance: 10
        }
    }
}
)

db.places.getIndexes()

// tao 4 diem
const p1 = [-122.4547, 37.77473]
const p2 = [-122.45303, 37.76641]
const p3 = [-122.51026, 37.76411]
const p4 = [-122.51088, 37.77131]

// khoanh vung tao thanh 1 hinh tron
db.places.find(
{
    location: {
        $geoWithin: {
            $geometry: {
                type: "Polygon",
                coordinates: [[p1, p2, p3, p4, p1]]
            }
        }
    }
}
)

db.areas.insertOne(
{
    name: "Golden gate park",
    area: {
        type: "Polygon",
        coordinates: [[p1, p2, p3, p4, p1]]
    }
}
)

db.areas.find()
db.areas.createIndex({area: "2dsphere"})
db.areas.getIndexes()

db.areas.find(
{
    area: {
        $geoIntersects: {
            $geometry: {
                type: "Point",
                coordinates: [-122.49089, 37.76992]
            }
        }
    }
}
)

db.places.find(
{
    location: {
        $geoWithin: {
            $centerSphere: [[-122.46203, 37.77286], 1/6378.1]
        }
    }
}
)
 
db.places.find()






