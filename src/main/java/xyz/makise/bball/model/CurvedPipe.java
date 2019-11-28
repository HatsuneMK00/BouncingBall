package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;

//弯曲的水管
public class CurvedPipe extends GameComponent{
//    弯曲的水管的图片的左上角
    private double positionX;
    private double positionY;
//    弯曲方向
//    0,1,2,3: 左上右下
    private double direction;
//    图片的边长

    public CurvedPipe() {
        super();
        super.setType(EntityType.CURVED_PIPE);
    }

    public CurvedPipe(double positionX, double positionY, double direction) {
        super();
        super.setType(EntityType.CURVED_PIPE);
        this.positionX = positionX;
        this.positionY = positionY;
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

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

}
