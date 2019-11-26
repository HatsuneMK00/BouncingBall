package xyz.makise.bball;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
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
        Rectangle rectangle = new Rectangle(10, 30, Color.RED);
        Point2D location = new Point2D(data.getX(),data.getY());

        Entity entity = entityBuilder()
                .type(EntityType.BALL)
                .from(data)
                //.viewWithBBox(new Circle(15, Color.RED))
//                .view(rectangle)
//                .bbox(new HitBox(location,BoundingShape.box(10,30)))
                .viewWithBBox(rectangle)
                .with(new BlockComponent())
                .collidable()
//                .with(new PhysicsComponent())
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
        Polygon polygon = new Polygon();
        double x = data.getX();
        double y = data.getY();
        polygon.setFill(Color.RED);
        polygon.getPoints().addAll(
                x, y,
                x + 30, y,
                x, y + 30
        );
        return entityBuilder()
                .type(EntityType.TRIANGLE)
                .from(data)
                .viewWithBBox(polygon)
                .collidable()
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
