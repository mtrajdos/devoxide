# Import required libraries
import os
import sys
import mne
import numpy as np
import pandas as pd
import tkinter as tk
from tkinter import filedialog
from tkinter import ttk
import matplotlib.pyplot as plt
import time
import os, getpass
from os.path import join, getsize
from os import walk

# Create an array of filenames to be processed in the DataFrame, using a single or multiple folders
csvPaths = ["/home/dioxan/Projects/Analysis/Data/csvData/Project3/FNS/NewestDatasets",
            "/home/dioxan/Projects/Analysis/Data/csvData/Project3/FNS/OldestDatasets",
            "/home/dioxan/Projects/Analysis/Data/csvData/Project3/NAP/OldestDatasets"]

# Create a list of lists to append both the filenames and complete paths, e.g.
# [[[FNSSub330(21022021).csv], [/home/dioxan/Projects/Analysis/Data/csvData/Project3/FNS/NewestDatasets]],
# [[FNSSub331(21022022).csv], [/home/dioxan/Projects/Analysis/Data/csvData/Project3/FNS/NewestDatasets]]]
files = []


# Append filenames and their paths to files list for future use in the DataFrame
def reeee(paths: list):
    for path in paths:
        filenames = next(walk(path), (None, None, []))[2]
        for filename in filenames:
            filenameAndPath = [[filename, path]]
            files.append(filenameAndPath)
    return files


csvs = reeee(csvPaths)

# Create an empty list for data to append into the DataFrame
data = []

# Create an integer for counting through processed csvs
i = 0

# Read the .csv files as NumPy dataframes
while i < len(csvs):
    for csv in csvs:
        csvPath = str(csvs[1]).split("'")[3] + "/" + str(csvs[0]).split("'")[1]
        csvDF = pd.read_csv(csvPath, on_bad_lines='skip')
        for row in csvDF:
            data.append(row)
    if i == len(csvs):
        df = pd.DataFrame(data)
        break

print(df.shape)
print(df)


# Define an empty array to store channels from currently processed .csv
# ch_names = []

# Some information about the channels
# ch_names = csvDF.columns

# Sampling rate of the Nautilus machine
# sf = 256.0  # Hz

# Create the info structure needed by MNE
# info = mne.create_info(ch_names, sf)

# Finally, create the Raw object
# raw = mne.io.RawArray(csvFile, info)

# Plot it!
# raw.plot()
