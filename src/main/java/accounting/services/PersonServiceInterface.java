package accounting.services;

import accounting.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonServiceInterface {
    void addPerson(Person person);

    List<Person> getAllPeople();

    Optional<Person> getPersonById(UUID id);

    void deletePersonById(UUID id);

    void updatePersonById(UUID id, Person person);
}
