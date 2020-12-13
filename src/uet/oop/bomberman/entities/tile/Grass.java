package uet.oop.bomberman.entities.tile;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends Tile {


    public Grass(double x, double y, Sprite sprite) {
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
