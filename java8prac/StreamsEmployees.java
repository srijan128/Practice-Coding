package java8prac;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsEmployees {

    public static void main(String[] args) {
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

        //Group the Employees by city.

        Map<String, List<Employee>> collect = empList.stream().collect(Collectors.groupingBy(Employee::getCity));
        System.out.println(collect);

        // Group the Employees by age.
        empList.stream().collect(Collectors.groupingBy(Employee::getAge));

        // Find the count of male and female employees present in the organization.

        empList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));

        // 4. Print the names of all departments in the organization.

        empList.stream().map(s -> s.getDeptName()).distinct().forEach(System.out::println);

        // 5. Print employee details whose age is greater than 28.

        empList.stream().filter(e-> e.getAge()>28).forEach(System.out::println);

        // 6. Find maximum age of employee.
        System.out.println("*******************************");
        Optional<Employee> first = empList.stream().sorted(Collections.reverseOrder(Comparator.comparing(Employee::getAge))).findFirst();
        System.out.println(first.get());

        OptionalInt max = empList.stream().mapToInt(Employee::getAge).max();
        if (max.isPresent())
            System.out.println("Maximum age of Employee: " + max.getAsInt());


        // 7. Print Average age of Male and Female Employees.
        Map<String, Double> collect1 = empList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));

        System.out.println(collect1);

        //  8. Print the number of employees in each department.

        Map<String, Long> collect2 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        System.out.println(collect2);

        // 9. Find oldest employee by age.
        Optional<Integer> first1 = empList.stream().map(e -> e.getAge()).sorted((o1, o2) -> o2 - o1).findFirst();
        System.out.println(first1.get());

        Optional<Employee> oldestEmp = empList.stream().max(Comparator.comparingInt(Employee::getAge));
        Employee oldestEmployee = oldestEmp.get();
        System.out.println("Oldest employee details:: \n" + oldestEmployee);

        // 10. Find longest serving employees in the organization.

        System.out.println(empList.stream().max(Comparator.comparingInt(Employee::getYearOfJoining)).get());

        // 11.Find longest serving employee in each department

        System.out.println("\n--- Oldest Employee per Department ---");
        empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors
                .minBy(Comparator.comparingInt(Employee::getYearOfJoining))))
                .forEach((d,e) -> e.ifPresent(e1 -> System.out.println(e1.getName() + e1.getYearOfJoining() + e1.getDeptName())));

        //12. Find youngest employee in the organisation

        Optional<Employee> first2 = empList.stream().sorted(Comparator.comparingInt(Employee::getAge)).findFirst();
        System.out.println("Find youngest employee in the organisation " + first2.get());

        // 13. Youngest employee per department
        empList.stream().collect(Collectors.
                groupingBy(Employee::getDeptName,Collectors.minBy(Comparator.comparingInt(Employee::getYearOfJoining))))
                .forEach((d,e)-> e.ifPresent(em-> System.out.println(em.getName() + " " + em.getDeptName()+ " " + em.getYearOfJoining())));

        // 14. Find youngest female employee.
        System.out.println("*********************");
        empList.stream().filter(e-> e.getGender().equals("F"))
                .collect(Collectors.minBy(Comparator.comparingInt(Employee::getAge))).
                ifPresent(employee -> System.out.println(employee.getName() + " " + employee.getGender() + " " + employee.getDeptName()));

       // 14. Find employees whose age is greater than 30 and less than 30.
        System.out.println("*********************");
        empList.stream().filter(e->e.getAge()>30 && e.getAge()<30).collect(Collectors.toList()).forEach(e1-> System.out.println(e1));

        System.out.println("Employees whose age is greater than 25 and less than 25");
        Map<Boolean, List<Employee>> collect3 =
                empList.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 25));

        Set<Map.Entry<Boolean, List<Employee>>> entries = collect3.entrySet();

        for(Map.Entry<Boolean, List<Employee>> entry:entries){
            if(entry.getKey().equals(Boolean.TRUE))
                System.out.println("Employees greater than 25 " + entry.getValue());
        }

        // 15. Find the department name which has the highest number of employees.

        Map.Entry<String, Long> stringLongEntry =
                empList.stream().collect(Collectors.groupingBy
                (Employee::getDeptName, Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).get();

        System.out.println(stringLongEntry);


        // 16. Find if there any employees from HR Department.
        empList.stream().filter(e-> e.getDeptName().equals("HR")).findAny();

        // 17. Find the department names that these employees work for,
        // where the number of employees in the department is over 3.

        List<Map.Entry<String, Long>> collect4 = empList.stream().collect(Collectors.
                        groupingBy(Employee::getDeptName, Collectors.counting())).entrySet().stream()
                .filter(o -> o.getValue() > 3).collect(Collectors.toList());

        System.out.println(collect4);

       // 18 . Find distinct department names that employees work for.

        empList.stream().map(e->e.getDeptName()).distinct().forEach(System.out::println);

        //19. Find all employees who lives in ‘Blore’ city,
        // sort them by their name and print the names of employees.

        System.out.println("*************************************");

        empList.stream().filter(e->e.getCity().equals("Blore")).sorted(Comparator.comparing(Employee::getName))
                .forEach(e1-> System.out.println(e1.getName()));

        // 20. No of employees in the organisation.

        Long collect5 = empList.stream().collect(Collectors.counting());
        System.out.println("20. No of employees in the organisation." + collect5);


        // 21. Find employee count in every department

        Map<String, Long> collect6 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        System.out.println(collect6);


        // 22. Find the department which has the highest number of employees.

        Map.Entry<String, Long> stringLongEntry1 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println(stringLongEntry1);

        //23. Sorting a Stream by age and name fields.

        Comparator<Employee> comparing = Comparator.comparing(Employee::getAge);
        Comparator<Employee> comparing1 = Comparator.comparing(Employee::getName);
        empList.stream().sorted(comparing.thenComparing(comparing1)).forEach(System.out::println);

        //24. Print average and total salary of the organization.

        LongSummaryStatistics collect7 = empList.stream().collect(Collectors.summarizingLong(Employee::getSalary));

        System.out.println(collect7.getAverage());
        System.out.println(collect7.getSum());


       // 25. Print Average salary of each department.

        empList.stream().collect(Collectors.groupingBy
                (Employee::getDeptName,Collectors.summarizingLong(Employee::getSalary))).entrySet().stream()
                .forEach(o-> System.out.println(o.getKey() + " " + o.getValue().getAverage()));

        empList.stream().collect(Collectors.groupingBy
                        (Employee::getDeptName,Collectors.averagingDouble(Employee::getSalary))).entrySet().stream()
                .forEach(o-> System.out.println(o.getKey() + " " + o.getValue()));


        // 26. Print Average salary by gender in each department .

        System.out.println("\n--- Average Salary by Gender in Department ---");
        Map<String, Map<String, Double>> avgSalaryByGenderPerDept = empList.stream().collect
                (Collectors.groupingBy(Employee::getDeptName,
                        Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary))));
        avgSalaryByGenderPerDept.forEach((dept, genderAvg) ->
                genderAvg.forEach((gender, avg) -> System.out.println(dept + " - " + gender + ": ₹" + avg)));

        Map<String, Map<String, Double>> collect8 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary))));

        for(Map.Entry<String, Map<String, Double>> deptGenAvg: collect8.entrySet()){
            Map<String, Double> map=deptGenAvg.getValue();
            for(Map.Entry<String,Double> genAvg:map.entrySet()){
                System.out.println(deptGenAvg.getKey() + " " + genAvg.getKey() + " " + genAvg.getValue());
            }
        }

        Map<String, Map<String, Double>> collect9 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.groupingBy(Employee::getGender
                , Collectors.averagingDouble(Employee::getSalary))));

        for(Map.Entry<String, Map<String, Double>> deptGenAvg: collect9.entrySet()){
            Map<String, Double> map=deptGenAvg.getValue();
            for(Map.Entry<String, Double> entry:map.entrySet()){
                System.out.println(deptGenAvg.getKey() + "  " + entry.getKey() + " " + entry.getValue());
            }
        }


        // 27. To get a list of employees from each department whose
        // salary is greater than the average salary of their department
        System.out.println("*****************************************");

        Map<String, Double> collect10 = empList.stream().collect(Collectors.groupingBy
                (Employee::getDeptName, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(collect10);

        System.out.println("*****************************************");

        Map<String, List<Employee>> collect11 = empList.stream().filter(e -> e.getSalary() > collect10.get(e.getDeptName()))
                .collect(Collectors.groupingBy(Employee::getDeptName));

        System.out.println(collect11);

        // 28. Find Highest salary in the organisation.

        System.out.println("*****************************************");

        Optional<Employee> first3 = empList.stream().sorted(Collections.reverseOrder(Comparator.comparingDouble(Employee::getSalary)))
                .collect(Collectors.toList()).stream().findFirst();
        System.out.println(first3.get());
        //.collect(Collectors.toList()).stream().forEach(System.out::println);


        // 29. Find Second Highest salary in the organisation.

        Optional<Employee> max1 = empList.stream().sorted(Collections.reverseOrder(Comparator.comparingDouble(Employee::getSalary))).
                skip(1).max(Comparator.comparingDouble(Employee::getSalary));

        System.out.println(max1.get());

        // 31. Print the top 3 highest salary earned employees

        System.out.println("*****************************************");

        List<Employee> collect12 = empList.stream().sorted(Collections.
                reverseOrder(Comparator.comparingDouble(Employee::getSalary))).limit(3).collect(Collectors.toList());

        System.out.println(collect12);
        System.out.println("*****************************************");
        empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(3).forEach(e -> System.out.println("Top earner: " + e.getName() + " – ₹" + e.getSalary()));


       // 32. Find highest paid salary in the organisation based on gender.

        Map<String, Optional<Employee>> collect13 = empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.maxBy((o1, o2) -> (int) o1.getSalary()
                - (int) o2.getSalary())));
        System.out.println("*****************************************");
        System.out.println(collect13);


        // To get a list of employees from each department
        // whose salary is greater than the average salary of their department

        System.out.println("*****************************************");

        Map<String, Double> collect14 = empList.stream().collect
                (Collectors.groupingBy(Employee::getDeptName, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(collect14);

        System.out.println("*****************************************");

     empList.stream().filter(e -> e.getSalary() > collect14.get(e.getDeptName()))
                        .forEach(o-> System.out.println(o.getName() + " " + o.getDeptName() + " " + o.getSalary()));

       // System.out.println(collect15);

        // count the occurence of each character in a string

        String s="aaaaaabbbbccc";
        Map<String, Long> collect15 = Arrays.stream(s.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(collect15);

        // sort by deptname and Id

        Comparator<Employee> comparator1=Comparator.comparing(Employee::getDeptName);
        Comparator<Employee> comparator2=Comparator.comparing(Employee::getId);
        empList.stream().sorted(comparator1.thenComparing(comparator2)).collect(Collectors.toList());

        System.out.println("*************************");
        // 45. Find the employees whose name start with J.

        List<Employee> j = empList.stream().filter(employee -> employee.getName().startsWith("F")).collect(Collectors.toList());
        System.out.println(j);

        System.out.println("*************************");

        // 44. Find list of employees whose age is less than 30 in Department HR

        List<Employee> hr = empList.stream().filter(employee -> employee.getDeptName().equals("HR") && employee.getAge() < 30).collect(Collectors.toList());
        System.out.println(hr);

        // VVVIMP 42. Sort the employees salary in each department in descending order

        Map<String, Stream<Employee>> sortedEmployeeDesc = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.collectingAndThen(Collectors.toList(),
                        list -> list.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()))));

        sortedEmployeeDesc.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v.collect(Collectors.toList()));
        });

        empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.collectingAndThen(Collectors.toList(),
                        list -> list.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()))));


