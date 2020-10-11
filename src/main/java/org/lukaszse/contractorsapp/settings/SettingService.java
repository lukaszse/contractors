package org.lukaszse.contractorsapp.settings;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SettingService {

    // == fiels ==
    private SettingsRepository settingsRepository;


    // == constructor ==
    SettingService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public Settings getCurrentSettings() {
        log.info("getCourrentSettings method invoked");
        var currentSettings = settingsRepository.findFirstByOrderByIdDesc();
        return currentSettings.map(settings -> settings)
                .orElseThrow(() ->  new IllegalArgumentException("!!!!!!  OPTIONAL IS NULL"));
    }

    public void writeSettings(Settings newSettings) {
        settingsRepository.save(newSettings);
    }

}
