import pandas as pd
import matplotlib.pyplot as plt

# plt.plot([0,5,10,15,20], [10,24,32,40,59])
# plt.title('Title 1')
# plt.xlabel('X label', fontsize=14)
# plt.ylabel('Y label', fontsize=14)
# plt.show()

df = pd.read_csv('SalesData.csv')
plt.bar(df['Rep'],df['Total'])
plt.title('Total sales by Rep')
plt.xlabel('Sales Rep')
plt.ylabel('Sales £')
plt.grid(True)
plt.show()

subset = df[['Item', 'Units']]
subset = subset.groupby(['Item']).sum().reset_index()
items = subset['Item']
units = subset['Units']
explode = (0.1, 0, 0, 0, 0)
plt.pie(units, labels=items, explode=explode)
plt.show()
