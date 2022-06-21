package accounting.repositories;

import accounting.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("inMemoryTransaction")
public class inMemoryTransactionsRepository implements TransactionRepositoryInterface{
    public static List<Transaction> transactionDB = new ArrayList<>();

    @Override
    public int addTransaction(UUID id, Transaction transaction){
        Transaction transactionToBeAdded = new Transaction(id,transaction.getSenderId(),transaction.getReceiverId(), transaction.getAmount());
        transactionDB.add(transactionToBeAdded);
        return 1;
    }

    @Override
    public  List<Transaction> getAllTransactions(){
        return transactionDB;
    }

    @Override
    public Optional<Transaction> getTransactionById(UUID id){
        return transactionDB.stream().filter(transaction -> transaction.getId().equals(id)).findFirst();
    }

    @Override
    public void deleteTransactionById(UUID id){
        Optional<Transaction> transactionIfFound = getTransactionById(id);
        if(transactionIfFound.isEmpty()){
            return;
        }
        transactionDB.remove(transactionIfFound.get());
    }
}
