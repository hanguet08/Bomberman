package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy {
    public Balloon(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        CollisionFlame();
    }

    @Override
    public void render(GraphicsContext gc) {
        if(!isMov){
            Direction();
            chooseSprite();
            gc.drawImage(img, x, y);
        }
        else {
            Die();
            gc.drawImage(img, x, y);
            Dem1();
        }
        animate1();
    }
    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                if(_moving)
                     img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 30).getFxImage();
                else img= Sprite.balloom_left1.getFxImage();
                break;
            case 2:
            case 3:
                if(_moving)
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 30).getFxImage();
                else img = Sprite.balloom_right1.getFxImage();
                break;
            default:
                img= Sprite.balloom_dead.getFxImage();
                break;
        }
    }
}
