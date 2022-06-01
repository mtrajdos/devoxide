# Ranges
all_numbers = range(1, 11)
print(all_numbers)

for number in all_numbers:
    print(number)

# Lists - quick intro
even_numbers = [2, 4, 6, 8]
odd_numbers = [1, 3, 5, 7]

print('even_numbers = ', even_numbers)

for odd in odd_numbers:
    print(odd)
    for even in even_numbers:
        print(even)

colours = ["Red", "Green", "Blue"]
for colour in colours:
    print(colour)

name = "David"
for character in name:
    print(character)
    if character == "v":
        print("Found v")
        break