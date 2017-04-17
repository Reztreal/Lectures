#$ python q7.py

k   = 1
ninc= 0 #non-increasing numbers
ratio = 1.0

while ratio >= 0.001:
   
   temp = k
   while temp > 9:      #Non-increase control start
      if (temp%10) > ((temp/10)%10):
         break
      temp/=10
   
   if temp < 10:
      ninc += 1         #Non-increase control finish
      
   k+=1
   ratio = float(ninc)/k

print " k : {}\n non-increasing numbers : {}\n ratio : {}".format(k, ninc, ratio)
