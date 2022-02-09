package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    public static boolean remove_=false;
    //Tọa độ X tính từ góc trái trên trong Canvas
    public double x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    public double y;

    protected Image img;
    protected boolean killed = false;
    public int timeToDie = 30; // thời gian diễn ra sự chết của thực thể
    public double timeToExplode = 120; //2 giay - thoi gian phat no
    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(double xUnit, double yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity(double xUnit, double yUnit) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public void remove() {
        remove_ = true;
    }
}
