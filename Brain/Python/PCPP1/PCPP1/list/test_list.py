# Using extend() with a list
numbers = [1, 2, 3]
more_numbers = [4, 5, 6]
numbers.extend(more_numbers)
print(numbers)  # Output: [1, 2, 3, 4, 5, 6]

# Using extend() with a tuple
letters = ['a', 'b']
more_letters = ('c', 'd')
letters.extend(more_letters)
print(letters)  # Output: ['a', 'b', 'c', 'd']

# Using extend() with a string
chars = ['X', 'Y']
chars.extend("Zed")
print(chars)  # Output: ['X', 'Y', 'Z', 'e', 'd']

print("----------------------------------------------------------")

# Starting with a simple list
fruits = ['apple', 'banana', 'cherry']

# Append a new element to the list
fruits.append('orange')
print(fruits)
# Output: ['apple', 'banana', 'cherry', 'orange']

# Appending a list as a single element
numbers = [1, 2, 3]
numbers.append([4, 5])
print(numbers)
# Output: [1, 2, 3, [4, 5]]

# Appending a tuple as a single element
numbers = [1, 2, 3]
numbers.append((4, 5))
print(numbers)
# Output: [1, 2, 3, (4, 5)]
