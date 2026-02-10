package com.pafiast.solid.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Web controller for serving the main user interface.
 * Handles navigation to the index page where users can interact with the SOLID examples.
 */
@Controller
public class UiController {

    /**
     * Serves the index page.
     *
     * @return the name of the index view template
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }
}

