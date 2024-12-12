package org.example.gruppe9bilabonnement.Repository;


import org.example.gruppe9bilabonnement.Model.Car;
import org.example.gruppe9bilabonnement.Model.Customer;
import org.example.gruppe9bilabonnement.Model.Rental_contract;
import org.example.gruppe9bilabonnement.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate template;


    /**
     * Method to add a customer to the database based on the values of a Customer object
     * @param customer - Customer object containing the values to be inserted into the DB
     * @return boolean - true if successfully added to the DB, false if failed to add to DB
     * @Author - Hans Erritzøe
     */
    public boolean addCustomer(Customer customer) {
        String sql = """
                    INSERT INTO customer (firstname, lastname, email, phone)
                     VALUES (?, ?, ?, ?)
                    """;
        try {
            int rowsAffected = template.update(sql, customer.getFirstname(), customer.getLastname(), customer.getEmail(), customer.getPhone());
            return rowsAffected > 0;
        } catch (DataAccessException errorMessage){
            //could add logging the error here
            return false;
        }
    }

    /**
     * Method to return a list of all customers from the database
     * @return List<Customer> - list of all customers from the DB
     * @Author - Hans Erritzøe
     */
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql,rowMapper);
    }

    /**
     * Method to return a Customer object with values from the DB
     * @param id - id of the customer to be retrieved
     * @return Customer - object containing the customer information from the DB
     * @Author - Hans Erritzøe
     */
    public Customer getCustomerByID(int id) {
        String sql = "SELECT * FROM customer WHERE id_customer = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    /**
     * Method to return a List<Customer> object containing 1 or 0 Customer objects based on the search query (id)
     * @param query - user's search query (string)
     * @return List<Customer> - List containing 1 or 0 Customers based on search result
     */
    public List<Customer> getCustomerByID(String query){
        String sql = "SELECT * FROM customer WHERE id_customer = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql, rowMapper, query);
    }

    /**
     * Method to update a customer in the database based on the values of a Customer object
     * @param customer - Object containing the values to be updated in the database
     * @return boolean - true if successfully updated in the DB, false if failed to update
     * @Author - Hans Erritzøe
     */
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET firstname = ?, lastname = ?, email = ?, phone = ? WHERE id_customer = ?";
        int affectedRows = template.update(sql,customer.getFirstname(), customer.getLastname(), customer.getEmail(), customer.getPhone(), customer.getId_customer());
        return affectedRows > 0;
    }

}
