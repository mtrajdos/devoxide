# Make extra copies for other channel derivations
edfFile_EEG_right_uV = edfFile_EEG_left_uV
edfFileEEG_mixedChannel_uV = edfFile_EEG_right_uV
edfFileAccelerometer_X = edfFile_EEG_right_uV
edfFileAccelerometer_Y = edfFile_EEG_right_uV
edfFileAccelerometer_Z = edfFile_EEG_right_uV
edfFilePPG = edfFile_EEG_right_uV
edfFileAudio_dB = edfFile_EEG_right_uV


# Extract EEG left (uV) channel
edfFile_EEG_left_uV.drop_channels(
    ['EEG right (uV)', 'Accelerometer X', 'Accelerometer Y', 'Accelerometer Z', 'PPG', 'Body temperature',
     'Room illuminatio', 'Battery (V)', 'Audio (dB)', 'Mixed Channel', 'Status'])

# Extract Mixed (uV) channel
edfFileEEG_mixedChannel_uV.drop_channels(
    ['EEG left (uV)', 'EEG right (uV)', 'Accelerometer X', 'Accelerometer Y', 'Accelerometer Z', 'PPG',
     'Body temperature',
     'Room illumination', 'Battery (V)', 'Audio (dB)', 'Status'])

# Extract Accelerometer X axis channel
edfFileAccelerometer_X.drop_channels(
    ['EEG left (uV)', 'EEG right (uV)', 'Accelerometer Y', 'Accelerometer Z', 'PPG', 'Body temperature',
     'Room illumination', 'Battery (V)', 'Audio (dB)', 'Mixed Channel', 'Status'])

# Extract Accelerometer Y axis channel
edfFileAccelerometer_Y.drop_channels(
    ['EEG left (uV)', 'EEG right (uV)', 'Accelerometer X', 'Accelerometer Z', 'PPG', 'Body temperature',
     'Room illumination', 'Battery (V)', 'Audio (dB)', 'Mixed Channel', 'Status'])

# Extract Accelerometer Z axis channel
edfFileAccelerometer_Z.drop_channels(
    ['EEG left (uV)', 'EEG right (uV)', 'Accelerometer X', 'Accelerometer Y', 'PPG', 'Body temperature',
     'Room illumination', 'Battery (V)', 'Audio (dB)', 'Mixed Channel', 'Status'])

# Extract PPG channel
edfFilePPG.drop_channels(
    ['EEG left (uV)', 'EEG right (uV)', 'Accelerometer X', 'Accelerometer Y', 'Accelerometer Z', 'Body temperature',
     'Room illumination', 'Battery (V)', 'Audio (dB)', 'Mixed Channel', 'Status'])

# Extract Audio (dB) axis channel
edfFileAudio_dB.drop_channels(
    ['EEG left (uV)', 'EEG right (uV)', 'Accelerometer X', 'Accelerometer Y', 'PPG', 'Accelerometer Z',
     'Body temperature',
     'Room illumination', 'Battery (V)', 'Mixed Channel', 'Status'])


# Check the data types in the recording prior to transformation
print(edfFile_EEG_left_uV.get_data().dtypes)
