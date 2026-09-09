package epamquestions;

import abstractclass.A;

import java.util.*;
import java.util.stream.Collectors;

public class Prac {

    public static void print(String s){
        Map<String,Integer> map=new HashMap<>();
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<s.length();j++){
                if(s.charAt(j)!=s.charAt(i) || j==s.length()-1){
                    StringBuilder sb=new StringBuilder();
                    String s1;
                    if(j!=s.length()-1)
                       s1= sb.append(s.substring(i,j)).toString();
                    else
                       s1= sb.append(s.substring(i,j+1)).toString();
                    if(map.containsKey(s1)) {
                        map.put(s1,map.get(s1)+1);
                    }else{
                        map.put(s1, map.getOrDefault(s1, 0) + 1);
                    }
                    i=j;
                }
            }
        }
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }




    public static void main(String[] args) {
        String s="XXXZZFFYYZZTTXXZZXX";
        //String s="FFXXXFF";
       // print(s);
        Employee e1=new Employee("Ram","IT",100.00);
        Employee e2=new Employee("Bam","IT",200.00);
        Employee e3=new Employee("Ram","HR",150.00);
        Employee e4=new Employee("Sam","HR",300.00);

        List<Employee> employeeList=new ArrayList<>();
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        employeeList.add(e4);

        Map<String, Double> collect = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getName, Collectors.summingDouble(Employee::getSalary)
                        ));


        List<Map.Entry<String, Double>> collect1 = collect.entrySet().stream().sorted(((o1, o2) -> (int) (o2.getValue() - o1.getValue())))
                .collect(Collectors.toList());
         System.out.println(collect1);

         List<Integer> list=List.of(1,2,3,4,5);
        Integer i = list.stream().reduce((a, b) -> a + b).get();
        System.out.println(i);
    }
}
