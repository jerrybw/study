'''
Created on 2017年8月16日

@author: XX
'''
while True:
    try:
        x = int(input('please enter a number:'))
        break
    except ValueError:
        print('your input is not a number, please try again')