// node .\TransformJson.js
var data = {
  "columns": [
    { "name": "DateAdded" },
    { "name": "SupportType" }
  ],
  "records": [
    [ "2016-07-05", "Uncategorised" ],
    [ "2016-08-06", "Categorised" ]
  ]
};

var transformedData = data.records.map(function(rec, idx) {
  return rec.reduce(function(result, value, idx) {
    result[data.columns[idx].name] = value;
    return result;
  }, {});
});
  
console.log(transformedData);