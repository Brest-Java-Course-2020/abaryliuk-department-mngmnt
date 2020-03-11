package com.epam.brest.courses.rest_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Root controller.
 */
@RestController
public class VersionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionController.class);

    private final static String VERSION = "0.0.1";

    @GetMapping(value = "/version")
    public String version() {
        LOGGER.debug("version");

        return VERSION;
    }
}
