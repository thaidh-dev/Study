from pyspark.sql import SparkSession
import time

spark = SparkSession.builder \
            .getOrCreate()
            
# spark = SparkSession.builder \
#             .config("spark.jars", "postgresql-42.3.1.jar") \
#             .getOrCreate()
            

postgreDF = spark.read.format("jdbc").option("url", "jdbc:postgresql://vm-postgres/superset") \
    .option("dbtable", "public.user").option("user", "jenkins").option("password", "jenkins123") \
    .option("driver", "org.postgresql.Driver") \
    .load()

mysqlDF = spark.read.format("jdbc").option("url", "jdbc:mysql://localhost:3306/world") \
    .option("driver", "com.mysql.cj.jdbc.Driver").option("dbtable", "thaidh_test").option("user", "root") \
    .option("password", "&Dht24111997") \
    .load()

start_time = time.time()

postgreDF.join(mysqlDF, postgreDF.user_id == mysqlDF.customer_id, "inner") \
    .select("user_id", "customer_id", "number", "browser") \
    .show()

print("--- %s seconds ---" % (time.time() - start_time))

spark.stop()



