package xyz.makise.bball.components;


import com.almasb.fxgl.entity.Entity;

public interface MyComponent {
    Entity rotate();
    Entity zoomOut();
    Entity zoomIn();
}
