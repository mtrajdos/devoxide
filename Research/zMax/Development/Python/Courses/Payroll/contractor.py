from payroll.person import Person

class Contractor(Person):

    # private
    __im_contractor = True

    def __init__(self, first_name, last_name, age, address, contractor_number):
        super().__init__(first_name, last_name, age, address)
        self.contractor_number = contractor_number