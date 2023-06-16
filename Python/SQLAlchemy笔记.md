# SQLAlchemy 笔记

## ORM 方式

```python
import sqlalchemy
from sqlalchemy import create_engine, Column, Integer, String
from sqlalchemy.orm import sessionmaker

##################################################
# 创建数据库引擎
##################################################
engine = create_engine('sqlite:///:memory:')

##################################################
# 创建会话对象
##################################################
Session = sessionmaker(bind=engine)
session = Session()

##################################################
# 用于衍生类定义的基类
##################################################
Base = sqlalchemy.orm.declarative_base()


##################################################
# 用户类
##################################################
class User(Base):
    __tablename__ = 'users'
    id = Column(Integer, primary_key=True)
    name = Column(String)
    age = Column(Integer)

    def __repr__(self):
        return f"User(id={self.id}, " \
               f"name={self.name}, " \
               f"age={self.age})"


##################################################
# 创建表格
##################################################
Base.metadata.create_all(engine)

##################################################
# 插入数据
##################################################
newUser = User(name='John Doe', age=25)
session.add(newUser)
session.commit()

##################################################
# 查询数据，第1次
##################################################
# 查询所有用户
users = session.query(User).all()

# 打印查询结果
for user in users:
    print(user)

##################################################
# 更新数据
##################################################
# 更新特定用户的年龄
user = session.query(User).filter_by(name='John Doe').first()
user.age = 30
session.commit()

##################################################
# 查询数据，第2次
##################################################
# 查询所有用户
users = session.query(User).all()

# 打印查询结果
for user in users:
    print(user)

##################################################
# 删除数据
##################################################
# 查询特定用户并删除
user = session.query(User).filter_by(name='John Doe').first()
session.delete(user)
session.commit()
```

输出

```
User(id=1, name=John Doe, age=25)
User(id=1, name=John Doe, age=30)
```

