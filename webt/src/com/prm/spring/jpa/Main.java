package com.prm.spring.jpa;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            PersonService personService = context.getBean(PersonService.class);

            // Create a new person
            Person person = new Person();
            person.setName("John");
            person.setAge(30);
            Person createdPerson = personService.createPerson(person);
            System.out.println("Created person: " + createdPerson);

            // Get a person by ID
            Long id = createdPerson.getId();
            System.out.println("Retrieving person by ID: " + id);
            Person retrievedPerson = personService.getPersonById(id).orElse(null);
            System.out.println("Retrieved person: " + retrievedPerson);

            // Update the person's age
            if (retrievedPerson != null) {
                retrievedPerson.setAge(35);
                Person updatedPerson = personService.updatePerson(retrievedPerson);
                System.out.println("Updated person: " + updatedPerson);
            }

            // Get all persons
            System.out.println("Retrieving all persons:");
            personService.getAllPersons().forEach(System.out::println);

            // Delete the person
            if (retrievedPerson != null) {
                Long deleteId = retrievedPerson.getId();
                System.out.println("Deleting person by ID: " + deleteId);
                personService.deletePerson(deleteId);
                System.out.println("Person deleted successfully");
            }
        }
    }
}