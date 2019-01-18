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

+ 使用方法引用::语法，就把这个方法当成方法的参数传给了listFiles方法，好处式代码读起来更接近问题的陈述。

```java
    public static List<File> getHiddenList1(){
        return Arrays.asList( new File("D:\\javaproject\\apache-tomcat-8.5.34\\webapps")
                    .listFiles(File::isHidden));
    }
```

### lambda匿名函数

