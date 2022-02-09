package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AIAdvance;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    public Oneal(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        CollisionFlame();
    }
    @Override
    public void render(GraphicsContext gc) {
        if(!isMov) {
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

    @Override
    public void Direction() {
        super.Direction();
        Entity a=BombermanGame.bomberman;
        _ai = new AIAdvance(BombermanGame.bomberman, this);
        _direction  = _ai.calculateDirection(_direction);
        if(Math.sqrt((x-a.x)*(x-a.x)+(y-a.y)*(y-a.y))<=100) speed=speed/2;
    }
    protected void chooseSprite() {
        switch(_direction) {
            case 0:
            case 1:
                if(_moving)
                    img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3,animate, 30).getFxImage();
                else
                    img = Sprite.oneal_left1.getFxImage();
                break;
            case 2:
            case 3:
                if(_moving)
                    img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3,animate, 30).getFxImage();
                else
                    img = Sprite.oneal_left1.getFxImage();
                break;
        }
    }
}
