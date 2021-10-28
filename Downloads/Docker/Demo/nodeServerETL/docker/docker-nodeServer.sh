set -e

cd /app/nodeServer
npm install 

echo "RUNNING SERVER"
node server.js