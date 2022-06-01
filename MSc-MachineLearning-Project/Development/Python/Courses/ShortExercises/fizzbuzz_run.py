import fizzbuzz

def start():
    numbers = range(1, 41)
    for number in numbers:
        result = fizzbuzz.fizzbuzz_game_test(number)
        print(result)

# Application entry point - good practice, not necessary in this case

# if __name__ == '__main__':
    # start()

start()