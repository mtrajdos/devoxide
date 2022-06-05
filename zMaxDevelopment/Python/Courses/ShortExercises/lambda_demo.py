# PEP8 discourages an identifier bound to a lambda
double = lambda x: x * 2
print(double(5))

multiply = lambda a, b: a * b
print(multiply(5,6))

# Better use of lambdas as anonymous (inner) functions
def myfunc(n):
    return lambda a: a * n

my_doubler = myfunc(2)

print(my_doubler)
print(my_doubler(11))

my_tripler = myfunc(3)
print(my_tripler(11))