b = False
if b:
    print("It's b")
else:
    print("Not b")

c = -1

if c <= 20 and c > 0:
    print("Less than 20")
elif c > 20 and b is False:
    print("Greater than 20 and b is False")
elif c > 20:
    print("Greater than 20")
else:
    print("None of the above")

sequence = [1,2,3,4,6]
if (3 in sequence):
    print("3 is in sequence")

if (6 not in sequence):
    print("6 not in sequence")
elif (5 in sequence):
    print("Found 5")
else:
    print("None of the above")