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
import xyz.makise.bball.components.*;
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

    @Spawns("ball")
    public Entity newBall(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.DYNAMIC);
        FixtureDef fd = new FixtureDef();
        fd.setDensity(0f);
        fd.setRestitution(1f);
        physicsComponent.setFixtureDef(fd);

        Point2D location = new Point2D(-12, -12);

        return entityBuilder()
                .type(EntityType.BALL)
                .from(data)
                .view(new Circle(12, Color.RED))
                .bbox(new HitBox(location, BoundingShape.circle(12)))
                .collidable()
                .with(physicsComponent)
                .build();
    }

    @Spawns("circle")
    public Entity newCircle(SpawnData data) {
        int scale = data.get("scale");
        double radius = 15 * scale;

        CircleComponent circleComponent = new CircleComponent();
        circleComponent.setScale(scale);

        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.STATIC);
        FixtureDef fd = new FixtureDef();
        fd.setDensity(0f);
        fd.setRestitution(1f);
        physicsComponent.setFixtureDef(fd);

        return entityBuilder()
                .type(EntityType.CIRCLE)
                .from(data)
                .view(new Circle(radius, Color.RED))
                .bbox(new HitBox(new Point2D(-radius, -radius), BoundingShape.circle(radius)))
                .collidable()
                .with(circleComponent)
                .with(physicsComponent)
                .build();
    }

    @Spawns("rectangle")
    public Entity newRectangle(SpawnData data) {
        int scale = data.get("scale");
        double width = 30 * scale;
        double height = 30 * scale;

        RectangleComponent rectangleComponent = new RectangleComponent();
        rectangleComponent.setScale(scale);

        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.STATIC);
        FixtureDef fd = new FixtureDef();
        fd.setDensity(0f);
        fd.setRestitution(1f);
        physicsComponent.setFixtureDef(fd);

        return entityBuilder()
                .type(EntityType.RECTANGLE)
                .from(data)
                .view(new Rectangle(width, height, Color.RED))
                .bbox(new HitBox(BoundingShape.box(width, height)))
                .collidable()
                .with(physicsComponent)
                .with(rectangleComponent)
                .build();
    }

    @Spawns("triangle")
    public Entity newTriangle(SpawnData data) {
        int direction = data.get("direction");
        int scale = data.get("scale");
        double length = 30 * scale;

        TriangleComponent triangleComponent = new TriangleComponent();
        triangleComponent.setDirection(direction);
        triangleComponent.setScale(scale);

        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.STATIC);
        FixtureDef fd = new FixtureDef();
        fd.setDensity(0f);
        fd.setRestitution(1f);
        physicsComponent.setFixtureDef(fd);

        HitBox hitBox = null;
        Polygon polygon = new Polygon();
        double x = data.getX();
        double y = data.getY();
        System.out.println(x);
        System.out.println(y);
        polygon.setFill(Color.RED);
        switch (direction) {
            case 0: {
                polygon.getPoints().addAll(
                        0.0, 0.0,
                        length, 0.0,
                        0.0, length
                );
                hitBox = new HitBox(BoundingShape.polygon(
                        length, 0.0,
                        0.0, 0.0,
                        0.0, length
                ));
                break;
            }
            case 1: {
                polygon.getPoints().addAll(
                        0.0, 0.0,
                        length, 0.0,
                        0.0, -length
                );
                hitBox = new HitBox(BoundingShape.polygon(
                        length, 0.0,
                        0.0, 0.0,
                        0.0, -length
                ));
                break;
            }
            case 2: {
                polygon.getPoints().addAll(
                        0.0, 0.0,
                        -length, 0.0,
                        0.0, -length
                );
                hitBox = new HitBox(BoundingShape.polygon(
                        -length, 0.0,
                        0.0, 0.0,
                        0.0, -length
                ));
                break;
            }
            case 3: {
                polygon.getPoints().addAll(
                        0.0, 0.0,
                        -length, 0.0,
                        0.0, length
                );
                hitBox = new HitBox(BoundingShape.polygon(
                        -length, 0.0,
                        0.0, 0.0,
                        0.0, length
                ));
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
                .bbox(hitBox)
                .build();
    }

    @Spawns("blackHole")
    public Entity newBlackHole(SpawnData data) {
        return entityBuilder()
                .type(EntityType.BLACK_HOLE)
                .from(data)
                .view("blackHole.png")
                .collidable()
                .build();
    }

    @Spawns("pipe")
    public Entity newPipe(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.STATIC);
        return entityBuilder()
                .type(EntityType.PIPE)
                .from(data)
                .viewWithBBox("pipe.png")
                .with(new PipeComponent())
                .with(physicsComponent)
                .collidable()
                .build();
    }

    @Spawns("curvedPipe")
    public Entity newCurvedPipe(SpawnData data) {
        return entityBuilder()
                .type(EntityType.CURVED_PIPE)
                .from(data)
                .view("curvedPipe.png")
                .collidable()
                .with(new CurvedPipeComponent())
                .build();
    }

    @Spawns("crossBar")
    public Entity newCrossBar(SpawnData data) {
        return entityBuilder()
                .type(EntityType.CROSS_BAR)
                .from(data)
                .view("crossBar.png")
                .collidable()
                .build();
    }

    @Spawns("block")
    public Entity newBlock(SpawnData data) {
        return entityBuilder()
                .from(data)
                .view(new Rectangle(29, 29, Color.BLACK))
                .build();
    }
}
