package com.prm.spring.jdbctemplate;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class)) {
            PersonDAO personDAO = context.getBean(PersonDAO.class);

            // Create a new person
            Person person = new Person();
            person.setId(1);
            person.setName("John");
            person.setAge(30);
            personDAO.createPerson(person);
            System.out.println("Person created successfully!");

            // Get a person by ID
            Person retrievedPerson = personDAO.getPersonById(1);
            System.out.println("Retrieved person: " + retrievedPerson);

            // Update the person's age
            retrievedPerson.setAge(35);
            personDAO.updatePerson(retrievedPerson);
            System.out.println("Person updated successfully!");

            // Get all persons
            List<Person> allPersons = personDAO.getAllPersons();
            System.out.println("All persons:");
            for (Person p : allPersons) {
                System.out.println(p);
            }

            // Delete the person
            personDAO.deletePerson(1);
            System.out.println("Person deleted successfully!");
        }
    }
}