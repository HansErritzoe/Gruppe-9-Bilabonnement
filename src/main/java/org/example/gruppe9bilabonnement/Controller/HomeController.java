package org.example.gruppe9bilabonnement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    /**
     * method for returning the login page for when user isn't logged in
     * @return string with html page location/name
     * Author - Hans Erritzøe
     */
    @GetMapping("/")
    public String login(){
        return "login/loginPage";
    }

}
