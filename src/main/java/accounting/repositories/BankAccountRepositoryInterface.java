package accounting.repositories;


import accounting.model.BankAccount;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankAccountRepositoryInterface {
    int insertBankAccount(UUID id, BankAccount bankAccount);
    default int insertBankAccount(BankAccount bankAccount){
        UUID id = UUID.randomUUID();
        return insertBankAccount(id,bankAccount);
    }

    List<BankAccount> returnAllBankAccounts();
    Optional<BankAccount> selectBankAccountById(UUID id);

    void deleteBankAccountById(UUID id);
    void updateBankAccountById(UUID id, BankAccount bankAccount);

}
