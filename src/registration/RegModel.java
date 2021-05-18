package registration;

import dbUtil.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegModel {
    Connection connection;

    public RegModel() {
        try {
            this.connection = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }

    //inserting student info from registration
    public void registerStudent(String name, String email, String roll, String password, String department) throws SQLException {
        String sqlInsert = "INSERT INTO student(roll,name,password,email,department,amount) VALUES (?,?,?,?,?,?)";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlInsert);
            pr.setString(1, roll);
            pr.setString(2, name);
            pr.setString(3, password);
            pr.setString(4, email);
            pr.setString(5, department);
            pr.setString(6, "0");
            pr.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //inserting a student info for login purpose
    public void loginStudent(String name, String password) throws SQLException {
        String sqlInsert = "INSERT INTO login(username,password,division) VALUES (?,?,?)";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlInsert);
            pr.setString(1, name);
            pr.setString(2, password);
            pr.setString(3, "student");
            pr.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
