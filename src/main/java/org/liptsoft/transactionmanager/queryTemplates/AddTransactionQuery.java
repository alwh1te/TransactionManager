package org.liptsoft.transactionmanager.queryTemplates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddTransactionQuery {
    private String name;
    private Double amount;
    private Integer month;
    private Long category_id;

    @Override
    public String toString() {
        return "AddTransactionQuery{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", month=" + month +
                ", category_id=" + category_id +
                '}';
    }
}
