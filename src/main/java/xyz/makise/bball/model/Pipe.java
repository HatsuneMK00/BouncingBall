package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;

public class Pipe extends GameComponent {
//    左上角位置
    private double positionX;
    private double positionY;
    private double direction;

    public Pipe() {
        super();
        super.setType(EntityType.PIPE);
    }

    public Pipe(double positionX, double positionY,double direction) {
        super();
        super.setType(EntityType.PIPE);
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction=direction;
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
}
