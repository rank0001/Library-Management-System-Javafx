package student;

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

public class SearchBookController implements Initializable {
    private DbConnection dbConnection;
    private Connection connection;
    private SearchBookData searchBookData;

    @FXML
    private TextField bookName;

    @FXML
    private TextField authorName;

    @FXML
    private Label bookInfo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dbConnection = new DbConnection();
        this.searchBookData = new SearchBookData();
        try {
            this.connection = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }

    //searching a particular book
    @FXML
    private void searchBook(ActionEvent event) {
        String name = this.bookName.getText();
        String author = this.authorName.getText();
        System.out.println(name + " " + author);
        try {
            String string_book_amount = this.searchBookData.bookCheck(name, author);
            if (string_book_amount != null) {
                int book_amount = Integer.parseInt(string_book_amount);
                if (book_amount > 0) {
                    this.bookInfo.setText("The Book is available");
                } else {
                    this.bookInfo.setText("The Book is not available");
                }
                this.authorName.setText("");
                this.bookName.setText("");
            } else {
                this.bookInfo.setText("The Book is not available");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





