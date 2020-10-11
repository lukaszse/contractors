package org.lukaszse.contractorsapp.settings;

import org.lukaszse.contractorsapp.util.Mappings;
import org.lukaszse.contractorsapp.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class settingsController {

    @GetMapping(Mappings.SETTINGS)
    String settings() {
        return ViewNames.SETTINGS;
    }
}
