package org.example.gruppe9bilabonnement.Service;

import org.example.gruppe9bilabonnement.Model.Customer;
import org.example.gruppe9bilabonnement.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    /**
     * Calls repository layer to retrieve a list of all Customer objects in the database
     * @return List<Customer> - the list of customers from the DB
     * @Author - Hans Erritzøe
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    /**
     * Calls repository layer to add a customer to the DB
     * @param customer - Customer object containing values to be inserted in the DB
     * @return boolean - true if successfully added to DB, false i failed to add to DB
     * @Author - Hans Erritzøe
     */
    public boolean addCustomer(Customer customer) {
        return customerRepository.addCustomer(customer);
    }

    /**
     * Calls repository layer to retrieve a Customer object based on an ID
     * @param id - id of the customer to be retrieved
     * @return Customer - object containing the current values of the customer
     * @Author - Hans Erritzøe
     */
    public Customer getCustomerByID(int id) {
        return customerRepository.getCustomerByID(id);
    }

    /**
     * Calls repository layer to retrieve a List<Customer> object based on an ID, list because html table expect list to display
     * @param query - user's search query
     * @return List<Customer> - list containing 1 or 0 Customer objects to be displayed in the html table
     * @Author - Hans Erritzøe
     */
    public List<Customer> getCustomerByID(String query) {
        return customerRepository.getCustomerByID(query);
    }

    /**
     * Calls repository layer to update a customer in the database based on a Customer object's values
     * @param customer - Customer objects containing the values to be updated in the DB
     * @return boolean - True if successfully updated, false if failed to update
     * @Author - Hans Erritzøe
     */
    public boolean updateCustomer(Customer customer) {
        return customerRepository.updateCustomer(customer);
    }

}
