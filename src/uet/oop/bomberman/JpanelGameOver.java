package uet.oop.bomberman;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.*;

public class JpanelGameOver extends AnchorPane{
    public Label gameOver ;
    public Label point;

    public JpanelGameOver() {
        gameOver = new Label("GAME OVER");
        gameOver.setLayoutX(140);
        gameOver.setLayoutY(50);
        gameOver.setFont(Font.font(120));
        gameOver.setTextFill(GREEN);

        point = new Label("POINT : "+ BombermanGame.point);
        point.setLayoutX(400);
        point.setLayoutY(220);
        point.setFont(Font.font(50));
        point.setTextFill(GREEN);

    }

    public void setGameOver() {
        gameOver.setTextFill(YELLOW);
        gameOver.setLayoutX(200);
        point.setTextFill(YELLOW);
        gameOver.setText("YOU WIN!");
    }
    public void setPane(){
        BombermanGame.ro1.getChildren().addAll(gameOver,point);
    }

    public void setPoint(int t) {
        point.setText("POINT : "+t);
    }

}
