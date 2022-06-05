#### 16/12/2019
#### Author: Michal Trajdos
#### Based on: Skillshare python course by Tony Staunton
#### Looping through a slice

# Looping through a slice with a for loop

names = ['Tysia', ' Michal', 'Kamil', 'Kuba', 'Tony', 'Frank', 'Mary', 'Carl']
print('Here are the names of the last 3 registered users: ')
for name in names[-3:]:
	print(name.title())