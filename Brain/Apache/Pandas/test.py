import jaydebeapi, os
import pandas
from datetime import datetime
import time

dsn_database = "superset"
dsn_hostname = "vm-postgres"
dsn_port = "5432"
dsn_uid = "jenkins"
dsn_pwd = "jenkins123"
jdbc_driver_name = "org.postgresql.Driver"
jdbc_driver_loc = os.path.join(r'C:\Users\Admin\Desktop\Apache\Pandas\postgresql-42.3.1.jar')

sql_str = "SELECT * FROM public.user"
connection_string='jdbc:postgresql://'+ dsn_hostname+':'+ dsn_port +'/'+ dsn_database

url = '{0}:user={1};password={2}'.format(connection_string, dsn_uid, dsn_pwd)
print("Connection String: " + connection_string)

conn = jaydebeapi.connect(jdbc_driver_name, connection_string, {'user': dsn_uid, 'password': dsn_pwd},
jars=jdbc_driver_loc)

start_time = time.time()

curs = conn.cursor()
curs.execute(sql_str)
result = curs.fetchall()

df = pandas.DataFrame(result)

print(df[1].value_counts())

print("--- %s seconds ---" % (time.time() - start_time))