# -*- coding: UTF-8 -*-
'''
Created on 2017年8月31日

@author: XX
'''
phoneFile = open("phone.txt","rb")
emailFile = open("email.txt","rb")
phoneFile.readline()
emailFile.readline()
phoneLines = phoneFile.readlines()
emailLines = emailFile.readlines()
listNamesInPhone = []
listNamesInEmail = []
listPhonesInPhone = []
listEmailsInEmail = []
lines = []
for line in phoneLines:
    splits = line.split()
    listNamesInPhone.append(str(splits[0].decode("utf-8")))
    listPhonesInPhone.append(str(splits[1].decode("utf-8")))
for line in emailLines:
    splits = line.split()
    listNamesInEmail.append(str(splits[0].decode("utf-8")))
    listEmailsInEmail.append(str(splits[1].decode("utf-8")))
lines.append("姓名\t电话\t邮箱\n".encode(encoding='utf-8', errors='strict'))
for i in range(len(listNamesInPhone)):
    name = listNamesInPhone[i]
    phone = listPhonesInPhone[i]
    if name in listNamesInEmail:
        indexInEmail = listNamesInEmail.index(name)
        email = listEmailsInEmail[indexInEmail]
        lines.append((name + "\t" + phone + "\t" + email + "\n").encode(encoding='utf-8', errors='strict'))
    else:
        lines.append((name + "\t" + phone + "\t" + "-----------\n").encode(encoding='utf-8', errors='strict'))
for name in listNamesInEmail:
    if name not in listNamesInPhone:
        indexInEmail = listNamesInEmail.index(name)
        email = listEmailsInEmail[indexInEmail]
        lines.append((name + "\t" + "-----------" + "\t" + email+"\n").encode(encoding='utf-8', errors='strict'))
filePhoneAndEmail = open("filePhoneAndEmail.txt","wb")
for i in range(len(lines)):
    print(lines[i])
    print(type(lines[i]))
    filePhoneAndEmail.write(lines[i])
phoneFile.close()
emailFile.close()
filePhoneAndEmail.close()
        