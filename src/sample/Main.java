package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static java.lang.Math.min;
import static java.lang.Math.pow;

public class Main extends Application {

    class Pixel {
        double x, y;

        public Pixel(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    Stage stage;
    static final int WIDTH = 1200, HEIGHT = 700;
    Pixel[] initialPoints;
    Group root;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        root = new Group();

        int rand = (int) (Math.random() * 10 + 75);
        initialPoints = new Pixel[rand];

        for (int i = 0; i < rand; i++) {
            double x = Math.random() * WIDTH;
            double y = Math.random() * HEIGHT;
            initialPoints[i] = new Pixel(x, y);
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Pixel min = initialPoints[0];
                for (int k = 1; k < initialPoints.length; k++) {
                    if (pow(initialPoints[k].x - i, 2) + pow(initialPoints[k].y - j, 2) < pow(min.x - i, 2) + pow(min.y - j, 2)) {
                        min = initialPoints[k];
                    }
                }
                double color;
                color = (pow(min.x - i, 2) + pow(min.y - j, 2)) / 8500;
                color = min(color, 1);

                Rectangle rectangle = new Rectangle(i, j, 1, 1);
                root.getChildren().add(rectangle);
                rectangle.setFill(new Color(color, color, color, 1));
            }
        }
        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
