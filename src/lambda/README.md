## lambda表达式

lambda 表达式的类型有一个限制：它必须是函数式接口。函数式接口是只有一个抽象方法的接口

- 创建接口对象

```
@FunctionalInterface
public interface Function<T, U, R> {

    R apply(T t, U u);
}
```

- 测试

```
public static void main(String[] args) {
    BiFunction<String,String,Integer> findWordInSentence = (word, sentence) -> sentence.indexOf(word);
    
    System.out.println(findWordInSentence.apply("j", "yjz"));
}
```

结果：

![3](/docs/3.jpg)

#### 方法引用

有时 lambda 表达式只是对现有方法的引用。在这种情况下，您可以将其编写为方法引用

```
Consumer<String> printer = s -> System.out.println(s);

=>

Consumer<String> printer = System.out::println;
```

- 静态方法引用

```
IntBinaryOperator max = (a, b) -> Integer.max(a, b);

=> 

IntBinaryOperator max = Integer::max;
```

- 未绑定的方法引用

```
BiFunction<String, String, Integer> indexOf = (sentence, word) -> sentence.indexOf(word);

->

BiFunction<String, String, Integer> indexOf = String::indexOf;
```

- 绑定方法引用

```
Function<User, String> getName = User::getName;
User anna = new User("Anna");
String name = getName.apply(anna);
```

- 编写构造方法引用

```
Supplier<List<String>> newListOfStrings = () -> new ArrayList<>();

-> 

Supplier<List<String>> newListOfStrings = ArrayList::new;
```
