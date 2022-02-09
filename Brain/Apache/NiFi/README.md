docker pull apache/nifi
docker run --name nifi -p 8443:8443 -p 3306:3306 -d -e SINGLE_USER_CREDENTIALS_USERNAME=admin -e SINGLE_USER_CREDENTIALS_PASSWORD=ctsBtRBKHRAx69EqUghvvgEvjnaLjFEB apache/nifi:latest

https://localhost:8443/nifi

docker exec -it nifi bash   