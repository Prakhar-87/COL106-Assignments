choose=input("Original [press 0] or New [press 1]?")
if(choose=="1"):
  filename1 = input("input file: ") 
  filename2 = input("Output file: ") 
else:
  filename1 = "dfs.txt"
  filename2 = "dfs_prakhar.txt"

file1 = open(filename1).readlines() 
 
file1_line = [] 
 
for lines in file1: 
 file1_line.append(lines) 
 
file2 = open(filename2).readlines() 
 
file2_line = [] 
 
for lines in file2: 
 file2_line.append(lines) 
count=0
for i in range(min(len(file1_line),len(file2_line))):
	if(file1_line[i]!=file2_line[i]):
		print("Line number:",i)
		print("File:" + str(filename1) + " is -> ",file1_line[i])
		print("File:" + str(filename2) + " is -> ",file2_line[i])
		count+=1
if(count==0):
	print("VOILA")
	
# import difflib

# case_a = file2_line[0]
# case_b = file1_line[0]

# #output_list = [li for li in difflib.ndiff(case_a, case_b) if li[0] != ' ']

# count=0
# for i in range(len(case_b)):
#   count+=1
#   if(case_b[i]!=case_a[i]):
#     for j in range(100):
#       print(case_b[i+j],end="")
#     print()
#     for j in range(100):
#       print(case_a[i+j],end="")

#     break
    
# print(count)