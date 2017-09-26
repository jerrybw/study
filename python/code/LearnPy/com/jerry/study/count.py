'''
Created on 2017年9月20日

@author: XX
'''
count={}
str = input()
BiaoDians = "`~!@#$%^&*()_+-={}[]|\:;"'<,>.?/'
for ch in str:
    if ch in BiaoDians:
        str.replace(ch," ")
splits = str.split()
for word in splits:
    if word.lower() in count.keys():
        count[word.lower()] += 1
    else:
        count[word.lower()] = 1
max = 0
printWord = ""
for a in count:
    if count[a] > max:
        max = count[a]
        printWord = a
print(printWord)