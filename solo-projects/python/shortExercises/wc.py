'''
wc is a text file line, word and character counter.
1. Take in a filename as a parameter
2. Open and read the file with exception protection
3. split('n') the line for the line count
4. Then calculate the word and character count
5. Print the result 'File XYZ has X lines, Y words and Z characters
'''

import sys 

def open_file():
    filename = sys.argv[1]
    with open(filename) as infile:
        print('\nProcessing file:', filename)
        return filename

def perform_count(filename):
    try:
        processedFile = open(filename)
        fileContent = processedFile.read()
        count_lines(fileContent)
        count_words(fileContent)
        count_characters(fileContent)
    except FileNotFoundError:
        print('File not found')

def count_lines(fileContent): 
    fileLines = fileContent.split('\n')
    numberOfLines = len(fileLines)
    print('Number of lines:\t', numberOfLines)

def count_words(fileContent):
    fileWords = fileContent.split(' ')
    numberOfWords = len(fileWords)
    print('Number of words:\t', numberOfWords)

def count_characters(fileContent):
    numberOfCharacters = len(fileContent)
    print('Number of characters:\t', numberOfCharacters, '\n')
        
perform_count(open_file())