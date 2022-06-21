package accounting.services;

import accounting.model.Person;
import accounting.repositories.PersonRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService implements PersonServiceInterface {
    private final PersonRepositoryInterface personDao;

    @Autowired
    public PersonService(@Qualifier("inMemoryPerson") PersonRepositoryInterface personDao) {
        this.personDao = personDao;
    }

    public void addPerson(Person person) {
        personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDao.returnAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public void deletePersonById(UUID id) {
        personDao.deletePersonById(id);
    }

    public void updatePersonById(UUID id, Person newPerson) {
        personDao.updatePersonById(id, newPerson);
    }
}
