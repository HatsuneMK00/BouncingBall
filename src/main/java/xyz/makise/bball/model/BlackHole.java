package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;

public class BlackHole extends GameComponent {
//    正方形左上角位置
    private double positionX;
    private double positionY;
//    边长
    private double length;

    public BlackHole() {
        super();
        super.setType(EntityType.BLACK_HOLE);
    }

    public BlackHole(double positionX, double positionY, double length) {
        super();
        super.setType(EntityType.BLACK_HOLE);
        this.positionX = positionX;
        this.positionY = positionY;
        this.length = length;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
