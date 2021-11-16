# docker run -it 470671670cac
FROM centos

# thực hiện các lệnh khi đang ở trong container
RUN yum update -y
# RUN yum install httpd httpd-tools -y
RUN yum install vim -y
# RUN yum install epel-release -y
# RUN yum update -y
# RUN yum install htop -y

# thiết lập thư mục làm việc mặc định
# WORKDIR /var/www/html
# Container sẽ lắng nghe trên các cổng mạng được chỉ định khi chạy
# EXPOSE 80

# . là nhận thư mục hiện tại của DockerFile
# ADD ./test.html /var/www/html/

# ENTRYPOINT [ "httpd" ]
# CMD [ "-D", "FOREGROUND" ]