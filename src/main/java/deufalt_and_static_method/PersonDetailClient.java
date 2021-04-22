package deufalt_and_static_method;

import deufalt_methods.PersonDetailImpl;
import deufalt_methods.PersonDetails;
import repo.Person;
import repo.PersonRepository;

import java.util.List;

public class PersonDetailClient {

    static List<Person> listOfPersons = PersonRepository.getAllPersons();


    public static void main(String[] args) {
        PersonDetails pd = new PersonDetailImpl();
        System.out.println("Total Salary :" + pd.calculateTotalSalary(listOfPersons));
        System.out.println("Total Kids Count :" + pd.totalKids(listOfPersons));
        PersonDetails.personNames(listOfPersons).forEach(System.out::println);
        PersonDetailImpl.personNames(listOfPersons).forEach(System.out::println);
    }

}