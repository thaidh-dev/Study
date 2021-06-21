# Multiple Linear Regression

# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import statistics
import math
import numpy
import pandas as pd

# X_train = [
#     [40, 25], 
#     [45, 20], 
#     [38, 30], 
#     [50, 30], 
#     [48, 28], 
#     [55, 30], 
#     [53, 34], 
#     [55, 36], 
#     [58, 32], 
#     [40, 34], 
#     [55, 38], 
#     [48, 28], 
#     [45, 30], 
#     [55, 36], 
#     [60, 34], 
#     [60, 38], 
#     [60, 42], 
#     [65, 38], 
#     [50, 34], 
#     [58, 38]
# ]
# y_train = [[1], [2], [1], [3], [2], [3], [3], [4], [4], [3], [5], [3], [3], [2], [4], [5], [5], [5], [4], [3]]
# y_train = [1, 2, 1, 3, 2, 3, 3, 4, 4, 3, 5, 3, 3, 2, 4, 5, 5, 5, 4, 3]
# X_train = [
#     [0, 1],
#     [0, 0]
# ]
# y_train = [2, 1]
# y_train = [[2], [1]]

X_train = [
    [1, 3, 5], 
    [5, 1, 3], 
    [3, 1, 5],
    [7, 1, 1],
]
y_train = [1, 2, 3, 7]
X_test = [
    [1, 3, 1.5],
    [1, 2, 0]
]


# Training the Multiple Linear Regression model on the Training set
from sklearn.linear_model import LinearRegression
regressor = LinearRegression()
regressor.fit(X_train, y_train)

# Predicting the Test set results
y_pred = regressor.predict(X_test)
print(y_pred)
# np.set_printoptions(precision=2)
# print(np.concatenate((y_pred.reshape(len(y_pred),1), y_test.reshape(len(y_test),1)),1))









# X_train = [
#     [0.94 ,4.22	,1.58],
#     [1.13 ,3.48	,1.28],
#     [0.61 ,2.2	,0.64],
#     [1.17 ,2.2	,0.08],
#     [0.93 ,2.25	,0.38],
#     [1.94 ,2.45	,1.45],
#     [2.12 ,2.62	,2.31],
#     [1.03 ,2.97	,3.6],
#     [0.67 ,2.9	,2.59],
#     [0.78 ,2.64	,1.62],
#     [1.1 ,2.64	,3.16],
#     [1.78 ,2.39	,0.23],
#     [1.54 ,2.76	,0.76],
#     [1.77 ,2.23	,1.42],
#     [2.22 ,3.35	,1.86]
# ]
# y_train = [8.23, 8.26, 9.33, 8.92, 8.89, 8.34, 8.51, 9.15, 9.4, 9.01, 8.77, 8.11, 8, 8.68, 8.11]
# X_test = [
#     [1, 3, 1.5],
#     [1, 2, 0]
# ]


# # Training the Multiple Linear Regression model on the Training set
# from sklearn.linear_model import LinearRegression
# regressor = LinearRegression()
# regressor.fit(X_train, y_train)

# # Predicting the Test set results
# y_pred = regressor.predict(X_test)
# print(y_pred)
# # np.set_printoptions(precision=2)
# # print(np.concatenate((y_pred.reshape(len(y_pred),1), y_test.reshape(len(y_test),1)),1))




