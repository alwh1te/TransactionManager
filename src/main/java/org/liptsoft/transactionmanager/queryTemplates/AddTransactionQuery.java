package org.liptsoft.transactionmanager.queryTemplates;

import lombok.Getter;

@Getter
public class AddTransactionQuery {
    private Long category_id;
    private String name;
    private Double amount;
    private Integer month;
}
