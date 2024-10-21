package org.bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AddressBook {

    // Method to establish a database connection
    public static Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/addressbookdb";
        String username = "root";
        String password = "Pass@123";
        return DriverManager.getConnection(jdbcURL, username, password);
    }

    public static void main(String[] args) {
        try {
            // Establishing the connection
            Connection connection = getConnection();
            System.out.println("Connected to the database!");

            // Creating contacts
            Contact contact1 = new Contact("Balaji", "Sapkal", "At Deulgaon Raja", "Buldhana", "IL", "62701", "555-1234", "balajisapkal302001@gmail.com");
            Contact contact2 = new Contact("Jane", "Smith", "456 Oak Street", "Metropolis", "NY", "10001", "555-5678", "jane.smith@example.com");
            Contact contact3 = new Contact("Alice", "Johnson", "789 Pine Street", "Gotham", "NJ", "07001", "555-4321", "alice.johnson@example.com");

            // Saving contacts to database
            contact1.saveToDatabase(connection);
            contact2.saveToDatabase(connection);
            contact3.saveToDatabase(connection);

            System.out.println("Contacts saved to the database!");

            Contact contactToUpdate = new Contact("John", "Doe", "321 Maple Street", "Springfield", "IL", "62702", "555-9876", "alice.johnson@example.com");

            // Update the contact in the database
            contactToUpdate.updateContactInDatabase(connection);

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

