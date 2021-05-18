package admin;

import add.AddBookController;
import bookReturn.BookReturnController;
import dbUtil.DbConnection;
import issue.IssueController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import studentData.StudentsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private DbConnection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.connection = new DbConnection();
    }

    //different admin sections

    @FXML
    private void addBooks(ActionEvent event) {

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/add/add.fxml").openStream());
            AddBookController addBookControl = (AddBookController) loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Book Details");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void issueBooks(ActionEvent event) {

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/issue/issue.fxml").openStream());
            IssueController addBookControl = (IssueController) loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Issue Books");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void returnBooks(ActionEvent event) {

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/bookReturn/bookReturn.fxml").openStream());
            BookReturnController bookReturnController = (BookReturnController) loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Returning books");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addStudent(ActionEvent event) {

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/studentData/students.fxml").openStream());
            StudentsController studentsController = (StudentsController) loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Adding Students");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
