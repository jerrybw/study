'''
Createdon2017年9月20日

@author:XX
'''
code1="abcdefghijklmnopqrstuvwxyz "
code2="nopqrstuvwxyzabcdefghijklm "
str = input()
str2 = ""
for ch in str:
    toUpper = False
    if ch not in code1:
        ch = ch.lower()
        toUpper = True
    if ch in code1:     
        index = code1.index(ch)
        ch = code2[index]
    str2 += ch.upper() if toUpper else ch
print(str2)