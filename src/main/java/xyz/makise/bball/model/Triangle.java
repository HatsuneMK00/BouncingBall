package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;

public class Triangle extends GameComponent {
//    直角位置
    private double positionX;
    private double positionY;
//    边长
    private double length;
    private double direction;

    public Triangle() {
        super();
        super.setType(EntityType.TRIANGLE);
    }

    public Triangle(double positionX, double positionY, double length,double direction) {
        super();
        super.setType(EntityType.TRIANGLE);
        this.positionX = positionX;
        this.positionY = positionY;
        this.length = length;
        this.direction = direction;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
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
