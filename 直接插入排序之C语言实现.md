# 直接插入排序之C语言实现

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

/* 直接插入排序 */
void straightInsertionSort(ElemType arr[], size_t length);

/* 打印数组 */
void printArr(char *message, ElemType arr[], size_t length);

/* 打印元素 */
void printElem(ElemType elem);

/* 比较元素 */
ComparisonResult compareElem(ElemType a, ElemType b);

int main(int argc, const char *argv[]) {

    ElemType arr[] = { 328, 954, 509, 235, 211, 904, 314, 295, 579, 376 };
    size_t length = sizeof arr / sizeof arr[0];

    printArr("排序前", arr, length);

    straightInsertionSort(arr, length);

    printArr("排序后", arr, length);

    return EXIT_SUCCESS;
}

void straightInsertionSort(ElemType arr[], size_t length) {
    for (size_t i = 1; i < length; ++i) {
        ElemType elem = arr[i];
        ssize_t j;
        for (j = i - 1; j >= 0 && compareElem(elem, arr[j]) == OrderedAscending; --j) {
            arr[j + 1] = arr[j];
        }
        arr[j + 1] = elem;
    }
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

```txt
排序前: (328, 954, 509, 235, 211, 904, 314, 295, 579, 376)
排序后: (211, 235, 295, 314, 328, 376, 509, 579, 904, 954)
```


