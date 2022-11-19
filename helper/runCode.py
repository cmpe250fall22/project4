import os

for i in range(0, 20):
    os.system(f"java project4main IO/testcases/input/inp{i}.txt IO/testcases/output/out{i}.txt IO/testcases/times/time{i}.txt")