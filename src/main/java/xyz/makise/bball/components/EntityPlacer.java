package xyz.makise.bball.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;

import java.util.HashMap;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class EntityPlacer {
    private HashMap<Integer, Entity> entityMap = new HashMap<>();
    private int[][] map = new int[20][20];//用于存储地图信息，0表示未占用，1表示为一般组件占用，2表示为三角形部件
    private static int entityCount = 0;

    public HashMap<Integer, Entity> getEntityMap() {
        return entityMap;
    }

    public int[][] getMap() {
        return map;
    }

    public Entity placeEntity(String name, double x, double y) {
        Entity entity = null;
        switch (name) {
            case "hand": {
                int entityId = map[(int)(x / 30 - 1)][(int)(y / 30 - 1)];
                if (entityMap.containsKey(entityId)){

                }
                break;
            }
            case "ball": {
                entity = spawn("ball", x + 12, y + 12);
                break;
            }
            case "triangle": {
                map[(int)(x / 30 - 1)][(int)(y / 30 - 1)] = entityCount;
                entity = spawn("triangle", new SpawnData(x, y).put("scale", 1).put("direction", 0));
                break;
            }
            case "rectangle": {
                map[(int)(x / 30 - 1)][(int)(y / 30 - 1)] = entityCount;
                entity = spawn("rectangle", new SpawnData(x, y).put("scale", 1));
                break;
            }
            case "circle": {
                map[(int)(x / 30 - 1)][(int)(y / 30 - 1)] = entityCount;
                entity = spawn("circle", new SpawnData(x + 15, y + 15).put("scale", 1));
                break;
            }
            case "black hole": {
                map[(int)(x / 30 - 1)][(int)(y / 30 - 1)] = entityCount;
                entity = spawn("blackHole", x, y);
                break;
            }
            case "pipe": {
                map[(int)(x / 30 - 1)][(int)(y / 30 - 1)] = entityCount;
                entity = spawn("pipe", x, y);
                break;
            }
            case "curved pipe": {
                map[(int)(x / 30 - 1)][(int)(y / 30 - 1)] = entityCount;
                entity = spawn("curvedPipe", x, y);
                break;
            }
            default:{
                System.out.println(name);
            }
        }
        entityMap.put(entityCount++, entity);
        return entity;
    }
}
