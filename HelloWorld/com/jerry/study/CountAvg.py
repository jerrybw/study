'''
Created on 2017年8月16日

@author: XX
'''
def countAvg():
    fileName = input('please enter the filename which is the number in')
    infile = open(fileName,'r')
    line = infile.readline()
    while line != '':
        count,sum= 0,0
        for xStr in line.split(','):
            sum += eval(xStr)
            count += 1
        print('avg =',sum/count)
        line = infile.readline()
countAvg()