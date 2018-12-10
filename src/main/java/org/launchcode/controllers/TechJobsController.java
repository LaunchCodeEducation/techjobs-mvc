package org.launchcode.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;

public class TechJobsController {

    static HashMap<String, String> actionChoices = new HashMap<>();

    static HashMap<String, String> columnChoices = new HashMap<>();

    public TechJobsController () {
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");

    }

    @ModelAttribute("actions")
    static HashMap<String, String> getActionChoices() {
        return actionChoices;
    }

    @ModelAttribute("columns")
    static HashMap<String, String> getColumnChoices(){
        return columnChoices;
    }


}
