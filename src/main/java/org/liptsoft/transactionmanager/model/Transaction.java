package org.liptsoft.transactionmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "month")
    private Integer month;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public String getMonthString() {
        return months[month - 1];
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", month=" + months[month - 1] +
                ", category=" + category +
                '}';
    }
    private static final String[] months = {"January", "February", "March",
            "April", "May", "June",
            "July", "August", "September",
            "October", "November", "December"};
}
