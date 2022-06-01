%% This script is meant to be used in MATLAB R2016 on ZMax-EventIDE pre-processed SignalData SET files

%% This script will 1. create PSD's and spectrograms, 2. calculate power per epoch of a recording.
%  
%  Make sure to use the 'Run and Advance' function, found in the 'Editor' tab of Matlab. And wait for MATLAB to 
%  complete it's computations before advancing with the next step in this script.
%
%  You will be instructed to ***CLOSE EEGLAB AND ALL OF ITS WINDOWS*** between certain steps, please make sure to do so!
%
%  If you are running this script outside of the UvA Tux server make sure all toolboxes and paths are set and
%  selected from this new device.

%% 1. Create a PSD and spectrogram of a pre-processed recording set file
%%
All_paths
%%
if isempty(strfind(path, ['/home/Public/Projects/sleep-and-cognition/generic/matlab-scripts/format conversions', pathsep])) %replace path with strcat and filesep
    addpath('/home/Public/Projects/sleep-and-cognition/generic/matlab-scripts/format conversions') 
end

if isempty(strfind(path, ['/home/Public/Projects/sleep-and-cognition/generic/matlab-scripts', pathsep]))
addpath('/home/Public/Projects/sleep-and-cognition/generic/matlab-scripts')
end
%%
% load paths and scripts
cd '/home/Public/Projects/sleep-and-cognition/experiments/0008.beta-product/4.Matlab-scripts'
pathdef 
cd '/home/Public/Projects/sleep-and-cognition/experiments/0008.beta-product/6.Pre-processed data/Zmax/Pre-processed/ZMax'

%Set output file path
output_path= ('/home/Public/Projects/sleep-and-cognition/experiments/0008.beta-product/8.Final-results/ZMAX/PSD')
%% 
% load the .set pre-processed and filtered dataset
eeglab;
EEG =pop_loadset;
eeglab redraw;
%%
cd (output_path)

Folder_name = EEG.setname;

if ~exist(['/home/Public/Projects/sleep-and-cognition/experiments/0008.beta-product/8.Final-results/ZMAX/PSD/',Folder_name]);
    mkdir(['/home/Public/Projects/sleep-and-cognition/experiments/0008.beta-product/8.Final-results/ZMAX/PSD/',Folder_name]);
