package duke;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/elon.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/mark.jpeg"));

    /**
     * Initializes the controller and sets up the initial state of the UI components.
     * This method is automatically called by the JavaFX framework when the associated
     * FXML file is loaded.
     * <p>
     * In this method:
     * - The vertical scroll position of the scrollPane is bound to the height of the
     * dialogContainer, ensuring that the scrollPane always shows the most recent
     * messages at the bottom.
     * - A welcome message is displayed in the dialogContainer.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        welcomeMessage();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        // Solution adapted and inspired from https://chat.openai.com/share/1eb7e367-a3b3-41b6-81bd-bdbf2a45edc5
        if (input.equalsIgnoreCase("bye")) {
            // Schedule a task to close the window after a 3-second delay
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close();
            }));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    //@@ruth-lim techjay-c-reused
    // Reused from MainWindow.java file

    /**
     * Show welcome message when the program is launched.
     */
    @FXML
    private void welcomeMessage() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(ui.displayWelcomeText(), dukeImage));
    }
}
