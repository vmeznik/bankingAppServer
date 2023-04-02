package bankingApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name = "transactions", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = -1;
    @Column(name = "senders_acc_number")
    private int senderAccNumber;
    @Column(name = "receivers_acc_number")
    private int receiverAccNumber;
    @Column(name = "amount_of_money")
    private double amount;
}
