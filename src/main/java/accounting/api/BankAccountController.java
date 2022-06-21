package accounting.api;

import accounting.model.BankAccount;
import accounting.services.BankAccountService;
import accounting.services.BankAccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static accounting.repositories.InMemoryPersonRepository.personDB;


@RequestMapping("api/bank-account")
@RestController
public class BankAccountController {

    private final BankAccountServiceInterface bankAccountServiceInterface;

    @Autowired
    public BankAccountController(BankAccountServiceInterface bankAccountServiceInterface){
        this.bankAccountServiceInterface=bankAccountServiceInterface;
    }

    @PostMapping
    public int addBankAccount(@Valid @NonNull @RequestBody BankAccount bankAccount){
        if(personDB.stream().noneMatch(person -> person.getId().equals(bankAccount.getOwnerId()))) {
            return 0;
        }
        return bankAccountServiceInterface.addBankAccount(bankAccount);
    }

    @GetMapping
    public List<BankAccount> getAllBankAccounts(){
        return bankAccountServiceInterface.getAllBankAccounts();
    }

    @GetMapping(path ="{id}")
    public BankAccount getBankAccountById(@PathVariable("id")UUID id){
        return bankAccountServiceInterface.getBankAccountById(id).orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void  deleteBankAccountById(@PathVariable("id")UUID id){
        bankAccountServiceInterface.deleteBankAccountById(id);
    }
    @PutMapping(path="{id}")
    public void updateBankAccount(@PathVariable("id")UUID id,@Valid @NonNull @RequestBody BankAccount bankAccountToUpdate){
        bankAccountServiceInterface.updateBankAccountById(id,bankAccountToUpdate);
    }
}
