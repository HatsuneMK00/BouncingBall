package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;

public class CrossBar extends GameComponent {
//    中心坐标
    private int centerX;
    private int centerY;
    private String crossbarType;

    public CrossBar() {
        super();
        super.setType(EntityType.CROSS_BAR);
    }

    public CrossBar(int centerX, int centerY, int length) {
        super();
        super.setType(EntityType.CROSS_BAR);
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }
}
