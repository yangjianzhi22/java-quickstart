import java.util.*;
import java.util.function.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Collection<String> strings =
                Arrays.asList("two", "three", "four", "five", "six", "seven", "eight", "nine",
                        "ten", "eleven", "twelve");

        Function<Map<Integer, Long>, Map.Entry<Integer, Long>> finisher =
                map -> map.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .get();

        Map.Entry<Integer, Long> maxValue =
                strings.stream()
                        .collect(
                                Collectors.collectingAndThen(
                                        Collectors.groupingBy(
                                                String::length,
                                                Collectors.counting()),
                                        finisher
                                ));

        System.out.println("maxValue = " + maxValue);
    }
}