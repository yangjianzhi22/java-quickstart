## 集合框架 collections

Collections Framework是JDK 中使用最广泛的 API, 无论您正在处理的应用程序是什么，您都可能需要在某个时候在内存中存储和处理数据

由于集合是一个对象，并且鉴于对象是可扩展的，因此您可以在 JDK 提供的大多数集合上添加您需要的任何操作。使用数组是不可能做到这一点的，因为数组不是 Java 中的对象

- 层次结果

![1](/docs/1.png)

#### api接口

> 处理单个元素

- add(element): 在集合中添加一个元素
- remove(element)：从集合中删除给定的元素

> 其他集合

- containsAll(): 定义包含
- addAll(): 定义联合
- removeAll(): 定义补码
- retainAll(): 定义交集

> 集合本身

- size(): 以 . 形式返回集合中的元素数int
- isEmpty(): 告诉你给定的集合是否为空

> 获取数组Array[]

- toArray(T[] tab)返回一个数组或T：T[]
- toArray(IntFunction<T[]> generator), 返回相同的类型，但语法不同

> 使用谓词过滤出集合的元素

```
Predicate<String> isNull = Objects::isNull;
Predicate<String> isEmpty = String::isEmpty;
Predicate<String> isNullOrEmpty = isNull.or(isEmpty);

Collection<String> strings = new ArrayList<>();
strings.add(null);
strings.add("");
strings.add("one");
strings.add("two");
strings.add("");
strings.add("three");
strings.add(null);

System.out.println("strings = " + strings);
strings.removeIf(isNullOrEmpty);
System.out.println("filtered strings = " + strings);
```

#### 迭代元素

- for-each

```
Collection<String> strings = List.of("one", "two", "three");

for (String element: strings) {
    System.out.println(string);
}
```

- 迭代器

```
Collection<String> strings = List.of("one", "two", "three", "four");
for (Iterator<String> iterator = strings.iterator(); iterator.hasNext();) {
    String element = iterator.next();
    if (element.length() == 3) {
        System.out.println(element);
    }
}
```

#### 用列表List扩展集合

- add(index, element): 在 处插入给定对象，index如果有剩余元素则调整索引
- get(index)：返回给定的对象index
- set(index, element): 用新元素替换给定索引处的元素
- remove(index): 删除给定的元素index，调整剩余元素的索引
- indexOf(element) lastIndexOf(element): 返回列表中给定元素的索引
- subList(start, end): 返回一个由索引start和之间的元素组成的列表end - 1
- addAll(int index, Collection collection): 在给定索引处插入集合
- sort(): 对列表的元素进行排序

> 迭代列表的元素

- hasPrevious() previous(): 以降序而不是升序进行迭代
- nextIndex(): 获取下一次调用或下一次调用将previousIndex()返回的元素的索引next()previous()
- set(element): 更新next()or返回的最后一个元素previous()。如果没有在这个迭代器上调用这些方法，IllegalStateException则引发

```
List<String> numbers = Arrays.asList("one", "two", "three");
for (ListIterator<String> iterator = numbers.listIterator(); iterator.hasNext();) {
    String nextElement = iterator.next();
    if (Objects.equals(nextElement, "two")) {
        iterator.set("2");
    }
}
System.out.println("numbers = " + numbers);

# 结果
numbers = [one, 2, three]
```

#### 使用 Set、SortedSet 和 NavigableSet 扩展集合

set: 禁止重复元素

```
List<String> strings = Arrays.asList("one", "two", "three", "four", "five", "six");
Set<String> set = new HashSet<>(strings);
set.forEach(System.out::println);
```

>  SortedSet 扩展集合

- first() last(): 返回集合的最小和最大元素
- headSet(toElement) tailSet(fromElement): 返回包含小于toElement或大于的元素的子集fromElement
- subSet(fromElement, toElement): fromElement为您提供和之间的元素的子集toElement

```
SortedSet<String> strings = new TreeSet<>(Set.of("a", "b", "c", "d", "e", "f"));
SortedSet<String> subSet = strings.subSet("aa", "d");
System.out.println("sub set = " + subSet);
```

> NavigableSet 扩展 SortedSet

