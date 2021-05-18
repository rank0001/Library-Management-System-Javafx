package registration;

import dbUtil.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegController implements Initializable {

    private DbConnection dbConnection;
    private Connection connection;
    private RegModel regModel;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password1;

    @FXML
    private PasswordField password2;

    @FXML
    private TextField roll;

    @FXML
    private TextField department;

    @FXML
    private Label confirmation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.dbConnection = new DbConnection();
        this.regModel = new RegModel();
        try {
            this.connection = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }

    //registration page
    @FXML
    private void register(ActionEvent event) {
        String password = this.password1.getText();
        String confirmPassword = this.password2.getText();
        String name = this.name.getText();
        String email = this.email.getText();
        String roll = this.roll.getText();
        String department = this.department.getText();
        if (!password.equals(confirmPassword)) {
            confirmation.setText("Your Passwords don't match!");
        } else {
            if (name != null && email != null && roll != null && department != null) {
                try {
                    this.regModel.registerStudent(name, email, roll, password, department);
                    this.regModel.loginStudent(name, password);
                    confirmation.setText("registration completed successfully!");
                } catch (SQLException e) {
                    e.printStackTrace();
                    confirmation.setText("Registration is invalid!");
                }
            }
        }
    }
}
