package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import uet.oop.bomberman.AI.AIBomber;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.items.Bombs;
import uet.oop.bomberman.entities.items.Flames;
import uet.oop.bomberman.entities.items.Portal;
import uet.oop.bomberman.entities.items.Speed;
import uet.oop.bomberman.entities.tiles.Brick;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import static java.lang.Thread.sleep;

public class Bomber extends AnimatedEntity {
    public Sound s= new Sound();
    protected boolean pass = false;
    private int dem=0;
    private int demSp=0;

    public Bomber(double x, double y, Image img) {
        super( x, y, img);
    }


    @Override
    public void update() {
        for(Entity e : BombermanGame.enemies) {
            if(Math.abs(x-e.x)<Sprite.SCALED_SIZE &&Math.abs(y-e.y)<Sprite.SCALED_SIZE) {
                mov = true;
            }
        }
        for(Bomb a: BombermanGame.bom){
            if(x>=a.x&& Math.abs(x- a.flameRight.x)<Sprite.SCALED_SIZE &&Math.abs(y-a.y)<Sprite.SCALED_SIZE&&a.timeToExplode<=0){
                mov=true;
            }
            if(x<=a.x&& Math.abs(x- a.flameLeft.x)<Sprite.SCALED_SIZE &&Math.abs(y-a.y)<Sprite.SCALED_SIZE&&a.timeToExplode<=0){
                mov=true;
            }
            if(y>=a.y&& Math.abs(y-a.flameDown.y)<Sprite.SCALED_SIZE &&Math.abs(x-a.x)<Sprite.SCALED_SIZE&&a.timeToExplode<=0){
                mov=true;
            }
            if(y<=a.y&& Math.abs(y- a.flameUp.y)<Sprite.SCALED_SIZE &&Math.abs(x-a.x)<Sprite.SCALED_SIZE&&a.timeToExplode<=0){
                mov=true;
            }
        }
        if(mov){
            s.playSound("Bomberman SFX (1).wav");
        }
    }

    public void kill() {
        img=Sprite.movingSprite(Sprite.player_dead1,Sprite.player_dead2,Sprite.player_dead3,dem,45).getFxImage();
    }

    public void Dem() {
        if (dem<45) {
            dem++;
        }
    }
    public void DemSp() {
        if(demSp<1000){
            demSp++;
        }
        else {
            speed=4;
        }
    }


    @Override
    public void render(GraphicsContext gc) {
        if(BombermanGame.ai==1){
            Direction();
        }
        if(BombermanGame.time==0){
            BombermanGame.lives--;
            BombermanGame.time=200;
            BombermanGame.dem=0;
            Khoi_Tao(BombermanGame.Xb*Sprite.SCALED_SIZE, BombermanGame.Yb*Sprite.SCALED_SIZE, Sprite.player_right.getFxImage());
            gc.drawImage(img,x,y);
            mov=false;
            dem=0;
        }
        if(!mov) {
            chooseSprite();
            _moving = false;
            gc.drawImage(img, x, y);
            animate1();
            skill();
            if(speed==2) {
                DemSp();
            }
            if (pass&&!BombermanGame.pass5) {
                PassLevel();
                Khoi_Tao(BombermanGame.Xb*Sprite.SCALED_SIZE, BombermanGame.Yb*Sprite.SCALED_SIZE, Sprite.player_right.getFxImage());
                gc.drawImage(img, x, y);
                BombermanGame.createMap();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                BombermanGame.time=200;
                BombermanGame.dem=1;
            }
        }
        else {
            kill();
            gc.drawImage(img,x,y);
            Dem();
            if(dem==45){
                Khoi_Tao(BombermanGame.Xb*Sprite.SCALED_SIZE, BombermanGame.Yb*Sprite.SCALED_SIZE, Sprite.player_right.getFxImage());
                gc.drawImage(img,x,y);
                BombermanGame.lives--;
                if(BombermanGame.lives==0) {
                    System.out.println("Game over !");
                    BombermanGame.stillObjects.removeAll(BombermanGame.stillObjects);
                    BombermanGame.enemies.removeAll(BombermanGame.enemies);
                }
                mov=false;
                dem=0;
            }
        }
    }
    @Override
    public void Direction() {
        super.Direction();
        super.Direction();
        if (!BombermanGame.enemies.isEmpty()) {
            Enemy e = BombermanGame.enemies.get(0);
            _ai = new AIBomber(this, e);
        }
        else {
            Portal e = BombermanGame.portals.get(0);
            _ai = new AIBomber(this, e);

        }
        if (BombermanGame.bom.isEmpty()) {
            _direction = _ai.calculateDirection(_direction);
        } else {
            for(Entity a:BombermanGame.stillObjects){
                if(a.x==32&&a.y==64){
                    _ai = new AIBomber(this, a);
                    _direction=_ai.calculateDirection(_direction);
                }
            }

        }

        if(_ai.setBomb()==1){
            Bomb bom= new Bomb(Math.round(x / Sprite.SCALED_SIZE), Math.round(y / Sprite.SCALED_SIZE), Sprite.bomb.getFxImage());
            if(BombermanGame.bom.isEmpty()) BombermanGame.bom.add(bom);
        }

    }
    public void PassLevel() {
        pass = false;
        flame=true;
        bombs=false;
        speed = 4;
        //Enemy.sp++;
        BombermanGame.path++;
        System.out.println("Level " + BombermanGame.path);
        BombermanGame.stillObjects.removeAll(BombermanGame.stillObjects);
        BombermanGame.enemies.removeAll(BombermanGame.enemies);
    }

