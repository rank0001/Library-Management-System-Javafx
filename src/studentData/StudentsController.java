package studentData;

import dbUtil.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {

    //fxml fields
    @FXML
    private TextField roll;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField amount;
    @FXML
    private TextField department;
    @FXML
    private TextField rollToDelete;
    @FXML
    private TableView<StudentInfo> studentData;
    @FXML
    private TableColumn<StudentInfo, String> nameColumn;
    @FXML
    private TableColumn<StudentInfo, String> rollColumn;
    @FXML
    private TableColumn<StudentInfo, String> passwordColumn;
    @FXML
    private TableColumn<StudentInfo, String> emailColumn;
    @FXML
    private TableColumn<StudentInfo, String> amountColumn;
    @FXML
    private TableColumn<StudentInfo, String> departmentColumn;

    //database connections
    public DbConnection dbConnection;
    private ObservableList<StudentInfo> data;
    private String sql = "SELECT * FROM student";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dbConnection = new DbConnection();
    }

    //displaying student info
    @FXML
    private void loadData(ActionEvent event) throws SQLException {
        try {
            Connection connection = DbConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                this.data.add(new StudentInfo(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("error");
        }
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("name"));
        this.rollColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("roll"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("email"));
        this.amountColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("amount"));
        this.departmentColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("department"));
        this.passwordColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("password"));
        this.studentData.setItems(null);
        this.studentData.setItems(this.data);
    }

    //adding student
    @FXML
    private void addData(ActionEvent event) {
        String sqlInsert = "INSERT INTO student(name,roll,password,email,department,amount) VALUES (?,?,?,?,?,?)";

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlInsert);
            pr.setString(1, this.name.getText());
            pr.setString(2, this.roll.getText());
            pr.setString(3, this.password.getText());
            pr.setString(4, this.email.getText());
            pr.setString(5, this.department.getText());
            pr.setString(6, this.amount.getText());
            pr.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //clearing input info
    @FXML
    private void clearData(ActionEvent event) {
        this.name.setText("");
        this.roll.setText("");
        this.password.setText("");
        this.amount.setText("");
        this.email.setText("");
        this.department.setText("");
    }

    //deleting student info
    @FXML
    private void deleteData(ActionEvent event) {
        String roll = this.rollToDelete.getText();

        String sqlDelete = "DELETE FROM student where roll = " + roll;

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlDelete);

            pr.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
