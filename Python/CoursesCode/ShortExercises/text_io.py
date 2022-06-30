# w=write, a=append, r=read, x=tries to create a file but throws an error if already exists
def save_my_file(filename, text):
    with open(filename, 'w') as outfile:
        outfile.write(text)
    print('File has been written')

def read_my_file(filename):
    with open(filename, 'r') as infile:
        read_data = infile.read()
        print(read_data)

save_my_file('memo.txt', '1. Phone Dentists \n2. Walk Dog \n3. Shopping')
read_my_file('memo.txt')