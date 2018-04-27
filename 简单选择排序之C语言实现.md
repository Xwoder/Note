# 简单选择排序之C语言实现

## 代码

```c
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef enum {
    OrderedAscending,
    OrderedSame,
    OrderedDescending,
} ComparisonResult;

typedef int ElemType;

/* 简单选择排序 */
void simpleSelectionSort(ElemType arr[], size_t length);

/* 交换元素 */
void swapElem(ElemType *a, ElemType *b);

/* 打印数组 */
void printArr(char *message, ElemType arr[], size_t length);

/* 打印元素 */
void printElem(ElemType elem);

/* 比较元素 */
ComparisonResult compareElem(ElemType a, ElemType b);

int main(int argc, const char *argv[]) {

    ElemType arr[] = { 656, 520, 753, 899, 820, 629, 146, 533, 214, 789 };
    size_t length = sizeof arr / sizeof arr[0];

    printArr("排序前", arr, length);

    simpleSelectionSort(arr, length);

    printArr("排序后", arr, length);

    return EXIT_SUCCESS;
}

void simpleSelectionSort(ElemType arr[], size_t length) {
    for (size_t i = 0; i < length - 1; ++i) {
        size_t targetElemIndex = i;
        for (size_t j = i + 1; j < length; ++j) {
            if (compareElem(arr[j], arr[targetElemIndex]) == OrderedAscending) {
                targetElemIndex = j;
            }
        }

        swapElem(arr + i, arr + targetElemIndex);
    }
}

void swapElem(ElemType *a, ElemType *b) {
    ElemType temp = *a;
    *a = *b;
    *b = temp;
}

void printArr(char *message, ElemType arr[], size_t length) {
    printf("%s: (", message);
    for (size_t i = 0; i < length; ++i) {
        printElem(arr[i]);
        printf(i == length - 1 ? ")\n" : ", ");
    }
}

void printElem(ElemType elem) {
    printf("%d", elem);
}

ComparisonResult compareElem(ElemType a, ElemType b) {
    if (a > b) {
        return OrderedDescending;
    } else if (a == b) {
        return OrderedSame;
    } else {
        return OrderedAscending;
    }
}
```

## 输出

```
排序前: (656, 520, 753, 899, 820, 629, 146, 533, 214, 789)
排序后: (146, 214, 520, 533, 629, 656, 753, 789, 820, 899)
```

