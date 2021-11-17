package org.lukaszse.contractorsapp.service;

import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.repository.SettingsRepository;
import org.lukaszse.contractorsapp.model.Settings;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SettingService {

    private SettingsRepository settingsRepository;

    SettingService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public Settings getCurrentSettings() {
        log.info("getCourrentSettings method invoked");
        var currentSettings = settingsRepository.findFirstByOrderByIdDesc();
        return currentSettings.map(settings -> settings)
                .orElseThrow(() -> new IllegalArgumentException("!!!!!!  OPTIONAL IS NULL"));
    }

    public void writeSettings(Settings newSettings) {
        settingsRepository.save(newSettings);
    }

}
