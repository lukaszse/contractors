package org.lukaszse.contractorsapp.settings.CurrencyExchangeRates;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class test {
    @GetMapping("/test")
    public ResponseEntity<List<CurrencyRates>> printPost() {

        List<CurrencyRates> currencyRatesList = CurrencyRatesReader.getRates();
        return ResponseEntity.ok(currencyRatesList);
    }
}
