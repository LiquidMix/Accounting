package accounting.repositories;

import accounting.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("inMemoryPerson")
public class InMemoryPersonRepository implements PersonRepositoryInterface {

    public static List<Person> personDB = new ArrayList<>();

    @Override
    public void insertPerson(UUID id, Person person) {
        personDB.add(new Person(id, person.getName()));
    }

    @Override
    public List<Person> returnAllPeople() {
        return personDB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return personDB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public void deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if (personMaybe.isEmpty()) {
            return;
        }
        personDB.remove(personMaybe.get());
    }

    @Override
    public void updatePersonById(UUID id, Person person) {
        selectPersonById(id).map(person1 -> {
            int indexOfPersonToUpdate = personDB.indexOf(person1);
            if (indexOfPersonToUpdate >= 0) {
                personDB.set(indexOfPersonToUpdate, new Person(id, person.getName()));
                return 1;
            }
            return 0;
        });
    }

}
