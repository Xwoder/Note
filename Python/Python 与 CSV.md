# Python 与 CSV

[TOC]

## CSV 示例文件

```csv
Supplier Name,Invoice Number,Part Number,Cost,Purchase Date
0,Supplier X,001-1001,2341,$500.00,1/20/14
1,Supplier X,001-1001,2341,$500.00,1/20/14
2,Supplier X,001-1001,5467,$750.00,1/20/14
3,Supplier X,001-1001,5467,$750.00,1/20/14
4,Supplier Y,50-9501,7009,$250.00,1/30/14
5,Supplier Y,50-9501,7009,$250.00,1/30/14
6,Supplier Y,50-9505,6650,$125.00,2/3/14
7,Supplier Y,50-9505,6650,$125.00,2/3/14
8,Supplier Z,920-4803,3321,$615.00,2/3/14
9,Supplier Z,920-4804,3321,$615.00,2/10/14
10,Supplier Z,920-4805,3321,$615.00,2/17/14
11,Supplier Z,920-4806,3321,$615.00,2/24/14
```

### 基础 Python 实现

```Python
input_file = "input.csv"
output_file = "output.csv"

with open(input_file, "r") as file_reader:
    with open(output_file, "w") as file_writer:
        header_list = file_reader.readline().strip().split(",")
        print(header_list)
        file_writer.write(",".join(map(str, header_list)) + "\n")
        for row in file_reader:
            row_list = row.strip().split(',')
            file_writer.write(",".join(map(str, row_list)) + "\n")
```

### pandas 库实现

```Python
# 复制 CSV 文件
import pandas as pd
import sys

input_file = sys.argv[1]
output_file = sys.argv[2]

data_frame = pd.read_csv(input_file)
data_frame.to_csv(output_file)
```

```Python
import pandas as pd

input_file = "supplier_data.csv"
output_file = "output.csv"

data_frame = pd.read_csv(input_file)
data_frame["Cost"] = data_frame["Cost"].str.strip("$").astype(float)
data_frame_value_meets_condition = data_frame.loc[
                                   (data_frame["Supplier Name"].str == "Supplier Z") |
                                   (data_frame["Cost"] >= 600), :
                                   ]
data_frame_value_meets_condition.to_csv(output_file, index=False)
```

```
import pandas as pd

important_date_list = ["1/20/14", "2/3/14"]

input_file = "supplier_data.csv"
output_file = "output.csv"

data_frame = pd.read_csv(input_file)
data_frame["Cost"] = data_frame["Cost"].str.strip("$").astype(float)
data_frame_value_meets_condition = data_frame.loc[
                                   (data_frame["Purchase Date"].isin(important_date_list)), :
                                   ]
data_frame_value_meets_condition.to_csv(output_file, index=False)
```

```Python
import pandas as pd

input_file = "supplier_data.csv"
output_file = "output.csv"

data_frame = pd.read_csv(input_file)
data_frame["Cost"] = data_frame["Cost"].str.strip("$").astype(float)
data_frame_value_meets_condition = data_frame.loc[
                                   data_frame["Invoice Number"].str.startswith("001-"), :
                                   ]
data_frame_value_meets_condition.to_csv(output_file, index=False)
```

```Python
import pandas as pd

input_file = "supplier_data.csv"
output_file = "output.csv"

data_frame = pd.read_csv(input_file)
data_frame["Cost"] = data_frame["Cost"].str.strip("$").astype(float)
data_frame_value_meets_condition = data_frame.iloc[:, [0, 3]]
data_frame_value_meets_condition.to_csv(output_file, index=False)
```

```Python
import pandas as pd

input_file = "supplier_data.csv"
output_file = "output.csv"

data_frame = pd.read_csv(input_file)
data_frame_column_by_name = data_frame.loc[:, ["Supplier Name", "Cost"]]
data_frame_column_by_name.to_csv(output_file, index=False)
```

### CSV 库实现

```Python
import csv

input_file = "supplier_data.csv"
output_file = "output.csv"

with open(input_file, "r") as csv_in_file:
    with open(output_file, "w") as csv_out_file:
        file_reader = csv.reader(csv_in_file, delimiter=",")
        file_writer = csv.writer(csv_out_file, delimiter=",")
        for row_list in file_reader:
          supp
            file_writer.writerow(row_list)
```

