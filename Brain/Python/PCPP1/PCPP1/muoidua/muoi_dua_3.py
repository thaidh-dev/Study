import pickle


def my_function_multi_lines_docstrings():
    """
    This function serves as an example of a function definition in Python.
    It prints a simple message to the console when called. The function does not
    take any parameters and does not return any value.
    Usage:
        Call the function directly to see the output message.
    Example:
        my_function_multi_lines_docstrings()
        Output: This is a function definition.
    """
    print("Hello")

# print(my_function_multi_lines_docstrings.__doc__)

# print("--------------------------------------------------")

def my_function():
    """
    This is a simple function that prints a message.
    """
    print("Bye")

# print(my_function.__doc__)

# print("--------------------------------------------------")

my_function_multi_lines_docstrings_pickled = pickle.dumps(my_function_multi_lines_docstrings)
my_function_multi_lines_docstrings_pickled_deepcopy = pickle.loads(my_function_multi_lines_docstrings_pickled)
print(my_function_multi_lines_docstrings_pickled_deepcopy.__doc__)
my_function_multi_lines_docstrings_pickled_deepcopy() # Hello

print("--------------------------------------------------")

func_pickled = pickle.dumps(my_function)
func_deepcopy = pickle.loads(func_pickled)
print(func_deepcopy.__doc__)
func_deepcopy() # Bye
