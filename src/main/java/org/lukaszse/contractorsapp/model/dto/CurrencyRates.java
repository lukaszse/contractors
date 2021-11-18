package org.lukaszse.contractorsapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CurrencyRates {
    private String currency;
    private String code;
    private Double mid;
}
