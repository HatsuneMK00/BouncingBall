package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;

public class Ball extends GameComponent {
//    半径
    private int radius;
//    圆心位置
    private int ballCenterX;
    private int ballCenterY;

    public Ball() {
     super();
     super.setType(EntityType.BALL);
    }

    public Ball(int radius, int ballCenterX, int ballCenterY) {
        super();
        super.setType(EntityType.BALL);
        this.radius = radius;
        this.ballCenterX = ballCenterX;
        this.ballCenterY = ballCenterY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getBallCenterX() {
        return ballCenterX;
    }

    public void setBallCenterX(int ballCenterX) {
        this.ballCenterX = ballCenterX;
    }

    public int getBallCenterY() {
        return ballCenterY;
    }

    public void setBallCenterY(int ballCenterY) {
        this.ballCenterY = ballCenterY;
    }
}
