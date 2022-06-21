package accounting.repositories;

import accounting.model.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepositoryInterface {
    int addTransaction(UUID id, Transaction transaction);
    default int addTransaction(Transaction transaction){
        UUID id =UUID.randomUUID();
        return  addTransaction(id,transaction);
    }

    List<Transaction> getAllTransactions();
    Optional<Transaction> getTransactionById(UUID id);

    void deleteTransactionById(UUID id);
}
