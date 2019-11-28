package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;

public class Circle extends GameComponent {
//    半径
    private double scale;
//    圆心位置
    private double circleCenterX;
    private double circleCenterY;

    public Circle() {
        super();
        super.setType(EntityType.CIRCLE);
    }

    public Circle(double scale, double circleCenterX, double circleCenterY) {
        super();
        super.setType(EntityType.CIRCLE);
        this.scale = scale;
        this.circleCenterX = circleCenterX;
        this.circleCenterY = circleCenterY;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getCircleCenterX() {
        return circleCenterX;
    }

    public void setCircleCenterX(double circleCenterX) {
        this.circleCenterX = circleCenterX;
    }

    public double getCircleCenterY() {
        return circleCenterY;
    }

    public void setCircleCenterY(double circleCenterY) {
        this.circleCenterY = circleCenterY;
    }
}
