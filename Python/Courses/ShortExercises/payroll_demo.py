from payroll.person import Person
from payroll.contractor import Contractor
from payroll.address import Address
from payroll.employee import Employee


david_address = Address(101, 'High Street', 'Gourock', 'PA19 2BN')
david = Employee('David', 'Cuthill', 21, david_address, '100000001')

print(david,'\n')
print('Using __repr__', david.__repr__(), '\n')
david_address.print_address()

marks_address = Address(2, 'Main Street', 'Larbet', 'LA3 RB2')
mark = Contractor('Mark', 'Lancaster', 22, marks_address, 'CON58303')

print('\nClass attibute')
print('From Person', Person.country)
print('From David:', david.country)
print('From Mark', mark.country)

# david.country = 'France'
Person.country = 'Germany'
print('From David:', david.country)
print('From Mark', mark.country)

david._protected = False
print('Protected david:', david)
david._private = False
print('Protected david:', david)
david.my_static_method()

# WTF
mark.salary = 50000
print(mark.salary)