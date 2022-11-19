import random
INPUT_DESTINATION: str = "../IO/input/"

MAX_NO_OF_FLAGS =  100
MAX_NO_OF_STREETS = 10000
MAX_DISTANCE = 640

def generateRandomInput(n: int):
    for i in range(n):
        with open(INPUT_DESTINATION + "inp{i}.txt", "w") as f:
            noOfStreets = random.randint(100, MAX_NO_OF_STREETS)
            noOfFlags = random.randint(1, MAX_NO_OF_FLAGS)
            noOfFlags = min(noOfFlags, noOfStreets - 2)
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
                for i in range(noOfEdges):
                    neighbour = random.randint(i + 1, noOfStreets - 1)
                    while neighbour in neighbours:
                        neighbour = random.randint(i + 1, noOfStreets - 1)
                    neighbours.add(neighbour)
                    temp += f" s{neighbour}"
                    distance = random.randint(1, MAX_DISTANCE)
                    temp += f" {distance}"
                f.write(temp + "\n")

def generateSpecialInput(n: int):
    for i in range(n):
        with open(INPUT_DESTINATION + "specialInput.txt", "w") as f:
            noOfStreets = random.randint(100, MAX_NO_OF_STREETS)
            noOfFlags = random.randint(1, MAX_NO_OF_FLAGS)
            noOfFlags = min(noOfFlags, noOfStreets - 2)
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
                noEdge = random.randint(1, 100)
                noOfEdges = min(noEdge, (noOfStreets - i) // 2)
                temp = f"s{i}"
                for neighbour in range(i + 1, i + 1 + noOfEdges):
                    temp += f" s{neighbour}"
                    distance = random.randint(1, MAX_DISTANCE)
                    temp += f" {distance}"
                f.write(temp + "\n")
