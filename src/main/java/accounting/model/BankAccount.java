package accounting.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class BankAccount {
    private final UUID id;

    @NotNull
    private final UUID ownerId;

    @NotBlank
    private final String currency;

    private int balance;

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public String getCurrency() {
        return currency;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int newBalance){
        this.balance=newBalance;
    }

    public BankAccount(@JsonProperty("id")UUID id,@JsonProperty("ownerId")UUID ownerId,@JsonProperty("currency") String currency,@JsonProperty("balance") int balance) {
        this.id = id;
        this.ownerId = ownerId;
        this.currency = currency;
        this.balance = balance;
    }
}
