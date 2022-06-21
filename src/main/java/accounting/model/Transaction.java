package accounting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Transaction {
    private final UUID id;

    public Transaction(@JsonProperty("id") UUID id, @JsonProperty("senderId") UUID senderId, @JsonProperty("receiverId") UUID receiverId, @JsonProperty("amount") int amount) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount=amount;
    }

    @NotNull
    private final UUID senderId;
    @NotNull
    private final  UUID receiverId;
    @NotNull
    private final int amount;

    public int getAmount() {return amount;}

    public UUID getId() {
        return id;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public UUID getReceiverId() {
        return receiverId;
    }
}
