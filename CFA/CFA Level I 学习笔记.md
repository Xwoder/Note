# CFA Level I 学习笔记

## 第6章 货币时间价值

### 6.1 利率

**对利率的理解**

* 要求回报率（`Required Rate of Return`）
* 折现率（`Discounted Rate`）
* 机会成本（`Opportunity Cost`）

**利率的组成**

* 实际无风险利率（`Real Risk-Free interest Rate`）
* 通货膨胀率（`Inflation Premium`）
* 违约风险溢价（`Default Risk Premium`）
* 流动性风险溢价（`Liquidity Premium`）
* 期限风险溢价（`Maturity Premium`）

即
$$
\begin{align}
利率 &= 实际无风险利率 + 预期通货膨胀率 + 风险溢价 \\
&=  实际无风险利率 + 预期通货膨胀率 + (违约风险溢价 + 流动性风险溢价 + 期限风险溢价)
\end{align}
$$
**风险**

* 违约风险
* 流动性风险
* 期限风险

**利率**

一般用字母 $r$ 表示

分为

* 报价利率（`Stated Annual Interest Rate` / `Quoted Interest Rate`）

    报价利率是一种年利率，结合给定的计息次数，可得出计息期利率（`periodic interest rate`），其公式为
    $$
    计息期利率 = \frac{报价利率}{计息次数}
    $$
    

* 有效年利率（`Effective Annual Rate`，`EAR`）

    指每单位货币在一年内获得的利息收益的总额，其公式为
    $$
    有效年利率 = (1+计息期利率)^{计息次数}-1 = (1+\frac{报价利率}{计息次数})^{计息次数}-1 = (1+\frac{r_s}{m})^{m} - 1
    $$
    若一年内计息次数趋于无穷，则可得到连续复利（`continuous componding`）公式
    $$
    e^{r_s} - 1
    $$
    连续复利是有效年利率的最大值

### 6.2 现值和终值

**现值**（`present value`，`PV`）

与终值相对的一个概念

**终值**（`future value`，`FV`）

与现值相对的一个概念

相互转换公式为
$$
\text{FV} = \text{PV} \times (1+利率)^{期数}
$$

$$
\text{PV} = \frac{\text{FV}} {(1+利率)^{期数}}
$$

**年金**

* 普通年金（`Ordinay Annuity`）

    期末支付

* 先付年金（`Annuity Due`）

    期初支付

* 永续年金（`Perpetuity Annuity`）

    期末支付，无期限

概率的分类

* 经验概率

    客观概率

* 先验概率

    客观概率

* 主观概率

赔率（Odds）
$$
事件 \text{E} 发生的赔率 = \frac{P(E)}{1-P(E)}
$$

$$
事件 \text{E} 不发生的赔率 = \frac{1-P(E)}{P(E)}
$$

**亏空风险**（`Shortfall Risk`）

**最低可接受水平**（`minimum acceptable level`）

罗伊安全第一比例（Roy's Safety-First Ratio）
$$
\text{SF Ratio} = \frac{E(R_p)-R_L}{\sigma_p}
$$


​                                                                                                                                                                                           

