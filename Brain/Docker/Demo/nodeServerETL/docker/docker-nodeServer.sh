set -e

cd /app/nodeServer
npm install 

echo "RUNNING SERVER"
# node server.js # ko debug
node --inspect=0.0.0.0:9229 server.js # có thể debug
