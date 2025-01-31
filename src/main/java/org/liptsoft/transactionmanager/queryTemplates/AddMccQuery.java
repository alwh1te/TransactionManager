package org.liptsoft.transactionmanager.queryTemplates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddMccQuery {
    private Long category_id;
    private Integer mcc;
}
