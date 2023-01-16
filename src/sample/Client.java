package sample;

import java.sql.*;

public class Client {
    private  int id;
    private String name;
    private String pass;

    public Client(int id, String name, String pass) {
        this.id = id;
        this.pass = pass;
        this.name = name;

    }

    public Client() {

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public static ResultSet geAdmin(Client client) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Users", "postgres", "0000")) {
            String select = "select * from users where Name=? and Password=?";
            PreparedStatement preparedStatement= connection.prepareStatement(select);
            ResultSet resultSet =null;
            try {
                preparedStatement.setString(1, "administrator");
                preparedStatement.setString(2, client.getPass());
                resultSet = preparedStatement.executeQuery();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return  resultSet;
        }
    }
    public static ResultSet getUser(Client client) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Users", "postgres", "0000")) {
            String select = "select * from users where Name=? and Password=?";
            PreparedStatement preparedStatement= connection.prepareStatement(select);
            ResultSet resultSet =null;
            try {
                preparedStatement.setString(1, "polz");
                preparedStatement.setString(2, client.getPass());
                resultSet = preparedStatement.executeQuery();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return  resultSet;
        }
    }
}
