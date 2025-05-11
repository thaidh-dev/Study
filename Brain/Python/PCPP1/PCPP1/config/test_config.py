import configparser
import os

# Ensure the database file is created in the current folder
os.chdir(os.path.dirname(__file__))

# config = configparser.ConfigParser(default_section='mysql')
config = configparser.ConfigParser()
config['DEFAULT'] = {
    'host': 'localhost',
    'port': 5432
}
config['postgresql'] = {
    'name': 'postgres',
    'user': 'postgres',
    'password': 'postgres'
}
config['mysql'] = {
    'name': 'mysql',
    'user': 'mysql',
    'password': 'mysql',
    'database': 'mysql'
}

with open('config.ini', 'w') as config_file:
    config.write(config_file)



# print(config.read('config.ini')) # Return list of successfully read files.
print(config.sections()) # Return list of sections in the config file.

# print(config.get('DEFAULT', 'host')) # Return the value of the host key in the DEFAULT section.
# print(config.get('postgresql', 'user')) # Return the value of the user key in the postgresql section.
# print(config.get('mysql', 'user')) # Return the value of the user key in the mysql section.

# print(config['postgresql']['password']) # Return the value of the user key in the postgresql section.
# print(config['mysql']['password']) # Return the value of the user key in the mysql section.

"""
    u can add a new section to the config file using the add_section method.
    an error will be raised: 
        if the section already exists.
        if u set a value for a section without adding the section by using add_section method first.
"""
# config.add_section('mysql') # configparser.DuplicateSectionError: Section 'mysql' already exists
config.add_section('sqlite') # Add a new section to the config file.
config.set('sqlite', 'name', 'sqlite')

# add a new section to the config file without using the add_section method.
config['sqlite'] = {
    'name': 'sqlite',
    'user': 'sqlite',
    'password': 'sqlite'
}

# print(config.get('sqlite', 'name'))
# print(config.sections())

print(config.default_section)
config.default_section = 'mysql'
print(config.default_section)
print(config.sections())
# print(config.get('DEFAULT', 'host')) # configparser.NoSectionError: No section: 'DEFAULT'

config.clear() # Clear the config object.
print(config.sections()) # Return [] - empty list.

