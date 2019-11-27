package xyz.makise.bball.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;

import static com.almasb.fxgl.dsl.FXGL.getPhysicsWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class EntityZoomer {
    //    放大
    public Entity zoomOut(Entity entity, EntityPlacer entityPlacer) {
        double x, y;
        Entity ret = null;
        int[][] map = entityPlacer.getMap();
        x = entity.getX();
        y = entity.getY();
        int entityId = entityPlacer.getMap()[(int) (x / 30)][(int) (y / 30)];
        System.out.println(entityId);
        SpawnData data = new SpawnData(entity.getX(), entity.getY());
        data.put("scale", entity.getComponent(ScaleComponent.class).getScale() + 1);
        if (entity.hasComponent(TriangleComponent.class)){
            data.put("direction", entity.getComponent(TriangleComponent.class).getDirection());
        }
        entityPlacer.getEntityMap().remove(entityId);
        getPhysicsWorld().onEntityRemoved(entity);
        entity.removeFromWorld();

        String entityName = null;

        switch (entity.getType().toString()){
            case "TRIANGLE":{
                entityName = "triangle";
                break;
            }
            case "RECTANGLE":{
                entityName = "rectangle";
                break;
            }
            case "CIRCLE":{
                entityName = "circle";
                break;
            }
        }

        entity = spawn(entityName, data);
        entityPlacer.getEntityMap().put(entityId, entity);
        return entity;
    }

    public Entity zoomIn(Entity entity, EntityPlacer entityPlacer) {
        double x, y;
        Entity ret = null;
        int[][] map = entityPlacer.getMap();
        x = entity.getX();
        y = entity.getY();
        int entityId = entityPlacer.getMap()[(int) (x / 30)][(int) (y / 30)];
        System.out.println(entityId);
        SpawnData data = new SpawnData(entity.getX(), entity.getY());
        data.put("scale", entity.getComponent(ScaleComponent.class).getScale() - 1);
        if (entity.hasComponent(TriangleComponent.class)){
            data.put("direction", entity.getComponent(TriangleComponent.class).getDirection());
        }
        entityPlacer.getEntityMap().remove(entityId);
        getPhysicsWorld().onEntityRemoved(entity);
        entity.removeFromWorld();

        String entityName = null;

        switch (entity.getType().toString()){
            case "TRIANGLE":{
                entityName = "triangle";
                break;
            }
            case "RECTANGLE":{
                entityName = "rectangle";
                break;
            }
            case "CIRCLE":{
                entityName = "circle";
                break;
            }
        }

        entity = spawn(entityName, data);
        entityPlacer.getEntityMap().put(entityId, entity);
        return entity;
    }
}
