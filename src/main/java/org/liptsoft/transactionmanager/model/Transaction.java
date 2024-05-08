package org.liptsoft.transactionmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
@Entity(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @Column(name = "category")
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="category",
            joinColumns=  @JoinColumn(name="category_id", referencedColumnName="id"))
    //inverseJoinColumns= @JoinColumn(name=" employee_id", referencedColumnName="id")
    private List<Category> category;

    @Column(name = "value")
    private int value;

    @Column(name = "month")
    private String month;
}
