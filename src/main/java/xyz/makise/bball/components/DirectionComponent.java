package xyz.makise.bball.components;

import com.almasb.fxgl.entity.component.Component;

public class DirectionComponent extends Component {
    private int direction;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
