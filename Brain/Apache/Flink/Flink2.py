from pyflink.table import EnvironmentSettings, TableEnvironment

env_settings = EnvironmentSettings.in_batch_mode()
table_env = TableEnvironment.create(env_settings)
configuration = table_env.get_config().get_configuration();
configuration.set_string("pipeline.jars", "file:///C:/Users/Admin/Desktop/Apache/Flink/flink-connector-jdbc_2.12-1.14.3.jar;file:///C:/Users/Admin/Desktop/Apache/Flink/postgresql-42.3.1.jar")

table_env.execute_sql("""
    CREATE TABLE test_nifi (
        codecountry VARCHAR(50), 
        name VARCHAR(50),
        PRIMARY KEY (codecountry) NOT ENFORCED
    ) WITH (
        'connector' = 'jdbc',
        'url' = 'jdbc:postgresql://localhost:5432/TestDS',
        'table-name' = 'public.test_nifi',
        'username' = 'postgres',
        'password' = 'postgres',
        'driver' = 'org.postgresql.Driver'
    )
""")

# result = table_env.from_path("test_nifi").select("codecountry, name")
test_nifi = table_env.from_path("test_nifi")
result = test_nifi.group_by("codecountry").select(test_nifi.codecountry, test_nifi.name.count.alias('count'))
print(result.to_pandas())




