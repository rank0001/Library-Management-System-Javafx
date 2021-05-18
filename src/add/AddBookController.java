package add;

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

public class AddBookController implements Initializable {
    @FXML
    private TextField author;
    @FXML
    private TextField name;
    @FXML
    private TextField location;
    @FXML
    private TextField amount;

    @FXML
    private TableView<BookData> bookData;
    @FXML
    private TextField idToDelete;

    @FXML
    private TableColumn<BookData, String> nameColumn;
    @FXML
    private TableColumn<BookData, String> authorColumn;
    @FXML
    private TableColumn<BookData, String> locationColumn;
    @FXML
    private TableColumn<BookData, String> amountColumn;
    @FXML
    private TableColumn<BookData, String> idColumn;

    public DbConnection dbConnection;
    private ObservableList<BookData> data;
    private String sql = "SELECT * FROM books";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dbConnection = new DbConnection();
    }
    //displayin books
    @FXML
    private void loadData(ActionEvent event) throws SQLException {
        try {
            Connection connection = DbConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                this.data.add(new BookData(resultSet.getString(1), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(2), resultSet.getString(5)));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("error");
        }
        this.idColumn.setCellValueFactory(new PropertyValueFactory<BookData, String>("id"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<BookData, String>("name"));
        this.authorColumn.setCellValueFactory(new PropertyValueFactory<BookData, String>("author"));
        this.locationColumn.setCellValueFactory(new PropertyValueFactory<BookData, String>("location"));
        this.amountColumn.setCellValueFactory(new PropertyValueFactory<BookData, String>("amount"));
        this.bookData.setItems(null);
        this.bookData.setItems(this.data);

    }
    //adding books
    @FXML
    private void addData(ActionEvent event) {
        String sqlInsert = "INSERT INTO books (name,author,location,amount) VALUES (?,?,?,?)";

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlInsert);
            pr.setString(1, this.name.getText());
            pr.setString(2, this.author.getText());
            pr.setString(3, this.location.getText());
            pr.setString(4, this.amount.getText());
            pr.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //deleting book info
    @FXML
    private void deleteData(ActionEvent event) {
        String id = this.idToDelete.getText();

        String sqlDelete = "DELETE FROM books where id = " + id;

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlDelete);

            pr.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //clearing the input data
    @FXML
    private void clearData(ActionEvent event) {
        this.name.setText("");
        this.author.setText("");
        this.location.setText("");
        this.amount.setText("");
    }
}
