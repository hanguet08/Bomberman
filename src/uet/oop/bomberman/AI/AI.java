package uet.oop.bomberman.AI;

import java.util.Random;

public abstract class AI {

    protected Random random = new Random();

    /**
     * Thuật toán tìm đường đi
     * @return hướng đi xuống/phải/trái/lên tương ứng với các giá trị 0/1/2/3
     */
    public abstract int calculateDirection(int t);

    public abstract void pr();
    public abstract int setBomb();
}
