package xyz.makise.bball.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;

import static com.almasb.fxgl.dsl.FXGL.getPhysicsWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class EntityRotator {
    public Entity triangleRotate(Entity entity, EntityPlacer entityPlacer) {
        double x, y;
        Entity ret = null;
        int[][] map = entityPlacer.getMap();
        x = entity.getX();
        y = entity.getY();
        int entityId = entityPlacer.getMap()[(int)(x / 30)][(int)(y / 30)];
        System.out.println(entityId);
        SpawnData data = new SpawnData(x, y);
        if (!entity.hasComponent(TriangleComponent.class)) return null;
        int scale = entity.getComponent(TriangleComponent.class).getScale();
        if (entity.hasComponent(TriangleComponent.class)){
            int direction = (entity.getComponent(TriangleComponent.class).getDirection() + 1) % 4;
            data.put("direction", direction);
        }
        data.put("scale", scale);
        entityPlacer.getEntityMap().remove(entityId);
        getPhysicsWorld().onEntityRemoved(entity);
        entity.removeFromWorld();
        ret = spawn("triangle", data);
        entityPlacer.getEntityMap().put(entityId,ret);
/*
* 演示的时候一定要点初始的位置 否则会出错
*
* */


//        switch (direction) {
//            case 0: {
//                for (int i = 0; i < scale; i++) {
//                    for (int j = 0; j < scale - i; j++) {
//                        map[(int)(x / 30 - 1 + i)][(int)(y / 30 - 1 - j)] = 0;
//                    }
//                }
//                for (int i = 0; i < scale; i++) {
//                    for (int j = 0; j < scale - i; j++) {
//                        map[(int)(x / 30 - 1 + i)][(int)(y / 30 - 1 + j)] = entityId;
//                    }
//                }
//                break;
//            }
//            case 1: {
//                for (int i = 0; i < scale; i++) {
//                    for (int j = 0; j < scale - i; j++) {
//                        System.out.println(map[(int)(x / 30 - 1 + i)][(int)(y / 30 - 1 + j)]);
//                        map[(int)(x / 30 - 1 + i)][(int)(y / 30 - 1 + j)] = 0;
//                    }
//                }
//                for (int i = 0; i < scale; i++) {
//                    for (int j = 0; j < scale - i; j++) {
//                        System.out.println(map[(int)(x / 30 - 1 - i)][(int)(y / 30 - 1 + j)]);
//                        map[(int)(x / 30 - 1 - i)][(int)(y / 30 - 1 + j)] = entityId;
//                    }
//                }
//                break;
//            }
//            case 2: {
//                for (int i = 0; i < scale; i++) {
//                    for (int j = 0; j < scale - i; j++) {
//                        map[(int)(x / 30 - 1 - i)][(int)(y / 30 - 1 + j)] = 0;
//                    }
//                }
//                for (int i = 0; i < scale; i++) {
//                    for (int j = 0; j < scale - i; j++) {
//                        map[(int)(x / 30 - 1 - i)][(int)(y / 30 - 1 - j)] = entityId;
//                    }
//                }
//                break;
//            }
//            case 3:{
//                for (int i = 0; i < scale; i++) {
//                    for (int j = 0; j < scale - i; j++) {
//                        map[(int)(x / 30 - 1 - i)][(int)(y / 30 - 1 - j)] = 0;
//                    }
//                }
//                for (int i = 0; i < scale; i++) {
//                    for (int j = 0; j < scale - i; j++) {
//                        map[(int)(x / 30 - 1 - i)][(int)(y / 30 - 1 + j)] = entityId;
//                    }
//                }
//                break;
//            }
//        }
        return ret;
    }
}
