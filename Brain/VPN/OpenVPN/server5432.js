const http = require('http');

const hostname = '0.0.0.0';
const port = 5432;

const server = http.createServer((req, res) => {
	if (req.url != '/favicon.ico') {
		res.statusCode = 200;
		res.setHeader('Content-Type', 'text/plain');
		res.end('This\'s postgresql, running on port 5432');
		console.log(new Date());
	}
});

server.listen(port, hostname, () => {
    console.log(`Server running at http://${hostname}:${port}/`);
});