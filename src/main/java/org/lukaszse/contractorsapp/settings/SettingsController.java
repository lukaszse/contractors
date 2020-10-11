package org.lukaszse.contractorsapp.settings;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;
import org.lukaszse.contractorsapp.settings.DTO.SettingsReader;
import org.lukaszse.contractorsapp.settings.DTO.SettingsWriter;
import org.lukaszse.contractorsapp.util.AttributeNames;
import org.lukaszse.contractorsapp.util.Mappings;
import org.lukaszse.contractorsapp.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    String updateSettings(@ModelAttribute(AttributeNames.SETTINGS) SettingsWriter settingsWriter, Model model) {
        log.info("updateSettings() method invoked (POST)");
        settingService.writeSettings(settingsWriter.toSettings());
        var settingReader = new SettingsReader(settingService.getCurrentSettings());
        model.addAttribute(AttributeNames.SETTINGS, settingReader);
        model.addAttribute("message", "Settings successfully updated!");
        log.info("Settings from database imported. id = " + settingService.getCurrentSettings().getId());
        return ViewNames.SETTINGS;
    }
}
