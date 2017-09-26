'''
Created on 2017年8月30日

@author: XX
'''
def copyFile(path):
    path.replace("\\","/")
    applicationDevFile = 1
    applicationProdFile = 2
    applicationDevTargetFile = 3
    applicationProdTargetFile = 4
    isYml = True
    try:
        applicationDevFile = open(path+"\\application-dev.yml","r")
        applicationProdFile = open(path+"\\application-prod.yml","r")
    except BaseException:
        isYml = False
        applicationDevFile = open(path+"\\application-dev.properties","r")
        applicationProdFile = open(path+"\\application-prod.properties","r")
    targetPath = path + "\\copy"
    if isYml:
        applicationDevTargetFile = open(targetPath+"\\application-dev.yml","w")
        applicationProdTargetFile = open(targetPath+"\\application-prod.yml","w")
    else:
        applicationDevTargetFile = open(targetPath+"\\application-dev.properties","w")
        applicationProdTargetFile = open(targetPath+"\\application-prod.properties","w")
    for line in applicationDevFile:
        if "101.200.44.115" in line:
            line = line.replace("101.200.44.115","47.93.83.134")
        applicationDevTargetFile.write(line)
    for line in applicationProdFile:
        if "101.200.44.115" in line:
            line = line.replace("101.200.44.115","47.93.83.134")
        applicationProdTargetFile.write(line)
    applicationDevFile.close()
    applicationProdFile.close()
    applicationDevTargetFile.close()
    applicationProdTargetFile.close()
copyFile("G:/work/java/AIQandA/AIInterface/src/main/resources")
copyFile("G:/work/java/AIQandA/Alert/src/main/resources")
copyFile("G:/work/java/AIQandA/Jenny/src/main/resources")
copyFile("G:/work/java/AIQandA/EventHander/src/main/resources")
copyFile("G:/work/java/AIQandA/mongodb/src/main/resources")
copyFile("G:/work/java/AIQandA/robot/src/main/resources")
copyFile("G:/work/java/AIQandA/Script/src/main/resources")