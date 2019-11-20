package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;

public abstract class GameComponent {
    private ComponentType type;
    private Entity entity;

    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
