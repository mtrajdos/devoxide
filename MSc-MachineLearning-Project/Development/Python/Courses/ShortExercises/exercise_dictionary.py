'''
1. Create 2 dictionaries and fill with data for 4 shop items
2. Show a stock items
3. Get user input for a stock item & verify it exists
4. Then ask for amount to purchase & verify enough in stock
5. Compute bill and print it
'''
stock = {
    "banana": 26,
    "apple": 40,
    "orange": 32,
    "pear": 15
}

prices = {
    "banana": 0.61,
    "apple": 0.35,
    "orange": 0.52,
    "pear": 0.55
}

#Show all prices and stock
def print_stock():
    for food in prices:
        print(food, "\t\tprice: £%s" % prices[food], "\tstock: %s" % stock[food])

# Input item name
def get_item_to_purchase():
    while True:
        item_to_buy = input("\nEnter the name of the item to purchase:")
        if item_to_buy in stock:
            break
        else:
            print("Item not recognised, please try again")
    return item_to_buy

# Input amount to purchase
def get_amount_to_purchase(item_key):
    while True:
        amount = int(input("Enter the number of items to purchase:"))
        if amount <= stock[item_key]:
            # Reduce stock amount
            stock[item_key] = stock[item_key] - amount
            break
        print("Not enough in stock")
    return amount

# Show total price
def calculate_final_cost(stock_item):
    total_price = amount_to_buy * prices[stock_item]
    return total_price

# Actual application
print_stock()
selected_item = get_item_to_purchase()
amount_to_buy = get_amount_to_purchase(selected_item)
print("Total price £{:.2f}".format(calculate_final_cost(selected_item)))
print_stock()
