git clone https://github.com/oracle/docker-images.git
cd docker-images/OracleDatabase/SingleInstance/dockerfiles/
trong này sẽ doc để đọc
vd nếu muốn cài oracle 19c, thì
    You will have to provide the installation binaries of https://www.oracle.com/database/technologies/oracle-database-software-downloads.html and put them into the dockerfiles/19.3.0 folder. The needed file is named LINUX.X64_193000_db_home.zip.
download file LINUX.X64_193000_db_home.zip về để vào folder dockerfiles/19.3.0

mở git bash
đứng ở folder docker-images/OracleDatabase/SingleInstance/dockerfiles/
chạy lệnh
    ./buildContainerImage.sh -s -v 19.3.0 -o '--build-arg SLIMMING=false'

run container = lệnh 
    docker run --name oracle_local -p 1521:1521 -p 5500:5500 -e ORACLE_PWD=dtsvn123 -v ./volume:/opt/oracle/oradata oracle/database:19.3.0-se2

vào đổi pass của user sys = lệnh
    sqlplus sys/dtsvn123@//localhost:1521/ORCLCDB as sysdba

create user mới = lệnh
    CREATE USER dsrepdata02 IDENTIFIED BY dtsvn123;


