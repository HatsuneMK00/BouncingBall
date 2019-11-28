package xyz.makise.bball.controller;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import xyz.makise.bball.MainGame;
import xyz.makise.bball.model.EntityType;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.*;

/*
* 由于fxml和控制器不在一个包的话 无法在scene builder中绑定属性 暂时先放在一个包里
* 全部弄好之后再把fxml移到resources的view里面
* */
public class GameLayout {
    private boolean gameStarted = false;

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private RadioButton hand;
    @FXML
    private RadioButton ball;
    @FXML
    private RadioButton blackHole;
    @FXML
    private RadioButton triangle;
    @FXML
    private RadioButton circle;
    @FXML
    private RadioButton rectangle;
    @FXML
    private RadioButton pipe;
    @FXML
    private RadioButton curvedPipe;
    @FXML
    private RadioButton leftCrossBar;
    @FXML
    private RadioButton rightCrossBar;

    public String getSelectedRadioButtonText(){
        RadioButton selectedButton = (RadioButton) toggleGroup.getSelectedToggle();
        return selectedButton.getText();
    }

    public void startGame(){
        List<Entity> entities = getGameWorld().getEntitiesByType(EntityType.BALL);
        for (Entity entity :
                entities) {
            SpawnData data = new SpawnData(entity.getX(),entity.getY());
            data.put("type",1);
            spawn("ball",data);
            getPhysicsWorld().onEntityRemoved(entity);
            entity.removeFromWorld();
        }
        gameStarted = true;
    }
}
