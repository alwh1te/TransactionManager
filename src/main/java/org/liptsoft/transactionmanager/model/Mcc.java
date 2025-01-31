package org.liptsoft.transactionmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mcc")
public class Mcc {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mcc")
    private int mcc;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category parentCategory;

    public Mcc(Integer mcc) {
        this.mcc = mcc;
    }

    @Override
    public String toString() {
        return String.valueOf(mcc);
    }
}
