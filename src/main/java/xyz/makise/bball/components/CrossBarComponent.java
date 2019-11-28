package xyz.makise.bball.components;


import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class CrossBarComponent extends Component {
    private static final double SPEED = 400;
    private int type;
    protected PhysicsComponent physicsComponent;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void left() {
        if (entity.getX() >= SPEED / 80) {
            physicsComponent.setVelocityX(-SPEED);
        } else {
            stop();
        }
    }

    public void right() {
        if (entity.getX() <= FXGL.getAppWidth() - 275 - SPEED / 80) {
            physicsComponent.setVelocityX(SPEED);
        } else {
            stop();
        }
    }

    public void stop() {
        physicsComponent.setLinearVelocity(0, 0);
    }
}
