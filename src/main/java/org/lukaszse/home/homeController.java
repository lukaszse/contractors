package org.lukaszse.home;

import org.lukaszse.util.Mappings;
import org.lukaszse.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping(Mappings.HOME)
    public String goHome() {
        return ViewNames.HOME;
    }
}
