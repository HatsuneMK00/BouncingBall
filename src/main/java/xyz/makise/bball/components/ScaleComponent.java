package xyz.makise.bball.components;

import com.almasb.fxgl.entity.component.Component;

public class ScaleComponent extends Component {
    private int scale;

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
