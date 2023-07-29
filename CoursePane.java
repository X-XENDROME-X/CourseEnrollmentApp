//This Java code defines a JavaFX application that manages course enrollment for a user. It provides a graphical interface with buttons, text fields, and checkboxes to add and drop courses. The user can select a course's subject, course number, and instructor, and the application displays the enrolled courses along with a total count.

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.*;
import javax.xml.soap.Node;

public class CoursePane extends HBox {
    private ArrayList<Course> courseList;
    private VBox checkboxContainer;
    Button Add;
    Button Drop;
    ComboBox<String> SubjectCB;
    CheckBox CheckBox;
    TextField NumCourse;
    TextField NameInstructor;
    GridPane Formation;
    BorderPane LeftSide;
    BorderPane RightSide;
    BorderPane Bottom;
    TilePane Center;
    Label Subject;
    Label CourseNo;
    Label Instructor;
    Label SystemMessage;
    Label CoursesEnrolled;
    String CourcesEnrolledString = "Total Courses Enrolled:";
    int CoursesEnrollCount = 0;

    public CoursePane() {
        Label labelLeft = new Label("Add Course(s)");
        labelLeft.setTextFill(Color.BLUE);
        labelLeft.setFont(Font.font(null, 14));
        Label labelRight = new Label("Course(s) Enrolled");
        labelRight.setTextFill(Color.BLUE);
        labelRight.setFont(Font.font(null, 14));

        courseList = new ArrayList<Course>();
        SubjectCB = new ComboBox<String>();

        Add = new Button("Add =>");
        Drop = new Button("Drop <=");

        Subject = new Label("Subject");
        CourseNo = new Label("Course Num");
        Instructor = new Label("Instructor");
        SystemMessage = new Label("No Course Entered");
        CoursesEnrolled = new Label("Total Courses Enrolled : 0");

        checkboxContainer = new VBox();
        RightSide = new BorderPane();
        LeftSide = new BorderPane();
        Bottom = new BorderPane();
        Formation = new GridPane();
        Center = new TilePane(Orientation.VERTICAL);

        Subject.setFont(Font.font(null, 16));
        CourseNo.setFont(Font.font(null, 16));
        Instructor.setFont(Font.font(null, 16));
        SystemMessage.setFont(Font.font(null, 16));
        CoursesEnrolled.setFont(Font.font(null, 16));

        NameInstructor = new TextField();
        NumCourse = new TextField();

        SubjectCB.getItems().addAll("ACC", "AME", "BME", "CHM", "CSE", "DAT", "EEE");
        SubjectCB.setValue("CSE");
        CheckBox = new CheckBox();

        HBox H1 = new HBox(10);
        H1.setSpacing(10);
        HBox H2 = new HBox(10);
        H2.setSpacing(10);
        HBox H3 = new HBox(10);
        H3.setSpacing(10);

        GridPane top = new GridPane();
        GridPane bottom = new GridPane();

        bottom.add(SystemMessage, 0, 0);
        bottom.setAlignment(Pos.BOTTOM_LEFT);
        bottom.setPadding(new Insets(40, 10, 0, 0));

        H1.getChildren().addAll(Subject, SubjectCB);
        H2.getChildren().addAll(CourseNo, NumCourse);
        H3.getChildren().addAll(Instructor, NameInstructor);

        GridPane left = new GridPane();
        left.setAlignment(Pos.TOP_LEFT);
        left.setPadding(new Insets(10));
        left.add(labelLeft, 0, 0);

        Formation.add(left, 0, 0);
        Formation.add(top, 0, 0);
        Formation.add(H1, 0, 1);
        Formation.add(H2, 0, 2);
        Formation.add(H3, 0, 3);
        Formation.add(bottom, 0, 4);

        Formation.setVgap(40);
        Formation.setPadding(new Insets(10, 10, 10, 10));
        Formation.setStyle("-fx-border-color: purple;");

        Center.getChildren().addAll(Add, Drop);
        Center.setAlignment(Pos.CENTER);
        Center.setVgap(10);
        Center.setPadding(new Insets(10, 10, 10, 10));

        RightSide.setStyle("-fx-padding: 5;" + "-fx-boder-style: solid inside;" + "-fx-boder-width: 2;"
                + "-fx-border-radius: 2;" + "-fx-border-color: purple;");

        GridPane right = new GridPane();
        right.setAlignment(Pos.TOP_LEFT);
        right.setPadding(new Insets(10));
        right.add(labelRight, 0, 0);

        GridPane bottom_pane = new GridPane();
        bottom_pane.setAlignment(Pos.BOTTOM_LEFT);
        bottom_pane.setPadding(new Insets(10));
        bottom_pane.add(CoursesEnrolled, 0, 0);

        RightSide.setTop(right);
        RightSide.setCenter(checkboxContainer);
        RightSide.setBottom(bottom_pane);

        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(Formation, Center, RightSide);
        Add.setOnAction(new ButtonHandler());
        Drop.setOnAction(new ButtonHandler());
    }

