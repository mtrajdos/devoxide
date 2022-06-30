# no return value, no arguments

def hello_function():
    print("Hello from hello_function")
    #return - optional

# with parameters, no return
def hello_with_params(username, greeting):
    print(f"Hello {username}, have a {greeting}")
    #return - optional

# return value
def sum_two_numbers(x, y):
    return x + y

# redefining a function with default values
def max(x=0, y=1):
    return x + y

def many_params(*numbers):
    if len(numbers) == 0:
        return None
    count = 0
    for x in numbers:
        print("param ", x)
        count += x
    return count

# Scope, cannot alter the outer value - pass by value
def outside_scope():
    z = 999
    return z

def outside_scope2(z):
    z = 999
    return z

hello_function()
hello_with_params("Michal", "nice day")
print("Sum: ", sum_two_numbers(1, 2) + 3)
result = sum_two_numbers(1, 2)
print("Sum: ", result)
print("No 2nd param", max(10))
print("None to many_params", many_params())
print("many_params", many_params(1, 2, 3))

z = 123
print("Before function call", z)
outside_scope()
print("After function call", z)

outside_scope2(z)
print("After function call", z)