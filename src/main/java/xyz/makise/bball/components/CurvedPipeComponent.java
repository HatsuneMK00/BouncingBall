package xyz.makise.bball.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;


public class CurvedPipeComponent extends Component {
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(entity.getCenter());
    }

    private int direction = 0;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void rotate(){
        getEntity().rotateBy(90);
    }
}
