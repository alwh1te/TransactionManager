package org.liptsoft.transactionmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "category")
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String name;

//    @ElementCollection(targetClass = Mcc.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "mcc", joinColumns = @JoinColumn(name = "mcc"))
    @Column(name = "mcc", nullable = false)
//    @JoinColumn(name = "mcc")
    @OneToMany(fetch= FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "mcc")
    private List<Mcc> mcc = new ArrayList<>();

//    @ElementCollection(targetClass = Category.class, fetch = FetchType.EAGER)
    @Column(name = "subCategories", nullable = false)
    @CollectionTable(name = "category", joinColumns = @JoinColumn(name = "category_id"))
//    @JoinColumn(name = "category_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Category> subCategories = new ArrayList<>();

    @Column(name = "transactions", nullable = false)
    @CollectionTable(name = "transaction", joinColumns = @JoinColumn(name = "transaction_id"))
//    @JoinColumn(name = "transaction_id")
    @OneToMany
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transactions) {
        this.transactions.add(transactions);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public void addSubCategories(List<Category> subCategories) {
        this.subCategories.addAll(subCategories);
    }

    public void addMcc(List<Mcc> mcc) {
        this.mcc.addAll(mcc);
    }

    public int getSum(Predicate<Transaction> predicate, UnaryOperator<Transaction> method) {
        int sum = 0;
        for (Transaction transaction : transactions) {
            if (predicate.test(method.apply(transaction))) {
                sum += transaction.getValue();
            }
        }
        return sum;
    }
}
