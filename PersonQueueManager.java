import java.util.LinkedList;
import java.util.Scanner;

class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return firstName + " " + lastName + " (" + age + ")";
    }
}

class PersonQueue {
    private LinkedList<Person> queue;

    public PersonQueue() {
        queue = new LinkedList<>();
    }

    public void enqueue(Person person) {
        queue.addLast(person);
    }

    public void displayQueue() {
        for (Person person : queue) {
            System.out.println(person);
        }
    }

    public void quickSortByLastNameDescending() {
        queue = quickSort(queue, "lastName");
    }

    public void quickSortByAgeDescending() {
        queue = quickSort(queue, "age");
    }

    private LinkedList<Person> quickSort(LinkedList<Person> list, String sortBy) {
        if (list.size() <= 1) {
            return list;
        }

        Person pivot = list.getFirst();
        LinkedList<Person> greater = new LinkedList<>();
        LinkedList<Person> lesser = new LinkedList<>();

        for (int i = 1; i < list.size(); i++) {
            Person current = list.get(i);
            if (sortBy.equals("lastName")) {
                if (current.getLastName().compareToIgnoreCase(pivot.getLastName()) > 0) {
                    greater.add(current);
                } else {
                    lesser.add(current);
                }
            } else if (sortBy.equals("age")) {
                if (current.getAge() > pivot.getAge()) {
                    greater.add(current);
                } else {
                    lesser.add(current);
                }
            }
        }

        LinkedList<Person> sorted = new LinkedList<>();
        sorted.addAll(quickSort(greater, sortBy));
        sorted.add(pivot);
        sorted.addAll(quickSort(lesser, sortBy));

        return sorted;
    }
}

public class PersonQueueManager {
    public static void main(String[] args) {
        PersonQueue queue = new PersonQueue();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter details for 5 people.");
            int count = 0;
            while (count < 5) {
                try {
                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Age: ");
                    int age = Integer.parseInt(scanner.nextLine());

                    queue.enqueue(new Person(firstName, lastName, age));
                    count++;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                }
            }

            System.out.println("\nOriginal Queue:");
            queue.displayQueue();

            System.out.println("\nSorted by Last Name (Descending):");
            queue.quickSortByLastNameDescending();
            queue.displayQueue();

            System.out.println("\nSorted by Age (Descending):");
            queue.quickSortByAgeDescending();
            queue.displayQueue();
        }
    }
}
