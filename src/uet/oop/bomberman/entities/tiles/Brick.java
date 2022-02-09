package uet.oop.bomberman.entities.tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Tile {
    public int dem = 0;
    public Brick(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
        if(!isMov) {
            img=Sprite.brick.getFxImage();
            gc.drawImage(img, x, y);
            animate1();
        }
        else {
            chooseSprite();
            gc.drawImage(img, x, y);
            Dem();
            animate1();
        }
    }

    public void Dem() {
        if (dem < 45) {
            dem++;
        }

    }
    public void chooseSprite() {
        img= Sprite.movingSprite(Sprite.brick_exploded,Sprite.brick_exploded1,Sprite.brick_exploded2,dem,45).getFxImage();
    }
}
