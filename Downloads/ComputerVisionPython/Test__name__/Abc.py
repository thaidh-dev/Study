def a():
    print("a")

def b():
    print("b")

if __name__ == '__main__':
    print("if: " + __name__)
    a()
else:
    print("else: " + __name__)