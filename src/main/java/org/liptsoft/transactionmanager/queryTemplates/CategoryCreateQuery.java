package org.liptsoft.transactionmanager.queryTemplates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateQuery {
    private String categoryName;
    private Long parentCategory_id;
}
