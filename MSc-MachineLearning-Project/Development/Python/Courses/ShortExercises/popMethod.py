#### 14/12/2019
#### Author: Michal Trajdos
#### Based on: Skillshare python course by Tony Staunton
#### Using the pop() method

subscribers = ['michal@example.com', 'john@example.com', 'mary@example.com']
print(subscribers)

poppedSubscriber = subscribers.pop()

print(subscribers)

print(poppedSubscriber)

poppedSubscriber = subscribers.pop(0)
print(poppedSubscriber)

print('Your first subscriber was ' + poppedSubscriber + '.')

subscribers = ['michal@example.com', 'john@example.com', 'mary@example.com']
print(subscribers)

subscribers.remove('michal@example.com')
print(subscribers)

subscribers = ['michal@example.com', 'john@example.com', 'mary@example.com']
subscribedByMistake = 'michal@example.com'
subscribers.remove(subscribedByMistake)
print(subscribers)

print('\nThis person ' + subscribedByMistake + ' did not mean to sign up.')
