counter = 5
while counter != -2:
    print(counter)
    counter -= 1

counter = 5
upcount = 0
while counter != -2 and upcount < 3:
    print('Counter:', counter)
    print('Upcount:', upcount)
    counter -= 1
    upcount += 1

while True: 
    response = input('Enter a number: ')
    if int(response) % 7 == 0:
        print("Buzz")
        break

print("Finished") 