package xyz.makise.bball.model;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.control.Alert;
import xyz.makise.bball.MainGame;
import xyz.makise.bball.components.CircleComponent;
import xyz.makise.bball.components.EntityPlacer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.prefs.Preferences;

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
            chessBoard.setCrossBarLeft(wrapper.getCrossBarLeft());
            chessBoard.setCrossBarRight(wrapper.getCrossBarRight());

            // Save the file path to the registry.
            setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            alert.showAndWait();
        }
    }
    private void SetChessBoard(){
        if(chessBoard==null)
            chessBoard=new ChessBoard();
        EntityPlacer entityPlacer = EntityPlacer.getEntityPlacer();
        HashMap<Integer, Entity> hashMap = entityPlacer.getEntityMap();
        for(int key : hashMap.keySet()){
            //GameComponent model = hashMap.get(key).getComponent()
        }
    }
    public void savePersonDataToFile(File file) {
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
