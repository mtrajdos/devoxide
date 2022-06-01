class Person():

    country = "UK"

    # protected - A convention no enforced
    _protected = True

    # private - enforced changes, makes a value immutable
    __private = True

    def __init__(self, first_name, last_name, age, address):
        self.first_name = first_name
        self.last_name = last_name
        self.age = age
        self.address = address

    @staticmethod
    def my_static_method():
        print('From my static method')

    # Unambiguous represenation
    def __repr__(self):
        return 'Name: {0} {1}, age {2}'.format(self.first_name, self.last_name, self.age)

    # Friendly output
    def __str__(self):
        return 'Name: {0} {1}, age {2} protected {3} private {4}'.format(self.first_name, self.last_name, self.age, self._protected, self.__private)