#### 14/12/2019
#### Author: Michal Trajdos
#### Based on: Skillshare python course by Tony Staunton
#### Organizing a list

# Creating a list of months

birthdayMonths = ['May', 'November', 'June']

#### Using a sort method (A to Z)

birthdayMonths.sort()
print(birthdayMonths)

#### Using a reverse sort method (Z to A)

birthdayMonths = ['May', 'November', 'June']

birthdayMonths.sort(reverse=True)
print(birthdayMonths)

print(sorted(birthdayMonths))
print(birthdayMonths)

#### Create a new list with days of the week and then reverse sort it into Z to A order.

daysInAWeek = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
daysInAWeek.reverse()
print(daysInAWeek)
