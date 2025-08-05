FROM ubuntu:20.04

ENV JAVA_HOME=/opt/java
ENV RESIN_HOME=/opt/resin
ENV CASSANDRA_HOME=/opt/cassandra
ENV PATH=$JAVA_HOME/bin:$RESIN_HOME/bin:$CASSANDRA_HOME/bin:$PATH

WORKDIR /opt

COPY jdk-8u45-linux-x64.tar.gz ./
COPY resin-pro-4.0.61.zip ./
COPY apache-cassandra-1.1.12-bin.tar.gz ./

# Install dependencies
RUN apt-get update && \
    apt-get install -y unzip tar curl libaio1 && \
    rm -rf /var/lib/apt/lists/*

# Install Java
RUN tar -xzf jdk-8u45-linux-x64.tar.gz && \
    mv jdk1.8.0_45 java && \
    rm jdk-8u45-linux-x64.tar.gz

# Install Resin
RUN unzip resin-pro-4.0.61.zip && \
    mv resin-pro-4.0.61 resin && \
    rm resin-pro-4.0.61.zip

# Install Cassandra
RUN tar -xzf apache-cassandra-1.1.12-bin.tar.gz && \
    mv apache-cassandra-1.1.12 cassandra && \
    rm apache-cassandra-1.1.12-bin.tar.gz

COPY postgresql-42.7.7.jar /opt/resin/lib/

WORKDIR /opt/resin

# Start Cassandra and Resin inline without a script
CMD ["bash", "-c", "cassandra -f & sleep 10 && resin.sh console"]