    public void updateCheckBoxContainer() {
        checkboxContainer.getChildren().clear();
        for (Course course : courseList) {
            CheckBox CB = new CheckBox(course.toString());
            checkboxContainer.getChildren().add(CB);
        }
        CoursesEnrolled.setText("Total course enrolled: " + courseList.size());
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            Object source = event.getSource();

            try {
                if (source == Add) {
                    String Subject = (String) SubjectCB.getValue();
                    String NumCourseSt = NumCourse.getText().trim();
                    String InstructorSt = NameInstructor.getText().trim();
                    int CourseNumber = 0;

                    if (InstructorSt.length() == 0 || Subject.length() == 0 || InstructorSt.length() == 0) {
                        SystemMessage.setTextFill(Color.RED);
                        SystemMessage.setText("At Least One Field Is Empty. Fill All Fields.");
                        return;
                    }

                    CourseNumber = Integer.parseInt(NumCourseSt);
                    Course NewCourse = new Course(Subject, CourseNumber, InstructorSt);

                    for (Course C : courseList) {
                        if (C.toString().equals(NewCourse.toString())) {
                            SystemMessage.setTextFill(Color.RED);
                            SystemMessage.setText("Duplicated course - Not added.");
                            return;
                        }
                    }
                    courseList.add(NewCourse);
                    SystemMessage.setTextFill(Color.ORANGE);
                    SystemMessage.setText("Course added successfully.");
                    updateCheckBoxContainer();
                    SubjectCB.setValue("CSE");
                    NumCourse.setText("");
                    NameInstructor.setText("");
                } else if (source == Drop) {
                    ObservableList<javafx.scene.Node> h = checkboxContainer.getChildren();
                    ArrayList<Course> Removal = new ArrayList<>();
                    for (int i = 0; i < courseList.size(); i++) {
                        Course c = courseList.get(i);
                        if ((((CheckBox) h.get(i))).isSelected()) {
                            Removal.add(c);
                        }
                    }

                    courseList.removeAll(Removal);
                    updateCheckBoxContainer();
                    CoursesEnrolled.setText("Total course enrolled: " + courseList.size());
                }
            } catch (NumberFormatException ex) {
                SystemMessage.setTextFill(Color.RED);
                SystemMessage.setText("Error! Course number must be an integer.");
                return;
            } catch (Exception exception) {

            }
        }
    }

    public class ComboBoxHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String selectedSubject = SubjectCB.getValue();
            Subject.setText("The Subject: " + selectedSubject);
        }

        public class CheckBoxHandler implements EventHandler<ActionEvent> {
            private Course oneCourse;

            public CheckBoxHandler(Course oneCourse) {
                this.oneCourse = oneCourse;
            }

            public void handle(ActionEvent e) {
                CheckBox cb = (CheckBox) e.getSource();
                Course oneCourse = null;
                for (Course S : courseList) {
                    if (S.toString().equals(cb.getText())) {
                        oneCourse = S;
                        break;
                    }
                }

                if (oneCourse != null) {
                    if (cb.isSelected()) {
                        courseList.add(oneCourse);
                    } else {
                        courseList.remove(oneCourse);
                    }
                }
            }
        }
    }
}
