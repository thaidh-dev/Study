class Vector:
    def __init__(self, *components):
        self.components = components
 
    def __repr__(self):
        print('đang gọi __repr__')
        return 'đang gọi __repr__'
 
    def __str__(self):
        print('đang gọi __str__')
        return 'đang gọi __str__'
 
 
def encode_vector(v):
    if isinstance(v, Vector):
        return v.__dict__
    else:
        raise TypeError(
            f'Object of type {v.__class__.__name__} is not JSON serializable'
        )

import json
 
v1 = Vector(4, 2, 6)
print(json.dumps(v1, default=encode_vector))