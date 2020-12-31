choose=input("Original [press 0] or New [press 1]?")
if(choose==1):
  filename1 = input("input file: ") 
  filename2 = input("Output file: ") 
else:
  filename1 = "rank_prakhar.txt"
  filename2 = "rank_aman.txt"

file1 = open(filename1).readlines() 
 
file1_line = [] 
 
for lines in file1: 
 file1_line.append(lines) 
 
file2 = open(filename2).readlines() 
 
file2_line = [] 
 
for lines in file2: 
 file2_line.append(lines) 


print("Length of file:" + filename1 + " is:" + str(len(file1_line)))
print("Length of file:" + filename2 + " is:" + str(len(file2_line)))
# print(len(file2_line))

if(file2_line[0]!=file1_line[0]):
  print("ERROR")
else:
  print("VOILA")

 
# n = 0 
# counter = 0
# for line in file1_line: 
#   if line != file2_line[n]: 
#     if(len(line)!=0):
#       print("Not Match:","Line :",n + 1,filename1,":",line,"|",filename2,":",file2_line[n]) 
#       counter+=1;
#   # else:
#       # print("Not Match:","Line :",n + 1,filename1,":|",filename2,":") 
  
#   n += 1 
# else: 
#   n += 1 
# print(counter)   
