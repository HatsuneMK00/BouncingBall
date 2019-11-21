package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;

public abstract class GameComponent {
    private EntityType type;

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }
}
