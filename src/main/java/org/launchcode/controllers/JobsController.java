package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LaunchCode
 */
@Controller
public class JobsController {

    @RequestMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

}
