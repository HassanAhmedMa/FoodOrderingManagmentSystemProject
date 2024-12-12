package Entities;


import Personchild.Customer;

import java.util.HashMap;
import java.util.Scanner;



public class UserManager {
    private HashMap<String, Customer> users;



    /**
     * Constructor to initialize the UserManager class.
     * Initializes an empty HashMap for storing user data.
     */
    public UserManager() {
        users = new HashMap<>();
    }

    // Register a new user

    /**
     * Registers a new user.
     *
     * @param username The username chosen by the user.
     * @param password The password chosen by the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email address of the user.
     * @param phoneNumber The phone number of the user.
     * @return            `true` if the user is registered successfully, `false` if the username already exists.
     */

    public boolean register(String username, String password,String firstName, String lastName, String email, String phoneNumber ) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
            return false;
        }
        Customer newUser = new Customer(username, password,firstName,lastName,email,phoneNumber);
        users.put(username, newUser);
        System.out.println("User registered successfully!");
        return true;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Logs in a user with the given username and password.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return         The `Customer` object of the logged-in user if credentials match, otherwise `null`.
     */

// Login a user
    public Customer login(String username, String password) {
        Customer user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            return user;
        }
        System.out.println("Invalid username or password.");
        return null;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Main method to test

}

