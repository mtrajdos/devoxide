# Import required libraries
import pandas as pd
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

# Ask for user input to pick the raw .edf file
print("Please select the raw .edf file of the sleep recording", file=sys.stderr)
edfPath = filedialog.askopenfilename(filetypes=[("Edf Files", "*.edf")])
edfFile_EEG_left_uV = mne.io.read_raw_edf(edfPath, preload=True)


# Check the data types in the recording prior to transformation
print(edfFile_EEG_left_uV.get_data().dtypes)

# Make extra copies for other channel derivations
edfFile_EEG_right_uV = edfFile_EEG_left_uV
edfFileEEG_mixedChannel_uV = edfFile_EEG_right_uV
edfFileAccelerometer_X = edfFile_EEG_right_uV
edfFileAccelerometer_Y = edfFile_EEG_right_uV
edfFileAccelerometer_Z = edfFile_EEG_right_uV
edfFilePPG = edfFile_EEG_right_uV
edfFileAudio_dB = edfFile_EEG_right_uV

# Extract EEG right (uV) channel
edfFile_EEG_right.drop_channels(
    ['EEG left (uV)', 'Accelerometer X', 'Accelerometer Y', 'Accelerometer Z', 'PPG', 'Body temperature',
     'Room illuminatio', 'Battery (V)', 'Audio (dB)', 'Mixed Channel', 'Status'])

# Extract EEG left (uV) channel
edfFile_EEG_left.drop_channels(
    ['EEG right (uV)', 'Accelerometer X', 'Accelerometer Y', 'Accelerometer Z', 'PPG', 'Body temperature',
     'Room illuminatio', 'Battery (V)', 'Audio (dB)', 'Mixed Channel', 'Status'])

# Extract Mixed (uV) channel
edfFileEEG_mixedChannel_uV.drop_channels(
    ['EEG left (uV)', 'EEG right (uV)', 'Accelerometer X', 'Accelerometer Y', 'Accelerometer Z', 'PPG', 'Body temperature',
     'Room illuminatio', 'Battery (V)', 'Audio (dB)', 'Status'])

# Extract Accelerometer X axis channel
edfFileEEG_accelerometer_X.drop_channels(
    ['EEG left (uV)', 'EEG right (uV)', 'Accelerometer Y', 'Accelerometer Z', 'PPG', 'Body temperature',
     'Room illuminatio', 'Battery (V)', 'Audio (dB)', 'Mixed Channel', 'Status'])

# Extract Accelerometer Y axis channel
edfFileEEG_accelerometer_Y.drop_channels(
    ['EEG left (uV)', 'EEG right (uV)', 'Accelerometer X', 'Accelerometer Z', 'PPG', 'Body temperature',
     'Room illuminatio', 'Battery (V)', 'Audio (dB)', 'Mixed Channel', 'Status'])

# Extract Accelerometer Z axis channel
edfFileEEG_accelerometer_Z.drop_channels(
    ['EEG left (uV)', 'EEG right (uV)', 'Accelerometer X', 'Accelerometer Y', 'PPG', 'Body temperature',
     'Room illuminatio', 'Battery (V)', 'Audio (dB)', 'Mixed Channel', 'Status'])

# Extract PPG channel
edfFilePPG.drop_channels(
    ['EEG left (uV)', 'EEG right (uV)', 'Accelerometer X', 'Accelerometer Y', 'Accelerometer Z', 'Body temperature',
     'Room illuminatio', 'Battery (V)', 'Audio (dB)', 'Mixed Channel', 'Status'])

# Extract Audio (dB) axis channel
edfFileAudio_dB.drop_channels(
    ['EEG left (uV)', 'EEG right (uV)', 'Accelerometer X', 'Accelerometer Y', 'PPG', 'Accelerometer Z', 'Body temperature',
     'Room illuminatio', 'Battery (V)', 'Mixed Channel', 'Status'])
