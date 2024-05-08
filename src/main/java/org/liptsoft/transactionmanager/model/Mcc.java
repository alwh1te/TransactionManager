package org.liptsoft.transactionmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "mcc")
public class Mcc {
    @Id
    @Column(name = "mcc")
    private Integer id;

    @ManyToOne
    private Category category;
}
