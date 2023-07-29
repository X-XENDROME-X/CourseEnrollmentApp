// Below creates a graphical interface for a course enrollment system. It utilizes the CoursePane class to manage course enrollment, and the main stage displays the system with a fixed size of 600x400 pixels.

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class Main extends Application {
    public static final int WIDTH = 600, HEIGHT = 400;

    public void start(Stage stage) {
        StackPane root = new StackPane();
        CoursePane coursePane = new CoursePane();
        root.getChildren().add(coursePane);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("Course Enrollment System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
