class MyMetaClass(type):
    def __new__(cls, name, bases, attrs):
        attrs['anhmth'] = 'anhmth'
        print(f"Creating class {name} with bases {bases} and attrs {attrs}")
        obj = super().__new__(cls, name, bases, attrs)
        obj.custom_attr = 'thaidh'
        return obj


class ParentClass:
    def __init__(self):
        self.parent_attr = 'Parent Attribute'


class MyClass(ParentClass, metaclass=MyMetaClass):
    class_variable = "I am a class variable"

    def __init__(self, value):
        self.value = value
        super().__init__()

    def display(self):
        print(f"Value: {self.value}")


my_instance = MyClass(10)
my_instance.display()
print(my_instance.__dict__)
print(MyClass.__dict__)

# DynamicClass = type(
#     'DynamicClass',
#     (ParentClass,),
#     {
#         'dynamic_variable': 'I am dynamic',
#         'dynamic_func': lambda self: 'I\'m lambda func'
#     }
# )  # Creating a class dynamically
# dynamic_instance = DynamicClass()
# print(dynamic_instance.dynamic_variable)
# print(dynamic_instance.dynamic_func())
