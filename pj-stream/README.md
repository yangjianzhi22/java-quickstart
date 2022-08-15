## 流Stream Api

Stream API 可能是继 lambda 表达式之后添加到 Java SE 8 中的第二个最重要的特性。简而言之，Stream API 是关于向 JDK 提供众所周知的 map-filter-reduce 算法的实现

Collections Framework 是关于在 JVM 内存中存储和组织数据的。您可以将 Stream API 视为 Collections Framework 的配套框架，以非常有效的方式处理这些数据

#### 入门

```
List<String> strings = Arrays.asList("one","two","three","four");
        Map<Integer, Long> map = strings.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        map.forEach((key, value) -> System.out.println(key + " :: " + value));
```

#### 在流中进行操作

```
List<String> strings = Arrays.asList("one", "two", "three", "four");

List<Integer> lengths = strings.stream()
        .map(String::length)
        .collect(Collectors.toList());
System.out.println("lengths = " + lengths);

IntSummaryStatistics stats = strings.stream()
        .mapToInt(String::length)
        .summaryStatistics();
System.out.println("stats = " + stats);

long count = strings.stream()
        .map(String::length)
        .filter(length -> length == 3)
        .count();
System.out.println("count = " + count);
```

- 流处理 1:p 关系

处理一个州列表，并且在某些时候您需要计算所有城市的人口

```
List<State> states = new ArrayList<>();
states.add(new State("1", Arrays.asList(new City("test", 12), new City("test", 11))));
states.add(new State("2", Arrays.asList(new City("test", 12), new City("test", 11))));
states.add(new State("3", Arrays.asList(new City("test", 12), new City("test", 11))));

int totalPopulation = states.stream()
        .flatMap(state -> state.getCities().stream())
        .mapToInt(City::getPopulation)
        .sum();
System.out.println("Total population = " + totalPopulation);
```

- 限制和跳过流的元素

```
List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

List<Integer> result =
        ints.stream()
                .skip(2)
                .limit(5)
                .collect(Collectors.toList());

System.out.println("result = " + result);
```

- 连接流

```
List<Integer> list0 = Arrays.asList(1, 2, 3);
List<Integer> list1 = Arrays.asList(4, 5, 6);
List<Integer> list2 = Arrays.asList(7, 8, 9);

// 1st pattern: concat
List<Integer> concat =
        Stream.concat(list0.stream(), list1.stream())
                .collect(Collectors.toList());

// 2nd pattern: flatMap
List<Integer> flatMap =
        Stream.of(list0.stream(), list1.stream(), list2.stream())
                .flatMap(Function.identity())
                .collect(Collectors.toList());

System.out.println("concat  = " + concat);
System.out.println("flatMap = " + flatMap);
```

- 调试流

```
List<String> strings = Arrays.asList("one", "two", "three", "four");
List<String> result =
        strings.stream()
                .peek(s -> System.out.println("Starting with = " + s))
                .filter(s -> s.startsWith("t"))
                .peek(s -> System.out.println("Filtered = " + s))
                .map(String::toUpperCase)
                .peek(s -> System.out.println("Mapped = " + s))
                .collect(Collectors.toList());
System.out.println("result = " + result);
```

#### 创建流

- 创建一个空流 Stream.empty()

接口stream有工厂方法创建空流

```
Stream<String> empty = Stream.empty();
List<String> strings = empty.collect(Collectors.toList());

System.out.println("strings = " + strings);
```

- 参数/数组 Stream.of(...)/Arrays.stream(...)

```
Stream<Integer> intStream = Stream.of(1, 2, 3);
List<Integer> ints = intStream.collect(Collectors.toList());

System.out.println("ints = " + ints);
```

```
String[] stringArray = {"one", "two", "three"};
Stream<String> stringStream = Arrays.stream(stringArray);
List<String> strings = stringStream.collect(Collectors.toList());

System.out.println("strings = " + strings);
```

- Stream.generate(...)

```
Stream<String> generated = Stream.generate(() -> "+");
List<String> strings = 
        generated
           .limit(10L)
           .collect(Collectors.toList());

System.out.println("strings = " + strings);
```

- Stream.iterate()

```
Stream<String> iterated = Stream.iterate("+", s -> s + "+");
iterated.limit(5L).forEach(System.out::println);
```

- 数字创建流 IntStream.range

