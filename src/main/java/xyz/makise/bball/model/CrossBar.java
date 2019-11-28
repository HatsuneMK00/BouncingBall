package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;

public class CrossBar extends GameComponent {
//    中心坐标
    private double centerX;
    private double centerY;
    private int crossbarType;

    public CrossBar() {
        super();
        super.setType(EntityType.CROSS_BAR);
    }

    public int getCrossbarType() {
        return crossbarType;
    }

    public void setCrossbarType(int crossbarType) {
        this.crossbarType = crossbarType;
    }

    public CrossBar(double centerX, double centerY, int crossbarType) {
        super();
        super.setType(EntityType.CROSS_BAR);
        this.centerX = centerX;
        this.centerY = centerY;
        this.crossbarType = crossbarType;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }
}
