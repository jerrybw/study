'''
Created on 2017年10月13日

@author: XX
'''
import urllib.request
print("测试第一种下载网页的方法")

url = "http://www.baidu.com"

response = urllib.request.urlopen(url);

print("状态码",response.getcode())

print("响应长度",len(response.read()))


print("测试第二种下载网页的方法")

request = urllib.request(url)

request.add_header("user-agent","Mozilla/5.0")

response1 = urllib.request.urlopen(request);

print("状态码",response1.getcode())

print("响应长度",len(response1.read()))
