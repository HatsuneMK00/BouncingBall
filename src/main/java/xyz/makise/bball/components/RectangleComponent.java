package xyz.makise.bball.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;

import static com.almasb.fxgl.dsl.FXGL.getPhysicsWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class RectangleComponent extends Component implements MyComponent{
    private int scale;

    @Override
    public Entity rotate() {
        return null;
    }

    @Override
    public Entity zoomOut() {
        SpawnData data = new SpawnData(getEntity().getX(), getEntity().getY());
        data.put("scale", getEntity().getComponent(CircleComponent.class).getScale() + 1);
        getPhysicsWorld().onEntityRemoved(getEntity());
        getEntity().removeFromWorld();
        return spawn("rectangle", data);
    }

    @Override
    public Entity zoomIn() {
        SpawnData data = new SpawnData(getEntity().getX(), getEntity().getY());
        data.put("scale", getEntity().getComponent(CircleComponent.class).getScale() - 1);
        getPhysicsWorld().onEntityRemoved(getEntity());
        getEntity().removeFromWorld();
        return spawn("rectangle", data);
    }

    @Override
    public int getScale() {
        return scale;
    }

    @Override
    public void setScale(int scale) {
        this.scale = scale;
    }
//    public void onAdded(){
//        entity.getTransformComponent().setScaleOrigin(entity.getCenter());
//    }
}
