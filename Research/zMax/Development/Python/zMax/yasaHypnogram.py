#!/usr/bin/env python3

# Import os library for running shell commands
import os
import pandas as pd
import sys
import yasa

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
print("This script generates a hypnogram from an .edf sleep recording using the YASA algorithm")
print("Prerequisite 1: .csv with sleep stages as integers, use EdfProcessingForYASA.R")
print("Prerequisite 2: tkinter")

# Try importing tkinter (install if missing)
print("Attempting to import tkinter library...")

# Try to import, if an error occurs, install tkinter based on user input
try:
    import tkinter as tk
    from tkinter import filedialog
    from tkinter import ttk
except ImportError:
    raise ImportError('Failed tkinter import, proceeding with tkinter installation...')
    install()

print("Please select the .csv file with sleep stages as integers")
csvFile = filedialog.askopenfilename()

# Extract the .csv filename based on the current file selected path
fileName = os.path.realpath(csvFile).split("/")[-1].split(".")[0]

# Process the hypnogram with pandas library from processed .csv
hypno = pd.read_csv(csvFile).squeeze("columns")

# Plot the hypnogram using YASA
ax = yasa.plot_hypnogram(hypno.stage)

# Save the hypnogram figure into a .png file
ax.figure.savefig("HYPNOGRAM-" + fileName + ".png")