- headSet(): 并且headSet()可以采用进一步的boolean参数来指定限制 (toElement或fromElement) 是否将包含在结果子集中
- ceiling(element)，floor(element): 返回小于或等于的最大元素，或大于或等于提供的最小元素element。如果没有这样的元素，则null返回
- floor(element)，higher(element): 返回小于或大于提供的最小元素的较大元素element。如果没有这样的元素，则null返回
- pollFirst(), pollLast(): 返回并删除集合中最小或最大的元素
- descendingIterator(): 它为您提供一个Iterator按降序遍历集合的正则
- descendingSet(): 你得到的回报是另一个NavigableSet关于这个集合的视图，它让你认为你有相同的集合，以相反的顺序排序

```
NavigableSet<String> sortedStrings = new TreeSet<>(Set.of("a", "b", "c", "d", "e", "f"));
System.out.println("sorted strings = " + sortedStrings);
NavigableSet<String> reversedStrings = sortedStrings.descendingSet();
System.out.println("reversed strings = " + reversedStrings);
```

#### 使用集合工厂方法创建和处理数据

> Arrays集合工厂

- asList()

> Collections集合工厂

- sort(): 对列表进行适当的排序
- shuffle(): 随机打乱提供列表的元素
- rotate(): 旋转列表的元素

#### 在堆栈和队列中存储元素

堆栈和队列结构是计算中的经典数据结构。堆栈也称为 LIFO 堆栈，其中 LIFO 代表后进先出。队列被称为 FIFO：先进先出。

- push(element): 将元素添加到队列或堆栈中
- pop()：从堆栈中移除一个元素，即添加的最年轻的元素
- poll()：从队列中移除一个元素，即最旧的元素加入
- peek()：允许您查看将通过pop()或poll()获得的元素，但无需将其从堆栈队列中删除

#### 使用Map存储键值对

- getKey(): 读取密钥
- getValue() setValue(value): 读取和更新绑定到该键的值

- put(key, value): 简单地添加键/值
- putIfAbsent: 向映射添加键/值对，前提是键不存在且未关联到空值
- get(key): 获得绑定到给定键的值
- getOrDefault(): 如果键不在映射中，则返回默认值
- remove(key): 删除键/值对
- remove(key, value): 当它完全匹配映射中的键/值对时，才会删除键/值对

- containsKey(key)和containsValue(value): 检查给定键或给定值是否存在
- isEmpty(): 空映射返回true
- size(): 返回键/值对的数量
- clear(): 删除映射的所有内容
- keySet(): 返回 的实例Set，包含映射中定义的键
- entrySet(): 返回 的实例Set<Map.Entry>，包含映射中包含的键/值对
- values(): 返回一个 的实例Collection，包含地图中存在的值

#### 使用 Lambda 表达式处理 Map

- 获取

```
Map<Integer, String> map = new HashMap<>();
map.put(1, "one");
map.put(2, "two");
map.put(3, "three");

map.forEach((key, value) -> System.out.println(key + " :: " + value));
```

- 替换

```
Map<Integer, String> map = new HashMap<>();

map.put(1, "one");
map.put(2, "two");
map.put(3, "three");

map.replaceAll((key, value) -> value.toUpperCase());
map.forEach((key, value) -> System.out.println(key + " :: " + value));
```

- 计算

```
List<String> strings = Arrays.asList("one", "two", "three", "four", "five", "six", "seven");
Map<Integer, List<String>> map = new HashMap<>();
for (String word: strings) {
    int length = word.length();
    map.computeIfAbsent(length, key -> new ArrayList<>())
            .add(word);
}

map.forEach((key, value) -> System.out.println(key + " :: " + value));
```

#### 使用 SortedMap 和 NavigableMap 保持键排序

JDK 提供了两个接口扩展Map：SortedMap和NavigableMap

NavigableMap是 的延伸SortedMap

- firstKey() lastKey()：返回地图的最低和最大键
- headMap(toKey)(fromKey)：返回  SortedMap，其键严格小于toKey、大于或等于fromKey
- subMap(fromKey, toKey): 返回 SortedMap，其键严格小于toKey、大于或等于fromKey

```
SortedMap<Integer, String> map = new TreeMap<>();
map.put(1, "one");
map.put(2, "two");
map.put(3, "three");
map.put(5, "five");
map.put(6, "six");

SortedMap<Integer, String> headMap = map.headMap(3);
headMap.put(0, "zero"); // this line is ok
headMap.put(4, "four"); // this line throws an IllegalArgumentException
```