package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.graphics.Sprite;

public class FlameSegment extends Entity{
    protected boolean last;
    public FlameSegment(int x, int y, int direction, boolean last) {
        this.x = x;
        this.y = y;
        this.last = last;

        switch (direction) {
            case 0:
                if(!last) {
                    img = Sprite.explosion_vertical2.getFxImage();
                } else {
                    img = Sprite.explosion_vertical_top_last2.getFxImage();
                }
                break;
            case 1:
                if(!last) {
                    img = Sprite.explosion_horizontal2.getFxImage();
                } else {
                    img = Sprite.explosion_horizontal_right_last2.getFxImage();
                }
                break;
            case 2:
                if(!last) {
                    img = Sprite.explosion_vertical2.getFxImage();
                } else {
                    img = Sprite.explosion_vertical_down_last2.getFxImage();
                }
                break;
            case 3:
                if(!last) {
                    img = Sprite.explosion_horizontal2.getFxImage();
                } else {
                    img = Sprite.explosion_horizontal_left_last2.getFxImage();
                }
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

    @Override
    public void update() {

    }

}