//                        ,list->
//                        list.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()))));

        System.out.println("*************************");

        // Print the no of Male and Female in each department

        Map<String, Map<String, Long>> collect16 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName
                , Collectors.groupingBy(Employee::getGender, Collectors.counting())));
        collect16.forEach((dept, genderCnt) -> System.out.println(dept + " -> " + genderCnt));


//        System.out.println(collect16);

        System.out.println("*************************");
        // 41. Sort the employees salary in each department in ascending order

        Map<String, Stream<Employee>> collect17 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.collectingAndThen(Collectors.toList(),
                list -> list.stream().sorted(Comparator.comparingDouble(Employee::getSalary)))));
        collect17.forEach((k,v) -> {
            System.out.println(k);
            System.out.println(v.collect(Collectors.toList()));
        });
       // System.out.println(collect17);

        // max age of the employee
        System.out.println("*************************");
        Employee employee = empList.stream().collect(Collectors.maxBy(Comparator.comparingInt(Employee::getAge))).get();
        System.out.println(employee.getAge());


        // Print Average age of Male and Female Employees.

        Map<String, Double> collect18 = empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getAge)));

        System.out.println(collect18);

        // print the no of employees in each department

        Map<String, Long> collect19 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        System.out.println(collect19);


       // 9. Find oldest employee by age.

        Employee employee1 = empList.stream().collect(Collectors.maxBy(Comparator.comparingInt(Employee::getAge))).get();
        System.out.println(employee1);

        // 10. Find longest serving employees in the organization.
        Employee employee2 = empList.stream().collect(Collectors.minBy(Comparator.comparingInt(Employee::getYearOfJoining))).get();
        System.out.println(employee2);

        // 11.Find longest serving employee in each department

        System.out.println("*************************");

        Map<String, Optional<Employee>> collect20 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.minBy
                (Comparator.comparingInt(Employee::getYearOfJoining))));

        collect20.forEach((k,v) -> {
            System.out.println(k);
            System.out.println(v.get());
        });

        System.out.println("*************************");
        // 12. Find youngest employee in the organisation
        Optional<Employee> min = empList.stream().min(Comparator.comparingInt(Employee::getAge));
        System.out.println(min.get());

        System.out.println("*************************");

        //13. Find youngest female employee.

        Employee f = empList.stream().filter(e -> e.getGender().equals("F")).min(Comparator.comparingInt(Employee::getAge)).get();

        System.out.println(f);

        System.out.println("*************************");


       //VVVIMP 14. Find employees whose age is greater than 30 and less than 30.

        List<Employee> collect21 = empList.stream().filter(e -> e.getAge() > 30 || e.getAge() < 30).collect(Collectors.toList());
        System.out.println(collect21);

        System.out.println("*************************");
        // VVVIMP 15. Find the department name which has the highest number of employees.

        Map.Entry<String, Long> stringLongEntry2 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting())).
                entrySet().stream().max(Map.Entry.comparingByValue()).get();
        //.stream().map(o -> o.getValue()).sorted((o1, o2) -> o1.intValue() - o2.intValue());
       // List<Long> collect22 = sorted.collect(Collectors.toList());

        System.out.println(stringLongEntry2);

        System.out.println("*************************");

        // 16. Find if there any employees from HR Department.

        System.out.println(empList.stream().filter(e->e.getDeptName().equals("HR")).findAny().get());


        //17. Find the department names that these employees work for,
        // where the number of employees in the department is over 3.


        System.out.println("*************************");

        empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.counting())).entrySet()
                .stream().filter(o->o.getValue()>3).forEach(o1-> System.out.println(o1.getKey()));

        //18 . Find distinct department names that employees work for.

        System.out.println("*************************");

        System.out.println(empList.stream().map(e->e.getDeptName()).distinct().collect(Collectors.toList()));

        // 19. Find all employees who lives in ‘Blore’ city, sort them by their name and print the names of employees.

        System.out.println("*************************");

        List<String> blore = empList.stream().filter(e -> e.getCity().equals("Blore"))
                .sorted((e1, e2) -> e1.getName().compareTo(e2.getName())).map(e -> e.getName()).collect(Collectors.toList());

        System.out.println(blore);

        // 20. No of employees in the organisation.

        System.out.println("*************************");

        long count = empList.stream().count();
        System.out.println(count);

        System.out.println("*************************");

        //21. Find employee count in every department

        empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.counting())).entrySet()
                .forEach(System.out::println);

        // 22. Find the department which has the highest number of employees.

        System.out.println("*************************");

        Map.Entry<String, Long> stringLongEntry3 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()
        )).entrySet().stream().max((o1, o2) -> o1.getValue().intValue() - o2.getValue().intValue()).get();

        System.out.println(stringLongEntry3);


        // 23. Sorting a Stream by age and name fields.

        System.out.println("*************************");

        Comparator<Employee> comparator=Comparator.comparingInt(Employee::getAge);
        Comparator<Employee> comparator8=Comparator.comparing(Employee::getName);
        empList.stream().sorted(comparator.thenComparing(comparator8)).collect(Collectors.toList());


        // 24. Print average and total salary of the organization.
        System.out.println("*************************");

        LongSummaryStatistics collect22 = empList.stream().collect(Collectors.summarizingLong(Employee::getSalary));
        System.out.println(collect22.getSum());
        System.out.println(collect22.getAverage());


        // 25. Print Average salary of each department.
        System.out.println("*************************");

        empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.averagingDouble(Employee::getSalary)));

        // 26. Print Average salary by gender in each department .

        System.out.println("*************************");

        Map<String, Map<String, Double>> collect23 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary))));

        System.out.println(collect23);

        System.out.println("*************************");

        //27. To get a list of employees from each department whose salary is greater
        // than the average salary of their department

        Map<String, Double> collect24 = empList.stream().collect(Collectors.
                groupingBy(Employee::getDeptName, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(collect24);

        Map<String, List<Employee>> collect25 = empList.stream().filter(e -> e.getSalary() > collect24.get(e.getDeptName()))
                .collect(Collectors.groupingBy(Employee::getDeptName));

        System.out.println(collect25);

        System.out.println("*************************");

        Map<String, Double> collect26 = empList.stream().collect(Collectors.
                groupingBy(Employee::getDeptName, Collectors.averagingDouble(Employee::getSalary)));

        System.out.println(collect26);

        empList.stream().filter(e->e.getSalary()>collect26.get(e.getDeptName()))
                .collect(Collectors.groupingBy(Employee::getDeptName))
                .forEach((k,v)-> {
                    System.out.println("dept Name " + k);
                    v.forEach(e1-> System.out.println(e1.getName() + " " + e1.getSalary()));
                });

        System.out.println("*************************");


        // 28. Find Highest salary in the organisation.

        Employee employee3 = empList.stream().max(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println(employee3.getName() + " " + employee3.getSalary());

        // 29. Find Second Highest salary in the organisation.

        System.out.println("*************************");

        Optional<Employee> first4 = empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(1).findFirst();

        System.out.println(first4.get());

        //31. Print the top 3 highest salary earned employees


        System.out.println("*************************");

        empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(3)
                .forEach(e-> System.out.println(e.getName() + " " + e.getSalary()));

        //32. Find highest paid salary in the organisation based on gender.

        System.out.println("*************************");

        Map<String, Optional<Employee>> collect27 = empList.stream().collect(Collectors.groupingBy
                (Employee::getGender, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));

        collect27.forEach((k,v)-> {
            System.out.println("gender " + k);
            System.out.println(v.get().getName() + " " + v.get().getSalary());
        });

        // 33. Find lowest paid salary in the organisation

        Employee employee4 = empList.stream().min(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println(employee4);

        // 34. Find lowest paid salary in the organisation based on the gender.
        System.out.println("*************************");

        empList.stream().collect(Collectors.groupingBy(Employee::getGender,
                Collectors.minBy(Comparator.comparingDouble(Employee::getSalary))))
                .forEach((k,v)-> {
                    System.out.println("gender " + k);
                    System.out.println(v.get().getName()+ " " + v.get().getSalary());
                });

        // 35. Sort the employees salary in the organisation in ascending order

        System.out.println("*************************");

        Stream<Employee> sorted = empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary));
        sorted.forEach(s1-> System.out.println(s1.getName()+ " " + s1.getSalary()));

        // 36. Sort the employees salary in the organisation in descending order.

        System.out.println("*************************");

        Stream<Employee> sorted1 = empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed());
        sorted1.forEach(s1-> System.out.println(s1.getName()+ " " + s1.getSalary()));


        // 37. Highest salary based on department.

        System.out.println("*************************");

        Map<String, Optional<Employee>> collect28 = empList.stream().collect(Collectors.
                groupingBy(Employee::getDeptName, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));

        collect28.entrySet().stream().forEach(o -> {
            System.out.println(o.getKey());
            System.out.println(o.getValue().get());
        });


        // 40. List of employee’s second highest record based on department
        empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.collectingAndThen(Collectors.toList(),
                        list-> list.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()
                                ).skip(1).findFirst())));

        // 42. Sort the employees salary in each department in descending order

 empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.collectingAndThen(Collectors.toList(),
                       list-> list.stream().sorted(Comparator.comparingDouble(Employee::getSalary)))));




    }
}
