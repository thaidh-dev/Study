from json import JSONEncoder, JSONDecoder
import json
import os

# Ensure the database file is created in the current folder
os.chdir(os.path.dirname(__file__))


class Animal:
    def __init__(self, sound, is_male, age, number_of_legs):
        self.sound = sound
        self.is_male = is_male
        self.age = age
        self.number_of_legs = number_of_legs


# animal = Animal('meo meo', True, float('-inf'), float('nan'))
# json_animal = json.dumps(animal.__dict__)
# print(json_animal)
# animal = json.loads(json_animal)
# print(animal) # result is a dict


class MyEncoder(JSONEncoder):
    def default(self, o):
        if isinstance(o, Animal):
            return o.__dict__
        return super().default(o)


class MyDecoder(JSONDecoder):
    def __init__(self, *, object_hook = None, parse_float = None, parse_int = None, parse_constant = None, strict = True, object_pairs_hook = None):
        super().__init__(object_hook=self.object_hook, parse_float=parse_float, parse_int=parse_int, parse_constant=parse_constant, strict=strict, object_pairs_hook=object_pairs_hook)

    def object_hook(self, dict):
        return Animal(**dict)


animal = Animal('meo meo meo', None, float('-inf'), float('nan'))
# json_animal = json.dumps(animal, cls=MyEncoder)
# print(json_animal)
# animal = json.loads(json_animal, cls=MyDecoder)
# print(animal) # result is a dict


with open('PCPP1.json', 'w') as json_file:
    json.dump(animal, json_file, cls=MyEncoder)
with open('PCPP1.json', 'r') as json_file:
    animal = json.load(json_file, cls=MyDecoder)
    print(animal.sound)
    print(animal.is_male)
    print(animal.age)
    print(animal.number_of_legs)