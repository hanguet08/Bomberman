package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.enemy.*;
import uet.oop.bomberman.entities.items.Bombs;
import uet.oop.bomberman.entities.items.Flames;
import uet.oop.bomberman.entities.items.Portal;
import uet.oop.bomberman.entities.items.Speed;
import uet.oop.bomberman.entities.tiles.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.LevelFile;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.*;

public class BombermanGame extends Application implements Initializable {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 14;
    private GraphicsContext gc;
    private GraphicsContext gci;
    private Canvas canvas;
    public static LevelFile level = new LevelFile();
    public static List<AnimatedEntity> entities = new ArrayList<>();
    public static List<Bomb> bom = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Portal>portals=new ArrayList<>();
    public static Bomber bomberman;
    public static double Xb = 1;
    public static double Yb = 2;
    public static int ai;
    public static int path = 1;
    public static int Enemies = 0;
    public static int point = 0;
    public static int lives = 3;
    public static int time = 200;
    public static boolean pass5 = false;
    public static AnchorPane ro = new AnchorPane();
    public static AnchorPane ro1 = new AnchorPane();
    public static StackPane roo = new StackPane();
    public static JPANEL jpanel = new JPANEL();
    public static JpanelGameOver jpanelGameOver = new JpanelGameOver();
    public Group root = new Group();
    public double xx = 0;
    public double yy = 0;
    public boolean moving = true;
    public static int dem =1;

    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        //gci = canvas.getGraphicsContext2D();

        // Tao root container
        roo.getChildren().addAll(new Rectangle(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT, GREEN));
        roo.getChildren().addAll(new Text("Loading..."));
        root.getChildren().add(roo);

        ro1.getChildren().addAll(new Rectangle(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT , BLACK));
        jpanelGameOver.setPane();
        root.getChildren().add(ro1);

        ro.getChildren().addAll(new Rectangle(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE, BLACK));
        jpanel.setPanel();
        root.getChildren().add(ro);

        root.getChildren().add(canvas);
        // Tao scene
        Scene scene = new Scene(root);
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
        createMap();
        bomberman = new Bomber(Xb, Yb, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        if (ai == 0) {
            if (moving){
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        bomberman.anime(event, bomberman);
                    }
                });
        }
    }
}


    public static void createMap() {

        level.insertFile(String.valueOf(path));
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 1; j < HEIGHT; j++) {
                addLevelEntity( level.lineTiles_[j].charAt(i),(double) i,(double) j );
            }
        }
    }
    public void demTime() {
        if(dem<200*60){
            dem++;
        }
    }

    public static void addLevelEntity(char c, double x, double y) {
        switch(c) { // TODO: minimize this method
            case '#':
                stillObjects.add(new Wall(x, y, Sprite.wall.getFxImage()));
                break;
            case '*':
                stillObjects.add(new Grass(x,y, Sprite.grass.getFxImage()));
                stillObjects.add(new Brick(x, y, Sprite.brick.getFxImage()));
                break;
            case 'x':
                stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage()));
                stillObjects.add(new Portal(x,y, Sprite.portal.getFxImage()));
                stillObjects.add(new Brick(x, y, Sprite.brick.getFxImage()));
                portals.add(new Portal(x,y, Sprite.portal.getFxImage()));
                break;
            case 'f':
                stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage()));
                stillObjects.add(new Flames(x,y, Sprite.powerup_flames.getFxImage()));
                stillObjects.add(new Brick(x, y, Sprite.brick.getFxImage()));
                break;
            case 's':
                stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage()));
                stillObjects.add(new Speed(x,y, Sprite.powerup_speed.getFxImage()));
                stillObjects.add(new Brick(x, y, Sprite.brick.getFxImage()));
                break;
            case 'b' :
                stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage()));
                stillObjects.add(new Bombs(x,y,Sprite.powerup_bombs.getFxImage()));
                stillObjects.add(new Brick(x, y, Sprite.brick.getFxImage()));
                break;
            case '1':
                Enemy balloon = new Balloon(x, y, Sprite.balloom_dead.getFxImage());
                stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage()));
                //entities.add(balloon);
                enemies.add(balloon);
                Enemies++;
                break;
            case '2':
                Enemy oneal =new Oneal(x, y, Sprite.oneal_dead.getFxImage());
                stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage()));
                //entities.add(oneal);
                enemies.add(oneal);
                Enemies++;
                break;
            case '3':
                Enemy kondoria =new Kondoria(x, y, Sprite.kondoria_dead.getFxImage());
                stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage()));
                //entities.add(oneal);
                enemies.add(kondoria);
                Enemies++;
                break;
            case '5':
                Enemy doll =new Doll(x, y, Sprite.doll_dead.getFxImage());
                stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage()));
                //entities.add(oneal);
                enemies.add(doll);
                Enemies++;
                break;
            case '4':
                Enemy coin =new Coin(x, y, Sprite.coin_dead.getFxImage());
                stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage()));
                //entities.add(oneal);
                enemies.add(coin);
                Enemies++;
                break;
            default:
                stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage()));
                break;
        }
    }

    public void update(){
       Iterator<AnimatedEntity> iter = BombermanGame.entities.iterator();
        while (iter.hasNext()) {
            AnimatedEntity animatedEntity=iter.next();
            if(lives==0) {
                iter.remove();
                break;
            }
            else  animatedEntity.update();
        }
        //entities.forEach(AnimatedEntity::update);
        Iterator<Bomb> iterator = BombermanGame.bom.iterator();
        while (iterator.hasNext()){
            Bomb bomb = iterator.next();
            if(bomb.timeToDie<=0) {
                iterator.remove();
                break;
            }
            else bomb.update();
        }
        Iterator<Enemy> iterator1 = BombermanGame.enemies.iterator();
        while (iterator1.hasNext()){
            Enemy e = iterator1.next();
            /*if(e instanceof Coin && e.dem1>=75) {
                xx=e.x;
                yy=e.y;
                e = new Balloon(xx,yy,Sprite.balloom_dead.getFxImage());
                e.dem1=0;
                e.isMov=false;
                Enemies++;
                break;
            } */
            if(e.dem1>=45) {
                if(e instanceof Coin) point+=150;
                if(e instanceof Balloon) point+=100;
                if(e instanceof Oneal) point+=150;
                if(e instanceof Kondoria) point+=150;
                if(e instanceof Doll) point+=200;
                jpanel.setPoint(point);
                iterator1.remove();
                break;
            }
            else e.update();
        }
        Iterator<Entity> iterator2 = BombermanGame.stillObjects.iterator();
        while (iterator2.hasNext()){
            Entity e1 =  iterator2.next();
            if(e1 instanceof Brick) {
                Brick e2 = (Brick) e1;
                if (e2.dem >= 45) {
                    iterator2.remove();
                    break;
                } else e2.update();
            }
            else e1.update();
        }

        jpanel.setLevel(path);
        jpanel.setTimes(time);
        jpanel.setPoint(point);
        jpanel.setLives(lives);
        if(lives==0&&!pass5) {
            root.getChildren().remove(ro);
            root.getChildren().remove(roo);
            jpanelGameOver.setPoint(point);
            moving=false;
        }
        else if(pass5){
            lives=0;
            root.getChildren().remove(ro);
            root.getChildren().remove(roo);
            stillObjects.removeAll(stillObjects);
            enemies.removeAll(enemies);
            bom.removeAll(bom);
            jpanelGameOver.setGameOver();
            jpanelGameOver.setPoint(point);
            moving=false;
        }

    }

    public void render() {
        demTime();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        bom.forEach(g -> g.render(gc));
        if(dem%60==0) time--;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
