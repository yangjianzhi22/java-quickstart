
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {

    public static void main(String[] args) {
        SortedMap<Integer, String> map = new TreeMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(5, "five");
        map.put(6, "six");

        SortedMap<Integer, String> headMap = map.headMap(3);
        headMap.put(0, "zero"); // this line is ok

        System.out.println(headMap);
        System.out.println(map);
    }
}