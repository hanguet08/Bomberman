package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;

public class Speed extends Skill {
    public Speed(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Speed(double xUnit, double yUnit) {
        super(xUnit, yUnit);
    }

    @Override
    public void update() {
        if(AnimatedEntity.speed==2.0) remove();
    }
}