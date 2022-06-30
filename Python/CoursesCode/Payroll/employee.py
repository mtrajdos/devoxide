from payroll.person import Person

class Employee(Person):

    def __init__(self, first_name, last_name, age, address, employee_number):
        super().__init__(first_name, last_name, age, address)
        self.employee_number = employee_number