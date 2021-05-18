package bookReturn;
import dbUtil.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
public class BookReturnController implements Initializable {

    @FXML
    private TextField bookName;

    @FXML
    private TextField authorName;

    @FXML
    private TextField rollNumber;

    @FXML
    private Label location;

    private BookReturnModel bookReturnModel;

    private DbConnection dbConnection;

    private Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dbConnection = new DbConnection();
        this.bookReturnModel = new BookReturnModel();
        try {
            this.connection = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }
    @FXML
    private void returnBook(ActionEvent event) {
        String name = this.bookName.getText();
        String author = this.authorName.getText();
        String roll = this.rollNumber.getText();
        try {
            String location = this.bookReturnModel.getLocation(name, author);
            String string_book_amount = this.bookReturnModel.bookCheck(name, author);
            String string_student_amount = this.bookReturnModel.getStudentAmount(roll);
            String book_name = this.bookReturnModel.getBookName(name);
            String id = this.bookReturnModel.getID(book_name,roll);
            String stat_book_amount = this.bookReturnModel.getBook(book_name,roll);
            if (string_book_amount != null && string_student_amount != null) {
                int student_amount = Integer.parseInt(string_student_amount);
                int book_amount = Integer.parseInt(string_book_amount);
                if ( student_amount>0 && this.bookReturnModel.isAvailable(roll,book_name,stat_book_amount)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setContentText("Do you want to return this book?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setContentText("The book is issued ");
                        book_amount++;
                        student_amount--;
                        System.out.println(book_amount + " " + student_amount);
                        this.bookReturnModel.updateBookAmount(String.valueOf(book_amount), name, author);
                        this.bookReturnModel.updateStudentAmount(String.valueOf(student_amount), roll);
                        this.location.setText(location);
                        this.bookReturnModel.deleteStatsData(id);
                        this.bookName.setText("");
                        this.authorName.setText("");
                        this.rollNumber.setText("");

                    } else {
                        //nothing happens here!
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("the operation is not possible!");
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

