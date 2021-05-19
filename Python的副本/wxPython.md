# wxPython

[TOC]

示例1：非面向对象版本

```python
# 导入 wxPython 包
import wx

app = wx.App()
frame = wx.Frame(None, title="Hello World")
frame.Show(True)
app.MainLoop()
```

示例2：面向对象版本

```python
# 导入 wxPython 包
import wx

class MyApp(wx.App):
    def OnInit(self):
        frame = wx.Frame(None, title='Hello World')
        frame.Show(True)
        return True


if __name__ == '__main__':
    app = MyApp()
    app.MainLoop()
```

示例3

```python
# 导入 wxPython 包
import wx


class MyFrame(wx.Frame):
    def __init__(self, superior, title):
        wx.Frame.__init__(self,
                          parent=superior,
                          title=title,
                          pos=(100, 200),
                          size=(350, 200))
        self.panel = wx.Panel(self)
        # wx.TextCtrl(self.panel, value='Hello World', size=(350, 200))
        self.panel.Bind(wx.EVT_LEFT_UP, self.onClick)

    def onClick(self, event):
        posm = event.GetPosition()
        wx.StaticText(parent=self.panel, label='Hello World', pos=(posm.x, posm.y))


class MyApp(wx.App):
    def OnInit(self):
        frame = MyFrame(None, 'Example')
        frame.Show(True)
        return True


if __name__ == '__main__':
    app = MyApp()
    app.MainLoop()
```

示例4

```python
import wx 
class Frame1(wx.Frame):
    def __init__(self, parent, title):
        wx.Frame.__init__(self, parent, title = title)
        panel = wx.Panel(self)
        sizer = wx.BoxSizer(wx.VERTICAL)
        self.text1= wx.TextCtrl(panel, value = "Hello, World!", size = (200,180), style = wx.TE_MULTILINE)
        sizer.Add(self.text1, 0, wx.ALIGN_TOP | wx.EXPAND)
        button = wx.Button(panel, label = "Click Me")
        sizer.Add(button)
        panel.SetSizerAndFit(sizer)        
        panel.Layout()
        self.Bind(wx.EVT_BUTTON,self.OnClick,button)
        self.Show(True)
        
    def OnClick(self, text):
        self.text1.AppendText("\nHello, World!")
        
if __name__ == '__main__': 
    app = wx.App()
    frame = Frame1(None, "Hello World in wxPython")
    app.MainLoop()
```

示例5

```python
import wx


class MyFrame(wx.Frame):
    def __init__(self, parent, title):
        wx.Frame.__init__(self, parent, title=title)

        # 容器对象
        panel = wx.Panel(self)

        # sizer 对象
        sizer = wx.BoxSizer(wx.VERTICAL)

        # 文本框对象
        self.textCtl = wx.TextCtrl(panel,
                                   value="Hello, World!",
                                   size=(200, 180),
                                   style=wx.TE_MULTILINE)

        # 按钮对象
        button = wx.Button(panel, label="Click Me")
        self.Bind(wx.EVT_BUTTON, self.OnClick, button)

        # 将文本框对象添加至 sizer
        sizer.Add(self.textCtl, 0, wx.ALIGN_TOP | wx.EXPAND)
        # 将按钮对象添加至 sizer
        sizer.Add(button)

        panel.SetSizerAndFit(sizer)
        panel.Layout()

    def OnClick(self, text):
        self.textCtl.AppendText("\nHello, World!")


class MyApp(wx.App):
    def OnInit(self):
        frame = MyFrame(None, "Hello World in wxPython")
        frame.Show(True)
        return True


if __name__ == '__main__':
    app = MyApp()
    app.MainLoop()
```

## 组件

### Button

文本按钮

### BitmapButton

位图按钮

### ToggleButton

开关按钮

### ListCtrl

列表组件

两种模式

* 列表模式

    ```python
    wx.LC_LIST
    ```

* 报告模式

    ```python
    wx.LC_REPORT
    ```

* 小图标模式

    ```python
    wx.LC_SMALL_ICON
    ```

* 图标模式

    ```python
    wx.LC_ICON
    ```

### 菜单组件

#### Menu

菜单事件

```
wx.EVT_MENU
```

#### MenuBar

#### MenuItem

### 文本组件

#### StaticText

静态标签

#### TextCtrl

文本框

## 布局

### sizer

屏幕布局算法

#### 常用 sizer

* wx.BoxSizer
* wx.FlexGridSizer
* wx.GridSizer
* wx.GridBagSizer
* wx.StaticBoxSizer

#### 使用步骤

* 创建自动调整尺寸的容器
* 创建 `sizer`
* 创建子窗口
* 使用 `sizer` 的 `Add()` 方法将每个自窗口添加个 `sizer`
* 调用容器的 `SetSizer(sizer)` 方法