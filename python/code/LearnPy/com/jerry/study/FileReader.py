'''
Created on 2017年8月30日

@author: XX
'''
filename = input("please enter the name of file:")
infile = open(filename,"r")
data = infile.read()
print(data)

outfile = open(filename,"w")
outfile.writelines(["sdasdasda","sdasdhjaksd","asdadsa"])
outfile.close()


file = open("","r")