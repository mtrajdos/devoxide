import random
import random as rd
from decimal import Decimal, ROUND_HALF_DOWN

rand1 = rd.random()
print('Rand1:',rand1)

rand2 = rd.randrange(0, 100, 10)
print('Rand2:',rand2)

calculation_error = (19/155) * (155/19)
print('Calculation error variable:', calculation_error)

vat = Decimal('20.00') / Decimal('100.00')
print('Vat:', vat)