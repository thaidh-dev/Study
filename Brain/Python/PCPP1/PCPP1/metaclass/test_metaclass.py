class MyMetaClass(type):
    def __new__(cls, name, bases, attrs):
        print(f"Creating class {name} with bases {bases} and attrs {attrs}")
        return super().__new__(cls, name, bases, attrs)


class ParentClass:
    pass


class MyClass(ParentClass, metaclass=MyMetaClass):
    class_variable = "I am a class variable"

    def __init__(self, value):
        self.value = value

    def display(self):
        print(f"Value: {self.value}")


my_instance = MyClass(10)
my_instance.display()

DynamicClass = type(
    'DynamicClass',
    (ParentClass,),
    {
        'dynamic_variable': 'I am dynamic',
        'dynamic_func': lambda self: 'I\'m lambda func'
    }
)  # Creating a class dynamically
dynamic_instance = DynamicClass()
print(dynamic_instance.dynamic_variable)
print(dynamic_instance.dynamic_func())
