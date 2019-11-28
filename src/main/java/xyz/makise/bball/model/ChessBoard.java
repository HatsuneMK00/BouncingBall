package xyz.makise.bball.model;

import com.almasb.fxgl.core.collection.Array;

import com.almasb.fxgl.entity.Entity;
import javafx.fxml.FXML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/*
* 要看看需不需要使用ObservableList去保存游戏组件
* 如果需要的话 就还需要加一个ChessBoardWrapper来处理文件系统的操作
*
* 这个包中的所有内容全部是为文件系统准备的 和游戏系统么有关系 就用来保存
*
* */
@XmlRootElement(name = "ChessBoard")
public class ChessBoard {
//    用于保存所有被加入进棋盘的游戏组件

    private ArrayList<EntityWrapper> components = new ArrayList<>();
    @XmlElement(name = "entity")
    public ArrayList<EntityWrapper> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<EntityWrapper> components) {
        this.components = components;
    }

    //    清空棋盘
public void clear(){
        components.clear();
    }
}
