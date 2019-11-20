package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;

//弯曲的水管
public class CurvedPipe extends GameComponent{
//    弯曲的水管的图片的左上角
    private int positionX;
    private int positionY;
//    弯曲方向
//    0,1,2,3: 左上右下
    private int direction;
//    图片的边长
    private int length;

    public CurvedPipe() {
        super();
        super.setType(ComponentType.CURVED_PIPE);
    }

    public CurvedPipe(int positionX, int positionY, int direction, int length) {
        super();
        super.setType(ComponentType.CURVED_PIPE);
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
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
