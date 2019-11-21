package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;

public class Circle extends GameComponent {
//    半径
    private int radius;
//    圆心位置
    private int circleCenterX;
    private int circleCenterY;

    public Circle() {
        super();
        super.setType(EntityType.CIRCLE);
    }

    public Circle(int radius, int circleCenterX, int circleCenterY) {
        super();
        super.setType(EntityType.CIRCLE);
        this.radius = radius;
        this.circleCenterX = circleCenterX;
        this.circleCenterY = circleCenterY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getCircleCenterX() {
        return circleCenterX;
    }

    public void setCircleCenterX(int circleCenterX) {
        this.circleCenterX = circleCenterX;
    }

    public int getCircleCenterY() {
        return circleCenterY;
    }

    public void setCircleCenterY(int circleCenterY) {
        this.circleCenterY = circleCenterY;
    }

    @Override
    public void setEntity(Entity entity) {
        super.setEntity(entity);
    }
}
