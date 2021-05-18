package login;

import dbUtil.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection connection;

    public LoginModel() {
        try {
            this.connection = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }

    //checking if the database is connected
    public boolean isDatabaseConnected() {
        return this.connection != null;
    }

    //Validating login process
    public boolean isLogin(String user, String pass, String opt) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM login where username = ? and password = ? and division = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, pass);
            pr.setString(3, opt);
            rs = pr.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        } finally {
            {
                pr.close();
                rs.close();
            }
        }
    }
}
