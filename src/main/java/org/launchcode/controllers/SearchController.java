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
import java.util.Map;

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
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);

        if(searchTerm.matches("[a-zA-Z ]+")){
            if(searchType.equals("all")){
                ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
                model.addAttribute("jobs", jobs);
            } else {
                ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
                model.addAttribute("jobs", jobs);
            }
            //model.addAttribute("jobs", jobs);
        } else {
            model.addAttribute("message", "Please input a search term.");
        }

//        for (HashMap<String, String> row : jobs) {
//            for(Map.Entry<String, String> e : row.entrySet()){
//                System.out.println(e.getKey() + ": " + e.getValue());
//            }
//        System.out.println("*****");  // separator for every job
//        }

        return "search";
    }

}
