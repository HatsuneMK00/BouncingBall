package xyz.makise.bball.model;

public class Rectangle extends GameComponent {
//    正方形左上角位置
    private int positionX;
    private int positionY;
//    边长
    private int length;

    public Rectangle() {
        super();
        super.setType(ComponentType.RECTANGLE);
    }

    public Rectangle(int positionX, int positionY, int length) {
        super();
        super.setType(ComponentType.RECTANGLE);
        this.positionX = positionX;
        this.positionY = positionY;
        this.length = length;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
