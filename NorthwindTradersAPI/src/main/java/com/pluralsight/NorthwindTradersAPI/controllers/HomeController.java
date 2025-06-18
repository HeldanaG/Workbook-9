package com.pluralsight.NorthwindTradersAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // Handles: http://localhost:8080 or http://localhost:8080?country=USA
    @GetMapping("/")
    public String homePage(@RequestParam(defaultValue = "World") String country) {
        return "Hello " + country;
    }

    // Bonus route: http://localhost:8080/Heldana
    @GetMapping("/Heldana")
    public String heldanaPage() {
        return "Hello Heldana";
    }

    // @GetMapping ("/") eduivalent with this  @RequestMapping(path="/", method=RequestMethod.GET)
    //public String home() { return "Hello World!"; }

    // equivalent with homePage method
    //    @RequestMapping(path="/", method=RequestMethod.GET)
    //    public String homePage2( @RequestParam(defaultValue="World") String name) { return "Hello " + name ; }

}
