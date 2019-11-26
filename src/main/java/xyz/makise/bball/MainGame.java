package xyz.makise.bball;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import xyz.makise.bball.components.*;
import xyz.makise.bball.model.EntityType;

import static com.almasb.fxgl.dsl.FXGL.*;

/*
 * 游戏的主类
 *
 * */
public class MainGame extends GameApplication {
    private Entity ball;
    private Entity currentEntity;
    private MyComponent currentComponent;
    private CrossBarComponent crossBar;
    private int[][] map;//用于存储地图信息，0表示未占用，1表示为一般组件占用，2表示为三角形部件

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
//                physicsOff();
//                ball.rotateBy(90);
//                triangle.rotateBy(90);
                currentEntity = currentComponent.rotate();
//                ball.rotateToVector(new Point2D(90,90));
                currentComponent = currentEntity.getComponent(TriangleComponent.class);
//                physicsOn();
            }
        }, KeyCode.Y);

        getInput().addAction(new UserMouseAction("click"), MouseButton.PRIMARY);

        getInput().addAction(new UserAction("begin") {
            @Override
            protected void onActionBegin() {
//                getPhysicsWorld().setGravity(0,100);
                ball = spawn("ball", 195, 45);
            }
        }, KeyCode.B);

        getInput().addAction(new UserAction("left") {
            @Override
            protected void onActionEnd() {
                crossBar.stop();
            }

            @Override
            protected void onAction() {
                crossBar.left();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("right") {
            @Override
            protected void onActionEnd() {
                crossBar.stop();
            }

            @Override
            protected void onAction() {
                crossBar.right();
            }
        }, KeyCode.D);
    }


    /*
     * 所有和collision有关的初始化
     *
     * */
    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PIPE, EntityType.BALL) {

            @Override
            protected void onCollisionEnd(Entity pipe, Entity ball) {
                int direction = pipe.getComponent(PipeComponent.class).getDirection();
                if (direction == 1) {
                    ball.getComponent(PhysicsComponent.class).setLinearVelocity(0, 100);
                } else {
                    ball.getComponent(PhysicsComponent.class).setLinearVelocity(0, 0);
                    System.out.println("collide with pipe");
                }
            }

            @Override
            protected void onHitBoxTrigger(Entity a, Entity b, HitBox boxA, HitBox boxB) {
                System.out.println("1");
            }

            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                System.out.println(2);
            }

            @Override
            protected void onCollision(Entity a, Entity b) {
                System.out.println(3);
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.CURVED_PIPE, EntityType.BALL) {
            @Override
            protected void onCollision(Entity curvedPipe, Entity ball) {
                int direction = curvedPipe.getComponent(CurvedPipeComponent.class).getDirection();
                switch (direction) {
                    case 0: {
                        ball.getComponent(PhysicsComponent.class).setLinearVelocity(100, 0);
                        break;
                    }
                    case 1: {
                        ball.getComponent(PhysicsComponent.class).setLinearVelocity(0, -100);;
                        break;
                    }
                    case 2:{
                        ball.getComponent(PhysicsComponent.class).setLinearVelocity(-100, 0);
                        break;
                    }
                    case 3:{
                        ball.getComponent(PhysicsComponent.class).setLinearVelocity(0, 100);
                        break;
                    }
                }
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

    }

    private void initBoard() {
        map = new int[20][20];
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

//    /*
//    * 文件系统相关 不知道有没有问题
//    * 文件系统最好放到MainGame类外面去 和model包放在一块
//    * */
//    public File getPersonFilePath(){
//        Preferences prefs = Preferences.userNodeForPackage(MainGame.class);
//        String filePath = prefs.get("filePath", null);
//        if (filePath != null) {
//            return new File(filePath);
//        } else {
//            return null;
//        }
//    }
//
//    public void setPersonFilePath(File file) {
//        Preferences prefs = Preferences.userNodeForPackage(MainGame.class);
//        if (file != null) {
//            prefs.put("filePath", file.getPath());
//
////            // Update the stage title.
////            primaryStage.setTitle("AddressApp - " + file.getName());
//        } else {
//            prefs.remove("filePath");
//
////            // Update the stage title.
////            primaryStage.setTitle("AddressApp");
//        }
//    }
//
//    public void loadPersonDataFromFile(File file) {
//        try {
//            JAXBContext context = JAXBContext
//                    .newInstance(ChessBoard.class);
//            Unmarshaller um = context.createUnmarshaller();
//
//            // Reading XML from the file and unmarshalling.
//            ChessBoard wrapper = (ChessBoard) um.unmarshal(file);
//
//            chessBoard.clear();
//            chessBoard.getComponents().addAll(wrapper.getComponents());
//            chessBoard.setCrossBarLeft(wrapper.getCrossBarLeft());
//            chessBoard.setCrossBarRight(wrapper.getCrossBarRight());
//
//            // Save the file path to the registry.
//            setPersonFilePath(file);
//
//        } catch (Exception e) { // catches ANY exception
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setContentText("Could not load data from file:\n" + file.getPath());
//            alert.showAndWait();
//        }
//    }
//
//    public void savePersonDataToFile(File file) {
//        try {
//            JAXBContext context = JAXBContext
//                    .newInstance(ChessBoard.class);
//            Marshaller m = context.createMarshaller();
//            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//            // Wrapping our person data.
////            ChessBoard wrapper = new ChessBoard();
////            wrapper.setComponents(personData);
//
//            // Marshalling and saving XML to the file.
//            m.marshal(chessBoard, file);
//
//            // Save the file path to the registry.
//            setPersonFilePath(file);
//        } catch (Exception e) { // catches ANY exception
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setContentText("Could not save data to file:\n" + file.getPath());
//            alert.showAndWait();
//        }
//    }
}
