package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import javafx.scene.control.Alert;
import xyz.makise.bball.MainGame;
import xyz.makise.bball.components.CircleComponent;
import xyz.makise.bball.components.DirectionComponent;
import xyz.makise.bball.components.EntityPlacer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.prefs.Preferences;

import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;

public class FileSystem {
    /*
    * 文件系统相关 不知道有没有问题
    * 文件系统最好放到MainGame类外面去 和model包放在一块
    */
    private ChessBoard chessBoard;
    private static FileSystem fileSystem;
    public File getPersonFilePath(){
        Preferences prefs = Preferences.userNodeForPackage(MainGame.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    private FileSystem(){
        this.chessBoard = new ChessBoard();
    }
    //FileSystem是一个单实例类
    public static FileSystem getFileSystem(){
        if(fileSystem==null){
            fileSystem = new FileSystem();
        }
        return fileSystem;
    }
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainGame.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

//            // Update the stage title.
//            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

//            // Update the stage title.
//            primaryStage.setTitle("AddressApp");
        }
    }

    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(ChessBoard.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            ChessBoard wrapper = (ChessBoard) um.unmarshal(file);

            chessBoard.clear();
            chessBoard.getComponents().addAll(wrapper.getComponents());
            loadFromChessBoard();
            // Save the file path to the registry.
            setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            alert.showAndWait();
        }
    }
    private void loadFromChessBoard(){
        ArrayList<GameComponent> entityList = chessBoard.getComponents();
        for(GameComponent model : entityList){
            spawnFromModel(model);
        }
    }
    private void spawnFromModel(GameComponent model){
        EntityType entityType = model.getType();
        if(entityType==EntityType.TRIANGLE){
            Triangle triangle = (Triangle) model;
            SpawnData spawnData = new SpawnData(triangle.getPositionX(),triangle.getPositionY());
            spawnData.put("scale",triangle.getLength());
            spawnData.put("direction",triangle.getLength());
            spawn("triangle",spawnData);
        }
        else if(entityType==EntityType.BLACK_HOLE){
            BlackHole blackHole = (BlackHole) model;
            SpawnData spawnData = new SpawnData(blackHole.getPositionX(),blackHole.getPositionY());
            spawnData.put("scale",blackHole.getLength());
            spawn("blackHole",spawnData);
        }
        else if(entityType==EntityType.CIRCLE){
            Circle circle = (Circle)model;
            SpawnData spawnData = new SpawnData(circle.getCircleCenterX(),circle.getCircleCenterY());
            spawnData.put("scale",circle.getScale());
            spawn("circle",spawnData);
        }
        else if(entityType==EntityType.BALL){
            Ball ball = (Ball)model;
            SpawnData spawnData = new SpawnData(ball.getBallCenterX(),ball.getBallCenterY());
            spawnData.put("scale",ball.getScale());
            spawn("ball",spawnData);
        }
        else if(entityType==EntityType.PIPE){
            Pipe pipe = (Pipe)model;
            SpawnData spawnData = new SpawnData(pipe.getPositionX(),pipe.getPositionY());
            spawnData.put("direction",pipe.getDirection());
            spawn("pipe",spawnData);
        }
        else if(entityType==EntityType.CURVED_PIPE){
            CurvedPipe curvedPipe = (CurvedPipe)model;
            SpawnData spawnData = new SpawnData(curvedPipe.getPositionX(),curvedPipe.getPositionY());
            spawnData.put("direction",curvedPipe.getDirection());
            spawn("curvedPipe",spawnData);
        }
        else if(entityType==EntityType.RECTANGLE){
            Rectangle rectangle = (Rectangle)model;
            SpawnData spawnData = new SpawnData(rectangle.getPositionX(),rectangle.getPositionY());
            spawnData.put("scale",rectangle.getLength());
            spawn("rectangle",spawnData);
        }
    }
    private GameComponent getModel(Entity entity){
        GameComponent model=null;
        if(entity.getType()==EntityType.BALL){
            model = new Ball(
                    entity.getTransformComponent().getScaleX(),
                    entity.getTransformComponent().getX(),
                    entity.getTransformComponent().getY());
        }
        else if(entity.getType()==EntityType.BLACK_HOLE){
            model = new BlackHole(
                    entity.getTransformComponent().getX(),
                    entity.getTransformComponent().getY(),
                    entity.getTransformComponent().getScaleX()
            );
        }
        else if(entity.getType()==EntityType.CURVED_PIPE){
            model = new CurvedPipe(
                    entity.getTransformComponent().getX(),
                    entity.getTransformComponent().getY(),
                    entity.getComponent(DirectionComponent.class).getDirection()
            );
        }
        else if(entity.getType()==EntityType.PIPE){
            model = new Pipe(
                    entity.getTransformComponent().getX(),
                    entity.getTransformComponent().getY(),
                    entity.getComponent(DirectionComponent.class).getDirection()
            );
        }
        else if(entity.getType()==EntityType.CIRCLE){
            model = new Circle(
                    entity.getTransformComponent().getScaleX(),
                    entity.getTransformComponent().getX(),
                    entity.getTransformComponent().getY()
            );
        }
        else if(entity.getType()==EntityType.RECTANGLE){
            model = new Rectangle(
                    entity.getTransformComponent().getX(),
                    entity.getTransformComponent().getY(),
                    entity.getTransformComponent().getScaleX()
            );
        }
        else if(entity.getType()==EntityType.TRIANGLE){
            model = new Triangle(
                    entity.getX(),
                    entity.getY(),
                    entity.getScaleX(),
                    entity.getComponent(DirectionComponent.class).getDirection()
            );
        }
        else if(entity.getType()==EntityType.CROSS_BAR){

        }
        return model;
    }
    private void setChessBoard(){
        if(chessBoard==null)
            chessBoard=new ChessBoard();
        EntityPlacer entityPlacer = EntityPlacer.getEntityPlacer();
        HashMap<Integer, Entity> hashMap = entityPlacer.getEntityMap();
        for(int key : hashMap.keySet()){
            //GameComponent model = hashMap.get(key).getComponent()
            Entity entity = hashMap.get(key);
            GameComponent model;
            model = getModel(entity);
            chessBoard.getComponents().add(model);
        }
    }
    public void savePersonDataToFile(File file) {
        setChessBoard();
        try {
            JAXBContext context = JAXBContext
                    .newInstance(ChessBoard.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
//            ChessBoard wrapper = new ChessBoard();
//            wrapper.setComponents(personData);

            // Marshalling and saving XML to the file.
            m.marshal(chessBoard, file);

            // Save the file path to the registry.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            alert.showAndWait();
        }
   }

}
