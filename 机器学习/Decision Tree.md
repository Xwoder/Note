# Decision Tree

[TOC]

## 不纯性

### 度量

#### 熵

$$
\operatorname{ Entropy }(t) = \sum_{i=0}^{c-1} p(i \mid t) \log _{2} p(i \mid t)
$$

#### 基尼系数

$$
\operatorname{Gini}(t) = 1-\sum_{i=0}^{c-1}[p(i \mid t)]^{2}
$$

#### 分类错误

$$
\operatorname { Classification Error }(t) =1-\max _{i}[p(i \mid t)]
$$

