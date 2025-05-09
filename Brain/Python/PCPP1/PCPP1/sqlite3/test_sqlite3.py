import sqlite3
import os

# Ensure the database file is created in the current folder
os.chdir(os.path.dirname(__file__))

conn = sqlite3.connect("chinook.db")
c = conn.cursor()

# c.execute("select * from employees where employeeid = 343364")
# rows = c.fetchall()
# print(rows)

# c.execute('drop table tasks;')
# c.execute('''
#           create table if not exists tasks (
#             id integer primary key,
#             name text not null,
#             priority int not null
#           );
#           ''')
# tasks = [
#     ('first task', 1),
#     ('second task', 5),
#     ('third task', 10)
# ]
# # c.execute('insert into tasks(name, priority) values (?, ?)', ('first task', 1))
# c.executemany('insert into tasks(name, priority) values (?, ?)', tasks)
# print(c.rowcount) # total number of rows that were inserted
# print(c.fetchone()) # None
# print(c.fetchmany(2)) # []
# print(c.fetchall()) # []

# c.execute('update tasks set priority = ? where id = ?', (777, 3))
c.execute('update tasks set priority = ?', (777,))
# c.execute('update tasks set priority = ?', (None,)) # sqlite3.IntegrityError: NOT NULL constraint failed: tasks.priority
conn.commit()

# c.execute('select * from tasks')
task_id = 1
task_id = '1 or 1=1'
c.execute(f"select * from tasks where id = {task_id}")

'''
This is expected behavior because the DB‑API specification states that rowcount is set to –1 when the number of rows affected or returned is not determinable until all rows are fetched
In SQLite’s case, rows are produced on the fly, and the module doesn’t pre‑count them, so even after a fetchall() the rowcount attribute remains –1
'''
print(c.rowcount) # -1
print(c.fetchone()) # (1, 'first task', 1)
print(c.fetchmany(2)) # [(2, 'second task', 5), (3, 'third task', 10)]
print(c.fetchall()) # []

conn.commit()
conn.close()
