package org.bridgelabz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Contact {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;

    // Constructor
    public Contact(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Method to display contact information
    public void displayContact() {
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Address: " + address);
        System.out.println("City: " + city + ", " + state + " " + zip);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println();
    }

    // Method to save contact to database
    public void saveToDatabase(Connection connection) throws SQLException {
        String query = "INSERT INTO contacts (first_name, last_name, address, city, state, zip, phone_number, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, address);
        preparedStatement.setString(4, city);
        preparedStatement.setString(5, state);
        preparedStatement.setString(6, zip);
        preparedStatement.setString(7, phoneNumber);
        preparedStatement.setString(8, email);

        preparedStatement.executeUpdate();
    }

    public void updateContactInDatabase(Connection connection) throws SQLException {
        String query = "UPDATE contacts SET first_name = ?, last_name = ?, address = ?, city = ?, state = ?, zip = ?, phone_number = ? WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, address);
        preparedStatement.setString(4, city);
        preparedStatement.setString(5, state);
        preparedStatement.setString(6, zip);
        preparedStatement.setString(7, phoneNumber);
        preparedStatement.setString(8, email);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Contact updated successfully.");
        } else {
            System.out.println("No contact found with the given email.");
        }
    }
}

