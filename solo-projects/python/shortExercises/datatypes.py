# 5 basic data types: int, float, complex, String, boolean

total_count = 0
total_count = total_count + 1
print(total_count)

total_count += 1
print(total_count)

new_total = total_count / 1.23
print(new_total)

print("int type", type(total_count))

name = "David"
print("David type", type(name))

boolean_value = False
print(boolean_value)

a = None
print("a =", a)
print("Is a none?",a == None)

print("Precedence of calculation")
x = 2
y = 3
z = 4

# Prints 14
print("Result", x + y * z)

# Prints 20
print("Result2", (x + y) * z)

# Throws error
# print(name + z)

# Doesn't throw an error
print(name, z)

# String number casted to a int type so can be used for addition without any errors
number = "1"
print(int(number) + z)

# x is an integer, number is a float so no error is thrown because Python is able to add these two different number types
number = 1.234
print(number + x)

print(int(number) + float(x))
print(str(number))

complex_number = 5j
print("Complex", type(complex_number))