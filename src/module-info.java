module Libarary.Management.System {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;
    opens login;
    opens admin;
    opens studentData;
    opens add;
    opens issue;
    opens bookReturn;
    opens registration;
    opens student;
}