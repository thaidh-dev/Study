from pyspark.sql import SparkSession
from pyspark.sql.functions import col


spark = SparkSession.builder.appName("SparkSample") \
    .master("spark://192.168.7.117:7077")\
    .config("spark.driver.host", "192.168.7.100")\
    .getOrCreate()
    # .config("spark.pyspark.driver.python", "C:\\Users\\Admin\\.pyenv\\pyenv-win\\versions\\3.8.10\\pytxxhon3.exe")\
    # .config("spark.pyspark.python", "C:\\Users\\Admin\\.pyenv\\pyenv-win\\versions\\3.8.10\\python.exe")\
simpleData = [("13795349", "Chrome"),
                ("13795350", "FireFox"),
                ("13795351", "Edge"),
                ("13795352", "Chrome"),
                ("13795353", "Safari"),
                ("13795354", "Edge"),
                ("13795355", "Edge"),
                ("13795356", "Safari"),
                ("13795358", "Opera"),
                ("13795359", "Opera"),
                ("13795360", "Opera"),
                ("13795361", "Opera"),
                ("13795362", "Opera"),
                ("13795363", "Opera")
                ]
schema = ["user_id", "browser"]

df = spark.createDataFrame(data=simpleData, schema=schema)

data = df.groupBy("browser").count() \
    .select(col("browser").alias("name"), col("count").alias("value")) \
    .show()

spark.stop()
