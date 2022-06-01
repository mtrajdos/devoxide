#!/usr/bin/env python3

# Import libraries
import os
import sys
import mne
import pandas as pd
from visbrain.gui import Sleep


# Grab Python version
pythonVersion = sys.version.split(" ")[0]

# Prepare empty string for the command variable
tkinterCommand = ""


# Define tkinter installation function
def install():
    # Ask user about the system they're using so a proper tkinter install command is executed
    system = input("What system are you running this .py script on? Windows or UNIX? [w/u]: ")

    # Lowercase the user input
    systemLower = system.lower()

    # Determine the installation command based on user input
    if systemLower == "w" or systemLower == "windows" or systemLower == "win":
        pipOrConda = input("Are you using pip or conda for running your Python commands? [pip/conda]: ")
        pipOrCondaLower = pipOrConda.lower()
        if pipOrCondaLower == "pip" or pipOrCondaLower == "pi" or pipOrCondaLower == "p":
            print("Parsing a Windows pip command for tkinter installation...")
            tkinterCommand = "pip install tk"
            os.system(tkinterCommand)
        elif pipOrCondaLower == "conda" or pipOrCondaLower == "cond" or pipOrCondaLower == "cond":
            print("Parsing a Windows conda command for tkinter installation...")
            tkinterCommand = "conda install -c anaconda tk"
            os.system(tkinterCommand)

    elif systemLower == "u" or systemLower == "unix" or systemLower == "un":
        print("Parsing an UNIX command for tkinter installation...")
        tkinterCommand = "sudo apt-get install python" + pythonVersion + "-tk"
        os.system(tkinterCommand)


# Print info about prerequisites to run this script
print("This script generates a spectrogram and a hypnogram using .csv and raw .edf files")

# Try importing tkinter (install if missing)
print("Attempting to import tkinter library...")

# Try to import, if an error occurs, install tkinter based on user input
try:
    import tkinter as tk
    from tkinter import filedialog
    from tkinter import ttk
except ImportError:
    print('Failed tkinter import, proceeding with tkinter installation...')
    install()
    import tkinter as tk
    from tkinter import filedialog
    from tkinter import ttk

# Ask for user input to pick the raw .edf file
print("Please select the raw .edf file of the sleep recording", file=sys.stderr)
edf = filedialog.askopenfilename(filetypes=[("Edf Files", "*.edf")])

# Open the .edf with MNE package to load and preprocess the data in Python
edfFile = mne.io.read_raw_edf(edf, preload=True)

# Ask for user input to pick the converted .csv file with sleep stages
print("Please select the .csv file with sleep stages as integers", file=sys.stderr)
csvFile = filedialog.askopenfilename(filetypes=[("CSV Files", "*.csv")])

# Extract the .csv filename based on the current file selected path
fileName = os.path.realpath(csvFile).split("/")[-1].split(".")[0]

# Read the list of channels in raw .edf
edfChannels = edfFile.ch_names

# Prompt user input for the EEG channel to be used for the spectrogram
print("Available channels: " + str(edfChannels))
chosenChannel = input("\nPlease select the channel for the single channel spectrogram: ")

# Define sampling frequency for spectrogram plot that matches the .edf SF
sf = edfFile.info["sfreq"]

# Fix the number of edfFile dimensions to one dimension [eegValue1, eegValue2 etc.]
edfFile1D = (((edfFile[0])[0])[0])

# Process the hypnogram with pandas library from processed .csv
hypno = pd.read_csv(csvFile).squeeze("columns")

# Extract stages array and overwrite it to hypno variable (YASA requires an array/1D data for the hypnogram)
hypno = hypno.stage

# Upsample sampling frequency of the hypnogram to match sampling frequency of EEG/PSG
hypno_up = yasa.hypno_upsample_to_data(hypno, sf_hypno=1/30, data=edfFile)

# Plot the hypnogram using YASA
h = yasa.plot_hypnogram(hypno)

# Plot the spectrogram using YASA
s = yasa.plot_spectrogram(edfFile1D, sf, hypno_up)

# Plot the single channel spectrogram using YASA
data = edfFile.get_data() * 1e6
schs = yasa.plot_spectrogram(data[edfChannels.index(chosenChannel)], sf, hypno_up);

# Save the hypnogram figure into a .png file
h.figure.savefig("YASA-HYPNOGRAM-" + fileName + ".png")
# Save the spectrogram figure into a .png file
s.figure.savefig("YASA-SPECTROGRAM-" + fileName + ".png")
# Save the single channel spectrogram figure into a .png file
schs.figure.savefig("YASA-SPECTROGRAM-SINGLE-CHANNEL-" + fileName + ".png")
