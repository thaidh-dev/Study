# import copy

# a = 'hello'
# b = 10
# c = a

# print(id(a))
# print(id(b))
# print(id(c))
# print(id(a) == id(c))  # True, because c is a reference to a
# print(id(a) == id(b))  # False, because a and b are different objects

# d = [1, 2, ['a', 'b', 'c']]
# e = d.copy()
# f = d[:]
# g = copy.copy(d)
# h = copy.deepcopy(d)
# i = d

# print(id(d) == id(e))  # False, because e is a shallow copy of d
# print(id(d) == id(f))  # False, because f is a shallow copy of d
# print(id(d) == id(g))  # False, because g is a shallow copy of d
# print(id(d) == id(h))  # False, because h is a deep copy of d
# print(id(d) == id(i))  # True, because i is a reference to d

# class Laptop:
#     def __init__(self, brand, model, code, price, margin):
#         self.brand = brand
#         self._model = model
#         self._code = code
#         self.__price = price
#         self.__margin = margin

# laptop = Laptop('Acer', 'Predator', 'AC-100', 5490, 0.2)
# print(getattr(laptop, '_model'))  # Predator
# print(laptop._Laptop_model)  # AttributeError: 'Laptop' object has no attribute '_Laptop_model'


def create_student(name, age, **details):
    student = {
        "name": name,
        "age": age,
        "courses": []
    }
 
    if "email" in details:
        student["email"] = details["email"]
    
    if "phone" in details:
        student["phone"] = details["phone"]
    
    if "address" in details:
        student["address"] = details["address"]
    
    return student

student = create_student(
    "Bob",
    email="bob@example.com",
    age=19,
    address="789 Elm St",
    phone="9876543210",
)
print(student)

