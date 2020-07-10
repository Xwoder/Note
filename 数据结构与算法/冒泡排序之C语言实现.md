# 冒泡排序之C语言实现

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

void bubbleSort(ElemType arr[], size_t length);

/* 交换元素 */
void swapElem(ElemType *a, ElemType *b);

/* 打印数组 */
void printArr(char *message, ElemType arr[], size_t length);

/* 打印元素 */
void printElem(ElemType elem);

/* 比较元素 */
ComparisonResult compareElem(ElemType a, ElemType b);

int main(int argc, const char *argv[]) {

    ElemType arr[] = { 987, 473, 686, 385, 213, 376, 848, 799, 774, 611 };
    size_t length = sizeof arr / sizeof arr[0];

    printArr("排序前", arr, length);

    bubbleSort(arr, length);

    printArr("排序后", arr, length);

    return EXIT_SUCCESS;
}

void bubbleSort(ElemType arr[], size_t length) {
    for (size_t i = 0; i < length - 1; ++i) {
        bool needSwap = false;
        for (size_t j = 0; j < length - i - 1; ++j) {
            if (compareElem(arr[j], arr[j + 1]) == OrderedDescending) {
                needSwap = true;
                swapElem(arr + j, arr + j + 1);
            }
        }
        if (!needSwap) {
            break;
        }
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

```txt
排序前: (987, 473, 686, 385, 213, 376, 848, 799, 774, 611)
排序后: (213, 376, 385, 473, 611, 686, 774, 799, 848, 987)
```


