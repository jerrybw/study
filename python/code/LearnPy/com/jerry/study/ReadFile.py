'''
Created on 2017年9月6日

@author: XX
'''
rfile = open("test.txt","r")
for line in rfile:
    print(line)
rbfile = open("test.txt","rb")
for line in rbfile:
    print(line)