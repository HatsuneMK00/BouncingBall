package xyz.makise.bball.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import static com.almasb.fxgl.dsl.FXGL.*;

public class TriangleComponent extends Component implements MyComponent {

    public void onAdded(){
        entity.getTransformComponent().setScaleOrigin(entity.getCenter());
    }
    private int direction = 0;


    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    private int scale = 1;

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public Entity rotate() {
        double x, y;
        Entity ret = null;
        x = getEntity().getX();
        y = getEntity().getY();
        SpawnData data = new SpawnData(x, y);
        data.put("scale", getEntity().getComponent(TriangleComponent.class).getScale());
        switch (direction) {
            case 0: {
                data.put("direction", 1);
                break;
            }
            case 1: {
                data.put("direction", 2);
                break;
            }
            case 2: {
                data.put("direction", 3);
                break;
            }
            case 3: {
                data.put("direction", 0);
                break;
            }
        }
//        getEntity().removeComponent(PhysicsComponent.class);
        getGameWorld().removeEntity(getEntity());
        ret = spawn("triangle",data);
        return ret;
    }

    @Override
    public Entity zoomOut() {
        return null;
    }

    @Override
    public Entity zoomIn() {
        return null;
    }
}
