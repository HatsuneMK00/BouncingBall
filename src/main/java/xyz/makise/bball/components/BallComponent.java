package xyz.makise.bball.components;

import com.almasb.fxgl.entity.component.Component;
import xyz.makise.bball.model.GameComponent;

public class BallComponent extends Component {
    @Override
    public void onAdded(){
        entity.getTransformComponent().setScaleOrigin(entity.getCenter());
    }
    public void rotateClockwise(){
        entity.rotateBy(90);
    }

}
