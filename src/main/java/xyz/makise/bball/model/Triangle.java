package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;

public class Triangle extends GameComponent {
//    直角位置
    private int rightAngleX;
    private int rightAngleY;
//    边长
    private int length;

    public Triangle() {
        super();
        super.setType(ComponentType.TRIANGLE);
    }

    public Triangle(int rightAngleX, int rightAngleY, int length) {
        super();
        super.setType(ComponentType.TRIANGLE);
        this.rightAngleX = rightAngleX;
        this.rightAngleY = rightAngleY;
        this.length = length;
    }

    public int getRightAngleX() {
        return rightAngleX;
    }

    public void setRightAngleX(int rightAngleX) {
        this.rightAngleX = rightAngleX;
    }

    public int getRightAngleY() {
        return rightAngleY;
    }

    public void setRightAngleY(int rightAngleY) {
        this.rightAngleY = rightAngleY;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void setEntity(Entity entity) {
        super.setEntity(entity);
    }
}
