package accounting.services;

import accounting.model.BankAccount;
import accounting.repositories.BankAccountRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankAccountService implements BankAccountServiceInterface {
    private final BankAccountRepositoryInterface bankAccountRepositoryInterface;

    @Autowired
    public BankAccountService(@Qualifier("inMemoryBankAccount") BankAccountRepositoryInterface bankAccountRepositoryInterface) {
        this.bankAccountRepositoryInterface = bankAccountRepositoryInterface;
    }

    public int addBankAccount(BankAccount bankAccount) {
        return bankAccountRepositoryInterface.insertBankAccount(bankAccount);

    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepositoryInterface.returnAllBankAccounts();
    }

    public Optional<BankAccount> getBankAccountById(UUID id) {
        return bankAccountRepositoryInterface.selectBankAccountById(id);
    }

    public void deleteBankAccountById(UUID id) {
        bankAccountRepositoryInterface.deleteBankAccountById(id);
    }

    public void updateBankAccountById(UUID id, BankAccount newBankAccount) {
        bankAccountRepositoryInterface.updateBankAccountById(id, newBankAccount);
    }
}
