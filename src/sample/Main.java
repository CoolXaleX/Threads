package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Main extends Application {

    //ЕСЛИ ПОМЕНЯТЬ КОЛ-ВО СЕКТОРОВ, ТО ПОЛЕ БУДЕТ ИЗМЕНЯТЬСЯ
    private static final int COUNT_SECTORS = 5;
    private static final int ROOT_SIZE = 500;
    private static final int COUNT_THREADS = 10;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        Scene scene = new Scene(root, ROOT_SIZE, ROOT_SIZE, Color.BLACK);

        double r = ROOT_SIZE / COUNT_SECTORS / 2;

        ArrayList<Circle> circles = new ArrayList<>();

        Circle circle;
        for (int i = 0; i < COUNT_THREADS; i++) {
            circle = new Circle(10, 10, r);
            circle.setFill(new Color(Math.random(), Math.random(), Math.random(), 1));
            circles.add(circle);
        }

        root.getChildren().addAll(circles);

        primaryStage.setScene(scene);
        primaryStage.show();

        Controller controller = new Controller(COUNT_SECTORS, COUNT_THREADS);
        controller.start();

        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(100), //100ms
                        ae -> {
                            ArrayList<Position> positions = controller.getPositions();
                            for (int i = 0; i < positions.size(); i++) {
                                Position p = positions.get(i);
                                circles.get(i).setCenterX(r * 2 * (p.getX() + 1) - r);
                                circles.get(i).setCenterY(r * 2 * (p.getY() + 1) - r);
                            }
                        }
                )
        );

        //я не очень понял, как заставить анимацию повторяться не по счётчику,
        // по-этому запускаю на большое кол-во повторений
        timeline.setCycleCount(10000);
        timeline.setAutoReverse(true);
        timeline.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
