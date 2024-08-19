package com.supplyboost;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }// Load the properties from the file
            props.load(input);

            Class.forName(props.getProperty("db.driver"));

            try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"),
                    props.getProperty("db.username"), props.getProperty("db.password"));
                 Statement statement = connection.createStatement();
            ) {

                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM task")) {
                    while (resultSet.next()) {
                        int taskID = resultSet.getInt("id");
                        boolean completed = resultSet.getBoolean("completed");
                        String description = resultSet.getString("description");
                        String title = resultSet.getString("title");

                        System.out.println("ID: " + taskID +
                                ", Completed: " + completed +
                                ", Description: " + description +
                                ", Title: " + title);
                    }

                } catch (SQLException e) {

                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}