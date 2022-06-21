package accounting.api;

import accounting.model.BankAccount;
import accounting.services.BankAccountService;
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
    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService){
        this.bankAccountService=bankAccountService;
    }

    @PostMapping
    public int addBankAccount(@Valid @NonNull @RequestBody BankAccount bankAccount){
        if(personDB.stream().noneMatch(person -> person.getId().equals(bankAccount.getOwnerId()))) {
            return 0;
        }
        return bankAccountService.addBankAccount(bankAccount);
    }

    @GetMapping
    public List<BankAccount> getAllBankAccounts(){
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping(path ="{id}")
    public BankAccount getBankAccountById(@PathVariable("id")UUID id){
        return bankAccountService.getBankAccountById(id).orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void  deleteBankAccountById(@PathVariable("id")UUID id){
        bankAccountService.deleteBankAccount(id);
    }
    @PutMapping(path="{id}")
    public void updateBankAccount(@PathVariable("id")UUID id,@Valid @NonNull @RequestBody BankAccount bankAccountToUpdate){
        bankAccountService.updateBankAccount(id,bankAccountToUpdate);
    }
}
