from snowflake import connector

def sfconnect():
    cnx = connector.connect(
        user='THAIDH',
        password='&Dht24111997',
        account='yg79731.ap-southeast-1',
        warehouse='COMPUTE_WH',
        database='DEMO_DB',
        schema='PUBLIC'
    )
    return cnx