# apache-camel-intro
An introduction to Apache Camel  integration framework

## How to ? 
1- mvn clean package

2- java -jar target/apache_camel_introduction-1.0-SNAPSHOT.jar

3- Test the routing behavior by adding a file under /tmp/input

## Example
run : $ echo "hello world..." > /tmp/input/text.txt

==> as a result, "/tmp/input/text.txt" is moved under "/tmp/done" folder and a new file "/tmp/output/test.txt" is created with uppercased content, as defined in "MyTransformer" bean.
