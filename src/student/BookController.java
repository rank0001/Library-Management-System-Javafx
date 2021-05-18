package student;

import dbUtil.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookController implements Initializable {

    public DbConnection dbConnection;
    private ObservableList<BookData> data;
    private String sql = "SELECT * FROM books";

    @FXML
    private TableView<BookData> bookData;
    @FXML
    private TableColumn<BookData, String> nameColumn;
    @FXML
    private TableColumn<BookData, String> authorColumn;
    @FXML
    private TableColumn<BookData, String> idColumn;
    @FXML
    private TableColumn<BookData, String> amountColumn;
    @FXML
    private TableColumn<BookData, String> locationColumn;

    //displaying all the books info
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dbConnection = new DbConnection();
        try {
            Connection connection = DbConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                this.data.add(new BookData(resultSet.getString(1), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(2), resultSet.getString(5)));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("error");
        }
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<BookData, String>("name"));
        this.locationColumn.setCellValueFactory(new PropertyValueFactory<BookData, String>("location"));
        this.authorColumn.setCellValueFactory(new PropertyValueFactory<BookData, String>("author"));
        this.amountColumn.setCellValueFactory(new PropertyValueFactory<BookData, String>("amount"));
        this.idColumn.setCellValueFactory(new PropertyValueFactory<BookData, String>("id"));

        this.bookData.setItems(null);
        this.bookData.setItems(this.data);
    }
}

