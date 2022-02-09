package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Flames extends Skill {
    public Flames(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Flames(double xUnit, double yUnit) {
        super(xUnit, yUnit);
    }

    @Override
    public void update() {

    }
}