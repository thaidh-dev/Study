# Multiple Linear Regression

# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

# Importing the dataset
dataset = pd.read_csv('50_Startups.csv')
X = dataset.iloc[:, :-1].values
y = dataset.iloc[:, -1].values
print(X)

# Encoding categorical data
from sklearn.compose import ColumnTransformer
# Encode categorical features as a one-hot numeric array: Mã hóa các đối tượng địa lý dưới dạng one-hot numeric array.
# This creates a binary column for each category and returns a sparse matrix or dense array (depending on the sparse parameter)
# sparse: bool, default=True: Will return sparse matrix if set True else will return an array
# A sparse matrix is a matrix in which many or most of the elements have a value of zero. This is in contrast to a dense matrix, where many or most of the elements have a non-zero value: Một ma trận thưa thớt là một ma trận trong đó có nhiều hoặc hầu hết các yếu tố có giá trị bằng không. Điều này trái ngược với một ma trận dày đặc, nơi có nhiều hoặc hầu hết các yếu tố có giá trị khác không.
from sklearn.preprocessing import OneHotEncoder
# ColumnTransformer(name, transformer, columns)
# remainder{‘drop’, ‘passthrough’} or estimator, default=’drop’: By default, only the specified columns in transformers are transformed and combined in the output, and the non-specified columns are dropped. (default of 'drop'). By specifying remainder='passthrough', all remaining columns that were not specified in transformers will be automatically bỏ qua. This subset of columns is concatenated with the output of the transformers
ct = ColumnTransformer(transformers=[('encoder', OneHotEncoder(), [3])], remainder='passthrough')
X = np.array(ct.fit_transform(X))
print(X)

# Splitting the dataset into the Training set and Test set
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state = 0)

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


# Building the optimal model using Backward Elimination
# import statsmodels.api as sm
# X = np.append(arr = np.ones((50, 1)).astype(int), values = X, axis = 1)
# X_opt = X[:, [0, 1, 2, 3, 4, 5]]
# X_opt = X_opt.astype(np.float64)
# regressor_OLS = sm.OLS(endog = y, exog = X_opt).fit()
# regressor_OLS.summary()
# X_opt = X[:, [0, 1, 3, 4, 5]]
# X_opt = X_opt.astype(np.float64)
# regressor_OLS = sm.OLS(endog = y, exog = X_opt).fit()
# regressor_OLS.summary()
# X_opt = X[:, [0, 3, 4, 5]]
# X_opt = X_opt.astype(np.float64)
# regressor_OLS = sm.OLS(endog = y, exog = X_opt).fit()
# regressor_OLS.summary()
# X_opt = X[:, [0, 3, 5]]
# X_opt = X_opt.astype(np.float64)
# regressor_OLS = sm.OLS(endog = y, exog = X_opt).fit()
# regressor_OLS.summary()
# X_opt = X[:, [0, 3]]
# X_opt = X_opt.astype(np.float64)
# regressor_OLS = sm.OLS(endog = y, exog = X_opt).fit()
# regressor_OLS.summary()       