package optional;

import repo.Person;
import repo.PersonRepository;

import java.util.Optional;

public class OptionalIfElseExample {

    public static void main(String[] args) {
        //	orElse();
        //orElseGet();
        orElseThrow();
    }

    static void orElse() {
        Optional<Person> person = PersonRepository.getPersonOptional();
        //String name = person.isPresent() ? person.get().getName() : "Data Not Found";
        String name = person.map(Person :: getName).orElse("Data Not Found");
        System.out.println("OrElse Person Name :"+name);
    }

    static void orElseGet() {
        Optional<Person> person = PersonRepository.getPersonOptional();
        String name = person.map(Person :: getName).orElseGet(()-> {
            // to send a notification mail
            // Initiate any script
            return "Data Not Found";
        });
        System.out.println("OrElseGet Person Name :"+name);
    }

    static void orElseThrow() {
        Optional<Person> person = PersonRepository.getPersonOptional();
        //String name = person.isPresent() ? person.get().getName() : "Data Not Found";
        String name = person.map(Person :: getName).orElseThrow(()-> new RuntimeException("Name Not Found"));
        System.out.println("OrElse Person Name :"+name);
    }

}