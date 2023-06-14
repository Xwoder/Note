import pandas as pd

# 创建一个示例DataFrame
df = pd.DataFrame({'A': [1, 2, 3],
                   'B': [4, 5, 6],
                   'C': [7, 8, 9]})

print(df)

# 定义两个函数来处理数据
def multiply_by_two(data):
    return data * 2

def add_five(data):
    return data + 5

# 使用pipe()方法应用函数
result = df.pipe(multiply_by_two).pipe(add_five)

print(result)
