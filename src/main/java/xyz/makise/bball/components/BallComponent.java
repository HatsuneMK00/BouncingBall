package xyz.makise.bball.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import xyz.makise.bball.model.GameComponent;

import java.util.ArrayList;
import java.util.List;

public class BallComponent extends Component {
    private ArrayList<Entity> pipes = new ArrayList<>();
    private PhysicsComponent physicsComponent;

    @Override
    public void onUpdate(double tpf) {

    }

    public void addPipe(Entity entity){
        pipes.add(entity);
    }

    private boolean isCollideWithPipe(){
        return false;
    }

}
