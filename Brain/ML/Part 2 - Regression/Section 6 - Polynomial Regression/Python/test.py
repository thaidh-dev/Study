# Polynomial Regression

# Importing the libraries
from sklearn.preprocessing import PolynomialFeatures

# tính theo library
# đa thức bậc degree
degree = 4
X = [
    [1, 2, 3],
    [2, 2, 3],
    [5, 7, 2]
]
poly_reg = PolynomialFeatures(degree)
X_poly = poly_reg.fit_transform(X)

# tính = cơm
# formula: [1, a, b, c, aa, ab, ac, bb, bc, cc, ...]
# tùy theo degree
# VD: degree = 4 => [1, a, b, c, aa, ab, ac, bb, bc, cc, aaa, aab, aac, abb, abc, acc, bbb, bbc, bcc, ccc, aaaa, aaab, aaac, aabb, aabc, aacc, abbb, abbc, abcc, accc, bbbb, bbbc, bbcc, bccc, cccc]
def calculate(arr, degreeMax):
    if degreeMax == -1: return
    degree = degreeMax
    for i in range(degree + 1):
        for j in range(degree, -1, -1):
            result.append(pow(arr[0], i) * pow(arr[1], degree - j) * pow(arr[2], j))

        degree -= 1
    
    calculate(arr, degreeMax - 1)

thaidh = []
for arr in X:
    result = []
    calculate(arr, degree)
    result.reverse()
    thaidh.append(result)

# so sánh kết quả do sklearn tính vs mình tính bằng quay for
print((X_poly == thaidh).all())




    

