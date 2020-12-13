package uet.oop.bomberman.graphics;

import javafx.scene.canvas.GraphicsContext;

public interface RendervsUpdate {
    void update();
    void render(GraphicsContext gc);
}
