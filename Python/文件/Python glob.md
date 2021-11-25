# Python glob

```python
import glob
import os

for file in glob.glob(os.path.join("./", "*.txt")):
    with open(file, "r") as file_stream:
        for row in file_stream:
            print(row)
```

