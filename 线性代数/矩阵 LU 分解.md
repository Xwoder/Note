# 矩阵 LU 分解

[TOC]

## LU分解定理

设 $A$ 是 $n$ 阶非奇异矩阵，则存在唯一的单位下矩阵 $L$ 和上三角矩阵 $U$，使得
$$
A = L U
$$
的充分必要条件是 $A$ 的顺序主子式均为非 $0$

其中矩阵 $L$ 型如
$$
L = \left[\begin{array}{ccccc}
1 \\
l_1 & 1\\
l_2 & l_3 & 1\\
\vdots & \vdots & \ddots & \ddots & \\
l_x & l_y & \ldots & l_z & 1
\end{array}\right]
$$


矩阵 $U$ 型如
$$
U = \left[\begin{array}{ccccc}
u_1 & u_2 & u_3 & \ldots & u_a \\
& u_4 & u_5 & \ldots & u_b \\
& & \ddots & \ddots & \vdots \\
&  & & \ddots & u_c \\
& & & & u_z
\end{array}\right]
$$

## 例

对于矩阵
$$
A = \left[\begin{array}{ccc}
2 & 3 & 4 \\
1 & 1 & 9 \\
1 & 2 & -6
\end{array}\right]
$$
其各个顺序主子式分别如下
$$
D_1 = \left|2\right| = 2\ne 0
$$

$$
D_2 = \left|\begin{array}{cc}
3 & 3\\
1 & 1 \\
\end{array}\right| = -1 \ne 0
$$

$$
D_3 = \left|\begin{array}{ccc}
2 & 3 & 4 \\
1 & 1 & 9 \\
1 & 2 & -6
\end{array}\right| 
= \left|\begin{array}{ccc}
0 & 1 & -14 \\
1 & 1 & 9 \\
0 & 1 & -15
\end{array}\right|
= \left|\begin{array}{ccc}
0 & 0 & 1 \\
1 & 1 & 9 \\
0 & 1 & -15
\end{array}\right|
= \left|\begin{array}{ccc}
0 & 0 & 1 \\
1 & 0 & 0 \\
0 & 1 & 0
\end{array}\right|= 1 \ne 0
$$

由此可知，矩阵 $A$ 可分解为如下形式的两个矩阵的相乘
$$
\begin{align}
L U &= \left[\begin{array}{ccc}
1 & 0 & 0 \\
l_1 & 1 & 0 \\
l_2 & l_3 & 1
\end{array}\right]\left[\begin{array}{ccc}
u_1 & u_2 & u_3 \\
0 & u_4 & u_5 \\
0 & 0 & u_6
\end{array}\right] \\
&= \left[\begin{array}{ccc}
u_1 & u_2 & u_3 \\
l_1 u_1 & l_1 u_2 + u_4 & l_1 u_3 + u_5 \\
l_2 u_1 & l_2 u_2 + l_3 u_4 & l_2 u_3 + l_3 u_5 + u_6
\end{array}\right]
\end{align}
$$


由此可知
$$
\left[\begin{array}{ccc}
2 & 3 & 4 \\
1 & 1 & 9 \\
1 & 2 & -6
\end{array}\right] = \left[\begin{array}{ccc}
u_1 & u_2 & u_3 \\
l_1 u_1 & l_1 u_2 + u_4 & l_1 u_3 + u_5 \\
l_2 u_1 & l_2 u_2 + l_3 u_4 & l_2 u_3 + l_3 u_5 + u_6
\end{array}\right]
$$
由此可知
$$
\left\{
  \begin{array}{l}
    u_1 &= 2 \\
    u_2 &= 3 \\
    u_3 &= 4 \\
    l_1 u_1 &= 1 \\
    l_1 u_2 + u_4 &= 1 \\
    l_1 u_3 + u_5 &= 9 \\
    l_2 u_1 &= 1 \\
    l_2 u_2 + l_3 u_4 &= 2 \\
    l_2 u_3 + l_3 u_5 + u_6 &= -6
  \end{array}
\right.
$$
