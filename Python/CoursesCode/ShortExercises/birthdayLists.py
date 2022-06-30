# Create a list of all the months in the year, create a list of friends' birthday months and print to check if it's working.

#### 14/12/2019
#### Author: Michal Trajdos
#### Based on: Skillshare python course by Tony Staunton
#### How to edit lists?


# Creating a list of months.
months = ['january', 'february', 'march', 'april','may','june','july','august','september','october','november','december']
birthdayMonths = ['barch', 'july', 'november']
print(months)
print(birthdayMonths)

# Correct the misspelt month with new spelling
birthdayMonths[0] = 'march'
print(birthdayMonths)

# Add a new friend's birthday month into the birthdayMonths list
birthdayMonths.append('june')
print(birthdayMonths)

# Make a change to birthdayMonths list and make it an empty one
birthdayMonths = []
print(birthdayMonths)

# Same way as with lists that have elements in them, add a new birthday month to the empty list
birthdayMonths.append('august')
print(birthdayMonths)

# Add a new element to the list before all the elements, at index 0, the first position
birthdayMonths.insert(0, 'may')
print(birthdayMonths)

# Remove a month with index 1 from the list 
del birthdayMonths[1]
print(birthdayMonths)

