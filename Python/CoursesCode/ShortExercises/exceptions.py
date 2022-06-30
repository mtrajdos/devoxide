numbers = [10,20,30,40]
try:
    print(numbers[4])
except IndexError:
    print('Error: out of range')
except:
    print('Unknown error')

try:
    file = open('exceptions.py')
    line = file.readline()
    print(line)
except FileNotFoundError:
    # raise 'Not found error'
    print('File not found')
except:
    print('Other exception')
finally:
    try:
        file.close()
    except NameError:
        print('Name error')
    
