package accounting.repositories;

import accounting.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//dao data access obj
public interface PersonRepositoryInterface {
    void insertPerson(UUID id, Person person);
    default void insertPerson(Person person){
        UUID id = UUID.randomUUID();
        insertPerson(id, person);
    }

    List<Person> returnAllPeople();

    Optional<Person> selectPersonById(UUID id);

    void deletePersonById(UUID id);

    void updatePersonById(UUID id, Person person);
}
