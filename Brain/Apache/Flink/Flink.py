from pyflink.table import EnvironmentSettings, TableEnvironment
import time
from datetime import datetime

# env_settings = EnvironmentSettings.in_streaming_mode()
env_settings = EnvironmentSettings.in_batch_mode()
table_env = TableEnvironment.create(env_settings)
configuration = table_env.get_config().get_configuration();
configuration.set_string("pipeline.jars", "file:///C:/Users/Admin/Desktop/Apache/Flink/flink-connector-jdbc_2.12-1.14.3.jar;file:///C:/Users/Admin/Desktop/Apache/Flink/postgresql-42.3.1.jar")
# đống tham số dưới này dùng cho cái in_streaming_mode()
# configuration.set_string("table.exec.mini-batch.enabled", "true"); # enable mini-batch optimization
# configuration.set_string("table.exec.mini-batch.allow-latency", "5 s"); # use 5 seconds to buffer input records
# configuration.set_string("table.exec.mini-batch.size", "5000"); # the maximum number of records can be buffered by each aggregate operator task
# configuration.set_string("table.optimizer.agg-phase-strategy", "TWO_PHASE"); # enable two-phase, i.e. local-global aggregation

table_env.execute_sql("""
    CREATE TABLE userTable (
        user_id BIGINT, 
        browser VARCHAR(50),
        PRIMARY KEY (user_id) NOT ENFORCED
    ) WITH (
        'connector' = 'jdbc',
        'url' = 'jdbc:postgresql://vm-postgres/superset',
        'table-name' = 'public.user',
        'username' = 'jenkins',
        'password' = 'jenkins123',
        'driver' = 'org.postgresql.Driver'
    )
""")

userTable = table_env.from_path("userTable")
start_time = time.time()
#print("--- Start time: %s ---" % (datetime.today().strftime('%Y-%m-%d-%H:%M:%S')))
result = userTable.group_by("browser").select(userTable.browser, userTable.user_id.count.alias('count'))
result.execute().print()
print("--- %s seconds ---" % (time.time() - start_time))




