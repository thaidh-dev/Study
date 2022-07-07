function hierarhySort(hashArr, key, result) {
    if (hashArr[key] == undefined) return;
    var arr = hashArr[key];
    for (var i = 0; i < arr.length; i++) {
        result.push(arr[i]);
        hierarhySort(hashArr, arr[i].id, result);
    }

    return result;
}

var arr = [
    { id: 4, parent: null },
    { id: 5, parent: 4 },
    { id: 6, parent: 4 },
    { id: 1, parent: null },
    { id: 2, parent: 1 },
    { id: 8, parent: 2 },
    { id: 7, parent: 2 },
    { id: 3, parent: 1 }
]
arr = [
    { id: 7, parent: 3 },
    { id: 2, parent: null },
    { id: 8, parent: 3 },
    { id: 4, parent: 2 },
    { id: 12, parent: 9 },
    { id: 6, parent: 2 },
    { id: 5, parent: 1 },
    { id: 3, parent: 1 },
    { id: 9, parent: 4 },
    { id: 1, parent: null },
    { id: 11, parent: 5 },
    { id: 10, parent: 7 },
];

var hashArr = {};

for (var i = 0; i < arr.length; i++) {
    if (hashArr[arr[i].parent] == undefined) hashArr[arr[i].parent] = [];
    hashArr[arr[i].parent].push(arr[i]);
}

var result = hierarhySort(hashArr, null, []);

for (var i = 0; i < result.length; i++) console.log(result[i]);
