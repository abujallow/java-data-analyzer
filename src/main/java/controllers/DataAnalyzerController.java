package controllers;

import businesslogic.DataAnalyzer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataAnalyzerController {

    @FXML private Label statsArea;

    private final DataAnalyzer analyzer = new DataAnalyzer();
    private File currentFile;

    @FXML
    private void handleSelectFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Data File");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        Stage stage = (Stage) statsArea.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            currentFile = file;

            try {
                analyzer.analyzeFile(file.getAbsolutePath());
                statsArea.setText(analyzer.getFormattedResults());
            } catch (IOException | NumberFormatException e) {
                logError(e);
                statsArea.setText("Error reading file: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleExport(ActionEvent event) {
        if (currentFile == null) {
            statsArea.setText("No file analyzed yet.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Stats");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        fileChooser.setInitialFileName("analysis_results.txt");

        Stage stage = (Stage) statsArea.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(analyzer.getFormattedResults());
                statsArea.setText("Results exported successfully!");
            } catch (IOException e) {
                logError(e);
                statsArea.setText("Error writing file: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleHelp(ActionEvent event) {
        Stage stage = (Stage) statsArea.getScene().getWindow();

        statsArea.setText("""
                1. Press "Select File"
                2. Choose a text file containing numeric data
                3. Press "Export Stats to .txt"
                4. Choose a location to save the results
                """);
    }

    private void logError(Exception e) {
        try (FileWriter writer = new FileWriter("error.log", true)) {
            writer.write("ERROR: " + e.getMessage() + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}