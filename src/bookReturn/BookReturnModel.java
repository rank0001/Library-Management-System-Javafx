package bookReturn;
import dbUtil.DbConnection;
import javafx.event.ActionEvent;
import java.sql.*;

public class BookReturnModel{
    Connection connection;

    public BookReturnModel() {
        try{
            this.connection = DbConnection.getConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        if(this.connection==null){
            System.exit(1);
        }
    }

    public String bookCheck(String name,String author) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        //String sql = "SELECT * FROM books where name = ? and author LIKE = ?";
        String sql = "SELECT amount from books where name like '" + name + "%' and author like '"
                + author + "%'";
        try {
            pr = this.connection.prepareStatement(sql);
            // pr.setString(1,name);
            //pr.setString(2,author);
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
        return  null;
    }

    public String getStudentAmount(String roll) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        //String sql = "SELECT * FROM books where name = ? and author LIKE = ?";
        String sql = "SELECT amount from student where roll==" + roll;
        try {
            pr = this.connection.prepareStatement(sql);
            // pr.setString(1,name);
            //pr.setString(2,author);
            rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try{
                pr.close();
                rs.close();
            }
            catch (SQLException e){

            }
        }
        return  null;
    }

    public String getLocation(String name,String author) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        //String sql = "SELECT * FROM books where name = ? and author LIKE = ?";
        String sql = "SELECT location from books where name like '" + name + "%' and author like '"
                + author + "%'";
        try {
            pr = this.connection.prepareStatement(sql);
            // pr.setString(1,name);
            //pr.setString(2,author);
            rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try{
                pr.close();
                rs.close();
            }
            catch (SQLException e){

            }
        }
        return  null;
    }
    public void  updateBookAmount(String book_amount,String name,String author) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        char ch='"';

        String sql_book = "UPDATE books SET amount =" + book_amount + " " + "where name like " + ch
                + name + "%" + ch + " " + "and author like " + ch + author + "%" + ch;

        System.out.println(sql_book);
        try {
            pr =this.connection.prepareStatement(sql_book);

            pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try{
                pr.close();
                //    rs.close();
            }
            catch (SQLException e){

            }
        }
    }
    public void  updateStudentAmount(String student_amount,String roll) throws SQLException {
        char ch = '"';
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql_student = "UPDATE student SET amount =" + student_amount + " " + "where roll =" + ch
                + roll  + ch;
        System.out.println(sql_student);
        try {
            pr =this.connection.prepareStatement(sql_student);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try{
                pr.close();
                //  rs.close();
            }
            catch (SQLException e){

            }
        }
    }
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

            try{
                pr.close();
                rs.close();
            }
            catch (SQLException e){

            }
        }
        return  null;
    }
    public void deleteStatsData(String id){
        String sqlInsert = "DELETE FROM stats WHERE id = ?";

        try{
            Connection connection = DbConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlInsert);
            pr.setString(1,id);
            pr.executeUpdate();
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean isAvailable(String roll,String name,String amount) throws SQLException {
        PreparedStatement pr =null;
        ResultSet rs = null;
        String sql = "SELECT * FROM stats where roll = ? and book_name = ? and amount = ?";
        try{
            pr = this.connection.prepareStatement(sql);
            pr.setString(1,roll);
            pr.setString(2,name);
            pr.setString(3,amount);
            rs = pr.executeQuery();
            return rs.next();
        }
        catch (SQLException e ){
            return false;
        }
        finally {
            {
                pr.close();
                rs.close();
            }
        }
    }
    public String getID(String name,String roll) throws SQLException {
        char ch = '"';
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT id from stats where book_name=" + ch + name + ch +  " and roll =" + ch + roll + ch;
        System.out.println(sql);
        try {
            pr = this.connection.prepareStatement(sql);
            rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try{
                pr.close();
                rs.close();
            }
            catch (SQLException e){
            }
        }
        return  null;
    }
    public String getBook(String name,String roll) throws SQLException {
        char ch = '"';
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT amount from stats where book_name=" + ch + name + ch +  " and roll =" + ch + roll + ch;
        System.out.println(sql);
        try {
            pr = this.connection.prepareStatement(sql);
            rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try{
                pr.close();
                rs.close();
            }
            catch (SQLException e){
            }
        }
        return  null;
    }
}

