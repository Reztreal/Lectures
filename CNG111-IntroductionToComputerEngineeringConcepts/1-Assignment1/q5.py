#$ python q5.py

num  = 2*3*5*7
num2 = 0

while True:
   i = 2      #Asal sayi olup olmadigini kontrol ederken kullaniliyor
   pFactor=0  #Prime factor sayisi
   temp = num #num degiskenimiz kaybedilmemeli 
   while i <= temp:
      if temp%i == 0:
         pFactor += 1
         while (temp%i) == 0:
            temp/=i
      else:
         i+=1
   
   if pFactor == 4:
      if num2 == num-1:
         break
      num2 = num
   
   num+=1

print "1.Number : {}\n2.Number : {}".format(num-1, num)
