package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    static String negativeSearchResult;
    static ArrayList<HashMap<String, String>> jobsResults = new ArrayList<>();

    static String printJobs(ArrayList<HashMap<String, String>> someJobs) {
        for (HashMap<String, String> job : someJobs) {
            for (Map.Entry<String, String> category : job.entrySet()) {
                return category.getKey() + " : " + category.getValue();

            }
        }return "";
    }


    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("title", "Search");
        return "search";
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public String redirectToSearch(Model model){
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("title", "Search");
        return "redirect:";
    }
    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public String processSearchRequest(@RequestParam String searchType, @RequestParam String searchTerm, Model model) {
        model.addAttribute("title", "Results");
        if (searchType.equals("all")) {
            if (JobData.findByValue(searchTerm).isEmpty()) {
                negativeSearchResult = "No matching jobs";
                model.addAttribute("negativeSearchResult", negativeSearchResult);
                return "search-results";
            } else {
                jobsResults = JobData.findByValue(searchTerm);
                model.addAttribute("jobsResults", jobsResults);
                return "search-results";
            }

        } else {
            if(JobData.findByColumnAndValue(searchType, searchTerm).isEmpty()) {
                negativeSearchResult = "No matching jobs";
                model.addAttribute("negativeSearchResult", negativeSearchResult);
                return "search-results";
            } else {
                jobsResults = JobData.findByColumnAndValue(searchType, searchTerm);
                model.addAttribute("jobsResults", jobsResults);
                return "search-results";
            }
        }

    }
}







