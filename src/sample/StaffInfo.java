package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StaffInfo implements Initializable {
    @FXML
    TableView<Employee> employeeTable;
    @FXML
    TableColumn<Employee, String> colEmpName;
    @FXML
    TableColumn<Employee, Character> colAttendance;
    @FXML
    TableColumn<Employee, String> colDepartment;

    ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();

    public void createEmployees() {
        String url = "jdbc:mysql://127.0.0.1:3306/bank_db";
        Connection conn = null;
        Statement statement1 = null;
        ResultSet resultSet = null;

        String name, department;
        Character attendance;

        try {
            conn = DriverManager.getConnection(url, "root", "Zatchbellpa19#");
            statement1 = conn.createStatement();
            resultSet = statement1.executeQuery("select *from emp_details");

            while (resultSet.next()) {
                name = resultSet.getString("_name");
                attendance = resultSet.getString("attendance").charAt(0);
                department = resultSet.getString("department");
                Employee emp = new Employee(name, attendance, department);
                employeeObservableList.add(emp);
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement1 != null) {
                    statement1.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch(SQLException sqlException) {
                System.out.println("Error while closing connection...");
            }
        }
    }

    public void initializeTable() {
        /*Employee emp1 = new Employee("Venugopal Lyer", 'P', "Relationship Manager");
        Employee emp2 = new Employee("Subramanyam Swami", 'A', "Accountant");
        Employee emp3 = new Employee("Shivraj Babar", 'P', "Auditor");
        Employee emp4 = new Employee("Aniruddha Kamble", 'P', "Accountant");
        Employee emp5 = new Employee("Dipesh More", 'A', "Loan Officer");
        Employee emp6 = new Employee("Mihir Swain", 'P', "Accountant");
        Employee emp7 = new Employee("Suyog Patil", 'P', "Accountant");
        Employee emp8 = new Employee("Ghansham Choudary", 'P', "Auditor");
        Employee emp9 = new Employee("Vishal Jadhav", 'P', "Accountant");
        Employee emp10 = new Employee("Sujay Thalkar", 'A', "Treasurer");
        Employee emp11 = new Employee("Gauri Rajput", 'P', "Bank Teller");
        Employee emp12 = new Employee("Shivraj Mhatre", 'P', "Loan Officer");*/
        createEmployees();

       //empList.addAll(employeeObservableList);

        TableColumn<Employee, String> nameOfEmp = new TableColumn<>("Employee name");
        nameOfEmp.setMinWidth(400);
        nameOfEmp.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Employee, Character> empAttendance = new TableColumn<>("Attendance");
        empAttendance.setMinWidth(400);
        empAttendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));

        TableColumn<Employee, String> empDept = new TableColumn<>("Department");
        empDept.setMinWidth(400);
        empDept.setCellValueFactory(new PropertyValueFactory<>("department"));

        employeeTable.setItems(employeeObservableList);
        employeeTable.getColumns().add(nameOfEmp);
        employeeTable.getColumns().add(empAttendance);
        employeeTable.getColumns().add(empDept);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAttendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));

    }
}