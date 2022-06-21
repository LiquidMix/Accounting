package accounting.api;

import accounting.model.BankAccount;
import accounting.model.Transaction;
import accounting.services.BankAccountService;
import accounting.services.BankAccountServiceInterface;
import accounting.services.TransactionService;
import accounting.services.TransactionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static accounting.repositories.inMemoryBankAccountRepository.bankDB;

@RequestMapping("api/transaction")
@RestController
public class TransactionController {
    private  final TransactionServiceInterface transactionServiceInterface;
    private  final BankAccountServiceInterface bankAccountServiceInterface;

    @Autowired
    public TransactionController(TransactionService transactionService, BankAccountService bankAccountService){
        this.transactionServiceInterface = transactionService;
        this.bankAccountServiceInterface = bankAccountService;
    }

    @PostMapping
    public int addTransaction(@Valid @NonNull @RequestBody Transaction transaction){
        BankAccount receiverAccount=bankDB.stream().filter(bankAccount ->bankAccount.getId().equals(transaction.getReceiverId())).findAny().orElse(null);
        BankAccount senderAccount=bankDB.stream().filter(bankAccount ->bankAccount.getId().equals(transaction.getSenderId())).findAny().orElse(null);
        if(receiverAccount==null || senderAccount==null){
            return 0;
        }
        if(!Objects.equals(receiverAccount.getCurrency(), senderAccount.getCurrency())){
            return 0;
        }
        if(senderAccount.getBalance()<transaction.getAmount()){
            return 0;
        }
        senderAccount.setBalance(senderAccount.getBalance()-transaction.getAmount());
        receiverAccount.setBalance(receiverAccount.getBalance()+transaction.getAmount());

        bankAccountServiceInterface.updateBankAccountById(senderAccount.getId(),senderAccount);
        bankAccountServiceInterface.updateBankAccountById(receiverAccount.getId(),receiverAccount);
        return transactionServiceInterface.addTransaction(transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionServiceInterface.getAllTransactions();
    }

    @GetMapping(path ="{id}")
    public Transaction getTransactionById(@PathVariable("id")UUID id){
        return transactionServiceInterface.getTransactionById(id).orElse(null);
    }

    @DeleteMapping(path ="{id}")
    public void deleteTransactionById(@PathVariable("id")UUID id){
        transactionServiceInterface.deleteTransactionById(id);
    }

}
