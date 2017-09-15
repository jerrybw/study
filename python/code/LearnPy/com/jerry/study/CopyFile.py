'''
Created on 2017年8月30日

@author: XX
'''
def copyFile(filename):
    file = open(filename,"r")
    targetFile = open("copy"+filename,"w")
    for line in file:
        targetFile.write(line)
    file.close()
    targetFile.close()
copyFile("abc.text")