package bankingApp.model;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;

@Entity
@Table(name = "transactions", schema = "public")
@Getter
@Setter
public class Transaction implements Serializable {
    @Id
    @SerializedName("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @SerializedName("senderAccNumber")
    @Column(name = "senders_acc_number")
    private String senderAccNumber;
    @SerializedName("receiverAccNumber")
    @Column(name = "receivers_acc_number")
    private String receiverAccNumber;
    @SerializedName("amount")
    @Column(name = "amount_of_money")
    private double amount;
    @SerializedName("date")
    @Column(name = "date")
    private String date;

    public Transaction(String senderAccNumber, String receiverAccNumber, double amount, String date) {
        this.id = -1;
        this.senderAccNumber = senderAccNumber;
        this.receiverAccNumber = receiverAccNumber;
        this.amount = amount;
        this.date = date;
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
