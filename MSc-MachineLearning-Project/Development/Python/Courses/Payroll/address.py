class Address():

    def __init__(self, house_number, street, city, post_code):
        self.house_number = house_number
        self.street = street
        self.city = city
        self.post_code = post_code

    def print_address(self):
        print('Printed by Address')
        print('House number:', self.house_number)
        print('Street:', self.street)
        print('City:', self.city)
        print('Post code:', self.post_code)