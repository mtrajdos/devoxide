name = "david cuthill"
print(name[0])
print(name[3])
print(name[1:4])
print(name[0:4])
print(name[:4])

print(name[1:len(name)])
print(name[1:])
print(name[:])

index = 0
print(name[index:8:2])
print(name[1::2])

what = "Learning Python @ FDM"
at_position = what.index("@")
print(what[0:at_position])
print(what[at_position:])
print(what.split(" "))
print(what.split(" ", 2))

name = input("Enter your name: ")
print("Your name is: " + name)
print(type(name))

number = int(input("A number: "))
print(type(number))

print("1.Development")
print("2.Live")
answer = int(input("Which environment?: "))