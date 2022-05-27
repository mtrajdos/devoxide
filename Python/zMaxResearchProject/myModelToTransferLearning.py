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