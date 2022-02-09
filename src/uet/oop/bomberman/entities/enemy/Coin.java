package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Coin extends Enemy{
    public Coin(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public void update() {
        CollisionFlame();
    }

    @Override
    public void render(GraphicsContext gc) {
        if(!isMov){
            sp=2;
            Direction();
            chooseSprite();
            gc.drawImage(img, x, y);
        }
        else{
            Die();
            gc.drawImage(img,x,y);
            Dem1();
        }
        animate1();
    }

    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                if(_moving)
                     img = Sprite.movingSprite(Sprite.coin_right1, Sprite.coin_right2, Sprite.coin_right3, animate, 30).getFxImage();
                else  img = Sprite.coin_left1.getFxImage();
                break;
            case 2:
            case 3:
                if(_moving)
                      img = Sprite.movingSprite(Sprite.coin_left1, Sprite.coin_left2, Sprite.coin_left3,animate, 30).getFxImage();
                else img=Sprite.coin_right1.getFxImage();
                break;
            default:
                img = Sprite.coin_dead.getFxImage();
                break;
        }
    }
}
