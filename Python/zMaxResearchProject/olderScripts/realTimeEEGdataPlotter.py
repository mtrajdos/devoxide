import os
import sys
import mne
import pandas as pd
import tkinter as tk
from tkinter import filedialog
from tkinter import ttk
import matplotlib.pyplot as plt

# Ask for user input to pick the raw .edf file
# print("Please select the raw .edf file of the sleep recording", file=sys.stderr)
# edf = filedialog.askopenfilename(filetypes=[("Edf Files", "*.edf")])

# Open the .edf with MNE package to load and preprocess the data in Python
edf = "/home/dioxan/UvA/FinalResults/Michal/21-03-2022/21-03-2022.edf"
edfFile = mne.io.read_raw_edf(edf, preload=True)

# Pick a single channel to transform data from into lengthOfSequence
raw_pick = edfFile.copy().pick_channels(["EEG right (uV)"], ordered=True)

# Transform the data
data = raw_pick.get_data() * 1e6

# Define sampling frequency for spectrogram plot that matches the .edf SF
sf = edfFile.info["sfreq"]

# Fix the number of edfFile dimensions to one dimension [eegValue1, eegValue2 etc.]
edfFile1D = (((edfFile[0])[0])[0])

# Save variables for plotting
numberOfRecords = len(edfFile1D)
lengthOfSequence = data.shape[1] / sf / 60

# Create a blank plot
plot = plt.figure()
plt.xlabel('Time [ms]')
plt.ylabel('Voltage [uV]')

i = 0
ii = 1
plotSegment = 0


def axisLimits():
    plt.xlim(plotSegment, i + 1000)
    plt.ylim(float(min(edfFile1D[plotSegment:plotSegment + 1000])),
             float(max(edfFile1D[plotSegment:plotSegment + 1000])))

axisLimits()

def dot():
    global i
    global ii
    global plotSegment
    while i < numberOfRecords and i < plotSegment:
        dotValue = float(str(edfFile1D[i:ii]).strip('[]'))
        plot.plot(plotSegment, dotValue)
        i += i
        ii += ii
        plotSegment += plotSegment
        if i == plotSegment:
            plot.savefig("Bepis.png")
            plotSegment == plotSegment + 1000
            break

dot()