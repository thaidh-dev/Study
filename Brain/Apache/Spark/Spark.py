from pyspark.sql import SparkSession
import time

spark = SparkSession.builder \
            .config("spark.jars", "postgresql-42.3.1.jar") \
            .getOrCreate()
            
postgreDF = spark.read.format("jdbc").option("url", "jdbc:postgresql://vm-postgres/superset") \
    .option("dbtable", "public.user").option("user", "jenkins").option("password", "jenkins123") \
    .option("driver", "org.postgresql.Driver") \
    .load()

start_time = time.time()

data = postgreDF.groupBy("browser").count().toJSON().collect()
print(data)

print("--- %s seconds ---" % (time.time() - start_time))

spark.stop()



