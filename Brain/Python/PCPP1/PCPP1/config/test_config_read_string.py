import configparser

config = configparser.ConfigParser()
config_str = """
    [DEFAULT]
    host = localhost
    port = 5432

    [postgresql]
    name = postgres
    user = postgres
    password = postgres

    [mysql]
    name = mysql
    user = mysql
    password = mysql
"""

config.read_string(config_str)
print(config.sections()) # Return list of sections in the config file.

print(config.get('DEFAULT', 'host')) # Return the value of the host key in the DEFAULT section.
print(config.get('postgresql', 'user')) # Return the value of the user key in the postgresql section.
print(config.get('mysql', 'user')) # Return the value of the user key in the mysql section.

print(config['postgresql']['password']) # Return the value of the user key in the postgresql section.
print(config['mysql']['password']) # Return the value of the user key in the mysql section.
