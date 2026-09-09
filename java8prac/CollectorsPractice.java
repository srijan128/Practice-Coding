package java8prac;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsPractice {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("one","two","three","four","five","six","seven","ten","twenty");
        Map<Integer, String> collect = list.stream().filter(e -> e.length() > 3).collect(Collectors.toMap(String::length, e -> e
        ,(old,neww)-> old + ":" + neww));
        System.out.println(collect);
        HashSet<String> collect1 = list.stream().collect(Collectors.toCollection(HashSet::new));
        TreeSet<String> collect2 = list.stream().collect(Collectors.toCollection(()->new TreeSet<>()));
        System.out.println(collect2);
        Long collect3 = list.stream().collect(Collectors.counting());
        System.out.println(collect3);

        // joining method joins or concatenates all the elements in the stream
        String collect4 = list.stream().collect(Collectors.joining());
        System.out.println(collect4);

        String collect5 = list.stream().collect(Collectors.joining(","));
        System.out.println(collect5);

        String collect6 = list.stream().collect(Collectors.joining(",","{","}"));
        System.out.println(collect6);

        Map<Boolean, List<String>> collect7 = list.stream().collect(Collectors.partitioningBy(s -> s.length() > 3));
        System.out.println(collect7);

        Map<Boolean, Long> collect8 = list.stream().collect(Collectors.partitioningBy(s -> s.length() > 3,
                Collectors.counting()));
        System.out.println(collect8);

        Map<Integer, List<String>> collect9 = list.stream().collect(Collectors.groupingBy(s -> s.length()));
        System.out.println(collect9);

        Map<Integer, String> collect10 = list.stream().collect(Collectors.groupingBy(s -> s.length(),Collectors.joining("=>")));
        System.out.println(collect10);

        int [] a={1,2,3,4,5,6};
        IntSummaryStatistics collect11 = Arrays.stream(a).boxed().collect(Collectors.summarizingInt(n -> n * 2));
        System.out.println(collect11.getMax());
        System.out.println(collect11.getSum());

        Optional<Integer> collect12 = Arrays.stream(a).boxed().collect(Collectors.maxBy(Comparator.comparingInt(n -> n)));
        System.out.println(collect12.get());


        String text = "This is a sample string with some words repeated words";

        Map<String, Long> wordCounts = Arrays.stream(text.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        wordCounts.forEach((word, count) -> System.out.println(word + ": " + count));
        System.out.println("check");


    }
}
