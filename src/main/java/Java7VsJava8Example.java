import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;



public class Java7VsJava8Example {
    //cosumer example

    static Consumer<Person> c1 = (p) -> System.out.println(p);

    static Consumer<Person> c2 = (p) -> System.out.println(p.getName().toUpperCase());

    static Consumer<Person> c3 = (p) -> System.out.println(p.getHobbies());
    static List<Person> personList = PersonRepository.getAllPersons();

    static void printNameandHobbies() {
        personList.forEach(c1.andThen(c2));
    }

    static void printWithCondition() {
        personList.forEach(p -> { // iterate student
            if(p.getGender().equals("Male") && p.getHeight() >=140) {
                c2.andThen(c3).accept(p);
            }
        });
    }

    //biconsumer example

    static void printPersonDetails() {
        BiConsumer<String, List<String>> personConsumer = (name,hobbies) -> System.out.println(name +" "+hobbies);
        BiConsumer<String, Double> salaryConsumer = (name,salary) -> System.out.println(name +" "+salary);

        List<Person> personList = PersonRepository.getAllPersons();

        //personList.forEach(per -> personConsumer.accept(per.getName(), per.getHobbies()));
        personList.forEach(per -> {
            personConsumer.accept(per.getName(), per.getHobbies());
            salaryConsumer.accept(per.getName(),per.getSalary());
        });
    }


    public static void main(String[] args) {
        //Runable lambda example
        {
            // Before
            Runnable t1 = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread 1");
                }
            };

            new Thread(t1).start();

            // After
            Runnable t2 = () -> {
                System.out.println("Thread 2");
            };
            new Thread(t2).start();

            Runnable t3 = () -> {
                System.out.println("Thread 3");
                System.out.println("Thread 3.1");
            };

            new Thread(t3).start();

            new Thread(new Runnable() {

                @Override
                public void run() {
                    System.out.println("Thread 4");

                }
            }).start();

            new Thread(() -> {
                System.out.println("Thread 5");
                System.out.println("Thread 5.1");
            }).start();
        }
        ////Comparator lambda example
        {
            // Before
            Comparator<Integer> c1 = new Comparator<Integer>() {

                @Override
                public int compare(Integer x, Integer y) {
                    return x.compareTo(y);
                }
            };

            System.out.println("Comparator1 :" + c1.compare(15, 15)); //0

            //After
            Comparator<Integer> c2 = (Integer x, Integer y) -> x.compareTo(y);
            System.out.println("Comparator2 :" + c2.compare(25, 15)); // 1

            Comparator<Integer> c3 = (x, y) -> x.compareTo(y);
            System.out.println("Comparator3 :" + c3.compare(15, 25)); // -1
        }
        {
            List<String> names = Arrays.asList("Sam", "Peter", "Billings", "Sam");

            //Before
            List<String> uniqueList = new ArrayList<>();
            for (String name : names) {
                if (!uniqueList.contains(name)) {
                    uniqueList.add(name);
                }
            }
            System.out.println("Unique List 1 :" + uniqueList);

            //After
            List<String> uniqueValues1 = names.stream()
                    .distinct()
                    .collect(Collectors.toList());
            System.out.println("Unique List 2 :" + uniqueValues1);
        }
        //consumer example
        {


            /*List<Person> personList = PersonRepository.getAllPersons();
            c1.accept(PersonRepository.getPerson());
            c2.accept(PersonRepository.getPerson());
            c3.accept(PersonRepository.getPerson());
            c1.andThen(c2).andThen(c3).accept(PersonRepository.getPerson());
            printNameandHobbies();*/
            printWithCondition();

        }
        //biconsumer example 1
        {
            /* BiConsumer<Integer, Integer> con1 = (a,b) -> System.out.println("Add :"+(a+b));
		con1.accept(10, 20);

		List<Integer> list1 = Arrays.asList(new Integer(10),new Integer(10),new Integer(10));
		List<Integer> list2 = Arrays.asList(new Integer(10),new Integer(10));

		BiConsumer<List<Integer>,List<Integer>> con2 = (l1,l2) -> {
			if(l1.size() == l2.size()) System.out.println("True");
			else System.out.println("False");
		};

		con2.accept(list1, list2);*/

            BiConsumer<Integer, Integer> addConsumer = (a,b) -> System.out.println("Add :"+(a+b));
            BiConsumer<Integer, Integer> subConsumer = (a, b) -> System.out.println("Subs :"+(a-b));
            BiConsumer<Integer, Integer> mulConsumer = (a,b) -> System.out.println("Mul :"+(a*b));
            //addConsumer.accept(10, 20);
            //subConsumer.accept(10, 20);
            //mulConsumer.accept(10, 20);
            addConsumer.andThen(subConsumer).andThen(mulConsumer).accept(10, 20);

        }
        //biconsumer example 2
        {
            printPersonDetails();
        }
    }

}