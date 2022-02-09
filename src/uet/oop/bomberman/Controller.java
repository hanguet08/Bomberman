package uet.oop.bomberman;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private BombermanGame scen=new BombermanGame();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void changeScreen(ActionEvent event){
        BombermanGame.ai=0;
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scen.start(stage);
    }

    public void changeToAI(ActionEvent event) {
        BombermanGame.ai=1;
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scen.start(stage);
    }
}
