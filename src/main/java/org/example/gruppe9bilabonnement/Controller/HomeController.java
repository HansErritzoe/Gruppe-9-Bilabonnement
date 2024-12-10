package org.example.gruppe9bilabonnement.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.gruppe9bilabonnement.Model.*;
import org.example.gruppe9bilabonnement.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @Autowired
    RentalContractService rentalContractService;

    @Autowired
    Damage_reportService damageReportService;

    @Autowired
    DamageService damageService;

    /**
     * method for returning the login page for when user isn't logged in, returns dashboard if logged in
     * @return string with html page location/name (either login page if not logged in, or dashboard if logged in)
     * @Author Hans Erritzøe
     */
    @GetMapping("/")
    public String login(HttpSession session){
        if(userIsLoggedIn(session)){
            return "dashboard/dashboard";
        } else {
            return "login/loginPage";
        }
    }

    /**
     * Method for returning the dashboard html file address - this is the default landing page once a user has logged in
     * @return string with dashboard address if user is logged in, else sends user to login page with error message
     * @Author Hans Erritzøe
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
     * Method for handling creating a new car in the database submitted by the user
     * @param session HTTPSession object used for checking that user is logged in
     * @param model Spring Model object used for adding error/success messages
     * @param @ModelAttribute - Spring handles creating a car object with the field values from the form
     * @return returns the add_car html page with either success or errormessage
     * @Author Hans Erritzøe
     */
    @PostMapping("car_inventory/add_car")
    public String addCar(HttpSession session, Model model, @ModelAttribute Car car){
        if(userIsLoggedIn(session)){
            boolean success = carService.addCar(car);
            if(success){
                model.addAttribute("successMessage","Success! Bilen er nu tilføjet til databasen");
            } else {
                model.addAttribute("errorMessage", "Fejl! Kunne ikke tilføje bilen til databasen, prøv igen eller kontakt admin for hjælp");
            }
            return "car_inventory/add_car";
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }

    /**
     * Method for displaying the add_car html page where user can add cars to the database
     * @param session - HTTPSession object used for checking that user is logged in
     * @param model - Model object used for displaying error message if user attempts to access without loggin in
     * @return string - returns the string of the html file address with the add_car page or login if not logged in
     * @Author Hans Erritzøe
     */
    @GetMapping("/car_inventory/add_car")
    public String addCar(HttpSession session, Model model){
        if(userIsLoggedIn(session)){
            return "car_inventory/add_car";
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }

    /**
     * Method for returning the car_inventory html file address
     * @return string with car_inventory address if user is logged in, else sends user to login page with error message
     * @Author Hans Erritzøe
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
     * @Author Hans Erritzøe
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
     * Method for displaying the add_rentalcontract html page where user can add rental contracts to the database
     * @param session - HTTPSession object used for checking that user is logged in
     * @param model - Model object used for displaying error message if user attempts to access without loggin in
     * @return string - returns the string of the html file address with the add_rentalcontract page or login if not logged in
     * @Author Jonas Jakobsen
     */
    @GetMapping("/rental_contract/add_rentalcontract")
    public String addRentalcontract(HttpSession session, Model model){
        if(userIsLoggedIn(session)){
            return "rental_contract/add_rentalcontract";
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }
    @PostMapping("/rental_contract/add_rentalcontract")
    public String addRentalcontract(@ModelAttribute Rental_contract rental_contract){
        rentalContractService.addRental_contract(rental_contract);
        return "rental_contract/rental_contract";
    }
    /**
     * Method for returning the rental_contract html file address
     * @return string with rental_contract address if user is logged in, else it returns the user to the login page with error message
     * @Author Jonas Jakobsen
     */
    @GetMapping("/rental_contract")
    public String rental_contract(HttpSession session, Model model){
        if(userIsLoggedIn(session)){

            List<Rental_contract> rentalContracts = rentalContractService.getAllRentalcontracts();
            model.addAttribute("rental_contract",rentalContracts);
            return "rental_contract/rental_contract";
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }

    /**
     * Method for handling when a user attempts to login
     * Checks if user exists, if so, checks if username and password match, if so, add User object to HttpSession
     * and proceed to dashboard page, if either checks fail, displays error message and return to loginPage
     * @return String with dashboard page if successfull, login page with error message if fail
     * @Author Hans Erritzøe (partially taken from previous project i've made)
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
     * @Author Hans Erritzøe (partially taken from previous project i've made)
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
     * @Author Hans Erritzøe
     */
    public boolean userIsLoggedIn(HttpSession session){
        User user = (User) session.getAttribute("loggedInUser");
        return user != null;
    }

    /**
     * Method to return the damage_report html file, which displays a list of all damage reports
     * @param session - HTTPSession object used for checking that user is logged in
     * @param model - Model object used for displaying error message if user attempts to access without loggin in
     * @return String - returns string with damage_report page or loginpage if user isn't logged in
     * @Author Hans Erritzøe
     */
    @GetMapping("/damage_report")
    public String damage_report(HttpSession session, Model model){
        if(userIsLoggedIn(session)){
            List<Damage_report> damageReports = damageReportService.getAllDamageReports();
            model.addAttribute("damageReports",damageReports);
            return "damage_report/damage_report";
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }

    /**
     * Method for handling when user attempts to search for specific damage reports
     * Adds the new list of damage reports to the model, based on the query and sets FilterOn to true,
     * in order to display the "clear filter" button
     * @param query - the query that the user has inputted in the search bar
     * @return String - returns String with damage_report html file address
     * or loginPage address if user isn't logged in
     * @Author Hans Erritzøe
     */
    @PostMapping("/damage_report_search")
    public String damage_report_search(@RequestParam String query,HttpSession session, Model model){
        if(userIsLoggedIn(session)){
            List<Damage_report> damageReports = damageReportService.getDamageReportsById(query);
            model.addAttribute("damageReports",damageReports);
            model.addAttribute("filterOn", true); //enables displaying the "clear filter" button
            return "damage_report/damage_report";
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }

    /**
     * Method for displaying the add_damage_report page
     * @return String - returns a string with the add_damage_report html file address
     * or loginpage address if user isn't logged in
     * @Author Hans Erritzøe
     */
    @GetMapping("/damage_report/add_damage_report")
    public String add_damage_report(HttpSession session, Model model){
        if(userIsLoggedIn(session)){
            return "damage_report/add_damage_report";
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }

    /**
     * Method for handling when user attempts to create a new Damage Report.
     * Checks if the car_id for the chosen car exists before proceeding to add.
     * If successfull, redirects to edit_damage_report page where the user can add damages to the report.
     * @param damage_report - the Damage_report object containing the values to be inserted in the DB
     * @return String - if successfull, returns a string that redirects to the edit_damage_report page
     * with the generated ID of the new Damage_report so the user can add the damages to the Damage report,
     * if either car doesnt exist with that id, or the insert failed: returns a string to the add_damage_report with
     * a corresponding error message added to the model
     * @Author Hans Erritzøe
     */
    @PostMapping("/damage_report/add_damage_report")
    public String addDamageReport(HttpSession session, Model model, @ModelAttribute Damage_report damage_report){
        if(userIsLoggedIn(session)){
            //check if car with id exists before creating damage report
            if(carService.doesCarExistWithId(damage_report.getCar_id_vehicle())){
                //get the generated ID for the report
                int rapportID = damageReportService.addDamageReport(damage_report);
                if(rapportID > 0){
                    model.addAttribute("successMessage","Success! Skaderapporten er nu oprettet");
                    return "redirect:/damage_report/edit_damage_report" + rapportID;
                } else {
                    model.addAttribute("errorMessage", "Fejl! Kunne ikke tilføje skaderapporten til databasen, prøv igen eller kontakt admin for hjælp");
                    return "damage_report/add_damage_report";
                }
            } else {
                model.addAttribute("errorMessage", "Kunne ikke oprette skaderapport. En bil med dette Vognnummer eksisterer ikke.");
                return "damage_report/add_damage_report";
            }
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }

    /**
     * Method for displaying the edit_damage_report page where the user can edit the damage report details,
     * along with adding, editing or removing the individual damages belonging to that damage report
     * @param id - the id of the damage_report to be edited
     * @return String - returns string containing the address of the edit_damage_report html file,
     * or login page address, if user isn't logged in
     * @Author Hans Erritzøe
     */
    @GetMapping("/damage_report/edit_damage_report{id_damage_report}")
    public String editDamageReport(HttpSession session, Model model, @PathVariable("id_damage_report") int id){
        if(userIsLoggedIn(session)){
            model.addAttribute("damageReport",damageReportService.getDamageReportByID(id));
            List<Damage> damageList = damageService.getAllDamagesForDamageReport(id);
            model.addAttribute("damageList", damageList);
            return "damage_report/edit_damage_report";
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }

    /**
     * Method for handling when user attempts to save the edits to a Damage report,
     * attempts to update the database with the edit and then display either success or failure message
     * @param damage_report - the Damage_report object to be updated in the database
     * @return String - calls the editDamageReport method to return the String of the edit_damage_report page,
     * with the correct damage_report to be displayed based on the damage report id
     * @Author Hans Erritzøe
     */
    @PostMapping("/damage_report/edit_damage_report{id_damage_report}")
    public String editDamageReport(HttpSession session, Model model, @ModelAttribute Damage_report damage_report){
        if(userIsLoggedIn(session)){
            model.addAttribute("damageReport", damage_report);
            boolean success = damageReportService.updateDamageReport(damage_report);
            if(success){
                model.addAttribute("successMessage","Success! Ændringer til skaderapporten blev gemt");
                return editDamageReport(session,model,damage_report.getId_damage_report());
            } else {
                model.addAttribute("errorMessage", "Kunne ikke gemme ændringerne til skaderapporten, kontakt admin for hjælp");
                return editDamageReport(session,model,damage_report.getId_damage_report());
            }
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }

    /**
     * Method for displaying the add_damage page with a reference to the damage report to which that damage is being added
     * @param id - id of the damage report to which the damage is being added
     * @return String - returns a string of the address to the html file for the add damage page,
     * or loginpage if user isn't logged in
     * @Author Hans Erritzøe
     */
    @GetMapping("/damage/add_damage{id_damage_report}")
    public String addDamage(HttpSession session, Model model, @PathVariable("id_damage_report") int id){
        if(userIsLoggedIn(session)){
            model.addAttribute("damage_report_id",id);
            return "damage/add_damage";
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }

    /**
     * Method for handling when user attempts to add a damage to a damage report and the DB, redirects to the edit page
     * of the damage report with success or failure message
     * @param damage - the Damage object to be added to the damage report and the DB
     * @return String - calls the editDamageReport() method to return the edit_damage_report address with the relevant
     * damage_report being displayed, or the userPage if user isn't logged in
     * @Author Hans Erritzøe
     */
    @PostMapping("/damage/add_damage")
    public String addDamage(HttpSession session, Model model, @ModelAttribute Damage damage){
        if(userIsLoggedIn(session)){
            boolean success = damageService.addDamage(damage);
            if(success){
                model.addAttribute("successMessage","Success! Skaden er tilføjet til skaderapporten");
                return editDamageReport(session,model,damage.getId_damage_report());
            } else {
                model.addAttribute("errorMessage", "Fejl! Kunne ikke oprette skaden, kontakt admin for hjælp");
                return editDamageReport(session,model,damage.getId_damage_report());
            }
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }

    /**
     * Method to handle when user attempts to remove a damage from a damage report
     * @param id - id of the damage to be removed
     * @param report_id - id of the damage report to which the damage belong
     * @return String - calls the editDamageReport() method to return the edit_damage_report address with the relevant
     * damage_report being displayed, or the userPage if user isn't logged in
     */
    @GetMapping("/delete_damage/{id_damage}/{id_damage_report}")
    public String deleteDamage(HttpSession session, Model model, @PathVariable("id_damage") int id, @PathVariable("id_damage_report") int report_id){
        if(userIsLoggedIn(session)){
            boolean success = damageService.deleteDamageByID(id);
            if(success){
                model.addAttribute("successMessage","Success! Skaden er slettet");
                return editDamageReport(session,model,report_id);
            } else {
                model.addAttribute("errorMessage", "Fejl! Kunne ikke slette skaden, kontakt admin for hjælp");
                return editDamageReport(session,model,report_id);
            }
        } else {
            model.addAttribute("loginErrorMessage", "Du er ikke logget ind - log ind for at kunne tilgå denne side");
            return "login/loginPage";
        }
    }

}
