# Mutable, Unordered, no Duplicates
colours = {"red", "green", "blue", "Yellow", "orange"}

print("Random order:", colours)

colours.add("purple")
print("added purple", colours)

colours.add("purple")
print("added purple again", colours)

colours.remove("Yellow")
print("removed yellow", colours)

# Set specific methods
more_colours = {"red", "green", "pink"}
print("Intersection:", colours.intersection(more_colours))
print("Union:", colours.union(more_colours))
print("Difference:", colours.difference(more_colours))