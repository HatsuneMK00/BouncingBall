package xyz.makise.bball;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Point2D;
import org.jetbrains.annotations.NotNull;
import xyz.makise.bball.controller.GameLayout;

import static com.almasb.fxgl.dsl.FXGL.*;

public class UserMouseAction extends UserAction {
    private GameLayout gameController = new GameLayout();
    public UserMouseAction(@NotNull String name) {
        super(name);
    }

    @Override
    protected void onActionBegin() {
        if (!gameController.isGameStarted()){
            Point2D mousePosition = getInput().getMousePositionWorld();
            System.out.println(mousePosition);
            double justifiedX = (int)(mousePosition.getX() / 30) * 30.0;
            double justifiedY = (int)(mousePosition.getY() / 30) * 30.0;
            System.out.println(justifiedX);
            System.out.println(justifiedY);
            spawn("circle",new SpawnData(justifiedX + 15,justifiedY + 15).put("scale",1));
        }
    }
}
