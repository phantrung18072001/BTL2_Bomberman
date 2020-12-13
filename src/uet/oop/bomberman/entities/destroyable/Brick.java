package uet.oop.bomberman.entities.destroyable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Item.*;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Brick extends Tile {
    private final int MAX_ANIMATE = 7500;
    private int _animate = 0;
    public boolean _destroyed = false;
    protected int _timeToDisapear = 20;


    @Override
    public void update() {
        if(_destroyed) {
            if(_animate < MAX_ANIMATE) _animate++; else _animate = 0;
            if(_timeToDisapear > 0)
                _timeToDisapear--;

        }
        for (Entity flame : listFlameSegment) {
            //@todo: CHINH LAI GIA TRI
            if (Math.abs(flame.getX() - x) == 0 && Math.abs(flame.getY() - y) == 0) {
                _destroyed = true;
                img = Sprite.grass.getFxImage();
            }
        }
    }

    protected Image movingSprite(Image img, Image img1, Image img2) {
        int calc = _animate % 30;

        if(calc < 10) {
            return img;
        }

        if(calc < 20) {
            return img1;
        }

        return img2;
    }

    public Brick(double x, double y, Sprite sprite) {
        super(x, y, sprite);
    }



    @Override
    public void render (GraphicsContext gc) {
        if(_destroyed) {
            if (_animate < 30)
                img = movingSprite(Sprite.brick_exploded.getFxImage(), Sprite.brick_exploded1.getFxImage(), Sprite.brick_exploded2.getFxImage());
            else {
                List<Entity> tmp = new ArrayList<>();
                for (int i = 0; i < stillObjects.size(); i++) {
                    Entity e = stillObjects.get(i);
                    if (e.getY() != y || e.getX() != x) {
                        tmp.add(e);
                    }
                }
                stillObjects = tmp;
                stillObjects.add(new Grass(x, y, Sprite.grass));
                // @todo: MAT BRICK THI THEM ITEM
                if (Entity.map.get((int)y).charAt((int)x) == 'f') {
                    stillObjects.add(new flameItem(x, y, Sprite.powerup_flames));
                }
                if (Entity.map.get((int)y).charAt((int)x) == 'b') {
                    stillObjects.add(new bombItem(x, y, Sprite.powerup_bombs));
                }
                if (Entity.map.get((int)y).charAt((int)x) == 's') {
                    stillObjects.add(new speedItem(x, y, Sprite.powerup_speed));
                }
                if(Entity.map.get((int)y).charAt((int)x) == 'x') {
                    stillObjects.add(new Portal(x, y, Sprite.portal));
                }

                if (Entity.map.get((int)y).charAt((int)x) == 'i') {
                    stillObjects.add(new flamepassItem(x, y, Sprite.powerup_flamepass));
                }
                if (Entity.map.get((int)y).charAt((int)x) == 'w') {
                    stillObjects.add(new wallpassItem(x, y, Sprite.powerup_wallpass));
                }
            }
        }
        super.render(gc);
    }

}
