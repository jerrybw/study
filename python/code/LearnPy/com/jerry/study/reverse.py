'''
Created on 2017年8月28日

@author: XX
'''
# print("hello")
# print("************************")
def reverse(s):
    if s == "":
        return ""
    return reverse(s[1:]) + s[0]
print(reverse("hello"))
