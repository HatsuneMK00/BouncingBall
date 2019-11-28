package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;

public class Ball extends GameComponent {
//    半径
    private double scale;
//    圆心位置
    private double ballCenterX;
    private double ballCenterY;

    public Ball() {
     super();
     super.setType(EntityType.BALL);
    }

    public Ball(double scale, double ballCenterX, double ballCenterY) {
        super();
        super.setType(EntityType.BALL);
        this.scale = scale;
        this.ballCenterX = ballCenterX;
        this.ballCenterY = ballCenterY;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getBallCenterX() {
        return ballCenterX;
    }

    public void setBallCenterX(double ballCenterX) {
        this.ballCenterX = ballCenterX;
    }

    public double getBallCenterY() {
        return ballCenterY;
    }

    public void setBallCenterY(double ballCenterY) {
        this.ballCenterY = ballCenterY;
    }
}
