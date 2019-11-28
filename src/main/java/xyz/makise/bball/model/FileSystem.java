package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import javafx.scene.control.Alert;
import xyz.makise.bball.MainGame;
import xyz.makise.bball.components.CircleComponent;
import xyz.makise.bball.components.CrossBarComponent;
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
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            alert.showAndWait();
        }
    }
    private void loadFromChessBoard(){
        ArrayList<EntityWrapper> entityList = chessBoard.getComponents();
        for(EntityWrapper model : entityList){
            spawnFromModel(model);
        }
    }
    private void spawnFromModel(EntityWrapper model){
        EntityType entityType = model.getEntityType();
        if(entityType==EntityType.TRIANGLE){
            EntityWrapper triangle = model;
            SpawnData spawnData = new SpawnData(triangle.getPositionX(),triangle.getPositionY());
            spawnData.put("scale",triangle.getScale());
            spawnData.put("direction",triangle.getDirection());
            spawn("triangle",spawnData);
        }
        else if(entityType==EntityType.BLACK_HOLE){
            EntityWrapper blackHole = model;
            SpawnData spawnData = new SpawnData(blackHole.getPositionX(),blackHole.getPositionY());
            spawnData.put("scale",blackHole.getScale());
            spawn("blackHole",spawnData);
        }
        else if(entityType==EntityType.CIRCLE){
            EntityWrapper circle = model;
            SpawnData spawnData = new SpawnData(circle.getPositionX(),circle.getPositionY());
            spawnData.put("scale",circle.getScale());
            spawn("circle",spawnData);
        }
        else if(entityType==EntityType.BALL){
            EntityWrapper ball = model;
            SpawnData spawnData = new SpawnData(ball.getPositionX(),ball.getPositionY());
            spawnData.put("scale",ball.getScale());
            spawn("ball",spawnData);
        }
        else if(entityType==EntityType.PIPE){
            EntityWrapper pipe = model;
            SpawnData spawnData = new SpawnData(pipe.getPositionX(),pipe.getPositionY());
            spawnData.put("direction",pipe.getDirection());
            spawn("pipe",spawnData);
        }
        else if(entityType==EntityType.CURVED_PIPE){
            EntityWrapper curvedPipe = model;
            SpawnData spawnData = new SpawnData(curvedPipe.getPositionX(),curvedPipe.getPositionY());
            spawnData.put("direction",curvedPipe.getDirection());
            spawn("curvedPipe",spawnData);
        }
        else if(entityType==EntityType.RECTANGLE){
            EntityWrapper rectangle = model;
            SpawnData spawnData = new SpawnData(rectangle.getPositionX(),rectangle.getPositionY());
            spawnData.put("scale",rectangle.getScale());
            spawn("rectangle",spawnData);
        }
        else if(entityType==EntityType.CROSS_BAR){
            EntityWrapper crossBar = model;
            SpawnData spawnData = new SpawnData(crossBar.getPositionX(),crossBar.getPositionY());
            spawnData.put("type",crossBar.getDirection());
            spawn("crossBar",spawnData);
        }
    }
    private EntityWrapper getModel(Entity entity){
        EntityWrapper model=null;
        if(entity.getType()==EntityType.BALL){
            model = new EntityWrapper(
                    EntityType.BALL,
                    -1,
                    (int) entity.getTransformComponent().getScaleX(),
                    entity.getTransformComponent().getX(),
                    entity.getTransformComponent().getY());
        }
        else if(entity.getType()==EntityType.BLACK_HOLE){
            model = new EntityWrapper(
                    EntityType.BLACK_HOLE,
                    -1,
                    (int) entity.getTransformComponent().getScaleX(),
                    entity.getTransformComponent().getX(),
                    entity.getTransformComponent().getY()
            );
        }
        else if(entity.getType()==EntityType.CURVED_PIPE){
            model = new EntityWrapper(
                    EntityType.CURVED_PIPE,
                    entity.getComponent(DirectionComponent.class).getDirection(),
                    -1,
                    entity.getTransformComponent().getX(),
                    entity.getTransformComponent().getY()
            );
        }
        else if(entity.getType()==EntityType.PIPE){
            model = new EntityWrapper(
                    EntityType.PIPE,
                    entity.getComponent(DirectionComponent.class).getDirection(),
                    -1,
                    entity.getTransformComponent().getX(),
                    entity.getTransformComponent().getY()

            );
        }
        else if(entity.getType()==EntityType.CIRCLE){
            model = new EntityWrapper(
                    EntityType.CIRCLE,
                    -1,
                    (int) entity.getTransformComponent().getScaleX(),
                    entity.getTransformComponent().getX(),
                    entity.getTransformComponent().getY()
            );
        }
        else if(entity.getType()==EntityType.RECTANGLE){
            model = new EntityWrapper(
                    EntityType.RECTANGLE,
                    -1,
                    (int) entity.getTransformComponent().getScaleX(),
                    entity.getTransformComponent().getX(),
                    entity.getTransformComponent().getY()

            );
        }
        else if(entity.getType()==EntityType.TRIANGLE){
            model = new EntityWrapper(
                    EntityType.TRIANGLE,
                    entity.getComponent(DirectionComponent.class).getDirection(),
                    (int) entity.getScaleX(),
                    entity.getX(),
                    entity.getY()
            );
        }
        else if(entity.getType()==EntityType.CROSS_BAR){
            model = new EntityWrapper(
                    EntityType.CROSS_BAR,
                    entity.getComponent(CrossBarComponent.class).getType(),
                    -1,
                    entity.getX(),
                    entity.getY()

            );
        }
        return model;
    }
    private void setChessBoard(){
        if(chessBoard==null)
            chessBoard=new ChessBoard();
        chessBoard.clear();
        EntityPlacer entityPlacer = EntityPlacer.getEntityPlacer();
        HashMap<Integer, Entity> hashMap = entityPlacer.getEntityMap();
        for(int key : hashMap.keySet()){
            //GameComponent model = hashMap.get(key).getComponent()
            Entity entity = hashMap.get(key);
            EntityWrapper model;
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
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            alert.showAndWait();
        }
   }

}
