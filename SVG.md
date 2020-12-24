# SVG

[TOC]

## 概述

`SVG`，全称 `Scalable Vector Graphics`，中文名为**可伸缩性矢量图形**，是基于 `XML` 格式的开放式图形、图像标准。

它是一种二维矢量图形格式，其中可以包含矢量图形、光栅图形及文本。

## 与 HTML

如果想在 `HTML` 中使用 `SVG`，可以使用如下的 `SVG` 标签：

```html
<svg version="1.1" xmlns="http://www.w3.org/2000/svg"></svg>
```

## 图形元素

### rect / 矩形

```xml
<rect 
      x="150"
      y="20"
      width="100"
      height="50"
      fill="red"
      stroke="green"
      stroke-width="2"
      rx=25
      ry=25>
</rect>
```

属性含义

|     属性名     |     含义      |
| :------------: | :-----------: |
|      `x`       | 起点 x 轴坐标 |
|      `y`       | 起点 y 轴坐标 |
|    `width`     |     宽度      |
|    `height`    |     高度      |
|     `fill`     |    填充色     |
|    `stroke`    |    描边色     |
| `stroke-width` |   描边宽度    |
|      `rx`      | 圆角 x 周半径 |
|      `ry`      | 圆角 y 周半径 |

### circle / 圆形

```xml
<circle 
        r=50 
        cx=80 
        cy=80 
        fill="red" 
        stroke="green"
        stroke-width="3">
</circle>
```

属性含义

|     属性名     |     含义      |
| :------------: | :-----------: |
|      `r`       |     半径      |
|      `cx`      | 圆心 x 轴坐标 |
|      `cy`      | 圆心 y 轴坐标 |
|     `fill`     |    填充色     |
|    `stroke`    |    描边色     |
| `stroke-width` |   描边宽度    |

### ellipse / 椭圆

```xml
<ellipse 
         rx=50 
         ry=30 
         cx=60 
         cy=50 
         fill="red" 
         stroke="green" 
         stroke-width="3">
</ellipse>
```

属性含义

|     属性名     |      含义      |
| :------------: | :------------: |
|      `rx`      | x 轴方向的半径 |
|      `ry`      | y 轴方向的半径 |
|      `cx`      | 圆心 x 轴坐标  |
|      `cy`      | 圆心 y 轴坐标  |
|     `fill`     |     填充色     |
|    `stroke`    |     描边色     |
| `stroke-width` |    描边宽度    |

### line / 线段

```xml
<line 
      x1="10" 
      y1="20" 
      x2="110" 
      y2="120" 
      stroke="red" 
      stroke-width="3" 
      stroke-dasharray="2">
</line>
```

属性含义

|       属性名       |     含义      |
| :----------------: | :-----------: |
|        `x1`        | 起点 x 轴坐标 |
|        `y1`        | 终点 y 轴坐标 |
|        `x2`        | 起点 x 轴坐标 |
|        `y2`        | 终点 y 轴坐标 |
|      `stroke`      |    描边色     |
|   `stroke-width`   |   描边宽度    |
| `stroke-dasharray` | 划线样式数组  |

### polyline / 折线

```xml
<polyline 
          points="10,10 100,30 40,90 10,70" 
          fill="none" 
          stroke="blue" 
          stroke-width="3">
</polyline>

<polyline 
          points="10,10 100,30 40,90 10,70" 
          fill="red" 
          stroke="blue" 
          stroke-width="3">
</polyline>
```

其绘制的折线不会自动闭合，默认使用黑色填充

### polygon / 多边形

```xml
<polygon points="10,10 100,30 40,90 10,70" 
         fill="none" 
         stroke="green" 
         stroke-width="3">
</polygon>
```

其绘制的折线会自动闭合，默认使用黑色填充