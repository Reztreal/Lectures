#$ python q1.py

n = int(raw_input(""))
count = 0

if n < 0:
   n *= -1
temp = n
while temp > 0:
   if( n%(temp%10) == 0):
      count+=1
   temp /= 10
   
print count
