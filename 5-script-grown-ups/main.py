# open file
pods = open('pods.txt', 'r')
Lines = pods.readlines()

print('Evicted pods:')
for line in Lines:
    columns = line.split()
    if columns[2] == "Evicted":
        print(columns[0])