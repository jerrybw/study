H = float(input('请输入您的身高，单位为m'))
W = float(input('请输入你的体重，单位为kg'))
BMI = W /(H * H)
print('您的BMI值为%.2f'%(BMI))
status = '肥胖'
if(BMI < 18.5):
    status = '偏瘦'
elif(BMI < 25):
    status = '正常'
elif(BMI < 30):
    status = '偏胖'
print('您处于{}的状态'.format(status))
print('正常体重的BMI值应该处于18.5与25之间')
print('建议您将体重维持在%.2fkg与%.2fkg之间'%(18.5*H*H,25*H*H))
