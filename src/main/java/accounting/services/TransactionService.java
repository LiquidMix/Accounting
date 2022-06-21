package accounting.services;

import accounting.model.Transaction;
import accounting.repositories.TransactionRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService implements TransactionServiceInterface {
    private final TransactionRepositoryInterface transactionRepositoryInterface;


    @Autowired
    public TransactionService(@Qualifier("inMemoryTransaction") TransactionRepositoryInterface transactionRepositoryInterface) {
        this.transactionRepositoryInterface = transactionRepositoryInterface;
    }

    public int addTransaction(Transaction transaction) {
        return transactionRepositoryInterface.addTransaction(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepositoryInterface.getAllTransactions();
    }

    public Optional<Transaction> getTransactionById(UUID id) {
        return transactionRepositoryInterface.getTransactionById(id);
    }

    public void deleteTransactionById(UUID id) {
        transactionRepositoryInterface.deleteTransactionById(id);
    }
}
