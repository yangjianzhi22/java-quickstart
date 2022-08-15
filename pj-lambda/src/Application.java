package src;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class Application {

    public static void main(String[] args) {
        // lambda表达式
        BiFunction<String,String,Integer> findWordInSentence = (word, sentence) -> sentence.indexOf(word);

        // 方法引用
        Consumer<Object> printer = System.out::println;
        printer.accept("方法引用: Consumer<Object> printer = System.out::println");
        printer.accept("--:" + findWordInSentence.apply("j", "yjz"));

        // 静态方法引用
        IntBinaryOperator max = Integer::max;
        printer.accept("静态方法引用: IntBinaryOperator max = Integer::max");
        printer.accept("--:" + max.applyAsInt(3,4));

        // 未绑定的方法引用
        BiFunction<String, String, Integer> indexOf = String::indexOf;
        printer.accept("未绑定的方法引用: BiFunction<String, String, Integer> indexOf = String::indexOf");
        printer.accept("--:" +indexOf.apply("j", "yjz"));

        // 绑定方法引用
        Function<User, String> getName = User::getName;
        printer.accept("绑定方法引用: Function<User, String> getName = User::getName");
        printer.accept("--:" + getName.apply(new User("yang")));

        // 编写构造方法引用
        Supplier<List<String>> newListOfStrings = ArrayList<String>::new;
        printer.accept("编写构造方法引用: Supplier<List<String>> newListOfStrings = ArrayList<String>::new");
        printer.accept(newListOfStrings.get());
    }
}