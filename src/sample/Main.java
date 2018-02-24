package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    //ЕСЛИ ПОМЕНЯТЬ КОЛ-ВО СЕКТОРОВ, ТО ПОЛЕ БУДЕТ ИЗМЕНЯТЬСЯ
    private static final int COUNT_SECTORS = 4;
    private static final int ROOT_SIZE = 500;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();

        Scene scene = new Scene(root, ROOT_SIZE, ROOT_SIZE, Color.BLACK);

        double r = ROOT_SIZE / COUNT_SECTORS / 2;

        Circle circle1 = new Circle(10, 10, r);
        circle1.setFill(Color.BLUE);
        Circle circle2 = new Circle(10, 10, r);
        circle2.setFill(Color.RED);

        root.getChildren().addAll(circle1, circle2);

        primaryStage.setScene(scene);
        primaryStage.show();


        Controller controller = new Controller();
        controller.start();


//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//
    }


    public static void main(String[] args) {
        launch(args);
    }
}
