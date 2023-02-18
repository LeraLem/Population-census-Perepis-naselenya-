package ru.netology;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //Creating an array
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        System.out.println(persons.toString());

        //number of minors
        long numberOfMinorsStream = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Number of minors: " + numberOfMinorsStream);

        //list of conscripts
        List<String> listOfConscriptsStream = persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getAge() < 27)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
        System.out.println("List of conscripts: " + listOfConscriptsStream);

        //List of highly qualified employees
        List<String> listOfHighlyQualifiedEmployees = persons.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> x.getAge() > 18 )
                .filter(x -> x.getAge() < 65)
                .filter(x -> x.getSex() == Sex.MAN ? x.getAge() < 65 :  x.getAge() < 60)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
        System.out.println("List of highly qualified employees: " + listOfHighlyQualifiedEmployees);
    }




}