```Python
"""
筛选行
"""

import csv

input_file = "supplier_data.csv"
output_file = "output.csv"

with open(input_file, "r") as csv_in_file:
    with open(output_file, "w") as csv_out_file:
        file_reader = csv.reader(csv_in_file, delimiter=",")
        file_writer = csv.writer(csv_out_file, delimiter=",")

        header = next(file_reader)
        file_writer.writerow(header)
        
        for row_list in file_reader:
            supplier = str(row_list[0]).strip()
            cost = float(str(row_list[3]).lstrip("$").replace(",", ""))
            if supplier == "Supplier Z" or cost >= 600:
                file_writer.writerow(row_list)
```

```Python
"""
筛选行
"""

import csv

important_date_list = ["1/20/14", "2/3/14"]

input_file = "supplier_data.csv"
output_file = "output.csv"

with open(input_file, "r") as csv_in_file:
    with open(output_file, "w") as csv_out_file:
        file_reader = csv.reader(csv_in_file, delimiter=",")
        file_writer = csv.writer(csv_out_file, delimiter=",")

        header = next(file_reader)
        file_writer.writerow(header)

        for row_list in file_reader:
            date_of_supply = str(row_list[4]).strip()
            if date_of_supply in important_date_list:
                file_writer.writerow(row_list)
```

```Python
"""
筛选列
"""

import csv

my_columns = [0, 3]

input_file = "supplier_data.csv"
output_file = "output.csv"

with open(input_file, "r") as csv_in_file:
    with open(output_file, "w") as csv_out_file:
        file_reader = csv.reader(csv_in_file, delimiter=",")
        file_writer = csv.writer(csv_out_file, delimiter=",")

        for row_list in file_reader:
            row_list_output = []
            for index_value in my_columns:
                row_list_output.append(row_list[index_value])
            file_writer.writerow(row_list_output)
```

```Python
"""
根据表头名称筛选列
"""

import csv

my_columns = ["Supplier Name", "Cost"]
my_columns_index = []

input_file = "supplier_data.csv"
output_file = "output.csv"

with open(input_file, "r") as csv_in_file:
    with open(output_file, "w") as csv_out_file:
        file_reader = csv.reader(csv_in_file, delimiter=",")
        file_writer = csv.writer(csv_out_file, delimiter=",")

        header = next(file_reader, None)
        for index_value in range(len(header)):
            if header[index_value] in my_columns:
                my_columns_index.append(index_value)
        file_writer.writerow(my_columns)

        for row_list in file_reader:
            row_list_output = []
            print(row_list)
            for index_value in my_columns_index:
                row_list_output.append(row_list[index_value])
            file_writer.writerow(row_list_output)
```

```Python
import csv

input_file = "supplier_data_unecessary_header_footer.csv"
output_file = "output.csv"

row_counter = 0

with open(input_file, "r") as input_file:
    with open(output_file, "w") as output_file:
        file_reader = csv.reader(input_file)
        file_writer = csv.writer(output_file)

        for row in file_reader:
            if 3 <= row_counter <= 15:
                file_writer.writerow([value.strip() for value in row])
            row_counter += 1
```

```Python
import csv
import glob
import os

input_path = "./"
file_counter = 0
for input_file in glob.glob(os.path.join(input_path, "supplier_data_?.csv")):
    with open(input_file, "r") as csv_input_file:
        file_counter += 1
        file_reader = csv.reader(csv_input_file)
        header = next(file_reader, None)
        row_counter = 1
        for row_list in file_reader:
            row_counter += 1
        print("{0}: {1} rows {2} columns".format(os.path.basename(input_file), row_counter, len(header)))
print("Number of files: %d" % file_counter)
```

```Python
import csv
import os
import glob

input_dir = "./"

is_first_file = True

for input_file in glob.glob(os.path.join(input_dir, "supplier_data_sub_*")):
    with open(input_file, "r") as csv_input_file:
        with open("supplier_data_megered.csv", "a") as csv_output_file:
            file_reader = csv.reader(csv_input_file)
            file_writer = csv.writer(csv_output_file)
            if is_first_file:
                for row_list in file_reader:
                    file_writer.writerow(row_list)
                is_first_file = False
            else:
                header = next(file_reader, None)
                for row_list in file_reader:
                    file_writer.writerow(row_list)
```