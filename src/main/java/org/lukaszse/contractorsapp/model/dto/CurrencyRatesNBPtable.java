package org.lukaszse.contractorsapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRatesNBPtable {
    private String table;
    private String no;
    private  String effectiveDate;

    @Embedded
    private List<CurrencyRates> rates;
}
