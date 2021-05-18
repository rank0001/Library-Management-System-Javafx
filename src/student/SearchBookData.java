package student;

import dbUtil.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchBookData {
    Connection connection;

    public SearchBookData() {
        try {
            this.connection = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }

    //Checking for the books in the database
    public String bookCheck(String name, String author) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT amount from books where name like '" + name + "%' and author like '"
                + author + "%'";
        try {
            pr = this.connection.prepareStatement(sql);
            rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            return null;
        } finally {
            {
                pr.close();
                rs.close();
            }
        }
        return null;
    }

}
