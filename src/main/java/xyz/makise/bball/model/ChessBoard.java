package xyz.makise.bball.model;

import com.almasb.fxgl.core.collection.Array;

import com.almasb.fxgl.entity.Entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/*
* 要看看需不需要使用ObservableList去保存游戏组件
* 如果需要的话 就还需要加一个ChessBoardWrapper来处理文件系统的操作
* */
@XmlRootElement(name = "ChessBoard")
public class ChessBoard {
//    左横杆
    private CrossBar crossBarLeft;
//    右横杆
    private CrossBar crossBarRight;
//    用于保存所有被加入进棋盘的游戏组件
    @XmlElement(name = "component")
    private ArrayList<GameComponent> components = new ArrayList<>();

    public CrossBar getCrossBarLeft() {
        return crossBarLeft;
    }

    public void setCrossBarLeft(CrossBar crossBarLeft) {
        this.crossBarLeft = crossBarLeft;
    }

    public CrossBar getCrossBarRight() {
        return crossBarRight;
    }

    public void setCrossBarRight(CrossBar crossBarRight) {
        this.crossBarRight = crossBarRight;
    }

    public ArrayList<GameComponent> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<GameComponent> components) {
        this.components = components;
    }

    //    向棋盘中添加一个被用户选择的游戏组件
    void addComponent(GameComponent component){

    }

//    从棋盘中删除一个对应位置的游戏组件
    void removeComponent(GameComponent component){

    }

//    清空棋盘
public void clear(){
        components.clear();
        crossBarLeft = null;
        crossBarRight = null;
    }
}
