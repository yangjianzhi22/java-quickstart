# java-quickstart

java快速入门

> 官网: [https://dev.java/learn/getting-started-with-java/](https://dev.java/learn/getting-started-with-java/)

## java

运行java应用程序所遵循的基本步骤

1. .java文件创建源代码
2. 编译这些文件,生成一组对应的.class二进制文件
3. 将它们作为应用程序一起运行

> 开发大型应用程序的开发人员不使用纯文本编辑器来管理他们的源代码；他们使用集成开发环境。
> IDE 是复杂的软件应用程序，专门用于软件开发。
> 这些应用程序自动处理源代码的编译，它们可以帮助您跟踪 Java 代码语法中的错误并确定其执行中的错误等

## [快速入门](/pj-quickstart)

- 前提

安装java开发工具包, 步骤略

> 下载“Java”意味着下载 Java 开发工具包，也称为JDK, JDK 包含许多工具，其中包括您将用于编译和运行 Java 应用程序的工具
> 
> JRE代表Java运行时环境。它是JDK的子集

- 创建一个java类

```
public class MyFirstClass {
    public static void main(String... args) {
        System.out.println("Hello, World!");
    }
}
```

- 编译代码

```
javac MyFirstClass.java
```

![1](/docs/1.jpg)

- 运行程序

```
java MyFirstClass
```

![2](/docs/2.jpg)

## 场景

1. [Lambda表达式](/pj-lambda)
2. [集合框架 collections](/pj-collections)
3. [流Stream Api](/pj-stream)

