import pickle
import os

# Ensure the database file is created in the current folder
os.chdir(os.path.dirname(__file__))

d = {
    'name': 'Muoi Dua',
    'age': 25,
    'hobby': 'Coding'
}

#################################################

# d_pickle = pickle.dumps(d)
# d['skills'] = ['Python']

# print(type(d_pickle))

# d_deepcopy = pickle.loads(d_pickle)

# print(d_deepcopy)
# print(d)

#################################################

with open('muoi_dua.pickle', 'wb') as file:
    pickle.dump(d, file)

d['skills'] = ['Python']

with open('muoi_dua.pickle', 'rb') as file:
    d_loaded = pickle.load(file)

print(d_loaded)

#################################################

def func(arg):
    abc = arg + ' Inner function'

    def inner_func():
        print(abc)

    inner_func()

func_pickle = pickle.dumps(func) # this will work, u can pickle a function
func_deepcopy = pickle.loads(func_pickle)
func_deepcopy('this is an') # this is an Inner function

def func_return_inner_func(arg):
    abc = arg + ' Inner function'

    def inner_func():
        print(abc)

    return inner_func

func_pickle = pickle.dumps(func_return_inner_func) # this will work, in this case, u still pickle an outer function 
func_deepcopy = pickle.loads(func_pickle)
func_deepcopy('this is an')() # this is an Inner function

# inner_func = func_return_inner_func("this is an")
# func_pickle = pickle.dumps(inner_func) # AttributeError: Can't pickle local object 'func_return_inner_func.<locals>.inner_func'

lambda_func = lambda : 'Inner function'
'''
    this will not work, u cannot pickle a lambda function
    _pickle.PicklingError: Can't pickle <function <lambda> at 0x0000010284E3F670>: attribute lookup <lambda> on __main__ failed
'''
# func_pickle = pickle.dumps(lambda_func)

class MyClass:
    class NestedClass:
        def method(self):
            print('Nested class method')

    def __init__(self, name='muoi dua'):
        self.name = name

    def method(self):
        print(self.name)

cls_pickle = pickle.dumps(MyClass) # this will work, u can pickle a class
cls_deepcopy = pickle.loads(cls_pickle)
my_class_instance = cls_deepcopy()
my_class_instance.method() # muoi dua

nested_cls_pickle = pickle.dumps(MyClass.NestedClass) # this will work, u can pickle a nested class
nested_cls_deepcopy = pickle.loads(nested_cls_pickle)
nested_class_instance = nested_cls_deepcopy()
nested_class_instance.method() # Nested class method

nested_cls_pickle = pickle.dumps(MyClass.NestedClass()) # this will work, u can pickle a nested class
nested_cls_instance = pickle.loads(nested_cls_pickle)
nested_cls_instance.method() # Nested class method

def func_return_inner_cls(arg):
    class InnerClass:
        def __init__(self):
            self.value = arg

        def __str__(self):
            return f"InnerClass(value={self.value})"

    return InnerClass

inner_cls = func_return_inner_cls(777)
print(inner_cls()) # InnerClass(value=777)
# cls_pickle = pickle.dumps(inner_cls) # AttributeError: Can't pickle local object 'func_return_inner_func.<locals>.InnerClass'

