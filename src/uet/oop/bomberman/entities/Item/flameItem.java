package uet.oop.bomberman.entities.Item;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;

public class flameItem extends Tile {
    public flameItem(double x, double y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }
}
