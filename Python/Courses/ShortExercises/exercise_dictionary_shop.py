from decimal import Decimal

# 1. Create 2 dictionaries and fill with data for 4 shop items
# stock = {}, prices = {}
# 2. Show a stock of items
# 3. Get user input for a stock item & verify it exists
# 4. Then ask for amount to purchase & verify enough in stock
# 5. Compute bill and print it

stock = {
    'MUW': 14,
    'MUV': 53,
    'MUG': 3,
    'MUR': 20
}

prices = {
    'MUW': Decimal('1.59'),
    'MUV': Decimal('1.29'),
    'MUG': Decimal('1.60'),
    'MUR': Decimal('1.40')
}

fullNames = {
    'MUW': 'Monster Ultra White',
    'MUV': 'Monster Ultra Violet',
    'MUG': 'Monster Ultra Green',
    'MUR': 'Monster Ultra Red'
}

def findDrink(drinkChoice):

    if drinkChoice in stock:
        print('Order for', fullNames[drinkChoice])
        orderDrink(drinkChoice)
    else:
        print('Failed to find a drink. Make sure you are using only 3 letter codes.')
        findDrink(askForDrink())

def askForDrink():
    drinkChoice = input('Which drink would you like to purchase? (use 3 letter codes, e.g. MUW for Monster Ultra White): ').upper()
    return drinkChoice

def orderDrink(drinkChoice):
    orderLive = True
    while orderLive is True:
        amount = input(f'How many units of {fullNames[drinkChoice]} would you like to order? ')
        try:
            amountInt = int(amount)
            orderTotal = Decimal(amount) * prices[drinkChoice]

            if amountInt > stock[drinkChoice]:
                orderLive = True
                print(f'Sorry, not enough stock...Currently {stock[drinkChoice]} of {fullNames[drinkChoice]} available.')
            else:
                decision = input(f'Order confirmation: price total £{orderTotal} for {amountInt} of {fullNames[drinkChoice]}. Proceed? Y/N: ')
                if 'y' or 'Y' in decision:
                    orderLive = False
                    finalizeOrder(drinkChoice, amountInt, orderTotal)
                elif 'n' or 'N' in decision:
                    orderLive = False
                    print('Order aborted. Resetting the shop application...')
                    findDrink(drinkChoice = askForDrink())
                else:
                    orderLive = True
                    print('Type in y for yes or n for no and press enter')
        except ValueError:
            orderLive = True
            print('Not a valid integer entered. Try again.')

def finalizeOrder(string, int, decimal):
    stock[string] = stock[string] - int
    print(f'Ordered £{decimal} worth of {int} {fullNames[string]} units. Thank you!')
                
print('Welcome to Energy Drink Shop\n')
for drink in fullNames:
    print(fullNames[drink])
    print("price: £%s" % prices[drink], "\nstock: %s\n" % stock[drink])

findDrink(drinkChoice = askForDrink())