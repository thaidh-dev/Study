# Simple Linear Regression

# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import statistics
import math
import numpy
import pandas as pd

# Importing the dataset
X_train = [[1], [3], [5]]
y_train = [[5], [6], [7]]
X_test = [[5], [3], [1]]

# Training the Simple Linear Regression model on the Training set
from sklearn.linear_model import LinearRegression
regressor = LinearRegression()
regressor.fit(X_train, y_train)

# Predicting the Test set results
y_pred = regressor.predict(X_test)

# Visualising the Training set results
plt.scatter(X_train, y_train, color = 'red')
plt.plot(X_train, y_pred, color = 'blue')
plt.title('Salary vs Experience (Training set)')
plt.xlabel('Years of Experience')
plt.ylabel('Salary')
# plt.show()



# We can use the regression line to predict values of Y given values of X. For any given value of X, we go straight up to the line, and then move horizontally to the left to find the value of Y. The predicted value of Y is called the predicted value of Y, and is denoted Y'. The difference between the observed Y and the predicted Y (Y-Y') is called a residual. The predicted Y part is the linear part. The residual is the error.
# 2021-01-05-17-04-15.png


# 1. tính predict
# a. tìm phương trình hồi quy của các tọa độ điểm cho trước. đây là cách tính may mắn do tất cả các điểm đều nằm sẵn trên 1 đường thẳng
# X_train = [[1], [3], [5]]
# y_train = [[5], [6], [7]]
# -> có 3 điểm vs tọa độ [1, 5], [3, 6], [5, 7]
# LinearRegression: y = a + bx -> 5 = a + b và 6 = a + 3b
# giải hệ -> y = 4.5 + x/2
# với X_test = [[5], [3], [1]], tính predict(y_pred = regressor.predict(X_test))
#     điểm đầu tiên: y = 4.5 + 5/2 = 7
#     điểm thứ 2: y = 4.5 + 3/2 = 6
# -> y_pred = [7, 6, 5]

# x = [1, 3, 5]
# y = [5, 6, 7]

# b. 1 cách tìm khác
# lấy đc của 1 thằng ngu trên mạng, đéo hiểu lấy đâu ra mà đc như thế
# tính tương quan
# 2021-01-06-15-50-58.png

x = [1, 3, 5]
y = [5, 6, 7]

meanX = statistics.mean(x)
meanY = statistics.mean(y)
stdX = numpy.std(x)
stdY = numpy.std(y)
df = pd.DataFrame({"a": x, "b": y})
corr = df["a"].corr(df["b"])
b = corr * (stdY / stdX)
a = meanY - b * meanX

print('y = ' + str(a) + ' + ' + str(b) + 'x')