# Java 注解

[TOC]

## 注解

### 定义

注解的本质是一个 `interface`，定义的语法如下

```java
public @interface MyAnnot {
}
```

其继承了 `java.lang.annotation.Annotation` 接口，经过反编译后可以得到

```java
public interface org.example.MyAnnot extends java.lang.annotation.Annotation {
}
```

由于注解的本质是一个接口，所以它可以像其他接口一样在内部定义各种方法，这些方法称为这个注解的**属性**。

这些方法的返回值可以是基本数据类型，字符串类型，枚举类型，或者是其他注解类型，以及以上类型的数组。例如：

```java
enum Color {
    Red, Green, Blue
}

public @interface MyAnnot {
    int func1();
    String func2();
    Color func3();
    
    int[] func4();
    String[] func5();
    Color[] func6();
}
```

注解中的方法之所以被称为属性，是因为这些方法在使用的时候，语法十分的像属性。使用这些属性的时候，需要给它们赋值，例如：

```java
public @interface MyAnnot {
    int val();
}

@MyAnnot(val = 1)
public class Person {
}
```

属性还可以有默认值：

```java
public @interface MyAnnot {
    int val() default 1;
}

@MyAnnot()
public class Person {
}
```

如果属性的名称叫 `value`，那么再给属性赋值时可以省略它的名字：

```java
public @interface MyAnnot {
    int value();
}

@MyAnnot(1)
public class Person {
}
```

### 使用

对于如下注解

```java
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.TYPE})
@Inherited
@Documented
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface MyAnnot {
    int value();
}
```

以及如下使用注解修饰的类

```java
@MyAnnot(10)
public class Person {
    public static void main(String[] args) {
        // 获取 class 对象
        Class<Person> personClass = Person.class;
        // 获取注解
        MyAnnot annotation = personClass.getAnnotation(MyAnnot.class);
        // 获取注解属性
        int value = annotation.value();
        System.out.println(value);
    }
}
```

`getAnnotation` 方法用于获取注解对象，之后通过注解对象调用在定义注解时定义的方法，进而获得在使用注解时传入的值，即属性的值。

## 元注解

因为描述注解的注解称之为**元注解**

### @Target

用于表示注解可以使用的范围，其取值为 `ElementType` 类型。

```java
public enum ElementType {
    /** Class, interface (including annotation interface), enum, or record
     * declaration */
    TYPE,

    /** Field declaration (includes enum constants) */
    FIELD,

    /** Method declaration */
    METHOD,

    /** Formal parameter declaration */
    PARAMETER,

    /** Constructor declaration */
    CONSTRUCTOR,

    /** Local variable declaration */
    LOCAL_VARIABLE,

    /** Annotation interface declaration (Formerly known as an annotation type.) */
    ANNOTATION_TYPE,

    /** Package declaration */
    PACKAGE,

    /**
     * Type parameter declaration
     *
     * @since 1.8
     */
    TYPE_PARAMETER,

    /**
     * Use of a type
     *
     * @since 1.8
     */
    TYPE_USE,

    /**
     * Module declaration.
     *
     * @since 9
     */
    MODULE,

    /**
     * Record component
     *
     * @jls 8.10.3 Record Members
     * @jls 9.7.4 Where Annotations May Appear
     *
     * @since 16
     */
    RECORD_COMPONENT;
}
```

### @Retention

用于指明该注解被保留的阶段，这是一个 `RetentionPolicy` 枚举类型的属性。该枚举的定义如下：

```java
public enum RetentionPolicy {
    /**
     * Annotations are to be discarded by the compiler.
     */
    SOURCE,

    /**
     * Annotations are to be recorded in the class file by the compiler
     * but need not be retained by the VM at run time.  This is the default
     * behavior.
     */
    CLASS,

    /**
     * Annotations are to be recorded in the class file by the compiler and
     * retained by the VM at run time, so they may be read reflectively.
     *
     * @see java.lang.reflect.AnnotatedElement
     */
    RUNTIME
}
```

### @Documented

用于指定该注解是否可以被抽取到文档中

### @Inherited

使用了该元注解修饰的注解，如果一个类或方法使用了这个注解进行修饰，并且这个类有子类，那么对应的这个子类或者该之类的方法也会被该该注解修饰，即注解的继承。