package org.lukaszse.contractorsapp.About;

import org.lukaszse.contractorsapp.util.Mappings;
import org.lukaszse.contractorsapp.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class aboutController {

    @GetMapping(Mappings.ABOUT)
    String about() {
        return ViewNames.ABOUT;
    }
}
