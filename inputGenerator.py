import random

MAX_DISTANCE = 64

for i in range(20):
    with open(f"IO/testcases/input/inp{i}.txt", "w") as f:
        noOfStreets = random.randint(100, 10000)
        noOfFlags = random.randint(1, noOfStreets - 2)
        f.write(f"{noOfStreets}\n{noOfFlags}\ns0 s{noOfStreets - 1}\n")
        flags = []
        for i in range(noOfFlags):
            flag = random.randint(1, noOfStreets - 2)
            while flag in flags:
                flag = random.randint(1, noOfStreets - 2)
            flags.append(flag)
            f.write(f"s{flag} ")
        f.write("\n")
        for i in range(noOfStreets):
            neighbours = set()
            noEdge = random.randint(1, 100)
            noOfEdges = min(noEdge, noOfStreets - i - 1)
            temp = f"s{i}"
            for neighbour in range(noOfEdges):
                neighbour = random.randint(i + 1, noOfStreets - 1)
                while neighbour in neighbours:
                    neighbour = random.randint(i + 1, noOfStreets - 1)
                neighbours.add(neighbour)
                temp += f" s{neighbour}"
                distance = random.randint(1, MAX_DISTANCE)
                temp += f" {distance}"
            f.write(temp + "\n")
    