from abc import ABC, abstractmethod


class MyAbstractClass(ABC):
    name = 'thaidh'

    def __init__(self):
        MyAbstractClass.name = 'hope'
        self.job = 'unemployment'

    '''
        Abstract Methods Can Have a Body
        Even though having a body, any subclass must override it before an instance of that subclass can be created
    '''
    @abstractmethod
    def my_abstract_method(self):
        MyAbstractClass.name = 'anhmth'
        print('43465768')

    def my_method(self):
        print('my_method')


class MyClass(MyAbstractClass):
    def my_abstract_method(self, age):
        print(MyAbstractClass.name, age, self.job)
        print(MyClass.name, age, self.job)


my_class = MyClass()
my_class.my_abstract_method(7)
my_class.my_method()