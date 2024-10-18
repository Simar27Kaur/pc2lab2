package com.example.lab2;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;
public class HelloController implements Initializable {
    public TextField iid;
    public TextField iname;
    public TextField idoctor;
    public TextField iroom;
    @FXML
    private TableView<Appointment> tableView;
    @FXML
    private TableColumn<Appointment, Integer> id;
    @FXML
    private TableColumn<Appointment, String> name;
    @FXML
    private TableColumn<Appointment, String> doctor;
    @FXML
    private TableColumn<Appointment, Integer> room;
    ObservableList<Appointment> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new
                PropertyValueFactory<Appointment, Integer>("id"));
        name.setCellValueFactory(new
                PropertyValueFactory<Appointment, String>("name"));
        doctor.setCellValueFactory(new
                PropertyValueFactory<Appointment, String>("doctor"));
        room.setCellValueFactory(new
                PropertyValueFactory<Appointment, Integer>("room"));
        tableView.setItems(list);
    }

    @FXML
    protected void onHelloButtonClick() {
        list.clear();
        tableView.setItems(list);
        populateTable();
    }

    public void populateTable() {

        String getid = iid.getText();
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab2a";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM tbl_appointment";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String doctor = resultSet.getString("doctor");
                int room = resultSet.getInt("room");
                tableView.getItems().add(new Appointment(id, name, doctor,
                        room));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadData(ActionEvent actionEvent) {

        String getId = iid.getText();
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database\

            String query = "SELECT * FROM appointment_table WHERE`id` = '" + getId + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String doctor = resultSet.getString("doctor");
                int room = resultSet.getInt("room");

                iname.setText(name);
                idoctor.setText(doctor);
                iroom.setText(String.valueOf(room));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(ActionEvent actionEvent) {
        String getId = iid.getText();
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database\

            String query = "DELETE FROM appointment_table WHERE`id` = '" + getId + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database


        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void insertData(ActionEvent actionEvent) {

        String getId = iid.getText();
        String getName = iname.getText();
        String getDoctor = idoctor.getText();
        String getRoom = iroom.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/lab2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database\

            String query = "INSERT INTO 'appointment_table'(`name`, `doctor`, `room`) VALUES ('"+getName+"','"+getDoctor+"','"+getRoom+"') ";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database


        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void updateData(ActionEvent actionEvent) {

        String getId = iid.getText();
        String getName = iname.getText();
        String getDoctor = idoctor.getText();
        String getRoom = iroom.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/lab2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database\

            String query = "UPDATE `tbl_appointment` SET `name`='"+getName+"',`doctor`='"+getDoctor+"',`room`='"+getRoom+"' WHERE `id` = '"+getId+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database


        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void updateDate(ActionEvent actionEvent) {

    }
}