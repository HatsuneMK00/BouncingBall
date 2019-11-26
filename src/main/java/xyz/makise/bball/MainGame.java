package xyz.makise.bball;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import xyz.makise.bball.model.ChessBoard;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.prefs.Preferences;

import static com.almasb.fxgl.dsl.FXGL.*;

/*
* 游戏的主类
*
* */
public class MainGame extends GameApplication {

    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setIntroEnabled(false);
        gameSettings.setMenuEnabled(false);
        gameSettings.setCloseConfirmation(false);
        gameSettings.setHeight(450);
        gameSettings.setWidth(650);
    }


    /*
    * 所有和输入初始化有关的工作
    *
    * */
    @Override
    protected void initInput() {
        super.initInput();
    }


    /*
     * 所有和collision有关的初始化
     *
     * */
    @Override
    protected void initPhysics() {
        super.initPhysics();


    }


    /*
     * 所有和游戏初始化有关的工作
     *
     * */
    @Override
    protected void initGame() {
        super.initGame();
        showUIView();

    }

    private void showUIView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/GameLayout.fxml"));
            GridPane gameUI = loader.load();
            getGameScene().addUINode(gameUI);
            getGameScene().getUiNodes().get(0).setTranslateX(450);
        }catch (Exception e){
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