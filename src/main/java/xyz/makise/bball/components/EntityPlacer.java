package xyz.makise.bball.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;

import java.util.HashMap;

import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class EntityPlacer {
    private HashMap<Integer, Entity> entityMap = new HashMap<>();
    private int[][] map = new int[20][20];//用于存储地图信息，0表示未占用，1表示为一般组件占用，2表示为三角形部件
    private static int entityCount = 0;
    private EntityRotator entityRotator = new EntityRotator();
    private EntityZoomer entityZoomer = new EntityZoomer();
    private static EntityPlacer entityPlacer;
    private EntityPlacer(){

    }

    public static EntityPlacer getEntityPlacer() {
        if(entityPlacer==null)
            entityPlacer=new EntityPlacer();
        return entityPlacer;
    }

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
                int entityId = map[(int) (x / 30)][(int) (y / 30)];
                if (entityMap.containsKey(entityId)) {
                    System.out.println(entityId);
                    System.out.println(entityMap.get(entityId));
                    return entityMap.get(entityId);
                } else {
                    System.out.println(entityId);
                    System.out.println("nothing selected");
                }
                break;
            }
            case "ball": {
                entity = spawn("ball", new SpawnData(x + 15, y + 15).put("type",0));
                break;
            }
            case "triangle": {
                entityCount++;
                map[(int) (x / 30)][(int) (y / 30)] = entityCount;
                entity = spawn("triangle", new SpawnData(x, y).put("scale", 1).put("direction", 0));
                break;
            }
            case "rectangle": {
                entityCount++;
                map[(int) (x / 30)][(int) (y / 30)] = entityCount;
                entity = spawn("rectangle", new SpawnData(x, y).put("scale", 1));
                break;
            }
            case "circle": {
                entityCount++;
                map[(int) (x / 30)][(int) (y / 30)] = entityCount;
                entity = spawn("circle", new SpawnData(x + 15, y + 15).put("scale", 1));
                break;
            }
            case "black hole": {
                entityCount++;
                map[(int) (x / 30)][(int) (y / 30)] = entityCount;
                entity = spawn("blackHole", x, y);
                break;
            }
            case "pipe": {
                entityCount++;
                map[(int) (x / 30)][(int) (y / 30)] = entityCount;
                entity = spawn("pipe", new SpawnData(x,y).put("scale",1).put("direction",0));
                break;
            }
            case "curved pipe": {
                entityCount++;
                map[(int) (x / 30)][(int) (y / 30)] = entityCount;
                entity = spawn("curvedPipe", new SpawnData(x,y).put("scale",1).put("direction",0));
                break;
            }
//            left cross bar
            case "L cross bar":{
                entityCount++;
                map[(int) (x / 30)][(int) (y / 30)] = entityCount;
                entity = spawn("crossBar", new SpawnData(x,y).put("scale",1).put("direction",0).put("type",0));
                break;
            }
//            right cross bar
            case "R cross bar":{
                entityCount++;
                map[(int) (x / 30)][(int) (y / 30)] = entityCount;
                entity = spawn("crossBar", new SpawnData(x,y).put("scale",1).put("direction",0).put("type",1));
                break;
            }
            default: {
                System.out.println(name);
            }
        }
        entityMap.put(entityCount, entity);
        return entity;
    }

    public Entity rotate(Entity entity){
        return entityRotator.rotate(entity,this);
    }

    public Entity zoomOut(Entity entity){
        return entityZoomer.zoomOut(entity,this);
    }

    public Entity zoomIn(Entity entity){
        return entityZoomer.zoomIn(entity,this);
    }

    public boolean isOutOfBound(double x,double y){
        return x > getAppWidth() - 250;
    }
}
