package issue;

import dbUtil.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import login.LoginModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class IssueController implements Initializable {

    @FXML
    private TextField bookName;

    @FXML
    private TextField authorName;

    @FXML
    private TextField rollNumber;

    @FXML
    private Label location;

    private IssueModel issueModel;

    private DbConnection dbConnection;

    private Connection connection;
    //initialization
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dbConnection = new DbConnection();
        this.issueModel = new IssueModel();
        try {
            this.connection = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }
    //checking if the book is available and user has less than 3 alloted books
    @FXML
    private void searchBook(ActionEvent event) {
        String name = this.bookName.getText();
        String author = this.authorName.getText();
        String roll = this.rollNumber.getText();
        try {

            String location = this.issueModel.getLocation(name, author);
            String string_book_amount = this.issueModel.bookCheck(name, author);
            String string_student_amount = this.issueModel.getStudentAmount(roll);
            String book_name = this.issueModel.getBookName(name);
            if (string_book_amount != null) {
                int student_amount = Integer.parseInt(string_student_amount);
                int book_amount = Integer.parseInt(string_book_amount);
                if (book_amount > 0 && student_amount < 3) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    // alert.setHeaderText("Look, a Confirmation Dialog");
                    alert.setContentText("The book is available.Do you want to issue it?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setContentText("The book is issued and the book location is " + location);
                        book_amount--;
                        student_amount++;
                        System.out.println(book_amount + " " + student_amount);
                        this.issueModel.updateBookAmount(String.valueOf(book_amount), name, author);
                        this.issueModel.updateStudentAmount(String.valueOf(student_amount), roll);
                        this.location.setText(location);
                        this.issueModel.addStatsData(roll, book_name, String.valueOf(student_amount));
                        this.bookName.setText("");
                        this.authorName.setText("");
                        this.rollNumber.setText("");

                    } else {
                        //nothing happens here!
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    //alert.setHeaderText("Look, an Error Dialog");
                    alert.setContentText("You cant issue this book!");
                    alert.showAndWait();
                }
            } else {
                System.out.println("data could not be fetched!");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
