package ru.netology;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //Creating an array
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

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
        List<String> listOfHighlyQualifiedEmployeesMan = persons.stream()
                .filter(x -> x.getAge() > 18 && x.getAge() < 65 && x.getSex().equals(Sex.MAN) && x.getEducation().equals(Education.HIGHER))
                .map(x -> x.getFamily())
                .collect(Collectors.toList());

        List<String> listOfHighlyQualifiedEmployeesWoman = persons.stream()
                .filter(x -> x.getAge() > 18 && x.getAge() < 60 && x.getSex().equals(Sex.WOMAN) && x.getEducation().equals(Education.HIGHER))
                .map(x -> x.getFamily())
                .collect(Collectors.toList());

        listOfHighlyQualifiedEmployeesMan.addAll(listOfHighlyQualifiedEmployeesWoman);
        List<String> listOfHighlyQualifiedEmployees = listOfHighlyQualifiedEmployeesMan.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("List of highly qualified employees: " + listOfHighlyQualifiedEmployees);
    }




}
