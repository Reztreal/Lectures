import random

def fullCandidates():
   cand=0
   sayi = 0
   candidates.append("")
   for i in xrange(1,10):
      cand = str(i);
      for j in xrange(0,10):
         if str(j) in cand:
            continue
         cand = cand[0:1] + str(j);
         for k in xrange(0,10):
            if str(k) in cand:
               continue
            cand = cand[0:2] + str(k);
            for l in xrange(0,10):
               if str(l) in cand:
                  continue
               candidates[sayi] = cand+str(l)
               sayi+=1
               candidates.append("")
   candidates.pop(len(candidates)-1)

def removing(macs, mems, gus):
   newList = []
   for i in xrange(0, len(candidates)):
      macsTemp, memsTemp = comparer(candidates[i],gus)
      if macs == str(macsTemp) and mems == str(memsTemp):
         newList.append(candidates[i])
   return newList

def generateUniquePw(n=4):#if number of digit more than 10, it return 0 &&& default n is 4
   if n > 10: return 0
   s = str(random.randint(1,9))
   while len(s) < n:
      newDigit = str(random.randint(0,9))
      if not newDigit in s:
         s += str(newDigit)
   return s

def comparer(pw, gs):
   matches = 0
   members = 0
   
   for i in xrange(len(gs)):
      if pw[i] == gs[i]:
         matches+=1
      elif gs[i] in pw:
         members+=1
   
   return matches, members

def agent(password, guess):
   macs, mems = comparer(password, guess)
   return str(macs)+" "+str(mems)

password = generateUniquePw(4)
candidates=[]
fullCandidates()
guess=0
turn = 0
report = ""
while turn < 8:
   guess = candidates[ random.randint(0,len(candidates)-1) ]
   print "The Guess is", guess
   report = agent(password, guess)
   print "Report:", report
   macs = str(report[0])
   mems = str(report[2])
   
   if not macs == "4" and len(candidates) > 1:
      candidates = removing(macs, mems, guess)
      print "Remaining possibilities:", len(candidates)
      print "Turn  Guess  Matches Members"
      print "{}.    {}   {}       {}".format(turn+1, guess, macs, mems)
      if len(candidates) <= 1:
         candidates.append(guess)
         turn = 10
   else:
      turn = 10 #Use turn like key. This will end the loop end turn will be 11 after loop
   turn+=1

if len(candidates) == 0 :
   print "The Breaker did something wrong :("#Isi garantiye almak lazim hocam :)
elif turn == 11 :
   print "The Breaker Wins"
   print "The Password was", candidates[0]
else :
   print "I lose :("
