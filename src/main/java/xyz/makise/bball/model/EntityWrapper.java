package xyz.makise.bball.model;

public class EntityWrapper {
    public EntityWrapper() {
    }

    public EntityWrapper(EntityType entityType, int direction, int scale, double positionX, double positionY) {
        this.entityType = entityType;
        this.direction = direction;
        this.scale = scale;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    private EntityType entityType;

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    private int direction;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    private int scale;

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    private double positionX;
    private double positionY;

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
}
