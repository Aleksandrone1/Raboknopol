package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller {
    @FXML
private Button vxod;
    @FXML
    private TextArea login;
    @FXML
    private PasswordField pass;
    @FXML
    void initialize() {
    vxod.setOnAction(event -> {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection ignored = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Users", "postgres", "0000")) {
            Client client = new Client();
            Client client1 = new Client();
            String password = pass.getText();
            String password1 = pass.getText();
            client1.setPass(password1);
            client.setPass(password)   ;
            ResultSet resultSet = Client.geAdmin(client);
            ResultSet resultSet1 = Client.getUser(client1);
            int count = 0;
            int count1 = 0;
            while (resultSet.next()) {
                count++;
            }
            while (resultSet1.next()) {
                count1++;
            }
            if (count >= 1) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/sample/Administator.fxml"));
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.showAndWait();
                System.out.println("Работает");
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Данные введены не верно");

                alert.showAndWait();
            }
        if (count1 >= 1) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/sample/polz.fxml"));
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.showAndWait();
                System.out.println("Работает");
            }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Данные введены не верно");

            alert.showAndWait();
        }
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
        }
    });
    }


    class DBConnector {
        private final Connection connection;

        DBConnector() throws ClassNotFoundException, SQLException {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/users", "postgres", "0000");
        }

        public ObservableList<Client> getUsers2(String selectRequest) throws SQLException {
            ObservableList<Client> res2 = FXCollections.observableArrayList();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(selectRequest);
            int id;
            String name;
            String pass;
            while (set.next()) {
                id = set.getInt("id_");
                name = set.getString("name");
                pass = set.getString("pass");

                res2.add(new Client(id, name, pass));
            }
            return res2;
        }
        public Connection getConnection() {
            return connection;
        }
    }
}
