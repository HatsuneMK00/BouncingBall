package xyz.makise.bball.components;

import com.almasb.fxgl.entity.component.Component;

public class RectangleComponent extends Component {
    public void onAdded(){
        entity.getTransformComponent().setScaleOrigin(entity.getCenter());
    }
}
