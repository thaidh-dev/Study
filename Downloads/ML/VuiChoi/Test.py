# import matplotlib.pyplot as plt

# x = [[1, 3, 5], [7, 9, 11], [13, 15, 17]]
# y = [[14, 16, 18], [8, 10, 12], [2, 4, 6]]
# z = [[9., 11., 13.], [12., 14., 16.], [15., 17., 19.]]

# plt.plot(x, y)
# plt.scatter(x, y)

# # vẽ biểu đồ
# plt.show()



import statistics
import math
import numpy
import pandas as pd
from sklearn.preprocessing import StandardScaler

x = [
    [0, 1, 0],
    [0, 0, 1],
]
y = [2, 1]

meanX = statistics.mean(x[0])
meanY = statistics.mean(y)
stdX = numpy.std(x[0])
stdY = numpy.std(y)
df = pd.DataFrame({"a": x[0], "b": y})
corr = df["a"].corr(df["b"])
b = corr * (stdY / stdX)
a = meanY - b * meanX

print('alo alo')

