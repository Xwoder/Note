import sys

from PyQt5.QtCore import QStringListModel, QModelIndex
from PyQt5.QtGui import QFont
from PyQt5.QtWidgets import QApplication, QMainWindow, QListView, QVBoxLayout, QWidget, QPushButton, QLineEdit


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("QStringListModel & QListView")

        # 创建一个字符串列表
        self.strings = ['Apple', 'Banana', 'Cherry', 'Date']

        # 创建一个QStringListModel并设置字符串列表
        self.stringListModel = QStringListModel()
        self.stringListModel.setStringList(self.strings)

        # 创建一个QListView并设置数据模型
        listView = QListView()
        listView.setModel(self.stringListModel)
        font = QFont()
        font.setPointSize(16)
        listView.setFont(font)
        self.listView = listView

        # 创建插入按钮
        insertButton: QPushButton = QPushButton("插入")
        insertButton.clicked.connect(self.insertRow)
        self.insertButton = insertButton

        # 创建删除按钮
        removeButton: QPushButton = QPushButton("删除")
        removeButton.clicked.connect(self.removeRow)
        self.removeButton = removeButton

        # 创建文本输入框
        lineEdit: QLineEdit = QLineEdit()
        self.lineEdit = lineEdit

        # 创建布局，并将QListView、文本输入框和按钮添加到布局中
        vBoxLayout: QVBoxLayout = QVBoxLayout()
        vBoxLayout.addWidget(listView)
        vBoxLayout.addWidget(lineEdit)
        vBoxLayout.addWidget(insertButton)
        vBoxLayout.addWidget(removeButton)

        # 创建一个QWidget作为中心部件，并设置布局
        centralWidget: QWidget = QWidget()
        centralWidget.setLayout(vBoxLayout)

        # 设置QWidget为主窗口的中心部件
        self.setCentralWidget(centralWidget)

    def insertRow(self):
        # 从文本输入框获取新项
        text = self.lineEdit.text()

        currentIndex: QModelIndex = self.listView.currentIndex()
        currentRow = currentIndex.row()

        # 在第一行之前插入新项
        self.stringListModel.insertRow(currentRow)
        self.stringListModel.setData(self.stringListModel.index(currentRow), text)

        # 清空文本输入框
        self.lineEdit.clear()

    def removeRow(self):
        # 获取选中项的索引
        indexes = self.listView.selectedIndexes()

        # 从后向前删除选中的行
        for index in reversed(indexes):
            self.stringListModel.removeRow(index.row())


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window: MainWindow = MainWindow()
    window.show()
    sys.exit(app.exec())
