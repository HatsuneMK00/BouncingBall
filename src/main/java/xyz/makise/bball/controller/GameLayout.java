package xyz.makise.bball.controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/*
* 由于fxml和控制器不在一个包的话 无法在scene builder中绑定属性 暂时先放在一个包里
* 全部弄好之后再把fxml移到resources的view里面
* */
public class GameLayout {
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
    private RadioButton crossBar;
}
