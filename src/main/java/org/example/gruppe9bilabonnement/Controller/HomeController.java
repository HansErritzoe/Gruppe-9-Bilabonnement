package org.example.gruppe9bilabonnement.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.gruppe9bilabonnement.Model.Car;
import org.example.gruppe9bilabonnement.Service.CarService;
import org.example.gruppe9bilabonnement.Service.UserService;
import org.example.gruppe9bilabonnement.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;


    /**
     * method for returning the login page for when user isn't logged in, returns dashboard if logged in
     * @return string with html page location/name (either login page if not logged in, or dashboard if logged in)
     * Author - Hans Erritzøe
     */
    @GetMapping("/")
    public String login(HttpSession session){
        if(userIsLoggedIn(session)){
            return "dashboard/dashboard";
        }
        return "login/loginPage";
    }


    /**
     * Method for returning the dashboard html file address - this is the default landing page once a user has logged in
     * @return string with dashboard address if user is logged in, else sends user to login page with error message
     * Author - Hans Erritzøe
     */
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model){
        if(userIsLoggedIn(session)){
            return "dashboard/dashboard";
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }

    /**
     * Method for returning the car_inventory html file address, this html file displays a list of cars in the inventory
     * @return string with car_inventory address if user is logged in, else sends user to login page with error message
     * Author - Hans Erritzøe
     */
    @GetMapping("/car_inventory")
    public String car_inventory(HttpSession session, Model model){
        if(userIsLoggedIn(session)){
            List<Car> cars = carService.getAllCars();
            model.addAttribute("cars",cars);
            return "car_inventory/car_inventory";
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }

    /**
     * Method for returning the car_inventory html page with the search result when user
     * attempts to search for cars in the database on either id_vehicle or VIN
     * @param query - vehicle id or VIN for car to be searched for in DB
     * @param session - HTTPSession object used for checking that user is logged in
     * @param model - Model used for adding Car list to be displayed and filterOn true in order to display "clear filter" button
     * @return String - returns car_inventory string with search result added to model or login page if not logged in
     * Author - Hans Erritzøe
     */
    @PostMapping("/car_inventory_search")
    public String car_inventory_search(@RequestParam String query,HttpSession session, Model model){
        if(userIsLoggedIn(session)){
            List<Car> cars = carService.getCarsByIdOrVIN(query);
            model.addAttribute("cars",cars);
            model.addAttribute("filterOn", true); //enables displaying the "clear filter" button
            return "car_inventory/car_inventory";
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }


    /**
     * Method for handling when a user attempts to login
     * Checks if user exists, if so, checks if username and password match, if so, add User object to HttpSession
     * and proceed to dashboard page, if either checks fail, displays error message
     * @return String with dashboard page if successfull, login page with error message if fail
     * Author - Hans Erritzøe (partially taken from previous project i've made)
     */
    @PostMapping("/login")
    public String attemptLogin(Model model, HttpSession session, @RequestParam String username, @RequestParam String password){
        if(userService.doesUsernameExist(username)){
            if(userService.doesUsernameMatchPassword(username,password)){
                User user = userService.retrieveUserByUsername(username);
                session.setAttribute("loggedInUser", user);
                return "dashboard/dashboard";
            } else { //if username and password don't match, display errormessage
                model.addAttribute("loginErrorMessage", "Brugernavn og password matcher ikke - Kontakt admin hvis du har glemt dit kodeord eller brugernavn");
                return "login/loginPage";
            }
        } else { //if username doesnt exists, display errormessage
            model.addAttribute("loginErrorMessage", "Brugernavn eksisterer ikke - Kontakt admin hvis du har glemt dit brugernavn");
            return "login/loginPage";
        }
    }

    /**
     * Method for logging user out by invalidating session data
     * @param session - HttpSession object wherein the User object is stored
     * @param model - Model used for adding the loggedOutMessage
     * @return - string with login page address
     * * Author - Hans Erritzøe (partially taken from previous project i've made)
     */
    @PostMapping("/logout")
    public String logout(HttpSession session, Model model){
        session.invalidate(); //deletes session data
        model.addAttribute("loggedOutMessage","Du er nu logget ud.");
        return "login/loginPage";
    }

    /**
     * method for checking if user is logged in before displaying any pages other than login page
     * @param session session to be checked for User object
     * @return true if logged in, false if not
     * Author - Hans Erritzøe
     */
    public boolean userIsLoggedIn(HttpSession session){
        User user = (User) session.getAttribute("loggedInUser");
        return user != null;
    }

}
