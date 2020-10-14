package org.lukaszse.contractorsapp.settings;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;
import org.lukaszse.contractorsapp.settings.CurrencyExchangeRates.CurrencyRates;
import org.lukaszse.contractorsapp.settings.CurrencyExchangeRates.CurrencyRatesNBPtable;
import org.lukaszse.contractorsapp.settings.CurrencyExchangeRates.CurrencyRatesReader;
import org.lukaszse.contractorsapp.settings.DTO.SettingsReader;
import org.lukaszse.contractorsapp.settings.DTO.SettingsWriter;
import org.lukaszse.contractorsapp.util.AttributeNames;
import org.lukaszse.contractorsapp.util.Mappings;
import org.lukaszse.contractorsapp.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class SettingsController {

    private SettingService settingService;


    SettingsController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping(Mappings.SETTINGS)
    String viewSettings(Model model) {
        log.info("viewSetting() method invoked (GET)");
        log.info("Settings from database imported. id = " + settingService.getCurrentSettings().getId());
        var settingsReader = new SettingsReader(settingService.getCurrentSettings());
        model.addAttribute(AttributeNames.SETTINGS, settingsReader);
        return ViewNames.SETTINGS;
    }

    @PostMapping(Mappings.SETTINGS)
    String updateSettings(
            @ModelAttribute(AttributeNames.SETTINGS) @Valid SettingsWriter settingsWriter,
            BindingResult bindingResult,
            Model model
    ) {

        if(!bindingResult.hasErrors()) {
            log.info("updateSettings() method invoked (POST)");
            settingService.writeSettings(settingsWriter.toSettings());
            model.addAttribute("message", "Settings successfully updated!");
        }
        model.addAttribute(AttributeNames.SETTINGS, settingsWriter);
        log.info("Settings from database imported. id = " + settingService.getCurrentSettings().getId());
        return ViewNames.SETTINGS;
    }

    @ModelAttribute(name = AttributeNames.CURRENCY)
    public List<CurrencyRates> currencyRates() {
        var currencyRates = CurrencyRatesReader.getRates();
        log.info("LISTA WALUT: " + currencyRates.toString());
        return currencyRates;
    }
}
