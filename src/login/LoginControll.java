package login;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import registration.RegController;
import student.StudentController;
import studentData.StudentsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginControll implements Initializable {

    LoginModel loginModel = new LoginModel();

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<Option> comboBox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.comboBox.setItems(FXCollections.observableArrayList(Option.values()));
    }

    //validating login process
    @FXML
    public void login(ActionEvent event) {
        try {
            if (this.loginModel.isLogin(this.username.getText(), this.password.getText(), ((Option) this.comboBox.getValue()).toString().toLowerCase())) {

                Stage stage = (Stage) this.loginButton.getScene().getWindow();
                stage.close();
                switch (((Option) this.comboBox.getValue()).toString()) {
                    case "Admin":
                        adminLogin();
                        break;
                    case "Student":
                        studentLogin();
                        break;
                }
            } else {
                this.loginStatus.setText("Wrong credentials!");
            }
        } catch (Exception localException) {
            System.out.println("exception occured!");
        }
    }

    //registration page
    @FXML
    public void register(ActionEvent event) {

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/registration/reg.fxml").openStream());
            RegController regController = (RegController) loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Registration");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //student dashboard
    public void studentLogin() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/student/student.fxml").openStream());
            StudentController studentController = (StudentController) loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Student Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //admin dashboard
    public void adminLogin() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/admin/admin.fxml").openStream());
            AdminController adminController = (AdminController) loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Admin Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
