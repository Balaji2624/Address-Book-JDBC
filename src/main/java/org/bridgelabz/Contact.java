package org.bridgelabz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    // Method to delete contact from database
    public void deleteContactFromDatabase(Connection connection) throws SQLException {
        String query = "DELETE FROM contacts WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("No contact found with the given email.");
        }
    }

    // Method to read (retrieve) a contact from the database
    public static Contact getContactFromDatabase(Connection connection, String email) throws SQLException {
        String query = "SELECT * FROM contacts WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Extract data from the result set and create a Contact object
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String address = resultSet.getString("address");
            String city = resultSet.getString("city");
            String state = resultSet.getString("state");
            String zip = resultSet.getString("zip");
            String phoneNumber = resultSet.getString("phone_number");

            // Create and return the Contact object
            return new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
        } else {
            System.out.println("No contact found with the given email.");
            return null;
        }
    }
}

