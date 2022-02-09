package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.AI.AI;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.items.Skill;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class AnimatedEntity extends Entity{
    protected AI _ai;
    public boolean cr=true, cl=true, cu=true, cd=true,cr1=true, cl1=true, cd1=true, cu1=true;
    public int _direction=1;
    protected final int MAX_ANIMATE = 7500; //save the animation status and dont let this get too big
    public boolean _moving=false;
    public boolean mov = false;
    public boolean isMov = false;
    public static int speed=4;
    public static boolean flame=true;
    public static boolean bombs=false;
    public boolean canMove=false;
    public int change =40;
    public int sp=1;
    protected Random random = new Random();
    public AnimatedEntity(double xUnit, double yUnit, Image img){
        super(xUnit, yUnit, img);
    }

    protected int animate = 0;

    public AnimatedEntity(double xUnit, double yUnit) {
        super(xUnit, yUnit);
    }

    public void update() {
        if (this.timeToExplode > 0) {
            --this.timeToExplode;
        }
        else
            --this.timeToDie;
    }

    public void right(AnimatedEntity o){
        _moving=true;
        _direction=1;
        for(Entity a: BombermanGame.stillObjects){
            if (Math.floor(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE+Sprite.SCALED_SIZE==a.x
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y
                    &&!a.getClass().toString().contains("Grass")&&!(a instanceof Skill)) {
                _moving=false;
            }
        }
        for(Bomb a: BombermanGame.bom){
            if(a.x-x>=32&&a.x-x<=40
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y) {
                _moving=false;
            }
        }
        if (x < 29 * Sprite.SCALED_SIZE&&_moving) {
            x +=16/4;
            BombermanGame.entities.remove(o);
            BombermanGame.entities.add(o);

        }
    }

    public void right(int v){
        _moving=true;
        _direction=1;
        for(Entity a: BombermanGame.stillObjects){
            if (Math.floor(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE+Sprite.SCALED_SIZE==a.x
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y
                    &&!a.getClass().toString().contains("Grass")&&!(a instanceof Skill)) {
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
            change-=v*Sprite.SCALED_SIZE/16;
        }
        canMove=_moving;
    }
    public void left(AnimatedEntity o){
        _moving=true;
        _direction=3;
        for(Entity a: BombermanGame.stillObjects){
            if (Math.ceil(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE==a.x
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y
                    &&!a.getClass().toString().contains("Grass")&&!(a instanceof Skill)) {
                _moving=false;
            }
        }
        for(Bomb a: BombermanGame.bom){
            if (x-a.x>=32&&x-a.x<=40
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y) {
                _moving=false;
            }
        }
        if (x > Sprite.SCALED_SIZE&&_moving) {
            x -= 16/4;
            BombermanGame.entities.remove(o);
            BombermanGame.entities.add(o);
        }
    }

    public void up(AnimatedEntity o){
        _moving=true;
        _direction=0;
        for(Entity a: BombermanGame.stillObjects){
            if (Math.abs(x-a.x)<Sprite.SCALED_SIZE
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE==a.y
                    &&!a.getClass().toString().contains("Grass")&&!(a instanceof Skill)) {
                _moving=false;
            }
        }
        for(Bomb a: BombermanGame.bom){
            if (Math.abs(x-a.x)<=Sprite.SCALED_SIZE
                    &&y-a.y>=32&&y-a.y<=40) {
                _moving=false;
            }
        }
        if (y > Sprite.SCALED_SIZE&&_moving) {
            y -= 16/4;
            BombermanGame.entities.remove(o);
            BombermanGame.entities.add(o);
        }
    }

    public void down(AnimatedEntity o){
        _direction=2;
        _moving=true;
        for(Entity a: BombermanGame.stillObjects){
            if (Math.abs(x-a.x)<Sprite.SCALED_SIZE
                    &&Math.floor(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE+Sprite.SCALED_SIZE==a.y
                    &&!a.getClass().toString().contains("Grass")&&!(a instanceof Skill)) {
                _moving=false;
            }
        }
        for(Bomb a: BombermanGame.bom){
            if (Math.abs(x-a.x)<=Sprite.SCALED_SIZE
                    &&a.y-y>=32&&a.y-y<=40) {
                _moving=false;
            }
        }
        if (y < 12 * Sprite.SCALED_SIZE&&_moving) {
            y += 16/4;
            BombermanGame.entities.remove(o);
            BombermanGame.entities.add(o);
        }
    }
    public void anime(KeyEvent event, AnimatedEntity o) {
        int code = 0;
        if (event.getCode().equals(KeyCode.RIGHT)) {
            right(o);
        } else if (event.getCode().equals(KeyCode.DOWN)) {
            down(o);
        }
        if (event.getCode().equals(KeyCode.UP)) {
            up(o);
        }
        if (event.getCode().equals(KeyCode.LEFT)) {
            left(o);
        }
        if (event.getCode().equals(KeyCode.SPACE)) {
            Bomb bom= new Bomb(Math.round(x / Sprite.SCALED_SIZE), Math.round(y / Sprite.SCALED_SIZE), Sprite.bomb.getFxImage());
            if(BombermanGame.bom.isEmpty()&&!bombs) BombermanGame.bom.add(bom);
            else if(bombs&&BombermanGame.bom.size()<2) BombermanGame.bom.add(bom);

        }
    }
    protected void animate1() {
        if (animate < MAX_ANIMATE) {
            animate++;
        } else {
            animate = 0; //reset animatio
        }
    }
    public  void Direction() {
        int d=_direction;
        if(!canMove||change==40){
            d= calculateDirection();
        }
        if(d==0) {
            up(sp);

        }
        else if(d==2) {
            down(sp);

        }
        else if(d==3) {
            left(sp);

        }
        else {
            right(sp);


        }
        if(change==0){
            change=64;
        }
    }

    public  int calculateDirection() {
        return random.nextInt(4);
    }














    public void left(int v){
        _moving=true;
        _direction=3;
        for(Entity a: BombermanGame.stillObjects){
            if (Math.ceil(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE==a.x&&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y&&!a.getClass().toString().contains("Grass")) {
                _moving=false;
            }
        }
        for(Bomb a: BombermanGame.bom){
            if (Math.ceil(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE==a.x&&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y&&a.getClass().toString().contains("Bomb")) {
                _moving=false;
            }
        }
        if (x > Sprite.SCALED_SIZE&&_moving) {
            x -= v;
            change-=v*Sprite.SCALED_SIZE/16;
        }
        canMove=_moving;
    }

    public void up(int v){
        _moving=true;
        _direction=0;
        for(Entity a: BombermanGame.stillObjects){
            if (Math.round(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.x&&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE==a.y&&!a.getClass().toString().contains("Grass")) {
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
            change-=v*Sprite.SCALED_SIZE/16;
        }
        canMove=_moving;
    }

    public void down(int v) {
        _direction = 2;
        _moving = true;
        for(Entity a: BombermanGame.stillObjects){
            if (Math.abs(x-a.x)<Sprite.SCALED_SIZE
                    &&Math.floor(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE+Sprite.SCALED_SIZE==a.y
                    &&!a.getClass().toString().contains("Grass")&&!(a instanceof Skill)) {
                _moving=false;
            }
        }
        for (Bomb a : BombermanGame.bom) {
            if (Math.round(x / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE == a.x && Math.floor(y / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE == a.y && a.getClass().toString().contains("Bomb")) {
                _moving = false;
            }
        }
        if (y < 12 * Sprite.SCALED_SIZE && _moving) {
            y += v;
            change -= v * Sprite.SCALED_SIZE / 16;
        }
        canMove = _moving;
    }


}
