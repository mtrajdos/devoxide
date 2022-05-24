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
edfFile_EEG_right_uV = mne.io.read_raw_edf(edfPath, preload=True)

# Extract EEG right (uV) channel
edfFile_EEG_right_uV.drop_channels(
    ['EEG left (uV)', 'Accelerometer X', 'Accelerometer Y', 'Accelerometer Z', 'PPG', 'Body temperature',
     'Room illuminatio', 'Battery (V)', 'Audio (dB)', 'Mixed Channel', 'Status'])

# Save the data as a pandas DataFrame so it is easier to work with
eegRightDF = pd.DataFrame(edfFile_EEG_right_uV.get_data()[0], columns = ['EEG right (uV)'])

# Confirm a successful transformation to DataFrame by printing data types in new table/dataframe
print(eegRightDF.dtypes)
