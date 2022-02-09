package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import javax.swing.*;
import java.util.Random;

public class Enemy extends AnimatedEntity {
    public Sound s= new Sound();
    public int dem1=0;

    public Enemy(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
    public void Dem1() {
        if (dem1 < 45) {
            dem1++;
        }
        if(dem1==45) {
            BombermanGame.Enemies--;
        }

    }
    public void Die() {
        s.playSound("Bomberman SFX (1).wav");
        img=Sprite.movingSprite(Sprite.mob_dead1,Sprite.mob_dead2,Sprite.mob_dead3,dem1,45).getFxImage();
    }
    public void CollisionFlame() {
        for (Bomb a : BombermanGame.bom) {
            if (x >= a.x && Math.abs(x- a.flameRight.x)<Sprite.SCALED_SIZE && Math.abs(y - a.y) < Sprite.SCALED_SIZE && a.timeToExplode <= 0) {
                isMov = true;
            }
            if (x <= a.x && Math.abs(x- a.flameLeft.x)<Sprite.SCALED_SIZE && Math.abs(y - a.y) < Sprite.SCALED_SIZE && a.timeToExplode <= 0) {
                isMov = true;
            }
            if (y >= a.y && Math.abs(y- a.flameDown.y)<Sprite.SCALED_SIZE && Math.abs(x - a.x) < Sprite.SCALED_SIZE && a.timeToExplode <= 0){
                isMov = true;
            }
            if (y <= a.y && Math.abs(y- a.flameUp.y)<Sprite.SCALED_SIZE&& Math.abs(x - a.x) < Sprite.SCALED_SIZE && a.timeToExplode <= 0) {
                isMov = true;
            }
        }
    }

}


