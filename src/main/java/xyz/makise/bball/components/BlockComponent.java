package xyz.makise.bball.components;

import com.almasb.fxgl.entity.component.Component;

public class BlockComponent extends Component {
    private int x;
    private int y;

    @Override
    public void onAdded() {
        x=(int)(entity.getX()/30);
        y=(int)(entity.getY()/30);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
