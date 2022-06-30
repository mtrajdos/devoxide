import pandas as pd

trainees = {
    'name' : ['David','Darren','Victoria','Brian','Alex'],
    'score': [80,72,90,65,93],
    'grade': ['Pass','Fail','Pass','Fail','Pass']
}

trainees_data = pd.DataFrame(trainees)
print(trainees_data)
print('\nHead:\n',trainees_data.head(2))
print('\nTail:\n',trainees_data.tail(2))
print('\nShape:\n',trainees_data.shape)
print('\nDescribe:\n',trainees_data.describe())
print('\nTranspose:\n',trainees_data.T)

data_frame = pd.read_csv('SalesData.csv')
print('\n')
# Single column 
print(data_frame['Rep'])

print('\n')
# Selected columns
print(data_frame[['Item', 'Total']])

# Filter rows by value
print(data_frame[data_frame['Item'] == 'Pen'])
print(data_frame[data_frame['Units'] > 75])

# Sum values from a column or multiple columns
print(data_frame['Units'].sum())
print(data_frame[['Total', 'Units']].sum())