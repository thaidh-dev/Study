# Data Preprocessing Template

# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

# Importing the dataset
dataset = pd.read_csv('Data.csv')
X = dataset.iloc[:, :-1].values
y = dataset.iloc[:, -1].values

# Splitting the dataset into the Training set and Test set
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state = 0)

# Lấy 3 cột đầu
print(X)
# Lấy cột cuối cùng
print(y)

# x có 1 phần tử, test_size = 0.2 có nghĩa là lấy 20% của 10 phần tử kia làm X_test
# do đó X_test có 2 phần tử
print(X_test)
# train_test không thấy khai báo thì sẽ tự động lấy nốt chỗ phần tử còn lại của X (80% còn lại)
print(X_train)

# y này thì cũng giống X thôi
print(y_train)
print(y_test)

# random_state: kết quả random của X_train, X_test, y_train, y_test sẽ được giữ nguyên trên mỗi state, nếu không đặt cái tham số này thì kết quả sẽ nhảy loạn mẹ nó hết cả lên (có thể đặt từ 0 -> 42)