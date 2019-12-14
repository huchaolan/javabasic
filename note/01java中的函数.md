# java中的函数

java8新增了函数--值的一种新形式，Java8可以进行多个处理器上并行编程。

## 方法和Lambda作为一等公民

让方法作为值(参数)构成了Java8功能的基础

### 方法引用

+ 筛选目录中隐藏文件,使用内部类的方式

```java
File[] hiddenFiles = new File(".").listFiles(new FileFilter(){
    public boolean accept(File file) {
        return file.isHidden();
    }
});
```

+ 使用方法引用::语法，就把这个方法当成方法的参数传给了listFiles方法，好处是代码读起来更接近问题的陈述。

```java
    public static List<File> getHiddenList1(){
        return Arrays.asList( new File("D:\\javaproject\\apache-tomcat-8.5.34\\webapps")
                    .listFiles(File::isHidden));
    }
```

### lambda匿名函数

Java8体现了更广义的将`函数`作为`值`的思想包括Lambda函数。
`(int x) -> x + 1`表示“调用时给定参数x,就返回x+1值的函数

>`谓词(predicate)`是类似函数的东西，它接受一个参数值，并返回true或false。lambda函数是一种记法，**解决短方法很多的问题，代码更干净，清晰**

```java
filterApples(inventory,(Apple a)->"green".equals(a.getColor()));
filterApples(inventory,(Apple a)->a.getWeight()>150);
```

### 流(Stream)

+ 迭代方式：StreamAPI处理数据的方式非常不同。for-each循环迭代元素，然后再处理元素，叫做外部迭代，StreamAPI不用考虑迭代的问题，数据处理完全是在库内部进行的，叫做内部迭代。
+ 并行:Stream提供了parallelStream方法达到并行处理数据，它关注数据分块而不是协调访问。

### 默认方法

默认方法为了支持库设计师能够写出更容易改进的接口