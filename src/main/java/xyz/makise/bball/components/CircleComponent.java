package xyz.makise.bball.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.*;

public class CircleComponent extends Component implements MyComponent {
    private int scale;

    @Override
    public Entity rotate() {
        return null;
    }

    @Override
    public Entity zoomOut() {
        int scale = getEntity().getComponent(CircleComponent.class).getScale() + 1;
        SpawnData data = new SpawnData(getEntity().getX() + 15 * scale, getEntity().getY() + 15 * scale);
        data.put("scale", scale);
        getPhysicsWorld().onEntityRemoved(getEntity());
        getEntity().removeFromWorld();
        return spawn("circle", data);
    }

    @Override
    public Entity zoomIn() {
        int scale = getEntity().getComponent(CircleComponent.class).getScale() - 1;
        SpawnData data = new SpawnData(getEntity().getX() + 15 * scale, getEntity().getY() + 15 * scale);
        data.put("scale", scale);
        getPhysicsWorld().onEntityRemoved(getEntity());
        getEntity().removeFromWorld();
        return spawn("circle", data);
    }

    @Override
    public int getScale() {
        return scale;
    }

    @Override
    public void setScale(int scale) {
        this.scale = scale;
    }
}
