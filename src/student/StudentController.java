package student;

import dbUtil.DbConnection;
import issue.IssueController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    public DbConnection dbConnection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dbConnection = new DbConnection();
    }

    //Book Details Page
    @FXML
    private void showBooksInfo(ActionEvent event) {

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/student/booksDisplay.fxml").openStream());
            BookController bookController = (BookController) loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Books");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Search Book Page
    @FXML
    private void searchBooks(ActionEvent event) {

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/student/searchBook.fxml").openStream());
            SearchBookController searchController = (SearchBookController) loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Search For Books");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
