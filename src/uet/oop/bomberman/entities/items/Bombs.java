package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;

public class Bombs extends Skill{
    public Bombs(double xUnit, double yUnit, Image img) {
        super(xUnit,yUnit,img);
    }

    public Bombs(double xUnit, double yUnit) {
        super(xUnit, yUnit);
    }

    @Override
    public void update() {

    }
}
