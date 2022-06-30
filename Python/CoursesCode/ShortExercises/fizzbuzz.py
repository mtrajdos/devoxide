'''
1. Refactor into 4 functions for fizzbuzz, fizz & buzz (all returning True or False )
tests plus a function
that calls the other 3 tests and returns the string or original number if no match
2. Write implementation code under the functions that act as tests calling all 4 functions a few times with parameters
3. No repeat code
'''

all_numbers = range(1, 30)

for number_to_test in all_numbers:
    if number_to_test % 3 == 0 and number_to_test % 5 == 0:
        print('FizzBuzz for number', number_to_test)
    elif number_to_test % 3 == 0:
        print('Fizz for number', number_to_test)
    elif number_to_test % 5 == 0:
        print('Buzz for number', number_to_test)
    else:
        print(number_to_test)

def is_fizz(number_to_test):
    return number_to_test % 3 == 0

def is_buzz(number_to_test):
    return number_to_test % 5 == 0

def is_fizzbuzz(number_to_test):
    if is_fizz(number_to_test) is True and is_buzz(number_to_test) is True:
        return True
    else:
        return False

def is_neither(number_to_test):
    if is_fizz(number_to_test) is False and is_buzz(number_to_test) is False:
        return True
    else:
        return False

def fizzbuzz_game_test(z):
    if z == 0:
        return z
    elif is_fizzbuzz(z) is True:
        return "FizzBuzz"
    elif is_fizz(z) is True:
        return "Fizz"
    elif is_buzz(z) is True:
        return "Buzz"
    elif is_neither(z) is True:
        return z

fizzbuzz_game_test(9)
fizzbuzz_game_test(13)
fizzbuzz_game_test(15)
fizzbuzz_game_test(20)