'''
Created on 2017年8月16日

@author: XX
'''
def openFile():
    infile = open('haha.text','r')
    line = infile.readline()
    while line != '':
        print(line)
        line = infile.readline()
openFile()
    