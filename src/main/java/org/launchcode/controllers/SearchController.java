package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
        //model.addAttribute( "listTitle", "All Jobs" );
        //model.addAttribute( "jobs", JobData.findAll() );
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping( value="results" )
    public String results( Model model, @RequestParam String searchType, @RequestParam String searchTerm ) {
        String strSearchType = new String();
        ArrayList<HashMap<String,String>> alResults;

        if( searchType.equals("all") ) {
            alResults = JobData.findByValue( searchTerm );
        } else {
            alResults = JobData.findByColumnAndValue( searchType, searchTerm );
        }

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute( "jobs", alResults );

        String strResTitle = "%d Result";
        if( alResults.size() != 1 ) {
            strResTitle += "s";
        }
        strResTitle += " for \"%s\" in `%s`.";
        switch(searchType) {
            case "all":
            case "position type":
            case "employer":
            case "location":
            case "core competency":

                strSearchType = searchType;
                break;
            default:
                strSearchType = "All";
                break;

        }
        // searchTerm
        strResTitle = String.format(strResTitle, alResults.size(), searchTerm, strSearchType );

        model.addAttribute( "listTitle", strResTitle);
        return "search";
    }
}
