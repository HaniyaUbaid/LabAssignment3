package com.example.demo3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));

        Label nameLabel = new Label("FullName:");
        TextField fullNameField = new TextField();
        HBox nameBox = new HBox(10, nameLabel, fullNameField);

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();
        HBox idBox = new HBox(10, idLabel, idField);

        Label genderLabel = new Label("Gender:");
        RadioButton maleRadio = new RadioButton("Male");
        RadioButton femaleRadio = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, genderLabel, maleRadio, femaleRadio);

        Label provinceLabel = new Label("HomeProvince:");
        TextField provinceField = new TextField();
        HBox provinceBox = new HBox(10, provinceLabel, provinceField);

        Label dobLabel = new Label("DOB:");
        DatePicker dobPicker = new DatePicker();
        HBox dobBox = new HBox(10, dobLabel, dobPicker);

        Button newButton = new Button("New");
        Button deleteButton = new Button("Delete");
        Button restoreButton = new Button("Restore");
        Button criteriaButton = new Button("Criteria");
        Button closeButton = new Button("Close");
        Button findButton = new Button("Find Next");

        closeButton.setOnAction(event -> stage.close());


       restoreButton.setOnAction(event -> {
           fullNameField.clear();
           idField.clear();
           genderGroup.selectToggle(null);
           provinceField.clear();
           dobPicker.setValue(null);




       });
        newButton.setOnAction(event -> {
            String fullName = fullNameField.getText();
            String homeProvince = provinceField.getText();
            String id = idField.getText();
            String gender = genderGroup.getSelectedToggle() != null ?
                    ((RadioButton) genderGroup.getSelectedToggle()).getText() : "";
            String dob = dobPicker.getValue() != null ? dobPicker.getValue().toString() : "";

            if (!fullName.isEmpty() && !id.isEmpty() && !gender.isEmpty() && !homeProvince.isEmpty() && !dob.isEmpty()) {
                String record = fullName + "-" + id + "-" + gender + "-" + homeProvince + "-" + dob;

                AddToFile(record);


        }});

        VBox buttonLayout = new VBox(10, newButton, findButton, closeButton, criteriaButton, deleteButton, restoreButton);
        VBox leftLayout = new VBox(10, nameBox, idBox, genderBox, provinceBox, dobBox);
        HBox mainLayout = new HBox(20, leftLayout, buttonLayout);

        Scene scene = new Scene(mainLayout, 500, 300);
        stage.setTitle("Data Entry Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void AddToFile(String record) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("target/info.txt", true))) {
            writer.write(record);
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



    public static void main(String[] args) {
        launch();
    }
}