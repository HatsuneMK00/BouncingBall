package xyz.makise.bball;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import org.w3c.dom.CDATASection;
import xyz.makise.bball.components.BallComponent;
import xyz.makise.bball.components.BlockComponent;
import xyz.makise.bball.components.TriangleComponent;
import xyz.makise.bball.model.Ball;
import xyz.makise.bball.model.EntityType;
import xyz.makise.bball.model.Triangle;

import java.awt.*;

import static com.almasb.fxgl.dsl.FXGL.*;


/*
 * 一个用于生成所有游戏所需要的组件的工厂
 * 被调用后将在对应的位置产生该物体
 * 需要注意设定位置时要考虑到物体本身的大小
 *
 * 需要添加特殊操作的组件继承Component写一个新的Component然后在工厂里面在entityBuilder中添加进去即可
 * 先不修改model中的类 文件系统的负责人再这些类进行修改
 *
 * */
public class GameFactory implements EntityFactory {
    private int[][] map;

    public GameFactory() {
        map = new int[20][20];
    }

    @Spawns("ball")
    public Entity newBall(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.DYNAMIC);
        FixtureDef fd = new FixtureDef();
        fd.setDensity(0.7f);
        fd.setRestitution(0.5f);
        physicsComponent.setFixtureDef(fd);

        Point2D location = new Point2D(-10,-10);

        Entity entity = entityBuilder()
                .type(EntityType.BALL)
                .from(data)
                .view(new Circle(10, Color.RED))
                .bbox(new HitBox(location,BoundingShape.circle(10)))
                .collidable()
                .with(physicsComponent)
                .with(new BallComponent())
                .build();
        return entity;
    }

    @Spawns("rectangle")
    public Entity newRectangle(SpawnData data){
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.STATIC);
        FixtureDef fd = new FixtureDef();
        fd.setDensity(0.7f);
        fd.setRestitution(0.5f);
        physicsComponent.setFixtureDef(fd);

        Point2D location = new Point2D(data.getX(),data.getY());

        Entity entity = entityBuilder()
                .type(EntityType.RECTANGLE)
                .from(data)
                .view(new Rectangle(30,30, Color.RED))
                .bbox(new HitBox(BoundingShape.box(30,30)))
                .collidable()
                .with(physicsComponent)
                .with(new BallComponent())
                .build();
        return entity;
    }

    @Spawns("blackHole")
    public Entity newBlackHole(SpawnData data) {
        return entityBuilder()
                .type(EntityType.BLACK_HOLE)
                .from(data)
                .viewWithBBox(new Circle(15, Color.WHITE))
                .collidable()
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("triangle")
    public Entity newTriangle(SpawnData data) {
        int direction = data.get("direction");
        int scale = data.get("scale");

        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.STATIC);
        FixtureDef fd = new FixtureDef();
        fd.setDensity(0.7f);
        fd.setRestitution(0.3f);
        physicsComponent.setFixtureDef(fd);

        TriangleComponent triangleComponent = new TriangleComponent();
        triangleComponent.setDirection(direction);
        triangleComponent.setScale(scale);

        Polygon polygon = new Polygon();
        double x = data.getX();
        double y = data.getY();
        polygon.setFill(Color.RED);
        switch (direction) {
            case 0: {
                polygon.getPoints().addAll(
                        x, y,
                        x + 30 * scale, y,
                        x, y + 30 * scale
                );
                break;
            }
            case 1: {
                polygon.getPoints().addAll(
                        x, y,
                        x + 30 * scale, y,
                        x, y - 30 * scale
                );
                break;
            }
            case 2: {
                polygon.getPoints().addAll(
                        x, y,
                        x - 30 * scale, y,
                        x, y - 30 * scale
                );
                break;
            }
            case 3: {
                polygon.getPoints().addAll(
                        x, y,
                        x - 30 * scale, y,
                        x, y + 30 * scale
                );
                break;
            }
        }

        return entityBuilder()
                .type(EntityType.TRIANGLE)
                .from(data)
                .view(polygon)
                .collidable()
                .with(triangleComponent)
                .with(physicsComponent)
                .bbox(new HitBox(BoundingShape.polygon(
                        x ,y,
                        x + 30, y,
                        x, y+30
                )))
                .build();
    }

    @Spawns("block")
    public Entity newBlock(SpawnData data) {
        return entityBuilder()
                .from(data)
                .view(new Rectangle(28, 28, Color.BLACK))
                .build();
    }
}
