package xyz.makise.bball.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;

import static com.almasb.fxgl.dsl.FXGL.getPhysicsWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class EntityRotator {
    public Entity triangleRotate(Entity entity, EntityPlacer entityPlacer){
        double x, y;
        Entity ret = null;
        int[][] map = entityPlacer.getMap();
        x = entity.getX();
        y = entity.getY();
        int scale = entity.getComponent(TriangleComponent.class).getScale();
        int direction = entity.getComponent(TriangleComponent.class).getDirection();
        SpawnData data = new SpawnData(x, y);
        data.put("scale", scale);
        data.put("direction", (direction + 1) % 4);
        getPhysicsWorld().onEntityRemoved(entity);
        entity.removeFromWorld();
        ret = spawn("triangle", data);
        return ret;
    }
}
