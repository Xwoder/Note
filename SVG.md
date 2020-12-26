# SVG

[TOC]

## 概述

`SVG`，全称 `Scalable Vector Graphics`，中文名为**可伸缩性矢量图形**，是基于 `XML` 格式的开放式图形、图像标准。

它是一种二维矢量图形格式，其中可以包含矢量图形、光栅图形及文本。

## 与 HTML

如果想在 `HTML` 中使用 `SVG`，可以使用如下的 `SVG` 标签：

```svg
<svg version="1.1" xmlns="http://www.w3.org/2000/svg"></svg>
```

## 图形元素

### rect / 矩形

```svg
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

```svg
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

```svg
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

```svg
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

```svg
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

```svg
<polygon points="10,10 100,30 40,90 10,70" 
         fill="none" 
         stroke="green" 
         stroke-width="3">
</polygon>
```

其绘制的折线会自动闭合，默认使用黑色填充

## 路径 / path

表明路径绘制操作的步骤需要在 `path` 标签中，用属性 `d` 来表示，例

```svg
<path 
      d="m10,10 l80,80 v50 h100 z" 
      stroke="red" 
      fill="none">
</path>
```

路径操作

| 绝对路径操作 | 相对路径操作 |             含义             |
| :----------: | :----------: | :--------------------------: |
|     `M`      |     `m`      |      移动至指定或相对点      |
|     `L`      |     `l`      |    绘制线段至指定或相对点    |
|     `H`      |     `h`      | 绘制水平线段至指定或相对坐标 |
|     `V`      |     `v`      | 绘制竖直线段至指定或相对坐标 |
|     `Z`      |     `z`      |           闭合路径           |

## 圆弧路径 / A

待完成

### 文本 / text

```svg
<text x="50" 
      y="80" 
      dx="10" 
      dy="20" 
      fill="red" 
      textLength="150" 
      rotate="45" 
      transform="rotate(-45 50 170)">
  Hello World
</text>
```

属性

|    属性名    |       含义       |
| :----------: | :--------------: |
|     `x`      |  起点 x 轴坐标   |
|     `y`      |  终点 y 轴坐标   |
|     `dx`     | x 轴方向偏移坐标 |
|     `dy`     | y 轴方向偏移坐标 |
|    `fill`    |      填充色      |
| `textLength` |     文本长度     |
|   `rotate`   | 单个字符旋转角度 |
| `transform`  |       变换       |

## 标签元素

### use 标签

用于复用已有元素

被复用的元素需要使用 `id` 属性来唯一标识。

`use` 标签则通过 `href` 属性来引用上述被复用的元素。

例

```svg
<svg version="1.1" xmlns="http://www.w3.org/2000/svg">
   <circle id="myCircle" cx="15" cy="15" r="10"></circle>
   <use href="#myCircle" fill="red" x="40" y="60"></use>
   <use href="#myCircle" fill="orange" x="60" y="80"></use>
</svg>
```

### g 标签

用于将一个或多个标签分成一组

需要分组的标签应被包含在 `g` 标签中，然后使用 `use` 标签复用该分组

```svg
<svg version="1.1" xmlns="http://www.w3.org/2000/svg">
  <g id="myGroup">
     <text x="0" y="20">Circle</text>
     <circle cx="60" cy="16" r="10"></circle>
  </g>
   <use href="#myGroup" x="0" y="30" fill="red"></use>
   <use href="#myGroup" x="0" y="60" fill="blue"></use>
</svg>
```

### defs 标签

用于存储稍后将使用的图形对象

```svg
<svg version="1.1" xmlns="http://www.w3.org/2000/svg">
    <defs>
        <g id="myGroup">
            <text x="0" y="20">Circle</text>
            <circle cx="60" cy="16" r="10"></circle>
        </g>
    </defs>
    <use fill="green" href="#myGroup" x="0" y="0"></use>
    <use fill="red" href="#myGroup" x="0" y="30"></use>
    <use fill="blue" href="#myGroup" x="0" y="60"></use>
</svg>
```

### pattern 标签

用于以平铺的方式展示图形对象

需要平铺的标签应被包含在 `pattern` 标签中

```svg
<svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="500" height="800">
    <defs>
        <pattern id="dots" x="0" y="0" width="100" height="100" patternUnits="userSpaceOnUse">
            <circle cx="50" cy="50" r="35" fill="green"></circle>
        </pattern>
    </defs>
    <rect x="0" y="0" width="100%" height="100%" fill="url(#dots)"></rect>
</svg>
```

属性

|     属性名     |          含义          |
| :------------: | :--------------------: |
|      `id`      |        唯一标识        |
|      `x`       |        x 轴坐标        |
|      `y`       |        y 轴坐标        |
|    `width`     |          宽度          |
|    `height`    |          高度          |
| `patternUnits` | 几何图形采用的坐标体系 |

### image 标签

```svg
<svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="500" height="800">
    <defs>
        <pattern id="logo" x="0" y="0" width="100" height="100" patternUnits="objectBoundingBox">
            <image href="logo.jpg" width="100%" height="100%"></image>
        </pattern>
    </defs>
    <rect x="0" y="0" width="100%" height="100%" fill="url(#logo)"></rect>
</svg>
```

## 动画

### animate 标签

用于实现动画效果

```svg
<svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="500" height="800">
    <rect width="100" height="100" x="0" y="0" fill="orange">
        <animate attributeName="x" from="0" to="500" dur="2" repeatCount="indefinite"></animate>
    </rect>
</svg>
```

### animateTransform 标签

```svg
<svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="500" height="800">
    <rect width="50" height="50" x="100" y="100" fill="orange">
        <animateTransform
                attributeName="transform"
                type="rotate"
                begin="0s"
                dur="10s"
                form="0 200 200"
                to="360 400 400"
                repeatCount="indefinite">
        </animateTransform>
    </rect>
</svg>
```

