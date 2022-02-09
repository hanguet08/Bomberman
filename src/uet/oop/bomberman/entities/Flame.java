package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends AnimatedEntity {
    protected int direction; //huong ngon lua : trên/dưới/trái/phải tương ứng là 0/1/2/3

    public Flame(double x, double y, int direction) {
        super(x, y);
        this.direction = direction;
    }

    public void collide(Entity e) {

    }
    public boolean canExplodes() { // hàm kiểm tra có thể nổ được ở vị trí hiện tại không
        return true;
    }

    public void render(GraphicsContext gc) {
        if (canExplodes()) {
            if (timeToDie > 0 && timeToExplode <= 0) {
                if (animate % 30 < 10) {
                    switch (direction) {
                        case 0:
                            if (flame) {
                                img = Sprite.explosion_vertical_top_last2.getFxImage();
                                break;
                            }
                            if (!flame) {
                                if (cu == true) {
                                    img = Sprite.explosion_vertical2.getFxImage();
                                }
                                if (cu1 == true) {
                                    img = Sprite.explosion_vertical2.getFxImage();
                                }
                            }

                            break;
                        case 1:
                            if (flame) {
                                img = Sprite.explosion_vertical_down_last2.getFxImage();
                                break;
                            }
                            if (!flame) {
                                if (cd == true) {
                                    img = Sprite.explosion_vertical2.getFxImage();
                                }
                                if (cd1 == true) {
                                    img = Sprite.explosion_vertical2.getFxImage();
                                }
                                break;
                            }
                        case 2:
                            if (flame) {
                                img = Sprite.explosion_horizontal_left_last2.getFxImage();
                                break;
                            }
                            if (!flame) {
                                if (cl == true)
                                    img = Sprite.explosion_horizontal2.getFxImage();
                                if (cl1 == true)
                                    img = Sprite.explosion_horizontal2.getFxImage();
                                break;
                            }
                        case 3:
                            if (flame) {
                                img = Sprite.explosion_horizontal_left_last2.getFxImage();
                                break;
                            }
                            if (!flame) {
                                if (cr == true) {
                                    img = Sprite.explosion_horizontal2.getFxImage();
                                }
                                if (cr1 == true) {
                                    img = Sprite.explosion_horizontal2.getFxImage();
                                }
                                break;
                            }
                    }
                } else if (animate % 30 < 20) {
                    switch (direction) {
                        case 0:
                            if (flame) {
                                img = Sprite.explosion_vertical_top_last1.getFxImage();
                                break;
                            }
                            if (!flame) {
                                if (cu == true) {
                                    img = Sprite.explosion_vertical1.getFxImage();
                                }
                                if (cu1 == true) {
                                    img = Sprite.explosion_vertical1.getFxImage();
                                }
                            }

                            break;
                        case 1:
                            if (flame) {
                                img = Sprite.explosion_vertical_down_last1.getFxImage();
                                break;
                            }
                            if (!flame) {
                                if (cd == true) {
                                    img = Sprite.explosion_vertical1.getFxImage();
                                }
                                if (cd1 == true) {
                                    img = Sprite.explosion_vertical1.getFxImage();
                                }
                                break;
                            }
                        case 2:
                            if (flame) {
                                img = Sprite.explosion_horizontal_left_last1.getFxImage();
                                break;
                            }
                            if (!flame) {
                                if (cl == true)
                                    img = Sprite.explosion_horizontal1.getFxImage();
                                if (cl1 == true)
                                    img = Sprite.explosion_horizontal1.getFxImage();
                                break;
                            }
                        case 3:
                            if (flame) {
                                img = Sprite.explosion_horizontal_right_last1.getFxImage();
                                break;
                            }
                            if (!flame) {
                                if (cr == true) {
                                    img = Sprite.explosion_horizontal1.getFxImage();
                                }
                                if (cr1 == true) {
                                    img = Sprite.explosion_horizontal1.getFxImage();
                                }
                                break;
                            }
                    }
                }
                else {
                    switch (direction) {
                        case 0:
                            if (flame) {
                                img = Sprite.explosion_vertical_top_last.getFxImage();
                                break;
                            }
                            if (!flame) {
                                if (cu == true) {
                                    img = Sprite.explosion_vertical.getFxImage();
                                }
                                if (cu1 == true) {
                                    img = Sprite.explosion_vertical.getFxImage();
                                }
                            }

                            break;
                        case 1:
                            if (flame) {
                                img = Sprite.explosion_vertical_down_last.getFxImage();
                                break;
                            }
                            if (!flame) {
                                if (cd == true) {
                                    img = Sprite.explosion_vertical.getFxImage();
                                }
                                if (cd1 == true) {
                                    img = Sprite.explosion_vertical.getFxImage();
                                }
                                break;
                            }
                        case 2:
                            if (flame) {
                                img = Sprite.explosion_horizontal_left_last.getFxImage();
                                break;
                            }
                            if (!flame) {
                                if (cl == true)
                                    img = Sprite.explosion_horizontal.getFxImage();
                                if (cl1 == true)
                                    img = Sprite.explosion_horizontal.getFxImage();
                                break;
                            }
                        case 3:
                            if (flame) {
                                img = Sprite.explosion_horizontal_right_last.getFxImage();
                                break;
                            }
                            if (!flame) {
                                if (cr == true) {
                                    img = Sprite.explosion_horizontal.getFxImage();
                                }
                                if (cr1 == true) {
                                    img = Sprite.explosion_horizontal.getFxImage();
                                }
                                break;
                            }
                    }
                }
                gc.drawImage(img, x, y);
            } else {
                remove();
            }
            animate1();
        } else {
            remove();
        }
    }
}