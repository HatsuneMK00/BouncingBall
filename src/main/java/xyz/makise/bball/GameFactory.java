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
        int type = data.get("type");
        if (type == 0) {
            physicsComponent.setBodyType(BodyType.STATIC);
        } else {
            physicsComponent.setBodyType(BodyType.DYNAMIC);
        }
        FixtureDef fd = new FixtureDef();
        fd.setDensity(0f);
        fd.setRestitution(0.75f);
        fd.setFriction(0.5f);
        physicsComponent.setFixtureDef(fd);

        Point2D location = new Point2D(-10, -10);

        return entityBuilder()
                .type(EntityType.BALL)
                .from(data)
                .view(new Circle(10, Color.BLUEVIOLET))
                .bbox(new HitBox(location, BoundingShape.circle(10)))
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

        ScaleComponent scaleComponent = new ScaleComponent();
        scaleComponent.setScale(scale);

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
                .with(scaleComponent)
                .build();
    }

    @Spawns("rectangle")
    public Entity newRectangle(SpawnData data) {
        int scale = data.get("scale");
        double width = 30 * scale;
        double height = 30 * scale;

        ScaleComponent scaleComponent = new ScaleComponent();
        scaleComponent.setScale(scale);

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
                .with(scaleComponent)
                .build();
    }

    @Spawns("triangle")
    public Entity newTriangle(SpawnData data) {
        int direction = data.get("direction");
        int scale = data.get("scale");
        double length = 30 * scale;

        ScaleComponent scaleComponent = new ScaleComponent();
        scaleComponent.setScale(scale);

        DirectionComponent directionComponent = new DirectionComponent();
        directionComponent.setDirection(direction);

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
//        System.out.println(x);
//        System.out.println(y);
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
                        length, length,
                        0.0, length
                );
                hitBox = new HitBox(BoundingShape.polygon(
                        length, length,
                        0.0, 0.0,
                        0.0, length
                ));
                break;
            }
            case 2: {
                polygon.getPoints().addAll(
                        length, length,
                        length, 0.0,
                        0.0, length
                );
                hitBox = new HitBox(BoundingShape.polygon(
                        length, 0.0,
                        length, length,
                        0.0, length
                ));
                break;
            }
            case 3: {
                polygon.getPoints().addAll(
                        0.0, 0.0,
                        length, 0.0,
                        length, length
                );
                hitBox = new HitBox(BoundingShape.polygon(
                        length, 0.0,
                        0.0, 0.0,
                        length, length
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
                .with(scaleComponent)
                .with(directionComponent)
                .bbox(hitBox)
                .build();
    }

    @Spawns("blackHole")
    public Entity newBlackHole(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.STATIC);

        return entityBuilder()
                .type(EntityType.BLACK_HOLE)
                .from(data)
                .viewWithBBox("blackHole.png")
                .collidable()
                .build();
    }

    @Spawns("pipe")
    public Entity newPipe(SpawnData data) {
        int direction = data.get("direction");
        DirectionComponent directionComponent = new DirectionComponent();
        directionComponent.setDirection(direction);
        int scale = data.get("scale");
        ScaleComponent scaleComponent = new ScaleComponent();
        scaleComponent.setScale(scale);

        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.STATIC);
        physicsComponent.setFixtureDef(new FixtureDef().restitution(1));
//        这边真的神奇的很 完全不知道为什么viewWithBBox的旋转中心是正确的
        Entity entity = entityBuilder()
                .type(EntityType.PIPE)
                .from(data)
                .viewWithBBox("pipe.png")
                .with(new PipeComponent())
                .with(physicsComponent)
                .with(directionComponent)
                .with(scaleComponent)
                .collidable()
                .build();
        entity.rotateBy(90 * direction);
//
        entity.getBoundingBoxComponent().clearHitBoxes();
//        HitBox hitBox = new HitBox(BoundingShape.chain(
//                new Point2D(0, 0), new Point2D(0, 30),
//                new Point2D(30, 0), new Point2D(30, 30)
//        ));
//        entity.getBoundingBoxComponent().addHitBox(hitBox);
        return entity;
    }

    @Spawns("curvedPipe")
    public Entity newCurvedPipe(SpawnData data) {
        int direction = data.get("direction");
        DirectionComponent directionComponent = new DirectionComponent();
        directionComponent.setDirection(direction);

        int scale = data.get("scale");
        ScaleComponent scaleComponent = new ScaleComponent();
        scaleComponent.setScale(scale);

        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.STATIC);

        HitBox hitBox = new HitBox(BoundingShape.chain(new Point2D(0, 0), new Point2D(30, 0)));

        Entity entity = entityBuilder()
                .type(EntityType.CURVED_PIPE)
                .from(data)
                .viewWithBBox("curvedPipe.png")
                .collidable()
                .with(new CurvedPipeComponent())
                .with(directionComponent)
                .with(scaleComponent)
                .with(physicsComponent)
                .build();
        entity.rotateBy(90 * direction);
        entity.getBoundingBoxComponent().clearHitBoxes();
        entity.getBoundingBoxComponent().addHitBox(hitBox);
        return entity;
    }

    @Spawns("crossBar")
    public Entity newCrossBar(SpawnData data) {
        int type = data.get("type");

        CrossBarComponent crossBarComponent = new CrossBarComponent();
        crossBarComponent.setType(type);

        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.KINEMATIC);
        physicsComponent.setFixtureDef(new FixtureDef().restitution(1.2f).friction(0.3f));

        return entityBuilder()
                .type(EntityType.CROSS_BAR)
                .from(data)
                .viewWithBBox("crossBar.png")
                .with(physicsComponent)
                .with(crossBarComponent)
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

    @Spawns("wall")
    public Entity newWall(SpawnData data) {
        HitBox hitBox = new HitBox(BoundingShape.chain(
                new Point2D(600, 0), new Point2D(600, 600),
                new Point2D(599, 599), new Point2D(1, 599),
                new Point2D(0, 600), new Point2D(0, 0),
                new Point2D(1, 1), new Point2D(599, 1)

        ));
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.STATIC);
        return entityBuilder()
                .from(data)
                .bbox(hitBox)
                .with(physicsComponent)
                .build();
    }
}
