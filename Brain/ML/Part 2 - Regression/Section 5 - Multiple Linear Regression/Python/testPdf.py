# cái này là để tính cho file 'Multiple Linear Regression.xlsx' - sheet 'Kết quả khác' 

# Multiple Linear Regression

# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

# Importing the dataset
dataset = pd.read_csv('pdf.csv')
X = dataset.iloc[:, :-1].values
y = dataset.iloc[:, -1].values
print(X)

# Splitting the dataset into the Training set and Test set
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.1, random_state = 0)

# Training the Multiple Linear Regression model on the Training set
from sklearn.linear_model import LinearRegression
regressor = LinearRegression()
regressor.fit(X_train, y_train)

# Predicting the Test set results
y_pred = regressor.predict(X_test)
np.set_printoptions(precision=2) # quy định số chữ số của phần thập phân: ở đây là 2 số
print(
    # nối 2 array vào vs nhau
    np.concatenate(
        (
            # convert 1-D array to 2-D array
            y_pred.reshape(len(y_pred), 1), 
            y_test.reshape(len(y_test), 1)
        ),
        # axis = 0: nối bottom
        # axis = 1: nối right
        0
    )
)


