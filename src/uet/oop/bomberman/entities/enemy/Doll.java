package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Doll extends Enemy{
    private int live=1;
    private int demX=0;
    public Doll(double xUnit, double yUnit, Image img) {
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
            DemX();
            if(demX==31&&live!=0){
                live--;
                sp=2;
                demX=0;
                isMov=false;
                Direction();
                chooseSprite();
                gc.drawImage(img, x, y);
            }
            else if(live==0) {
                Die();
                gc.drawImage(img, x, y);
                Dem1();
            }
            else {
                img=Sprite.mob_dead1.getFxImage();
                gc.drawImage(img,x,y);
            }
        }
        animate1();
    }

    public void DemX() {
        if(demX<31){
            demX++;
        }
    }
    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                if(_moving)
                    img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3,animate, 60).getFxImage();
                else img=Sprite.doll_left1.getFxImage();
                break;
            case 2:
            case 3:
                if(_moving)
                     img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3,animate, 60).getFxImage();
                else img=Sprite.doll_right1.getFxImage();
                break;
            default:
                img = Sprite.doll_dead.getFxImage();
                break;
        }
    }
}
