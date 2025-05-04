import csv
import os

# Ensure the database file is created in the current folder
os.chdir(os.path.dirname(__file__))

fieldnames = ['name', 'country', 'age']

""" 
    writing will not rollback when an error occurs
"""

with open('pcpp1.csv', 'w', newline='') as file:
    # writer = csv.DictWriter(file, fieldnames=fieldnames)
    # writer.writeheader()
    # writer.writerow({'name': 'John', 'country': 'USA', 'age': 30})
    # writer.writerow({'name': 'Alice', 'country': 'Canada', 'age': 25})
    # writer.writerow({'name': 'Bob', 'country': 'UK', 'age': 35})

    writer = csv.DictWriter(file, fieldnames=fieldnames, escapechar='\\', quoting=csv.QUOTE_NONE)
    writer.writeheader()
    writer.writerow({'name': 'John', 'country': 'USA', 'age': 30})
    writer.writerow({'name': 'Alice', 'country': 'Canada, Viet Nam, Japan', 'age': 25})
    writer.writerow({'name': 'Bob', 'country': 'UK', 'age': 35})



    # writer = csv.writer(file, quotechar='"', quoting=csv.QUOTE_MINIMAL)
    # writer.writerow(['name', 'country', 'age'])
    # writer.writerow(['John', 'USA', 30])
    # writer.writerow(['Alice', 'Canada, Viet Nam, Japan', 25])
    # writer.writerow(['Bob', 'UK', 35])

    # writer = csv.writer(file, escapechar='\\', quoting=csv.QUOTE_NONE)
    # writer.writerow(['name', 'country', 'age'])
    # writer.writerow(['John', 'USA', 30])
    # writer.writerow(['Alice', 'Canada, Viet Nam, Japan', 25])
    # writer.writerow(['Bob', 'UK', 35])

    # writer = csv.writer(file, quoting=csv.QUOTE_NONNUMERIC)
    # writer.writerow(['name', 'country', 'age'])
    # writer.writerow(['John', 'USA', 30])
    # writer.writerow(['Alice', 'Canada, Viet Nam, Japan', 25])
    # writer.writerow(['Bob', 'UK', 35])

    # writer = csv.writer(file, quoting=csv.QUOTE_ALL)
    # writer.writerow(['name', 'country', 'age'])
    # writer.writerow(['John', 'USA', 30])
    # writer.writerow(['Alice', 'Canada, Viet Nam, Japan', 25])
    # writer.writerow(['Bob', 'UK', 35])

with open('pcpp1.csv', 'r') as file:
    # reader = csv.reader(file, delimiter=',')
    # print(type(reader))  # <class '_csv.reader'>
    # for row in reader:
    #     print(row)

    # dict_reader = csv.DictReader(file, fieldnames=['name', 'country', 'age'])
    # print(type(dict_reader))  # <class '_csv.DictReader'>
    # for row in dict_reader:
    #     print(row)

    reader = csv.reader(file, escapechar='\\', quoting=csv.QUOTE_NONE)
    for row in reader:
        print(row)

    # reader = csv.reader(file, quoting=csv.QUOTE_NONNUMERIC)
    # for row in reader:
    #     print(row)

    # reader = csv.reader(file, quoting=csv.QUOTE_ALL)
    # for row in reader:
    #     print(row)
