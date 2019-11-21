package xyz.makise.bball;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

import static com.almasb.fxgl.dsl.FXGL.*;


/*
* 一个用于生成所有游戏所需要的组件的工厂
* 被调用后将在对应的位置产生该物体
* 需要注意设定位置时要考虑到物体本身的大小
*
* 需要添加特殊操作的组件继承Component写一个新的Component然后在工厂里面在entityBuilder中添加进去即可
* 先不修改model中的类 文件系统的负责人再这些类进行修改
*
* */
public class GameFactory implements EntityFactory {

    @Spawns("ball")
    public Entity newBall(SpawnData data){

        return null;
    }

    @Spawns("blackHole")
    public Entity newBlackHole(SpawnData data){
        return null;
    }
}
