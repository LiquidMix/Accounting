package accounting.api;


import accounting.model.Person;
import accounting.services.PersonService;
import accounting.services.PersonServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/person")
@RestController
public class PersonController {
    private final PersonServiceInterface personServiceInterface;

    @Autowired
    public PersonController(PersonServiceInterface personServiceInterface) {
        this.personServiceInterface = personServiceInterface;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person){
        personServiceInterface.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personServiceInterface.getAllPeople();
    }

    @GetMapping(path ="{id}")
    public Person getPersonByID(@PathVariable("id") UUID id){
        return personServiceInterface.getPersonById(id).orElse(null); //should return exception/ error code 404
    }

    @DeleteMapping(path="{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personServiceInterface.deletePersonById(id);
    }

    @PutMapping(path="{id}")
    public void  updatePerson(@PathVariable("id")UUID id, @Valid @NonNull @RequestBody Person personToUpdate){
        personServiceInterface.updatePersonById(id,personToUpdate);
    }

}
