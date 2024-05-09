package org.liptsoft.transactionmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "mcc")
public class Mcc {
    @Id
    @Column(name = "mcc_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "mcc")
    private int mcc;

    @ManyToOne
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mcc mcc1 = (Mcc) o;
        return mcc == mcc1.mcc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mcc + 1) * 17;
    }
}
