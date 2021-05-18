package issue;

import dbUtil.DbConnection;

import java.sql.*;

public class IssueModel {
    Connection connection;

    public IssueModel() {
        try {
            this.connection = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }

    //checking the availability of the book
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

    //Checking how many books the student already have
    public String getStudentAmount(String roll) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT amount from student where roll==" + roll;
        try {
            pr = this.connection.prepareStatement(sql);
            rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                pr.close();
                rs.close();
            } catch (SQLException e) {

            }
        }
        return null;
    }

    //getting the location of the book
    public String getLocation(String name, String author) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT location from books where name like '" + name + "%' and author like '"
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
            try {
                pr.close();
                rs.close();
            } catch (SQLException e) {

            }
        }
        return null;
    }

    //updating the number of the books after issuing
    public void updateBookAmount(String book_amount, String name, String author) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        char ch = '"';

        String sql_book = "UPDATE books SET amount =" + book_amount + " " + "where name like " + ch
                + name + "%" + ch + " " + "and author like " + ch + author + "%" + ch;

        System.out.println(sql_book);
        try {
            pr = this.connection.prepareStatement(sql_book);

            pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                pr.close();
                //    rs.close();
            } catch (SQLException e) {

            }
        }
    }

    //updating the number of books student has after allocation
    public void updateStudentAmount(String student_amount, String roll) throws SQLException {
        char ch = '"';
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql_student = "UPDATE student SET amount =" + student_amount + " " + "where roll =" + ch
                + roll + ch;
        System.out.println(sql_student);
        try {
            pr = this.connection.prepareStatement(sql_student);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                pr.close();
                //  rs.close();
            } catch (SQLException e) {

            }
        }
    }

    //retrieving the name of the book from the database
    public String getBookName(String name) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT name from books where name like '" + name + "%'";
        try {
            pr = this.connection.prepareStatement(sql);
            rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            return null;
        } finally {

            try {
                pr.close();
                rs.close();
            } catch (SQLException e) {

            }
        }
        return null;
    }

    public void addStatsData(String roll, String name, String amount) {
        String sqlInsert = "INSERT INTO stats (roll,book_name,amount) VALUES (?,?,?)";

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlInsert);
            pr.setString(1, roll);
            pr.setString(2, name);
            pr.setString(3, amount);
            pr.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
