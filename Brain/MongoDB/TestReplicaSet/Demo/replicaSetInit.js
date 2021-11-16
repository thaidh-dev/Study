rs.initiate({
    _id: "rs0",
    members: [
        { _id: 0, host: "192.168.0.119:27017" },
        { _id: 1, host: "192.168.0.119:27018" },
        { _id: 2, host: "192.168.0.119:27019" },
    ]
})
rs.add({ host: "192.168.0.119:27019" })

rs.initiate({
    _id: "rs0",
    members: [
        { _id: 0, host: "192.168.0.119:27017" },
        { _id: 1, host: "192.168.0.119:27018" },
        { _id: 2, host: "192.168.0.119:27019", arbiterOnly: true },
    ]
})

docker run -d -p 27020:27017 -v volume_test_replicaset_x:/data/db --name mongox --net network_test_replica_set mongo:3.2.0 mongod --replSet rs0
rs.add({ host: "192.168.0.119:27020" })

rs.add({ host: "192.168.0.119:27018" })
rs.add({ host: "192.168.0.119:27019" })

rs.remove("192.168.0.119:27017")
rs.remove("192.168.0.119:27018")
rs.remove("192.168.0.119:27019")

// rs.initiate({
//     _id: "rs0",
//     members: [
//         { _id: 0, host: "192.168.0.119:27017", tags: { usage: "production" } },
//         { _id: 1, host: "192.168.0.119:27018", tags: { usage: "production" } },
//         { _id: 2, host: "192.168.0.119:27019", priority: 0, votes: 1, tags: { usage: "readonly" } },
//     ]
// })
