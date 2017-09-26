'''
Created on 2017年9月20日

@author: XX
'''
str = input()
try:
    if str[-2]+str[-1] == "PY":
        print("YES")
    else:
        print("NO")
except BaseException:
    print("NO")