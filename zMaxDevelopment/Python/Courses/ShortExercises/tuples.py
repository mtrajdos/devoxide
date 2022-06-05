# Immutable, ordered, duplicates
alphabet = ("a", "b", "c", "d", "d")
numbers = (1, 2, 3, 4, 5, 6, 4, 3)

# Error, cannot change a tuple
#numbers[0] = 99

print("Duplicates & Ordered", alphabet)
print("Duplicates & Ordered", numbers)

# Slicing 
new_numbers = numbers[:4]
print("Sliced:", new_numbers)

print("Count 4's:", numbers.count(4))
print("Index of B:", alphabet.index("b"))

# Built-In
print("Length:", len(alphabet))
print("Max:", max(numbers))

numbers_list = list(numbers)
numbers_list.append(99)
print("It's a List:", numbers_list)

if "b" in alphabet:
    print("Found B")