package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Skill extends Entity {
    public Skill(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Skill(double xUnit, double yUnit) {
        super(xUnit, yUnit);
    }

    @Override
    public void update() {

    }
}
