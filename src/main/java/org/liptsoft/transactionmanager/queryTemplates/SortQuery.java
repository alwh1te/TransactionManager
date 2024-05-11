package org.liptsoft.transactionmanager.queryTemplates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SortQuery {
    private Long categoryId;
    private Long transactionId = null;
    private Integer month = null;
}
