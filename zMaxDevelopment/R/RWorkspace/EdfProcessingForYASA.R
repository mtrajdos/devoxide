#!/usr/bin/env Rscript

# Import libraries
library(stringr)

# Set directory for reading and saving CSV files as present working directory
path = getwd()

# Set custom directory instead of default working directory
path1 = "./Sebastiaan/09-03-2022/09-03-2022_SebastiaanScores-MichalScorer.csv"
path2 = "./Sebastiaan/09-03-2022/09-03-2022_SebastiaanScores-SebastiaanScorer.csv"
path3 = 1

#--------------------------------SET UP---------------------------------------

# Read CSV with strings as factors
csv1 = read.csv(path1, skip=1, stringsAsFactors=FALSE)
csv2 = read.csv(path2, skip=1, stringsAsFactors=FALSE)
csv3 = read.csv(path3, skip=1, stringsAsFactors=FALSE)

# Define indexes of specific sleep stages
# e.g. number of rows in CSV that have "Wake" defined as sleep stage
undefinedIndex1 = which(csv1$stage == "Undefined")
unknownIndex1 = which(csv1$stage == "Unknown" | csv1$stage == "Movement")
wakeIndex1 = which(csv1$stage == "Wake")
n1Index1 = which(csv1$stage == "NREM1")
n2Index1 = which(csv1$stage == "NREM2")
n3Index1 = which(csv1$stage == "NREM3")
remIndex1 = which(csv1$stage == "REM")

undefinedIndex2 = which(csv2$stage == "Undefined")
unknownIndex2 = which(csv2$stage == "Unknown" | csv2$stage == "Movement")
wakeIndex2 = which(csv2$stage == "Wake")
n1Index2 = which(csv2$stage == "NREM1")
n2Index2 = which(csv2$stage == "NREM2")
n3Index2 = which(csv2$stage == "NREM3")
remIndex2 = which(csv2$stage == "REM")

undefinedIndex3 = which(csv3$stage == "Undefined")
unknownIndex3 = which(csv3$stage == "Unknown" | csv3$stage == "Movement")
wakeIndex3 = which(csv3$stage == "Wake")
n1Index3 = which(csv3$stage == "NREM1")
n2Index3 = which(csv3$stage == "NREM2")
n3Index3 = which(csv3$stage == "NREM3")
remIndex3 = which(csv3$stage == "REM")

# Convert "Undefined" to -2
csv1[undefinedIndex1,4] = -2
csv2[undefinedIndex2,4] = -2
csv3[undefinedIndex3,4] = -2

# Convert "Unknown/Artefact/Movement" to -1
csv1[unknownIndex1,4] = -1
csv2[unknownIndex2,4] = -1
csv3[unknownIndex3,4] = -1

# Convert "Wake" to 0
csv1[wakeIndex1,4] = 0
csv2[wakeIndex2,4] = 0
csv3[wakeIndex3,4] = 0

# Convert "NREM1" to 1
csv1[n1Index1,4] = 1
csv2[n1Index2,4] = 1
csv3[n1Index3,4] = 1

# Convert "NREM2" to 2
csv1[n2Index1,4] = 2
csv2[n2Index2,4] = 2
csv3[n2Index3,4] = 2

# Convert "NREM3" to 3
csv1[n3Index1,4] = 3
csv2[n3Index2,4] = 3
csv3[n3Index3,4] = 3

# Convert "REM" to 4
csv1[remIndex1,4] = 3
csv2[remIndex2,4] = 3
csv3[remIndex3,4] = 3

# Confirm the conversion was successful
csv1$stage
csv2$stage
csv3$stage

# Remove last 4 characters from filename
path1 = str_sub(path1, end=-4)
path2 = str_sub(path2, end=-4)
path3 = str_sub(path3, end=-4)

# Append YASA to mark this .csv has integer sleep stages, and define file format
path1 = paste(path1,"YASA",".csv",sep="")
path2 = paste(path2,"YASA",".csv",sep="")
path3 = paste(path3,"YASA",".csv",sep="")

# Save processed csv1 file
write.csv(csv1, path1)
write.csv(csv2, path2)
write.csv(csv3, path3)
