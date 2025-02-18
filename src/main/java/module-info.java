module com.example.csc311maze {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.csc311maze to javafx.fxml;
    exports com.example.csc311maze;
}