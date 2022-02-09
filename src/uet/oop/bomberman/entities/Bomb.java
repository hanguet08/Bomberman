package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.tiles.Brick;
import uet.oop.bomberman.entities.tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.Iterator;

public class Bomb extends AnimatedEntity{
    public Sound s= new Sound();
    public Flame flameUp = new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE - 1, 0);
    public Flame flameDown = new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE + 1, 1);
    public Flame flameLeft = new Flame(x / Sprite.SCALED_SIZE - 1, y / Sprite.SCALED_SIZE, 2);
    public Flame flameRight = new Flame(x / Sprite.SCALED_SIZE + 1, y / Sprite.SCALED_SIZE, 3);
    public Flame flameUp1 = new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE - 2, 0);
    public Flame flameDown1 = new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE + 2, 1);
    public Flame flameLeft1 = new Flame(x / Sprite.SCALED_SIZE - 2, y / Sprite.SCALED_SIZE, 2);
    public Flame flameRight1 = new Flame(x / Sprite.SCALED_SIZE + 2, y / Sprite.SCALED_SIZE, 3);

    public Bomb(double x, double y, Image fxImage) {
        super(x, y);
        img = Sprite.bomb.getFxImage();
    }

    public void collide(Entity e) {

    }
    public void render(GraphicsContext gc) {
        if (timeToExplode > 0) {
            if (animate % 60 < 20) {
                img = Sprite.bomb_2.getFxImage();
            } else if (animate % 60 < 40) {
                img = Sprite.bomb_1.getFxImage();
            } else {
                img = Sprite.bomb.getFxImage();
            }
            gc.drawImage(img, x, y);
        } else {
            if (timeToDie > 0) {
                s.playSound("Bomberman SFX (4).wav");
                if (animate % 30 < 10) {
                    img = Sprite.bomb_exploded.getFxImage();
                } else if (animate % 30 < 20) {
                    img = Sprite.bomb_exploded1.getFxImage();
                } else {
                    img = Sprite.bomb_exploded2.getFxImage();
                }
                gc.drawImage(img, x, y);
            }
            else remove();
        }
        if (cu) flameUp.render(gc);
        if (cd) flameDown.render(gc);
        if (cl) flameLeft.render(gc);
        if (cr) flameRight.render(gc);
        if (!flame) {
            if (cu1) flameUp1.render(gc);
            if (cd1) flameDown1.render(gc);
            if (cl1) flameLeft1.render(gc);
            if (cr1) flameRight1.render(gc);
        }
        animate1();
    }

    @Override
    public void update() {
        byeRock();
        super.update();
        flameUp.update();
        flameDown.update();
        flameLeft.update();
        flameRight.update();
        if (!flame) {
            flameUp1.update();
            flameDown1.update();
            flameLeft1.update();
            flameRight1.update();
        }

    }

    public void byeRock(){
        for(Entity a: BombermanGame.stillObjects){
            if (Math.floor(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE+Sprite.SCALED_SIZE==a.x
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y){
               if(a instanceof Brick) {
                   Brick e = (Brick) a;
                   if (timeToExplode <= 0) {
                       e.isMov=true;
                       cr1=false;
                       break;
                   }
               }
               else if(a instanceof Wall) {
                   cr = false;
                   cr1 = false;
               }
            }
            if (Math.floor(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE+Sprite.SCALED_SIZE*2==a.x
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y){
                if(!flame&&a instanceof Brick&&cr1) {
                    Brick e = (Brick) a;
                    if (timeToExplode <= 0) {
                        e.isMov=true;
                        break;
                    }

                }
            }

        }
        for(Entity a: BombermanGame.stillObjects){
            if (Math.ceil(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE==a.x
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y){
                if(a instanceof Brick) {
                    Brick e = (Brick) a;
                    if (timeToExplode <= 0) {
                        e.isMov=true;
                        cl1=false;
                        break;
                    }
                }
               else if(a instanceof Wall){
                    cl = false;
                    cl1=false;
                }
            }
            if (Math.ceil(x/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE*2==a.x
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE==a.y){
                if(!flame&&a instanceof Brick&&cl1) {
                    Brick e = (Brick) a;
                    if (timeToExplode <= 0) {
                        e.isMov=true;
                        break;
                    }

                }
            }
        }
        for(Entity a: BombermanGame.stillObjects){
            if (Math.abs(x-a.x)<Sprite.SCALED_SIZE
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE==a.y){
                if(a instanceof Brick) {
                    Brick e = (Brick) a;
                    if (timeToExplode <= 0) {
                        e.isMov=true;
                        cu1=false;
                        break;
                    }
                }
                else if(a instanceof Wall){
                    cu = false;
                    cu1=false;
                }
            }
            if (Math.abs(x-a.x)<Sprite.SCALED_SIZE
                    &&Math.ceil(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE-Sprite.SCALED_SIZE*2==a.y){
                if(!flame&&a instanceof Brick&&cu1) {
                    Brick e = (Brick) a;
                    if (timeToExplode <= 0) {
                        e.isMov=true;
                        break;
                    }

                }
            }
        }

        for(Entity a: BombermanGame.stillObjects){
            if (Math.abs(x-a.x)<Sprite.SCALED_SIZE
                    &&Math.floor(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE+Sprite.SCALED_SIZE==a.y){
                if(a instanceof Brick) {
                    Brick e = (Brick) a;
                    if (timeToExplode <= 0) {
                        e.isMov=true;
                        cd1=false;
                        break;
                    }
                }
                else if(a instanceof Wall){
                    cd = false;
                    cd1=false;
                }
            }
            if (Math.abs(x-a.x)<Sprite.SCALED_SIZE
                    &&Math.floor(y/Sprite.SCALED_SIZE)*Sprite.SCALED_SIZE+Sprite.SCALED_SIZE*2==a.y){
                if(!flame&&a instanceof Brick&&cd1) {
                    Brick e = (Brick) a;
                    if (timeToExplode <= 0) {
                        e.isMov=true;
                        break;
                    }

                }

            }
        }
    }

}
