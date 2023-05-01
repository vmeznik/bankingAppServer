package bankingApp.model;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.Getter;


import java.io.Serializable;

@Entity
@Table(name = "transactions", schema = "public")
@Getter
public class Transaction implements Serializable {
    @Id
    @SerializedName("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @SerializedName("senderAccNumber")
    @Column(name = "senders_acc_number")
    private int senderAccNumber;
    @SerializedName("receiverAccNumber")
    @Column(name = "receivers_acc_number")
    private int receiverAccNumber;
    @SerializedName("amount")
    @Column(name = "amount_of_money")
    private double amount;

    public Transaction( int senderAccNumber, int receiverAccNumber, double amount) {
        this.id = -1;
        this.senderAccNumber = senderAccNumber;
        this.receiverAccNumber = receiverAccNumber;
        this.amount = amount;
    }

    public Transaction() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "senderAccNumber=" + senderAccNumber +
                ", receiverAccNumber=" + receiverAccNumber +
                ", amount=" + amount +
                '}';
    }
}
