import datetime

my_pet = "Dog"
your_pet = "Cat"

print(my_pet == your_pet)
print(my_pet != your_pet)

print(my_pet + ', ' + your_pet)

both_pets = my_pet + ', ' + your_pet

print(both_pets)

# String built-in functions

name = "david cuthill"
print(name.capitalize())
print(name.upper())
print(name.index("u"))
print(name.replace("d", "A"))
print(name.isnumeric())
print(name.center(40, "*"))

multi_line = """
This is a
multiple line 
sequence of characters
"""

print(multi_line)

# Control characters
months = "Jan\nFeb\nMar"
print(months)
months = "Jan\tFeb\tMar"
print(months)

print("I'm a 6'2\"")

# String format()
name = "David Cuthill"
age = 21
print('Hello, {0}. You are aged {1}'.format(name, age))

# f formatting
print(f'Hello, {name}. You are aged {age}')

money = 50123.2345
print('You got {:.2f} of bepis coins.'.format(money))

date = datetime.date(1996, 7, 20)
print(date)
print(f"{date} was on a {date:%A}")