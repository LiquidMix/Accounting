package accounting.services;

import accounting.model.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionServiceInterface {
    int addTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();

    Optional<Transaction> getTransactionById(UUID id);

    void deleteTransactionById(UUID id);
}
