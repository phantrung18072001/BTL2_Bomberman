package uet.oop.bomberman.entities.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Tile {
    public boolean isOpen = false;

    public Portal(double x, double y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        if(countEnemy == 0){
            isOpen = true;
            this.img = new Image(String.valueOf(Portal.class.getResource("/sprites/portal_open.png")));
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }
}
