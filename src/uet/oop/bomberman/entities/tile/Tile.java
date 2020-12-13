package uet.oop.bomberman.entities.tile;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Tile extends Entity {

    public Tile(double x, double y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.img = sprite.getFxImage();
        this.sprite = sprite;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }
}
