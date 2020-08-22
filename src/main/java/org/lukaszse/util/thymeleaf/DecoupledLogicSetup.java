package org.lukaszse.util.thymeleaf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class DecoupledLogicSetup {

    // == fields ==
    private final SpringResourceTemplateResolver templateResolver;

    // == constructor ==

    public DecoupledLogicSetup(SpringResourceTemplateResolver templateResolver) {
        this.templateResolver = templateResolver;
    }

    // == init
    @PostConstruct
    public void init() {
        templateResolver.setUseDecoupledLogic(true);
        log.info("Thymeleaf decoupled template logic is enabled");
    }
}
