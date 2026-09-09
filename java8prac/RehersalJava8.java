package java8prac;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RehersalJava8 {

    public static void main(String[] args) {
        List<String> sentences = Arrays.asList(
                "Java is fun",
                "Java 8 introduces Streams",
                "Streams help with functional programming"
        );

        List<String> collect = sentences.stream().flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .distinct().collect(Collectors.toList());
        System.out.println(collect);

       sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" "))) // Splitting sentences into words
                .distinct() // Removing duplicates
                .forEach(e-> System.out.print(" " +e));
        System.out.println("***************************");


        // . Find All Distinct Characters in a List of Words
        List<String> words = Arrays.asList("apple", "banana", "cherry");

        words.stream().flatMap(word-> word.chars().mapToObj(c->(char) c)).distinct().forEach(e-> System.out.print(" " +e));


        String s3="count two count";

        Map<String, Long> collect6 = Arrays.stream(s3.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(collect6);


        String s1="lllootv";

        String key = Arrays.stream(s1.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new
                , Collectors.counting())).entrySet().stream().filter(e -> e.getValue() == 1).findFirst().get().getKey();

//        LinkedHashMap<String, Long> collect2 = Arrays.stream(s1.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new
//                , Collectors.counting()));


        System.out.println(key);

        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(1, "Yanksha", 28, 123, "F", "HR", "Blore", 2020));
        empList.add(new Employee(2, "Francesca", 29, 120, "F", "HR", "Hyderabad", 2015));
        empList.add(new Employee(3, "Ramesh", 30, 115, "M", "HR", "Chennai", 2014));
        empList.add(new Employee(4, "Melanie", 32, 125, "F", "HR", "Chennai", 2013));

        empList.add(new Employee(5, "Padma", 22, 150, "F", "IT", "Noida", 2013));
        empList.add(new Employee(6, "Milad", 27, 140, "M", "IT", "Gurugram", 2017));
        empList.add(new Employee(7, "Uzma", 26, 130, "F", "IT", "Pune", 2016));
        empList.add(new Employee(8, "Ali", 23, 145, "M", "IT", "Trivandam", 2015));
        empList.add(new Employee(9, "Ram", 25, 160, "M", "IT", "Blore", 2010));

        System.out.println("\n--- Oldest Employee per Department ---");
        empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors
                        .minBy(Comparator.comparingInt(Employee::getYearOfJoining))))
                .forEach((d,e) -> e.ifPresent(e1 -> System.out.println(e1.getName() + e1.getYearOfJoining() + e1.getDeptName())));


        // 26. Print Average salary by gender in each department .



        empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.groupingBy(Employee::getGender
                     ,Collectors.averagingDouble(Employee::getSalary)))).entrySet()
                .forEach(o-> System.out.println(o.getKey()+ " " + o.getValue()));




        // 27. To get a list of employees from each department whose
        // salary is greater than the average salary of their department

        Map<String, Double> collect1 = empList.stream().collect(Collectors.groupingBy
                (Employee::getDeptName, Collectors.averagingDouble(Employee::getSalary)));

        Stream<Employee> employeeStream = empList.stream().filter(e -> e.getSalary() > collect1.get(e.getDeptName()));
        employeeStream.collect(Collectors.toList()).forEach(System.out::println);

        Map<String, List<Employee>> collect11 = empList.stream().filter(e -> e.getSalary() > collect1.get(e.getDeptName()))
                .collect(Collectors.groupingBy(Employee::getDeptName));

        System.out.println(collect11);

    }
}
