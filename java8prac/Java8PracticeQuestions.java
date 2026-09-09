package java8prac;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.reverseOrder;


public class Java8PracticeQuestions {

    public static void main(String[] args) {
        Map<String,Integer> map=new HashMap<>();
        map.put("John",25);
        map.put("Alice",30);
        map.put("Bob",20);
        map.put("Eve",35);
       // map.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).forEach(System.out::println);
        // sort map based on value
        Map<String,Integer> sortedMap=map.entrySet().stream().sorted((entry1,entry2)-> entry1.getValue().compareTo(entry2.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,
                (o,n)->o, LinkedHashMap::new
                ));

        sortedMap.forEach((key,value) -> System.out.println(key + " : " + value));
        System.out.println("***********************************");

        // sort map based on key
        Map<String,Integer> sortedMap1=map.entrySet().stream().sorted((entry1,entry2)-> entry1.getKey().compareTo(entry2.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,
                        (o,n)->o, LinkedHashMap::new
                ));

        sortedMap1.forEach((key,value) -> System.out.println(key + " : " + value));

        // Tradtional Approach
        System.out.println("Traditional Approach");
        List<Map.Entry<String,Integer>> entries=new ArrayList<>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        for(Map.Entry<String,Integer> entry:entries){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // split fn converts a string to an array

        // 1.count the occurrence of each character in a string
        String input="ilovejavatechie";
        Map<String, Long> collect = Arrays.stream(input.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);


        // 2. Java program to find duplicate elements in a given string
        List<String> collect1 = Arrays.stream(input.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(x -> x.getValue() > 1)
                .map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println(collect1);

        // re 2
        Arrays.stream(input.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream()
                .filter(x->x.getValue()>1).map(Map.Entry::getKey).collect(Collectors.toList());

        // 3. Java program to find first non-repeating element in a given string
        // while grouping by uses hashmap so the elements are stored in random order and we get any element whose frequency is 1. to preserve the insertion order we need to pass linked hashmap
        //just below the line of code
        Arrays.stream(input.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream().filter(x->x.getValue()==1).findFirst().get().getKey();
        Arrays.stream(input.split("")).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting())).entrySet().stream().filter(x->x.getValue()==1).findFirst().get().getKey();


        // 4. Second highest number in a given array

            int [] a={5,9,11,2,8,21,1};
        Integer secondHighest = Arrays.stream(a).boxed().sorted(reverseOrder()).skip(1).findFirst().get();
        System.out.println(secondHighest);

        // 5. largest string in a given array

        String [] arr={"abc","bcd","amazon"};
        Arrays.stream(arr).reduce((word1,word2)-> word1.length()>word2.length()?word1:word2).get();
        String string = Arrays.stream(arr).max(Comparator.comparing(String::length)).get();
        System.out.println("String " + string);

        // String join

        // 2nd Highest Salary

        Map<String,Integer> map1=new HashMap<>();
        map1.put("anil",1000);
        map1.put("bhavna",1300);
        map1.put("micheal",1500);
        map1.put("tom",1600);
        map1.put("ankit",1200);
        map1.put("daniel",1700);
        map1.put("james",1400);

        Map<String,Integer> map2=new HashMap<>();
        map2.put("anil",1000);
        map2.put("ankit",1200);
        map2.put("bhavna",1200);
        map2.put("james",1200);
        map2.put("micheal",1000);
        map2.put("tom",1300);
        map2.put("daniel",1300);


      /*  Map.Entry<String, Integer> entry = map1.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList()).get(1);
        System.out.println(entry);
        //.findFirst().get();
        System.out.println(getNthHighestSalary(2,map1)); */

       // System.out.println(getNthHighestSalary(2,map2));

        Map<Integer, List<String>> collect2 = map2.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue
                , Collectors.mapping(Map.Entry::getKey, Collectors.toList())
        ));
        System.out.println(collect2);
        //System.out.println(collect2);

        Map.Entry<Integer, List<String>> integerListEntry = map2.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue
                        , Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                ))
                .entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors.toList()).get(1);
        System.out.println(integerListEntry);

        List<Map.Entry<Integer, List<String>>> collect3 = map2.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue
                        , Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                ))
                .entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors.toList());
        System.out.println(collect3);

        //.collect(Collectors.toList()).get(1);

        String s="aaabbcc";

        Map<String, Long> collect4 = Arrays.stream(s.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect4);


        List<String> sentences = Arrays.asList(
                "Java is fun",
                "Java 8 introduces Streams",
                "Streams help with functional programming"
        );

        List<String> uniqueWords = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" "))) // Splitting sentences into words
                //.distinct() // Removing duplicates
                .collect(Collectors.toList());



        sentences.stream().flatMap(sentence -> Arrays.stream(sentence.split(" "))).distinct().collect(Collectors.toList());

        System.out.println(uniqueWords);
        // . Find All Distinct Characters in a List of Words
        List<String> words = Arrays.asList("apple", "banana", "cherry");

       // words.stream().flatMap(word-> word.chars().mapToObj(c->(char) c)).distinct().collect(Collectors.toList());
        words.stream().flatMap(word-> word.chars().mapToObj(c->(char) c)).distinct().collect(Collectors.toList());

        // 4. Flatten a List of Optional Values Using flatMap()


        List<Optional<String>> optionalList = Arrays.asList(
                Optional.of("Java"),
                Optional.of("Stream"),
                Optional.empty(),
                Optional.of("FlatMap")
        );

        List<String> collect5 = optionalList.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
        System.out.println(collect5);


        String s3="count two count";

        Map<String, Long> collect6 = Arrays.stream(s3.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(collect6);


        List<String> stringList=new ArrayList<>();
        stringList.add("Apple");
        stringList.add("Banana");
        stringList.add("Apple");
        stringList.add("Mango");

        // distinct chars in a list of string

        stringList.stream().flatMap(word->word.chars().mapToObj(c->(char)c)).distinct()
                .forEach(System.out::println);

        Map<String, Long> collect30 = stringList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        collect.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(o-> System.out.println(o.getKey() + " " + o.getValue()));

        System.out.println(collect30);

        String s1="lllootv";
        Set<String> characterSet=new LinkedHashSet<>();
        Set<String> collect7 = Arrays.stream(s1.split("")).filter(e -> !characterSet.add(e)).collect(Collectors.toSet());

        //.collect(Collectors.toSet());
        System.out.println("*************************************");
        System.out.println(characterSet);

        // First non repeating character in a string
        Character c1 = s1.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,
                        Collectors.counting())).entrySet().stream().filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey).findFirst().orElse(null);

        System.out.println(c1);

        // sort a map using comparator
        Map<String,Integer> map3=new HashMap<>();
        map.put("John",25);
        map.put("Alice",30);
        map.put("Bob",20);
        map.put("Eve",35);

        LinkedHashMap<String, Integer> collect8 = map1.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).collect(Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue, (o, n) -> o, LinkedHashMap::new));

        collect8.forEach((k,v)-> System.out.println(k + " " + v));


    }

    public static Map.Entry<String,Integer> getNthHighestSalary(int num,Map<String,Integer> map){
        return map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList()).get(num-1);


    }

    public static int secondHighestArrayElement(int [] a){
        int res=-1,largest=0;
        for(int i=1;i<a.length;i++){
            if(a[i]>a[largest]){
                res=largest;
                largest=i;
            }else if(a[i] != a[largest]){
                if(res==-1 || a[i]>a[res])
                    res=i;
            }
        }
        return res;
    }
}
