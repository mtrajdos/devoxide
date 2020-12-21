import math
import collections

import numpy as np
import pandas as pd
import matplotlib.pyplot as pp


words = sorted({line.strip().lower() for line in open('words.txt', 'r')})

def find_palindromes():
    pairs = {}
    
    for word in words:
        reversed_word = word[::-1]
        if reversed_word in words:
            pairs.update({word : reversed_word})

find_palindromes()