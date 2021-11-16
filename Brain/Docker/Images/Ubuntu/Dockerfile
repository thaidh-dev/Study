FROM ubuntu@sha256:626ffe58f6e7566e00254b638eb7e0f3b11d4da9675088f4781a50ae288f3322

RUN apt update -y
RUN apt upgrade -y
RUN apt-get install -y build-essential libssl-dev libffi-dev python3-dev python3-pip libsasl2-dev libldap2-dev wget vim curl redis-server python3-venv

# nodejs & yarn
RUN curl -fsSL https://deb.nodesource.com/setup_16.x | bash -
# chay apt-get install -y nodejs nó sẽ hỏi 2 câu liên quan đến địa điểm của mình, vẫn chưa tìm được cách auto answer nên dùng cái noninteractive
# noninteractive – You use this mode when you need zero interaction while installing or upgrading the system via apt. 
#   It accepts the default answer for all questions. 
#   It might mail an error message to the root user, but that's it all. 
#   Otherwise, it is totally silent and humble, a perfect frontend for automatic installs
RUN DEBIAN_FRONTEND=noninteractive apt-get install -y nodejs
#	RUN npm install --global yarn

# postgresql-10
RUN wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | apt-key add -
RUN echo "deb http://apt.postgresql.org/pub/repos/apt/ $(lsb_release -cs)-pgdg main" | tee /etc/apt/sources.list.d/postgresql-pgdg.list > /dev/null
RUN apt-get update
RUN DEBIAN_FRONTEND=noninteractive apt-get install -y postgresql-10
RUN service postgresql restart


