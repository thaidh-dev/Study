import statistics
import math
import numpy
from sklearn.preprocessing import StandardScaler

# 2021-01-05-17-06-42.png
# cách tính StandardScaler: z = (x - u) / s

# tính u: tính trung bình của cái array data1
# statistics.mean(data1)

# tính s
data1 = [9, 2, 5, 4, 12, 7, 8, 11, 9, 3, 7, 4, 12, 5, 4, 10, 9, 6, 9, 4]
data2 = [[9, 0], [2, 0], [5, 0], [4, 0], [12, 0], [7, 0], [8, 0], [11, 0], [9, 0], [3, 0], [7, 0], [4, 0], [12, 0], [5, 0], [4, 0], [10, 0], [9, 0], [6, 0], [9, 0], [4, 0]]
summation = 0

# 2020-12-11-09-07-57.png
# step 1: tính trung bình của cái array data1 = tính u
u = statistics.mean(data1)
# step 2:
for i in data1:
    summation += (i - u) ** 2
# step 3
mean = summation / len(data1)
# step 4
s = math.sqrt(mean)

# print("Standard deviation tính bằng cơm = ", s)
# print("Standard deviation = ", numpy.std(data1))

# tính x, z
z = []
maxArray = max(data1)
minArray = min(data1)

for i in data1:
    z.append((i - u) / s)
print(z)

scaler = StandardScaler()
print(scaler.fit_transform(data2))
# # fit là bước tính ra đc u và s
# print(scaler.fit(data2))
# # transform là bước tính z vs u và s lấy đc ở fit
# print(scaler.transform(data2))


# tính mix/max scaling:
for i in data1:
    x = (i - minArray) / (maxArray - minArray)
