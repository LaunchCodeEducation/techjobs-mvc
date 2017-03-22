package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        System.out.println("SC.24.model = " + model);
        System.out.println("SC.25.columnChoices = " + columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);
        System.out.println("SC.37.searchType == " + searchType + " & searchTerm == " + searchTerm);
        ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        for(Object job : jobs){
            System.out.println("SC.39.job in jobs = " + job.toString());
        }
        model.addAttribute("jobs", jobs);
        return "search";
    }

}