end
cd (['/home/Public/Projects/sleep-and-cognition/experiments/0008.beta-product/8.Final-results/ZMAX/PSD/',Folder_name])
%%
% selects channel 3 (EEG left (uV) for generating figures
EEG = pop_select(EEG, 'channel', 3);
eeglab redraw
%% 
% generates a PSD of EEG left (uV)
pop_spectopo(EEG, 1, [0 EEG.xmax*200], 'EEG' , 'percent', 100, 'freqrange',[0.1 49], 'electrodes', 'off');

%% ***MAXIMIZE THE EEGLAB SPECTOPO PLOT FIGURE WINDOW BEFORE SAVING IN NEXT STEP***

%%
% saves the PSD
figname = [EEG.filename,'_spectopo.png'];
saveas (gcf,figname);
%%
% generates a spectrogram, this takes a while!
spectraplot(EEG, 1, mfilename, 45)

%% ***MAXIMIZE THE EEGLAB SPECTRA PLOT FIGURE WINDOW BEFORE SAVING IN NEXT STEP***

%%
% saves the spectrogram
figname = [EEG.filename,'_spectraplot.png'];
saveas (gcf,figname);

%% ***CLOSE EEGLAB AND ALL OF ITS WINDOWS***

%% 2. Calculate power (range 0.5 to 48 Hz, bins of 0.25 Hz) per epoch and save as spreadsheet

% after closing EEGLab and all of its windows clear the workspace and command window
clear all
clc

%%
All_paths
%%
% set paths
EEGdata = ~isempty(strfind(path, ['/home/Public/Projects/sleep-and-cognition/experiments/0008.beta-product/6.Pre-processed data/Zmax/Pre-processed', pathsep]));
if EEGdata == 0
    addpath('/home/Public/Projects/sleep-and-cognition/experiments/0008.beta-product/6.Pre-processed data/Zmax/Pre-processed');
end

Sleepstaging = ~isempty(strfind(path, ['/home/Public/Projects/sleep-and-cognition/experiments/0008.beta-product/6.Pre-processed data/Zmax/Pre-processed/Sleep Scoring', pathsep]));
if Sleepstaging == 0
    addpath('/home/Public/Projects/sleep-and-cognition/experiments/0008.beta-product/6.Pre-processed data/Zmax/Pre-processed/Sleep Scoring');
end
%%
% load the .set dataset for which you want to calculate power per epoch
eeglab;
EEG=pop_loadset();

% gets name and location from file and create header used in outputname
name=EEG.filename;
path=EEG.filepath;

% sets location for output using header and condition and create a folder for the output
locationoutput=['/home/Public/Projects/sleep-and-cognition/experiments/0008.beta-product/8.Final-results/ZMAX/PSD/'];

% displays data
pop_eegplot(EEG, 1, 1, 1); 
eegplot(EEG.data,'srate',256,'limits',[-1000 2500],'color','on','events',EEG.event,'eloc_file',EEG.chanlocs)

eeglab redraw
%% 
% select data
% *** change the file names below for every participant's sleep scoring!! ***
% checks sampling rate of recording
Fs=EEG.srate;
if Fs ~= 256
    fprintf('Please check dataset, samplingrate is not 256 Hz')
    fprintf('Press any button to continue processing')
    pause
end
% epoch length that will be used in seconds
Epochlength=30;
% determine the length of the data in seconds
LengthDataSeconBS=length(EEG.data)/Fs;
% gets data
Data=EEG.data;
% determines amount of samples in 1 epoch
EpochSrate=Fs*Epochlength;
% determines number of epochs based on length of data and amount of samples in epoch
NEpochsEEG=floor(length(EEG.data)/EpochSrate);

% reads XLSX file that holds the sleepstaging information
fprintf('Selecting the excel file containing sleepstaging for this participant...\n')
loadnamestaging=['/home/Public/Projects/sleep-and-cognition/experiments/0008.beta-product/6.Pre-processed data/Zmax/Pre-processed/Sleep Scoring/SUB300.xlsx'];
sleepstaging=xlsread(loadnamestaging);
sleepstaging=sleepstaging(:,1:3);
sleepstaging(:,2)=(sleepstaging(:,1)/30)+1;

% determines amount of epochs in the XLSX file
NEpochsXls=length(sleepstaging(:,1));

% checks if the number of epochs matches the data and prints result of check
if NEpochsEEG ~= NEpochsXls
    Diff= NEpochsEEG - NEpochsXls;
    fprintf('Difference between EEG epochs and Excel is: %f\n', Diff)
    error('Number of epochs in EEG data does not match number of epochs in the Excel file')
end
fprintf('Equal amount of epochs in EEG and excel data :) \n')
%% 
% selects channel 3 (EEG left (uV) for analysis
EEG = pop_select(EEG, 'channel', 3);
eeglab redraw
%%
% filtering - highpass and lowpass
EEG =pop_eegfiltnew(EEG, 0.1, 49);
%%
pop_eegplot(EEG, 1, 1, 1); 
%% 
% electrode structure for power analysis
Electrodes={'EEG left (uV)';1};
MinHz=0.5;
MaxHz=48;
analyseElectrode='EEG left (uV)';
%% 
% power spectral density analysis
% *** make sure PowerFunction is in path!! ***
% *** change the save names below!! ***
% loops that calls on PowerFunction to compute Hz bins per electrode per epoch
for index=1:length(Electrodes(1,:))
    ElectrodeN=Electrodes{2,index};
    ElectrodeName=Electrodes{1,index};
    EEGdataElectrode=Data(ElectrodeN,:);
    fprintf('Computing bins for electrode %s ....\n',ElectrodeName)
    datastruct=PowerFunctionDelta(EEGdataElectrode, Fs, sleepstaging, Epochlength, MinHz, MaxHz);
    fprintf('Saving %s ....\n',ElectrodeName)
    savename=[locationoutput, '\SUB300\' '_', ElectrodeName,'_HzBins_Per_Epoch'];
    if strcmp(ElectrodeName, analyseElectrode) == 1
        analysestruct=datastruct;
        fprintf('%s selected for further analysis',analyseElectrode)
    end
    
    %xlswrite(savename,datastruct)
    fprintf('%s done\n',ElectrodeName)
end
%%
% saves epoch power to CSV spreadsheet for statistics
% *** change participant name! ***
cell2csv('SUB300_ABSolutePower_WholeNight_HzBins_Per_Epoch.csv', datastruct);