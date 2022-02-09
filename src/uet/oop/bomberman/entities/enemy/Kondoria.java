package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria extends Enemy{
    public Kondoria(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        CollisionFlame();
    }

    @Override
    public void render(GraphicsContext gc) {
        if(!isMov) {
            Direction1();
            chooseSprite();
            gc.drawImage(img, x, y);
        }else {
            Die();
            gc.drawImage(img, x, y);
            Dem1();
        }
        animate1();
    }

    public void right1(int v) {
        _moving=true;
        _direction=1;
        for(Entity a: BombermanGame.stillObjects){
            if (Math.floor(x/ Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE+Sprite.SCALED_SIZE==a.x
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y
                    &&!a.getClass().toString().contains("Grass")
                    &&!a.getClass().toString().contains("Brick")) {
                _moving=false;
            }
        }
        for(Bomb a: BombermanGame.bom){
            if (Math.floor(x/ Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE+Sprite.SCALED_SIZE==a.x&&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y&&a.getClass().toString().contains("Bomb")) {
                _moving=false;
            }
        }
        if (x < 29 * Sprite.SCALED_SIZE&&_moving) {
            x += v;
            change-=v*Sprite.SCALED_SIZE/20;
        }
        canMove=_moving;
    }

    public void left1(int v) {
        _moving=true;
        _direction=3;
        for(Entity a: BombermanGame.stillObjects){
            if (Math.ceil(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE==a.x
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y
                    &&!a.getClass().toString().contains("Grass")
                    &&!a.getClass().toString().contains("Brick")) {
                _moving=false;
            }
        }
        for(Bomb a: BombermanGame.bom){
            if (Math.ceil(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE==a.x
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y&&a.getClass().toString().contains("Bomb")) {
                _moving=false;
            }
        }
        if (x > Sprite.SCALED_SIZE&&_moving) {
            x -= v;
            change-=v*Sprite.SCALED_SIZE/20;
        }
        canMove=_moving;
    }

    public void up1(int v) {
        _moving=true;
        _direction=0;
        for(Entity a: BombermanGame.stillObjects){
            if (Math.round(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.x
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE==a.y
                    &&!a.getClass().toString().contains("Grass")
                    &&!a.getClass().toString().contains("Brick")) {
                _moving=false;
            }
        }
        for(Bomb a: BombermanGame.bom){
            if (Math.round(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.x&&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE==a.y&&a.getClass().toString().contains("Bomb")) {
                _moving=false;
            }
        }
        if (y > Sprite.SCALED_SIZE&&_moving) {
            y -= v;
            change-=v*Sprite.SCALED_SIZE/20;
        }
        canMove=_moving;
    }

    public void down1(int v) {
        _direction=2;
        _moving=true;
        for(Entity a: BombermanGame.stillObjects){
            if (Math.round(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.x
                    &&Math.floor(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE+Sprite.SCALED_SIZE==a.y
                    &&!a.getClass().toString().contains("Grass")
                    &&!a.getClass().toString().contains("Brick")) {
                _moving=false;
            }
        }
        for (Bomb a : BombermanGame.bom) {
            if (Math.round(x / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE == a.x && Math.floor(y / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE == a.y && a.getClass().toString().contains("Bomb")) {
                _moving = false;
            }
        }
        if (y < 12 * Sprite.SCALED_SIZE&&_moving) {
            y += v;
            change-=v*Sprite.SCALED_SIZE/20;
        }
        canMove=_moving;
    }
    public  void Direction1() {
        int d=_direction;
        if(!canMove||change==40){
            d= calculateDirection();
        }
        if(d==0) {
            up1(sp);

        }
        else if(d==2) {
            down1(sp);

        }
        else if(d==3) {
            left1(sp);

        }
        else {
            right1(sp);


        }
        if(change==0){
            change=64;
        }
    }

    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                if(_moving)
                    img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate, 30).getFxImage();
                else img = Sprite.kondoria_left1.getFxImage();
                break;
            case 2:
            case 3:
                if(_moving)
                     img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate, 30).getFxImage();
                else img = Sprite.kondoria_right1.getFxImage();
                break;
            default:
                img = Sprite.kondoria_dead.getFxImage();
                break;
        }
    }
}
