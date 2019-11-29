package xyz.makise.bball.controller;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import xyz.makise.bball.model.EntityType;
import xyz.makise.bball.model.FileSystem;

import java.io.File;
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

    @FXML
    public void startGame(){
        List<Entity> entities = getGameWorld().getEntitiesByType(EntityType.BALL);
        for (Entity entity :
                entities) {
            SpawnData data = new SpawnData(entity.getX(),entity.getY());
            data.put("type",1);
            Entity ball = spawn("ball",data);
            ball.getComponent(PhysicsComponent.class).setLinearVelocity(0,-800);
            getPhysicsWorld().onEntityRemoved(entity);
            entity.removeFromWorld();
        }
        gameStarted = true;
    }

    @FXML
    public void endGame(){
        getGameWorld().removeEntities(getGameWorld().getEntities());
        gameStarted = false;
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            FileSystem.getFileSystem().loadPersonDataFromFile(file);
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            FileSystem.getFileSystem().savePersonDataToFile(file);
        }
    }
}