    public void Khoi_Tao(double a, double b, Image c) {
        this.x=a;
        this.y=b;
        this.img=c;
    }

    public void chooseSprite() {
        switch(_direction) {
            case 0:
                img = Sprite.player_up.getFxImage();
                if(_moving) {
                    img = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2,animate, 20).getFxImage();
                }
                break;
            case 1:
                img = Sprite.player_right.getFxImage();
                if(_moving) {
                    img = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20).getFxImage();

                }
                break;
            case 2:
                img=Sprite.player_down.getFxImage();
                if(_moving) {
                    img = Sprite.movingSprite( Sprite.player_down_1, Sprite.player_down_2, animate, 20).getFxImage();
                }
                break;
            case 3:
                img = Sprite.player_left.getFxImage();
                if(_moving) {
                    img = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20).getFxImage();
                }
                break;
            default:
                img = Sprite.player_right.getFxImage();
                if(_moving) {
                    img = Sprite.movingSprite( Sprite.player_right_1, Sprite.player_right_2, animate, 20).getFxImage();
                }
                    break;
        }
    }

    public void skill() {
        for(Entity e : BombermanGame.stillObjects) {
            if((e instanceof Speed) &&Math.abs(x-e.x)<Sprite.SCALED_SIZE &&Math.abs(y-e.y)<Sprite.SCALED_SIZE) {
               s.playSound("Bomberman SFX (4).wav");
                speed=2;
                x=e.x;
                y=e.y;
                BombermanGame.stillObjects.remove((Entity) e);
                break;
            }
            if((e instanceof Flames) &&Math.abs(x-e.x)<Sprite.SCALED_SIZE
                    &&Math.abs(y-e.y)<Sprite.SCALED_SIZE) {
                s.playSound("Bomberman SFX (4).wav");
                flame=false;
                BombermanGame.stillObjects.remove(e);
                break;
            }
            if((e instanceof Bombs)&&Math.abs(x-e.x)<Sprite.SCALED_SIZE
                    &&Math.abs(y-e.y)<Sprite.SCALED_SIZE) {
                s.playSound("Bomberman SFX (4).wav");
                bombs=true;
                BombermanGame.stillObjects.remove(e);
                break;
            }
            if((e instanceof Portal)
                    &&Math.abs(x-e.x)<=Sprite.SCALED_SIZE/2
                    &&Math.abs(y-e.y)<=Sprite.SCALED_SIZE/2
                &&BombermanGame.Enemies==0) {
                s.playSound("Bomberman SFX (4).wav");
                BombermanGame.stillObjects.remove(e);
                if(BombermanGame.path<5) {
                    pass = true;
                }
                else if(BombermanGame.path>=5){
                    pass=false;
                    BombermanGame.pass5=true;
                }
                break;
            }
        }
    }

}
