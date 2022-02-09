const https = require('https');
const http = require('http');
const fs = require('fs');

const options = {
    key: fs.readFileSync('key.pem'),
    cert: fs.readFileSync('cert.pem')
};
const hostname = '0.0.0.0';
const portHttps = 3030;
const portHttp = 3000;

https.createServer(options, function (req, res) {
    if (req.url != '/favicon.ico') {
        res.writeHead(200);
        res.end("This\'s IOT server, running on port 8080");
        console.log(new Date());
    }
}).listen(portHttp, hostname, () => {
    console.log(`Server running at https://${hostname}:${portHttp}/`);
});

const server = http.createServer((req, res) => {
    if (req.url != '/favicon.ico') {
        res.statusCode = 200;
        res.setHeader('Content-Type', 'text/plain');
        res.end('This\'s IOT server, running on port 8080');
        console.log(new Date());
    }
});

server.listen(portHttps, hostname, () => {
    console.log(`Server running at http://${hostname}:${portHttps}/`);
});