/*===================================================================================================
Class that defines all the properties on an Employee.
===================================================================================================*/

package sample;

public class Employee {
    private String name;
    private char attendance;
    private String department;

    public Employee(String name, char attendance, String department) {
        this.name = name;
        this.attendance = attendance;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public char getAttendance() {
        return attendance;
    }

    public String getDepartment() {
        return department;
    }
}