package Title;

import MainGame.GameManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static GameManager _gm;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        _gm = new GameManager(primaryStage);
        _gm.Start();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
