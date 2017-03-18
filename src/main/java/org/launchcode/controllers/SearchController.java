package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value="results")
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        ArrayList<HashMap<String, String>> allJobs = JobData.findAll();
        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();
        if (searchType.equals("all")) {
            for (HashMap<String, String> row : allJobs){
                for (String column : row.keySet()){
                    String aValue = row.get(column);
                    if (aValue.matches("(?i:.*" + searchTerm + ".*)")){
                        if (!jobs.contains(row)){
                            jobs.add(row);
                        }
                    }
                }
            }
        }else {
            for (HashMap<String, String> row : allJobs) {
                String aValue = row.get(searchType);
                if (aValue.matches("(?i:.*" + searchTerm + ".*)")) {
                    jobs.add(row);
                }
            }
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs",jobs);
        return "search";
    }
}
