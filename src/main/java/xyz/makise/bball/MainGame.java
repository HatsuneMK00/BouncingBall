package xyz.makise.bball;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import xyz.makise.bball.components.*;
import xyz.makise.bball.controller.GameLayout;
import xyz.makise.bball.model.EntityType;

import static com.almasb.fxgl.dsl.FXGL.*;

/*
 * 游戏的主类
 *
 * */
public class MainGame extends GameApplication {
    private Entity currentEntity;
    //    private MyComponent currentComponent;
    private CrossBarComponent leftCrossBar;
    private CrossBarComponent rightCrossBar;
    private GameLayout gameUiController;

    public GameLayout getGameUiController() {
        return gameUiController;
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setIntroEnabled(false);
        gameSettings.setMenuEnabled(false);
        gameSettings.setCloseConfirmation(false);
        gameSettings.setHeight(600);
        gameSettings.setWidth(820);
    }


    /*
     * 所有和输入初始化有关的工作
     *
     * */
    @Override
    protected void initInput() {
//        onKey(KeyCode.D,()->ball.rotateBy(90));
        getInput().addAction(new UserAction("rotate") {
            @Override
            protected void onActionBegin() {
                currentEntity = EntityPlacer.getEntityPlacer().rotate(currentEntity);
            }
        }, KeyCode.R);

        getInput().addAction(new UserAction("zoomOut") {
            @Override
            protected void onActionBegin() {
                currentEntity = EntityPlacer.getEntityPlacer().zoomOut(currentEntity);
            }
        }, KeyCode.K);

        getInput().addAction(new UserAction("startGame") {
            @Override
            protected void onActionBegin() {
//                Entity entity = spawn("crossBar",300,570);
//                leftCrossBar = entity.getComponent(CrossBarComponent.class);
                gameUiController.startGame();
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("zoomIn") {
            @Override
            protected void onActionBegin() {
                currentEntity = EntityPlacer.getEntityPlacer().zoomIn(currentEntity);
            }
        }, KeyCode.L);

        getInput().addAction(new UserAction("click") {
            @Override
            protected void onActionBegin() {

                Point2D mousePosition = getInput().getMousePositionWorld();
                System.out.println(mousePosition);
                double x = mousePosition.getX();
                double y = mousePosition.getY();
                if (EntityPlacer.getEntityPlacer().isOutOfBound(x, y)) return;
                currentEntity = EntityPlacer.getEntityPlacer().placeEntity(gameUiController.getSelectedRadioButtonText(), x,
                        y);
                if (currentEntity.hasComponent(CrossBarComponent.class)) {
                    if (currentEntity.getComponent(CrossBarComponent.class).getType() == 0) {
                        leftCrossBar = currentEntity.getComponent(CrossBarComponent.class);
                    } else {
                        rightCrossBar = currentEntity.getComponent(CrossBarComponent.class);
                    }
                }
            }
        }, MouseButton.PRIMARY);

        getInput().addAction(new UserAction("left") {
            @Override
            protected void onActionEnd() {
                leftCrossBar.stop();
            }

            @Override
            protected void onAction() {
                leftCrossBar.left();
            }
        }, KeyCode.Z);

        getInput().addAction(new UserAction("right") {
            @Override
            protected void onActionEnd() {
                leftCrossBar.stop();
            }

            @Override
            protected void onAction() {
                leftCrossBar.right();
            }
        }, KeyCode.C);

        getInput().addAction(new UserAction("left2") {
            @Override
            protected void onActionEnd() {
                rightCrossBar.stop();
            }

            @Override
            protected void onAction() {
                rightCrossBar.left();
            }
        }, KeyCode.B);

        getInput().addAction(new UserAction("right2") {
            @Override
            protected void onActionEnd() {
                rightCrossBar.stop();
            }

            @Override
            protected void onAction() {
                rightCrossBar.right();
            }
        }, KeyCode.M);

        getInput().addAction(new UserAction("delete") {
            @Override
            protected void onActionBegin() {
                if (currentEntity != null){
                    getPhysicsWorld().onEntityRemoved(currentEntity);
                    currentEntity.removeFromWorld();
                }
            }
        },KeyCode.D);
    }


    /*
     * 所有和collision有关的初始化
     *
     * */
    @Override
    protected void initPhysics() {
//        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PIPE, EntityType.BALL) {
//
//            @Override
//            protected void onCollisionBegin(Entity pipe, Entity ball) {
//                Point2D velocity = ball.getComponent(PhysicsComponent.class).getLinearVelocity();
//                Point2D newVelocity = new Point2D(0,velocity.getY()*1.2);
//                getPhysicsWorld().onEntityRemoved(ball);
//                ball.removeFromWorld();
//                Entity newBall = spawn("ball",new SpawnData(pipe.getX()+30,pipe.getY()+30).put("type",1));
//                newBall.getComponent(PhysicsComponent.class).setLinearVelocity(newVelocity);
//            }
//        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.CURVED_PIPE, EntityType.BALL) {
            @Override
            protected void onHitBoxTrigger(Entity a, Entity ball, HitBox boxA, HitBox boxB) {
                String direction = boxA.getName();
                switch (direction) {
                    case "right": {
                        ball.getComponent(PhysicsComponent.class).setLinearVelocity(-500, 0);
                        break;
                    }
                    case "left": {
                        ball.getComponent(PhysicsComponent.class).setLinearVelocity(500, 0);
                        break;
                    }
                }
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.BLACK_HOLE, EntityType.BALL) {
            @Override
            protected void onCollisionBegin(Entity blackHole, Entity ball) {
                getPhysicsWorld().onEntityRemoved(ball);
                ball.removeFromWorld();
                System.out.println("generate next ball");
            }
        });

    }

    /*
     * 所有和游戏初始化有关的工作
     *
     * */
    @Override
    protected void initGame() {
        super.initGame();
        initUIView();
        initBoard();
        initGameEntity();
        spawn("wall", 0, 0);
    }

    private void initBoard() {
        getGameWorld().addEntityFactory(new GameFactory());
        for (int i = 0; i <= 570; i += 30) {
            for (int j = 0; j <= 570; j += 30) {
                spawn("block", i, j);
            }
        }
    }

    private void initGameEntity() {
//        SpawnData data = new SpawnData(180, 180);
//        data.put("direction", 0);
//        data.put("scale", 2);
//        currentEntity = spawn("triangle", data);
//        currentComponent = currentEntity.getComponent(TriangleComponent.class);
//        spawn("blackHole",90,180);
//        spawn("pipe",180,180);
    }

    private void initUIView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/GameLayout.fxml"));
            GridPane gameUI = loader.load();
            getGameScene().addUINode(gameUI);
            getGameScene().getUiNodes().get(0).setTranslateX(620);
            getGameScene().getUiNodes().get(0).setTranslateY(20);
            gameUiController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
