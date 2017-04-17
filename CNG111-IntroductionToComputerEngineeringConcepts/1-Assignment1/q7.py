#$ python q7.py
#Very fast (Hocam normalde cok yavas oluyordu hizlandirabilmek icin 4 saatimi harcadim :D )
#Non-increasing bulma yontemi, sol basamak sag basamaktan buyukse soldaki basamagi 1 arttirip
#sagda kalan tum basamaklari sifir yapiyor. Her bir adimda ninc degiskenini 1 arttirip 
#kontrol edilen sayiya kadar kac tane non-increasing sayisi oldugunu sakliyoruz. Buldugum her
#yeni non-increasing sayisi ise k aluyor zaten.

k     = 1
ratio = 1.0
ninc  = 0         #total of non-increasing numbers
n     = 0         #next non-increasing numbers creating to this variable
preN  = 0         #we use this to calculate exact k

#This while finds a non-increase number in a lap
while ratio > 0.001:
   preN = n
   temp = k
   n = 0
   on= 1
   #Non-increase control is starting
   while temp > 9:
      if (temp%10) > ((temp/10)%10):
         n = 0
         temp = (temp/10)+1
      else:
         n += on*(temp%10)
         temp/=10
      on*=10

   n += on*temp
   #Non-increasing number is found = n
   ninc+=1
   
   k = n+1
   ratio = float(ninc)/n
#We found interval of k which ratio is 0.001>ratio>0.009...

#So now we control for exact k (= preN)
ratio = 1.0
while ratio >= 0.001:
   preN +=1
   ratio = float(ninc-1)/preN

print "\nNon-increasing numbers : {}\nk : {}\nRatio : {}".format(ninc-1,preN,ratio)
