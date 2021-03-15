import java.util.*;
import java.util.function.*;
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
            if (p.getGender().equals("Male") && p.getHeight() >= 140) {
                c2.andThen(c3).accept(p);
            }
        });
    }

    //biconsumer example

    static void printPersonDetails() {
        BiConsumer<String, List<String>> personConsumer = (name, hobbies) -> System.out.println(name + " " + hobbies);
        BiConsumer<String, Double> salaryConsumer = (name, salary) -> System.out.println(name + " " + salary);

        List<Person> personList = PersonRepository.getAllPersons();

        //personList.forEach(per -> personConsumer.accept(per.getName(), per.getHobbies()));
        personList.forEach(per -> {
            personConsumer.accept(per.getName(), per.getHobbies());
            salaryConsumer.accept(per.getName(), per.getSalary());
        });
    }

    //predicate example
    static Predicate<Person> p1 = per -> per.getHeight() >= 140;

    static Predicate<Person> p2 = per -> per.getGender().equals("Male");

    static BiConsumer<String, List<String>> hobbiesConsumer = (name, hobbies) -> System.out.println(name + " " + hobbies);


    //bipredicate example
    static BiPredicate<Integer, String> p3 = (height, gender) -> height >= 140 && gender.equals("Male");

    static Consumer<Person> personConsumer = per -> {
        //if(p1.and(p2).test(per)) {
        if (p3.test(per.getHeight(), per.getGender())) {
            hobbiesConsumer.accept(per.getName(), per.getHobbies());
        }
    };

    //function example
    static Function<String, Integer> f1 = name -> name.length();
    //from first example
    //static Function<String, String> f1 = name -> name.toUpperCase();
    //static Function<String, String> f2 = name -> name.toUpperCase().concat(" features");

    static Function<List<Person>, Map<String, Double>> f2 = personList -> {
        Map<String, Double> map = new HashMap<String, Double>();
        personList.forEach(per -> {
            if (p1.and(p2).test(per))
                map.put(per.getName(), per.getSalary());
        });
        return map;

    };
    //bifunction example
    static BiFunction<String, String, String> bf1 = (a, b) -> (a + " " + b);
    static BiFunction<List<Person>, Predicate<Person>, Map<String, Double>> bf2 = (listOfPersons, predicate) -> {
        Map<String, Double> map = new HashMap<String, Double>();
        listOfPersons.forEach(per -> {
            if (p1.and(p2).test(per))
                map.put(per.getName(), per.getSalary());
        });
        return map;
    };
    //unarybinary example
    static UnaryOperator<String> uo1 = name -> name.toUpperCase();
    static UnaryOperator<Integer> uo2 = a -> a + 10;
    static Comparator<Integer> comp = (a, b) -> a.compareTo(b);

    //supplier example
    static Supplier<Person> s1 = () -> PersonRepository.getPerson();
    static Supplier<List<Person>> s2 = () -> PersonRepository.getAllPersons();


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

            BiConsumer<Integer, Integer> addConsumer = (a, b) -> System.out.println("Add :" + (a + b));
            BiConsumer<Integer, Integer> subConsumer = (a, b) -> System.out.println("Subs :" + (a - b));
            BiConsumer<Integer, Integer> mulConsumer = (a, b) -> System.out.println("Mul :" + (a * b));
            //addConsumer.accept(10, 20);
            //subConsumer.accept(10, 20);
            //mulConsumer.accept(10, 20);
            addConsumer.andThen(subConsumer).andThen(mulConsumer).accept(10, 20);

        }
        //biconsumer example 2
        {
            printPersonDetails();
        }
        //predicate example
        {
            Predicate<Integer> lessThan = a -> (a <= 50);
            Predicate<Integer> greaterThan = a -> (a >= 100);
            Predicate<Integer> equalTo = a -> (a == 0);


		/*boolean result = lessThan.test(15);
		System.out.println("Result :"+result);*/

            System.out.println("Greater Than :" + greaterThan.test(150));
            System.out.println("Less Than :" + lessThan.test(150));
            System.out.println("Equal :" + equalTo.test(0));

            boolean result1 = lessThan.and(greaterThan).and(equalTo).test(150);
            System.out.println("Result 1:" + result1);

            boolean result2 = lessThan.or(greaterThan).test(150);
            System.out.println("Result 2:" + result2);

            boolean result3 = lessThan.or(greaterThan).negate().test(150);
            System.out.println("Result 3:" + result3);
        }
        //predicate example 2
        {
            personList.stream()
                    .filter(p1)
                    .collect(Collectors.toList());

            personList.forEach(per -> {
                if (p1.and(p2).test(per)) {
                    System.out.println(per);
                }
            });

        }
        //predicate example 3, 4
        {
            List<Person> personList = PersonRepository.getAllPersons();
            personList.forEach(personConsumer);
        }
        //function example
        {
            System.out.println("Result 1:" + f1.apply("java"));
            //System.out.println("Result 2:"+f2.apply("java"));
            //System.out.println("And Then Result :"+ f1.andThen(f2).apply("java"));
            //System.out.println("Compose Result :"+f1.compose(f2).apply("java"));
        }
        //function example 2
        {//System.out.println(f1.apply("java features"));
            List<Person> personList = PersonRepository.getAllPersons();
            Map<String, Double> map = f2.apply(personList);
            System.out.println("Result :" + map);
        }
        //bi function example
        {
            //System.out.println("Result :"+bf1.apply("java", "features"));
            List<Person> personList = PersonRepository.getAllPersons();
            Map<String, Double> map = bf2.apply(personList, p2);
            System.out.println("Result :" + map);
        }
        //ub example
        {
            //System.out.println("Result 1:"+uo1.apply("java8"));
            //System.out.println("Result 2:"+uo2.apply(20));

            BinaryOperator<Integer> bo1 = BinaryOperator
                    .maxBy((a, b) -> (a > b) ? 1 : (a == b) ? 0 : -1);
            System.out.println("Binary Operator MaxBy Result 1:" + bo1.apply(102, 15));

            BinaryOperator<Integer> bo2 = BinaryOperator.maxBy(comp);
            System.out.println("Binary Operator MaxBy Result 2:" + bo2.apply(102, 15));

            BinaryOperator<Integer> bo3 = BinaryOperator.minBy(comp);
            System.out.println("Binary Operator MinBy Result 3:" + bo3.apply(102, 15));
        }
        //supplier example
        {
            //System.out.println("Result :"+s1.get());
            System.out.println("Result :"+s2.get());
        }

    }
}