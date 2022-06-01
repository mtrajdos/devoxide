# Key/Value, mutable, unordered, unique keys, no duplicates
empty_dictionary = {}
print("Empty Dictionary:", empty_dictionary)
print("Type:",type(empty_dictionary))

countries = {
    'Brazil' : 209000000,
    "Canada" : 37000000,
    "France" : 67000000,
}

print("All:", countries)

# Find by Key
for key in countries:
    print("Key:", key)
    print("Values:", countries[key])

print("Canada Population:", countries["Canada"])
countries["France"] = 68000000
print("Update a value:", countries["France"])

if 'Brazil' in countries:
    print("Found Brazil")

bepis = "Brazil"

if bepis in countries:
    print("Found Brazil")

