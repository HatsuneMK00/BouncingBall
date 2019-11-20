package xyz.makise.bball.model;

public class CrossBar extends GameComponent {
//    中心坐标
    private int centerX;
    private int centerY;
//    杆长
    private int length;

    public CrossBar() {
        super();
        super.setType(ComponentType.CROSS_BAR);
    }

    public CrossBar(int centerX, int centerY, int length) {
        super();
        super.setType(ComponentType.CROSS_BAR);
        this.centerX = centerX;
        this.centerY = centerY;
        this.length = length;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
