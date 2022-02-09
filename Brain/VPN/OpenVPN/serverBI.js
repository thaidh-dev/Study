const https = require('https');
const http = require('http');
const fs = require('fs');

const options = {
    key: fs.readFileSync('key.pem'),
    cert: fs.readFileSync('cert.pem')
};
const hostname = '0.0.0.0';
const port = 4840;

process.argv.forEach(function (val, index, array) {
    switch (val) {
        case 'http':
            const server = http.createServer((req, res) => {
                if (req.url != '/favicon.ico') {
                    res.statusCode = 200;
                    res.setHeader('Content-Type', 'text/plain');
                    res.end('This\'s BI tool server, running on port 4840');
                    console.log(new Date());
                }
            });

            server.listen(port, hostname, () => {
                console.log(`Server running at http://${hostname}:${port}/`);
            });
            break;

        case 'https':
            https.createServer(options, function (req, res) {
                if (req.url != '/favicon.ico') {
                    res.writeHead(200);
                    res.end("This\'s BI tool server, running on port 4840");
                    console.log(new Date());
                }
            }).listen(port, hostname, () => {
                console.log(`Server running at https://${hostname}:${port}/`);
            });
            break;
    }
});


