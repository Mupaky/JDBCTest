package com.supplyboost;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=tasknotes";
    private static String name = "sa";
    private static String password = "Peralnq1";
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
             ){

            try(ResultSet resultSet = statement.executeQuery("SELECT * FROM task")){
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

            }catch (SQLException e){

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}