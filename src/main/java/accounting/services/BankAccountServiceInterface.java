package accounting.services;

import accounting.model.BankAccount;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankAccountServiceInterface {
    int addBankAccount(BankAccount bankAccount);

    List<BankAccount> getAllBankAccounts();

    Optional<BankAccount> getBankAccountById(UUID id);

    void deleteBankAccountById(UUID id);

    void updateBankAccountById(UUID id, BankAccount bankAccount);
}
