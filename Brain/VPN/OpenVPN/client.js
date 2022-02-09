var http = require('http');

//The url we want is: 'localhost:3000'
var options = {
    host: '10.8.0.2',
    port: 3000
};

callback = function (response) {
    var str = '';

    //another chunk of data has been received, so append it to `str`
    response.on('data', function (chunk) {
        str += chunk;
    });

    //the whole response has been received, so we just print it out here
    response.on('end', function () {
        console.log('response: ' + str);
    });
}

console.log('Start');
http.request(options, callback).end();