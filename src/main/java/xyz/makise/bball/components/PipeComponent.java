package xyz.makise.bball.components;

import com.almasb.fxgl.entity.component.Component;

public class PipeComponent extends Component {
    private int direction = 0;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
