# pandas DataFrame Merge

```
A = pd.DataFrame([['Tom', 18, 'boy'],
                  ['Alice', 17, 'girl'],
                  ['Jack', 17, 'girl']],
                 columns=['name', 'age', 'gender'])
```

|      | name  | age  | gender |
| :--: | :---: | :--: | ------ |
|  0   |  Tom  |  18  | boy    |
|  1   | Alice |  17  | girl   |
|  2   | Jack  |  17  | girl   |

```
B = pd.DataFrame([['Tom', 96, 88],
                  ['Alice', 89, 79],
                  ['Rose', 99, 30]],
                 columns=['name', 'Chinese', 'Math'])
```

| name | Chinese | Math 
:--: | :--: | :-----: | :--:
0|Tom|96|88
1|Alice|89|79
2|Rose|99|30

