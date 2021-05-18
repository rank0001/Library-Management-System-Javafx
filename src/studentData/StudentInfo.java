package studentData;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentInfo {
    private final StringProperty name;
    private final StringProperty password;
    private final StringProperty email;
    private final StringProperty amount;
    private final StringProperty roll;
    private final StringProperty department;

    public StudentInfo(String roll, String name, String password, String email, String department, String amount) {
        this.roll = new SimpleStringProperty(roll);
        this.name = new SimpleStringProperty(name);
        this.password = new SimpleStringProperty(password);
        this.email = new SimpleStringProperty(email);
        this.amount = new SimpleStringProperty(amount);
        this.department = new SimpleStringProperty(department);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getRoll() {
        return roll.get();
    }

    public StringProperty rollProperty() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll.set(roll);
    }

    public String getDepartment() {
        return department.get();
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }
}