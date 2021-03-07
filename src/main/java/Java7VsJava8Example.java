import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Java7VsJava8Example {

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
        Runnable t2 = () -> {System.out.println("Thread 2");};
        new Thread(t2).start();

        Runnable t3 = () -> {System.out.println("Thread 3");
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
    }}

        ////Comparator lambda example
        {
            // Before
            Comparator<Integer> c1 = new Comparator<Integer>() {

                @Override
                public int compare(Integer x, Integer y) {
                    return x.compareTo(y);
                }
            };

            System.out.println("Comparator1 :"+c1.compare(15, 15)); //0

            //After
            Comparator<Integer> c2 = (Integer x, Integer y) -> x.compareTo(y);
            System.out.println("Comparator2 :"+c2.compare(25, 15)); // 1

            Comparator<Integer> c3 = (x, y) -> x.compareTo(y);
            System.out.println("Comparator3 :"+c3.compare(15, 25)); // -1
        }
        {
        List<String> names = Arrays.asList("Sam","Peter","Billings","Sam");

        //Before
        List<String> uniqueList = new ArrayList<>();
        for(String name : names) {
            if(!uniqueList.contains(name)) {
                uniqueList.add(name);
            }
        }
        System.out.println("Unique List 1 :"+uniqueList);

        //After
        List<String> uniqueValues1 = names.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique List 2 :"+uniqueValues1);}




}