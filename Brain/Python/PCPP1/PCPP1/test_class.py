class A:
    def method_A(self):
        return "Method in A"
 
class B(A):
    def method_B(self):
        return "Method in B"
 
class C(B):
    def method_C(self):
        return "Method in C"
 
obj = C()

print(C.__base__)  # Output: <class '__main__.B'>
print(C.__bases__)  # Output: (<class '__main__.B'>,)
print(A.__base__)  # Output: <class 'object'>
print(A.__bases__)  # Output: (<class 'object'>,)
