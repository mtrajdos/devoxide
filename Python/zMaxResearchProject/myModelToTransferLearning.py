# Import libraries
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

# ---- Load the data for training the model ----#
# Ask for user input to pick the raw .edf file
print("Please select the raw .edf file of the sleep recording", file=sys.stderr)
edfPath = filedialog.askopenfilename(filetypes=[("Edf Files", "*.edf")])
edfFile = mne.io.read_raw_edf(edfPath, preload=True)

# Display order of the channels for correct isolation of single derivation data, e.g. from BrainMax:
# ['EEG right (uV)', 'EEG left (uV)', 'Accelerometer X', 'Accelerometer Y', 'Accelerometer Z', 'PPG', 'Body temperature',
#  'Room illuminatio', 'Battery (V)', 'Audio (dB)', 'Mixed Channel', 'Status']
print("Channels in current edf: " + str(edfFile.ch_names))

# Extract data
edfData = edfFile.get_data()

# ---- Extract separate derivations from the sleep recording ----#
# Extract EEG right (uV) channel
EEG_right_uV = edfData[0]

# Extract the mixed EEG (uV) channel
EEG_mixed_uV = edfData[10]

# Extract EEG left (uV) channel
EEG_left_uV = edfData[1]

# Extract accelerometer data for axis X
Accelerometer_X = edfData[2]

# Extract accelerometer data for axis X
Accelerometer_Y = edfData[3]

# Extract accelerometer data for axis X
Accelerometer_Z = edfData[4]

# Extract PPG channel
PPG = edfData[5]

# Extract audio (dB) channel
Audio_dB = edfData[9]

# Extract the battery status
Battery_V = edfData[8]

# Extract the number of readings by splitting and stripping
# the string that shows upon raw edf call, e.g.
# <RawEDF | 21-03-2022.edf, 12 x 9050880 (35355.0 s), ~828.6 MB, data loaded>
readings = int(str(edfFile).split(",")[1].split("x")[1].split("(")[0])  # | readings = 90550880

# Create an empty dataframe with ordered columns
df = pd.DataFrame(columns=['Reading', 'Time (s)', 'EEG right (uV)', 'EEG mixed (uV)', 'EEG left (uV)', 'Battery (V)', 'Acc X', 'Acc Y',
                           'Acc Z', 'Audio (dB)', 'PPG'])

# Populate the dataframe with extracted derivations
df['EEG right (uV)'] = EEG_right_uV
df['EEG mixed (uV)'] = EEG_mixed_uV
df['EEG left (uV)'] = EEG_left_uV
df['Battery (V)'] = Battery_V
df['Acc X'] = Accelerometer_X
df['Acc Y'] = Accelerometer_Y
df['Acc Z'] = Accelerometer_Z
df['Audio (dB)'] = Audio_dB
df['PPG'] = PPG

# Confirm a successful transformation to dataframe by printing data types in new table/dataframe
print("Data types in df: " + str(df.dtypes))
print("Df shape: " + str(df.shape))
