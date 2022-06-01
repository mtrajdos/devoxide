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
edfFile = filedialog.askopenfilename(filetypes=[("Edf Files", "*.edf")])

# Open the .edf with MNE package to load and preprocess the data in Python
edfPath = "/home/dioxan/UvA/FinalResults/Michal/21-03-2022/21-03-2022.edf"
edfFile = mne.io.read_raw_edf(edfPath, preload=True)
edfFileName = edfPath.split('/')[7]

# Pick a single channel to transform data from into lengthOfSequence
edfFile.drop_channels(
    ['EEG left (uV)', 'Accelerometer X', 'Accelerometer Y', 'Accelerometer Z', 'PPG', 'Body temperature',
     'Room illuminatio', 'Battery (V)', 'Audio (dB)', 'Mixed Channel', 'Status'])

# Print current channel
print("Current channel: " + str(edfFile.ch_names))

# Transform the data
data = edfFile.get_data() * 1e6

# Define sampling frequency for spectrogram plot that matches the .edf SF
sf = edfFile.info["sfreq"]

# Fix the number of edfFile dimensions to one dimension [eegValue1, eegValue2 etc.]
edfFile1D = (((edfFile[0])[0])[0])


# Create a blank plot
def plot():
    plt.figure()
    plt.xlabel('Time [ms]')
    plt.xlim(0, len(milliSeconds))
    plt.ylabel('Voltage [uV]')
    plt.ylim(0, max(edfFile1D))


# Save variables for plotting
dotCount = 4000
totalDots = len(edfFile1D)
msInRecording = int(str(edfFile).split()[6].split('(')[1].split('.')[0]) * 4000
milliSeconds = np.arange(1, 4001)

# It's 4000 ms, not 1000ms because of 256Hz SF

def dot():
    x = 0
    a = 0
    b = 4000
    print("Plotting EEG right [LIVE-RUN-REC-" + edfFileName + " graph[4000ms]...")
    for i in range(x, b):
        plt.xlim(0, 4000)
        x = milliSeconds[a]
        y = edfFile1D[a]
        plt.ion()
        plt.scatter(x, y)
        plt.title("EEG right [LIVE-RUN-REC-" + edfFileName + "]")
        plt.xlabel('Time [ms]')
        plt.ylabel('Voltage [uV]')
        a += 1
        plt.pause(0.001)
        if i not in range(x, b):
            x += a
            b += 4000
    plt.show()


def dot2():
    import matplotlib
    import collections
    # selecting the right backend, change qt4agg to your desired backend
    matplotlib.use('QtAgg')
    import matplotlib.pyplot as plt
    import matplotlib.animation as animation

    # command to open the pipe
    datapipe = open(edfPath, 'r')

    # amount of data to be displayed at once, this is the size of the x axis
    # increasing this amount also makes plotting slightly slower
    data_amount = 4000

    # set the size of the deque object
    datalist = collections.deque([0] * data_amount, data_amount)

    # configure the graph itself
    fig, ax = plt.subplots()
    line, = ax.plot([0, ] * data_amount)

    # size of the y axis is set here
    ax.set_ylim(0, max(edfFile1D))

    def update(data):
        line.set_ydata(data)
        return line,

    def data_gen():
        while True:
            """
            We read two data points in at once, to improve speed
            You can read more at once to increase speed
            Or you can read just one at a time for improved animation smoothness
            data from the pipe comes in as a string,
            and is seperated with a newline character,
            which is why we use respectively eval and rstrip.
            """
            datalist.append(eval((datapipe.readline()).rstrip('\n')))
            datalist.append(eval((datapipe.readline()).rstrip('\n')))
            yield datalist

    ani = animation.FuncAnimation(fig, update, data_gen, interval=0, blit=True)
    plt.show()


dot()
