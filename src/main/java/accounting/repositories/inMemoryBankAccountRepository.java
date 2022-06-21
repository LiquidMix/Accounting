package accounting.repositories;

import accounting.model.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Repository("inMemoryBankAccount")
public class inMemoryBankAccountRepository implements BankAccountRepositoryInterface{

    public static List<BankAccount> bankDB = new ArrayList<>();

    @Override
    public int insertBankAccount(UUID id, BankAccount bankAccount){
        BankAccount bankAccountToBeAdded=new BankAccount(id,bankAccount.getOwnerId(),bankAccount.getCurrency(),bankAccount.getBalance());
        bankDB.add(bankAccountToBeAdded);
        return 1;
    }

    @Override
    public List<BankAccount> returnAllBankAccounts(){return bankDB;}

    @Override
    public Optional<BankAccount> selectBankAccountById(UUID id) {
        return bankDB.stream().filter(bankAccount -> bankAccount.getId().equals(id)).findFirst();
    }

    @Override
    public void deleteBankAccountById(UUID id){
        Optional<BankAccount> bankAccountIfFound = selectBankAccountById(id);
        if(bankAccountIfFound.isEmpty()){
            return;
        }
        bankDB.remove(bankAccountIfFound.get());
    }
    public void updateBankAccountById(UUID id, BankAccount bankAccount){
        selectBankAccountById(id).map(bankAccount1 -> {
            int indexOfBankAccountToUpdate = bankDB.indexOf(bankAccount1);
            if (indexOfBankAccountToUpdate >= 0) {
                bankDB.set(indexOfBankAccountToUpdate, new BankAccount(id, bankAccount.getOwnerId(), bankAccount.getCurrency(), bankAccount.getBalance()));
                return 1;
            }
            return 0;
        });
    }
}