```
String[] letters = {"A", "B", "C", "D"};
List<String> listLetters =
        IntStream.range(0, 10)
                .mapToObj(index -> letters[index % letters.length])
                .collect(Collectors.toList());
System.out.println("listLetters = " + listLetters);
```

- 随机流 random.ints random.doubles

```
Random random = new Random(314L);
List<String> letters =
        random.doubles(1_000, 0d, 1d)
                .mapToObj(rand ->
                        rand < 0.5 ? "A" : // 50% of A
                                rand < 0.8 ? "B" : // 30% of B
                                        rand < 0.9 ? "C" : // 10% of C
                                                "D")  // 10% of D
                .collect(Collectors.toList());

Map<String, Long> map =
        letters.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

map.forEach((letter, number) -> System.out.println(letter + " :: " + number));
```

- 字符串的字符创建流 sentence.chars()

```
String sentence = "Hello Duke";
List<String> letters =
        sentence.chars()
                .mapToObj(codePoint -> (char)codePoint)
                .map(Object::toString)
                .collect(Collectors.toList());
System.out.println("letters = " + letters);
```

- 文本文件的行创建流 Files.lines

```
Path log = Path.of("/tmp/debug.log"); // adjust to fit your installation
try (Stream<String> lines = Files.lines(log)) {
    
    long warnings = 
        lines.filter(line -> line.contains("WARNING"))
             .count();
    System.out.println("Number of warnings = " + warnings);
    
} catch (IOException e) {
    // do something with the exception
}
```

- 正则表达式创建流 pattern.splitAsStream

```
String sentence = "For there is good news yet to hear and fine things to be seen";

Pattern pattern = Pattern.compile(" ");
Stream<String> stream = pattern.splitAsStream(sentence);
List<String> words = stream.collect(Collectors.toList());

System.out.println("words = " + words);
```

- Builder 创建流

```
Stream.Builder<String> builder = Stream.<String>builder();

builder.add("one")
        .add("two")
        .add("three")
        .add("four");

Stream<String> stream = builder.build();

List<String> list = stream.collect(Collectors.toList());
System.out.println("list = " + list);
```

#### Reducing a Stream

第一种: T identity, BinaryOperator<T> accumulator

```
Stream<Integer> ints = Stream.of(3, 6, 2, 1);

int sum = ints.reduce(0, Integer::sum);
System.out.println("sum = " + sum);
```

第二种: BinaryOperator<T> accumulator

```
Stream<Integer> ints = Stream.of(2, 8, 1, 5, 3);
Optional<Integer> optional = ints.reduce((i1, i2) -> i1 > i2 ? i1: i2);

if (optional.isPresent()) {
    System.out.println("result = " + optional.get());
} else {
    System.out.println("No result could be computed");
}
```

第三种： U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner

```
Stream<String> strings = Stream.of("one", "two", "three", "four");

Function<String, Integer> mapper = String::length;
BinaryOperator<Integer> combiner = Integer::sum;

BiFunction<Integer, String, Integer> accumulator =
        (partialReduction, element) -> partialReduction + mapper.apply(element);

int result = strings.reduce(0, accumulator, combiner);
System.out.println("sum = " + result);
```

#### 在流上添加终端操作

避免使用reduce方法

- count(): 计算数量
- forEach(): 将流的每个元素传递给Consumer接口的一个实例

> 将流处理的所有元素收集到集合中

- collect(Collectors.toList())
- collect(Collectors.toCollection(() -> new ArrayList<>(10_000)))
- collect(Collectors.toUnmodifiableList())): 在不可变列表中收集
- collect(Collectors.toCollection(LinkedList::new)): 在不可变列表中收集
- collect(Collectors.toSet())
- collect(Collectors.toUnmodifiableSet())
- toArray()

> 其他

- max()
- findFirst()
- anyMatch()

#### 使用收集器作为终端操作

- Collectors.counting
- Collectors.joining
- Collectors.partitioningBy
- Collectors.groupingBy
- Collectors.toMap
- Collectors.mapping

#### 创建自己的收集器

- supplier: 创建将收集流元素的容器
- ObjIntConsumer: 累加器
- BiConsumer: 组合器

```
Supplier<List<Integer>> supplier                  = ArrayList::new;
ObjIntConsumer<List<Integer>> accumulator         = Collection::add;
BiConsumer<List<Integer>, List<Integer>> combiner = Collection::addAll;

List<Integer> collect =
        IntStream.range(0, 10)
                .collect(supplier, accumulator, combiner);

System.out.println("collect = " + collect);
```