package org.lukaszse.contractorsapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.dto.CurrencyRates;
import org.lukaszse.contractorsapp.model.dto.SettingsUpdateDto;
import org.lukaszse.contractorsapp.model.dto.SettingsViewDto;
import org.lukaszse.contractorsapp.service.CurrencyRatesReaderService;
import org.lukaszse.contractorsapp.service.SettingService;
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

@Slf4j
@Controller
public class SettingsController {

    private final SettingService settingService;

    SettingsController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping(Mappings.SETTINGS)
    String viewSettings(Model model) {
        log.info("Settings from database imported. id = " + settingService.getCurrentSettings().getId());
        var settingsReader = new SettingsViewDto(settingService.getCurrentSettings());
        model.addAttribute(AttributeNames.SETTINGS, settingsReader);
        return ViewNames.SETTINGS;
    }

    @PostMapping(Mappings.SETTINGS)
    String updateSettings(
            @ModelAttribute(AttributeNames.SETTINGS) @Valid SettingsUpdateDto settingsWriter,
            BindingResult bindingResult,
            Model model) {
        if (!bindingResult.hasErrors()) {
            settingService.writeSettings(settingsWriter.toSettings());
            model.addAttribute(AttributeNames.MESSAGE, "Settings successfully updated!");
        }
        model.addAttribute(AttributeNames.SETTINGS, settingsWriter);
        log.info("Settings from database imported. id = " + settingService.getCurrentSettings().getId());
        return ViewNames.SETTINGS;
    }

    @ModelAttribute(name = AttributeNames.CURRENCY)
    public List<CurrencyRates> currencyRates() {
        return CurrencyRatesReaderService.getRates();
    }
}
