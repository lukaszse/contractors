package org.lukaszse.contractorsapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.dto.CurrencyRates;
import org.lukaszse.contractorsapp.model.dto.CurrencyRatesNBPtable;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CurrencyRatesReaderService {


    public static List<CurrencyRates> getRates() {
        CurrencyRates currencyRates = null;
        CurrencyRatesNBPtable currencyRatesNBPtable = null;
        ObjectMapper mapper = new ObjectMapper();
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader in = null;

        try {
            URL nbp = new URL("http://api.nbp.pl/api/exchangerates/tables/A?format=json");
            URLConnection nbpConnection = nbp.openConnection();
            in = new BufferedReader(
                    new InputStreamReader(
                            nbpConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line);
            }

            log.info("Json before processing" + stringBuffer.toString());

            if(stringBuffer.toString().startsWith("[")) {
                stringBuffer.deleteCharAt(0);
                var length = stringBuffer.length();
                stringBuffer.deleteCharAt(length - 1);
            }
            log.info("Json after processing" + stringBuffer.toString());
            currencyRatesNBPtable = mapper
                    .readValue(stringBuffer.toString(), CurrencyRatesNBPtable.class);
        } catch (IOException e) {
            log.info(e.getMessage() + e.getStackTrace());
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        List<CurrencyRates> currencyRatesList = currencyRatesNBPtable.getRates();

        currencyRatesList = currencyRatesList.stream()
                .filter(c -> c.getCode().equals("EUR") || c.getCode().equals("GBP") || c.getCode().equals("USD"))
                .sorted(Comparator.comparing(c -> c.getCode()))
                .collect(Collectors.toList());


        return currencyRatesList;
    }


}
