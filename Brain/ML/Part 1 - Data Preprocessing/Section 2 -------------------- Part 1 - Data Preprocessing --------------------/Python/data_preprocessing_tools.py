# Data Preprocessing Tools

# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

# Importing the dataset
dataset = pd.read_csv('Data.csv')
X = dataset.iloc[:, :-1].values
y = dataset.iloc[:, -1].values
# print(X)
# print(y)

# Taking care of missing data
from sklearn.impute import SimpleImputer
# missing_values=np.nan: The placeholder for the missing values, đối vs excel(pandas) thì missing_values should be set to np.nan vì NA trong excel (pd.NA) will be converted to np.nan.
# strategy='mean': thay thế các giá trị bị thiếu bằng giá trị trung bình dọc theo mỗi cột. Chỉ có thể được sử dụng với dữ liệu số. bỏ qua phần tử na
imputer = SimpleImputer(missing_values=np.nan, strategy='mean')
# fit: quá trình thu thập dữ liệu
# transform: quá trình biến đổi dữ liệu
imputer.fit(X[:, 1:3])
X[:, 1:3] = imputer.transform(X[:, 1:3])
# print(X)

# Encoding categorical data
# Encoding the Independent Variable https://nces.ed.gov/nceskids/help/user_guide/graph/variables.asp
from sklearn.compose import ColumnTransformer
from sklearn.preprocessing import OneHotEncoder
# transformers=[('encoder', OneHotEncoder(), [0])]: (name, transformer, columns)
# remainder='passthrough': By default, only the specified columns in transformers are transformed and combined in the output, and the non-specified columns are dropped. (default of 'drop'). By specifying remainder='passthrough', all remaining columns that were not specified in transformers will be automatically passed through. This subset of columns is concatenated with the output of the transformers
ct = ColumnTransformer(transformers=[('encoder', OneHotEncoder(), [0])], remainder='passthrough')
X = np.array(ct.fit_transform(X))
# print(X)

# Encoding the Dependent Variable https://nces.ed.gov/nceskids/help/user_guide/graph/variables.asp
# LabelEncoder: nó kiểu như đánh chú thích biểu đồ
# VD: t có 1 mảng như này ["paris", "paris", "tokyo", "amsterdam"]
# LabelEncoder sẽ đánh số chú thích như này
#     0: "paris", 
#     1: "tokyo", 
#     2: "amsterdam"
from sklearn.preprocessing import LabelEncoder
le = LabelEncoder()
y = le.fit_transform(y)
# print(y)

# Splitting the dataset into the Training set and Test set
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state = 1)
# print(X_train)
# print(X_test)
# print(y_train)
# print(y_test)

# Feature Scaling
# Các điểm dữ liệu đôi khi được đo đạc với những đơn vị khác nhau, m và feet chẳng hạn. Hoặc có hai thành phần (của vector dữ liệu) chênh lệch nhau quá lớn, một thành phần có khoảng giá trị từ 0 đến 1000, thành phần kia chỉ có khoảng giá trị từ 0 đến 1 chẳng hạn. Lúc này, chúng ta cần chuẩn hóa dữ liệu
# StandardScaler performs the task of Standardization. Usually a dataset contains variables that are different in scale. For e.g. an Employee dataset will contain AGE column with values on scale 20-70 and SALARY column with values on scale 10000-80000. As these two columns are different in scale, they are Standardized to have common scale while building machine learning model.
from sklearn.preprocessing import StandardScaler
sc = StandardScaler()
X_train[:, 3:] = sc.fit_transform(X_train[:, 3:])
X_test[:, 3:] = sc.transform(X_test[:, 3:])
# print(X_train)
print(X_test)
