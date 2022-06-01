import fizzbuzz
import unittest

class fizzbuzz_tests(unittest.TestCase):

    def test_is_zero_when_number_is_zero(self):
        self.assertEqual(fizzbuzz.fizzbuzz_game_test(0), 0)

    def test_returns_number_when_number_is_neither(self):
        self.assertEqual(fizzbuzz.fizzbuzz_game_test(29), 29)

    def test_is_fizz_when_number_is_three(self):
        self.assertTrue(fizzbuzz.fizzbuzz_game_test(3), "Fizz")

    def test_is_buzz_when_number_is_five(self):
        self.assertTrue(fizzbuzz.fizzbuzz_game_test(5), "Buzz")

    def test_is_fizzbuzz_when_number_is_fifteen(self):
        self.assertTrue(fizzbuzz.fizzbuzz_game_test(15), "FizzBuzz")

if __name__ == '__main__':
    unittest.main(exit=False)