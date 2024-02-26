import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class PuzzleCreatorScene {
    private Scene scene;
    private MainApp mainApp;
    private Button createPuzzleButton;
    private ChoiceBox<String> gridSizeChBox;
    private ChoiceBox<String> diffChBox;

    public PuzzleCreatorScene(MainApp mainApp) {
        this.mainApp = mainApp;
        this.gridSizeChBox = new ChoiceBox<>();
        this.diffChBox =  new ChoiceBox<>();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(100));
        VBox centerBox = setupCenterBox();
        root.setCenter(centerBox);
        root.setBottom(createPuzzleButton);
        this.scene = new Scene(root, 800, 600);
    }

    private VBox setupCenterBox() {
        VBox centerBox = new VBox(35);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(0, 0, 0, 0));

        Label titleL = new Label("Select Your Puzzle Parameters");
        titleL.setFont(new Font("Time New Romans", 40));
        titleL.setTextFill(Color.DARKGREEN);

        Label descriptionL = new Label("Select your grid size and difficulty range below" );
        descriptionL.setWrapText(true);
        descriptionL.setTextAlignment(TextAlignment.CENTER);
        descriptionL.setFont(new Font("Calibri", 16));

        //gridSizeChBox.setPrefWidth(1000);
        gridSizeChBox.setPrefHeight(5);
        gridSizeChBox.setStyle("-fx-font-size: 16px;" + // Larger text size
                "-fx-background-radius: 5px;" + // Rounded corners
                "-fx-border-radius: 5px;" + // Rounded border corners to match
                "-fx-padding: 5px 10px;"); // Larger padding for a bigger box
        gridSizeChBox.getItems().addAll("3x4", "3x5", "4x4", "4x5", "4x6", "4x7");
        gridSizeChBox.setValue("3x4");

        diffChBox.getItems().addAll("Easy", "Intermediate", "Hard");
        diffChBox.setStyle("-fx-font-size: 16px;" + // Larger text size
                "-fx-background-radius: 5px;" + // Rounded corners
                "-fx-border-radius: 5px;" + // Rounded border corners to match
                "-fx-padding: 5px 10px;"); // Larger padding for a bigger box
        diffChBox.setValue("Easy");

        createPuzzleButton = new Button("Create Puzzle");
        createPuzzleButton.setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");
        createPuzzleButton.setFont(new Font("Calibri", 22));
        createPuzzleButton.setTextFill(Color.WHITE);
        createPuzzleButton.setStyle("-fx-background-color: #006400; -fx-padding: 20;");
        createPuzzleButton.setMinWidth(600);
        createPuzzleButton.setOnAction(e -> createPuzzle());

        centerBox.getChildren().addAll(titleL, descriptionL, gridSizeChBox, diffChBox, createPuzzleButton);
        return centerBox;
    }

    private void createPuzzle() {
        if ("3x4".equals(gridSizeChBox.getValue()) && "Easy".equals(diffChBox.getValue())) {
            // If the selected grid size and difficulty are as required, switch to the GridDisplayScene
            mainApp.showGridDisplayScene(gridSizeChBox.getValue(), diffChBox.getValue());
        } else {
            // If the conditions are not met, show an alert to the user
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Incorrect Puzzle Parameters");
            alert.setContentText("Choose '3x4 Grid' for your grid size and 'Easy' as your difficulty setting. Additional options will be made available in the future.");
            alert.showAndWait();
        }
    }

    public Scene getScene() {
        return scene;
    }

    // Getter for the selected grid size
    public String getSelectedGridSize() {
        return gridSizeChBox.getValue();
    }

    // Getter for the selected difficulty level
    public String getSelectedDifficultyLevel() {
        return diffChBox.getValue();
    }

}
