# class A:
#     def method_A(self):
#         return "Method in A"
 
# class B(A):
#     def method_B(self):
#         return "Method in B"
 
# class C(B):
#     def method_C(self):
#         return "Method in C"
 
# obj = C()

# print(C.__class__)  # Output: <class 'type'>
# print(C.__base__)  # Output: <class '__main__.B'>
# print(C.__bases__)  # Output: (<class '__main__.B'>,)
# print(A.__base__)  # Output: <class 'object'>
# print(A.__bases__)  # Output: (<class 'object'>,)
# print(isinstance(obj, A))  # Output: (<class 'object'>,)
# print(issubclass(C, A))  # Output: (<class 'object'>,)

# print('--------------------------------------------------')

# help(C) # help(C) will show the method resolution order (MRO) and other information about the class C.

print('--------------------------------------------------')

class A:
    def greet(self):
        print("Hello from A")

class B(A):
    def greet(self):
        print("Hello from B")

class C(A):
    def greet(self):
        print("Hello from C")

class D(B, C):
    pass

# Inspecting MRO
print(D.__mro__) # (<class '__main__.D'>, <class '__main__.B'>, <class '__main__.C'>, <class '__main__.A'>, <class 'object'>)
# Alternatively, you can use:
print(D.mro()) # [<class '__main__.D'>, <class '__main__.B'>, <class '__main__.C'>, <class '__main__.A'>, <class 'object'>]

# Output when calling the greet method
d_instance = D()
d_instance.greet()  # This will call greet() from class B based on the MRO.
