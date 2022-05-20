# Ordered, Mutable, allow duplicates

number_list = [1,2,3,4,5,6]
float_list = [2.3, 4.5, 7.4, 1.3]
mixed_list = ["London", 2.4, 123, True]
cities = ["London", "Leeds", "Glasgow", "Brighton", "Birmingham"]

print("All Collections:", number_list, float_list, mixed_list, cities)

# Slicing Elements
print("First Elements:", cities[0])
print("Backwards:", cities[-1])
print("Last Element:", cities[len(cities) -1])

print("Range", cities[1:4])
print("Range", cities[2:])
print("Range", cities[1::2])

# Membership
if "Leeds" in cities:
    print("Found Leeds")
if "Manchester" not in cities:
    print("Manchester not a member")

# List Functions
cities.append("Manchester")
cities.insert(2, "Edinburgh")
cities.remove("Brighton")
print("After Manipulation", cities)
cities[0] = "Aberdeen"
print("Added Aberdeen", cities)
cities[0] = ["Perth"]
print("Added Perth List", cities)

if "Dundee" in cities:
    cities.remove("Dundee")

# Copies
cities_copy = cities[:]
cities_copy[2] = "XYZ"
print("Cities:", cities, "Copy:", cities_copy)
cities.remove(["Perth"])
cities.sort()
print("Sorting:", cities)
cities.reverse()
print("Reversed:", cities)

print("Max", max(number_list))
print("Min", min(number_list))

del number_list[0]
print("Deleted number:", number_list)
print("Sum:", sum(number_list))