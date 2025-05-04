import shelve
import os

# Ensure the database file is created in the current folder
os.chdir(os.path.dirname(__file__))

with shelve.open('棚') as db:
    print(type(db))  # Output: <class 'shelve.DbfilenameShelf'>
    db['name'] = 'John Doe'
    db['age'] = 25
    db['hobby'] = 'Coding'
    db['skills'] = ['Python', 'Java', 'C++']

with shelve.open('棚', flag='r') as db:
    print(type(db))  # Output: <class 'shelve.DbfilenameShelf'>
    print(db['name'])  # Output: John Doe
    print(db['age'])   # Output: 25
    print(db['hobby']) # Output: Coding
    print(db['skills']) # Output: ['Python', 'Java', 'C++']