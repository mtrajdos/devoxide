#### 16/12/2019
#### Author: Michal Trajdos
#### Based on: Skillshare python course by Tony Staunton
#### Using the list function, creating a list of numbers

# Converting numbers into a list
numbers = list(range(1,6))
print(numbers)

# Create an oddNumbers list in range 1 to 101, adding 2 to each number making them odd up until the end of the range 100
oddNumbers = list(range(1,101, 2))
print(oddNumbers)

# Create a new empty squares list
squares = []
for value in range(1, 11):

	# Take a square from value in range
	square = value ** 2
	# Add the new calculated value at the end of the squares list
	squares.append(square)
print(squares)

digits = [1,2,3,4,5]
print(min(digits))
print(max(digits))
print(sum(digits))