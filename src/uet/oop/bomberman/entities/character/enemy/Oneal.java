package uet.oop.bomberman.entities.character.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.character.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Oneal extends Enemy {
    //@todo: Cham tuong vs nga 3 doi huong toc do cham
    public Oneal(double x, double y, Sprite sprite) {
        super(x, y, sprite, 100, 0.03);
        _direction = calculateDirection();
    }

    @Override
    public int calculateDirection() {
        Random random = new Random();
        int value = random.nextInt(4);
        return value;
    }

    @Override
    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                img = Sprite.movingSprite(Sprite.oneal_right1.getFxImage(),
                        Sprite.oneal_right2.getFxImage(),
                        Sprite.oneal_right3.getFxImage(), _animate, 60);
                break;
            case 2:
            case 3:
                img = Sprite.movingSprite(Sprite.oneal_left1.getFxImage(),
                        Sprite.oneal_left2.getFxImage(),
                        Sprite.oneal_left3.getFxImage(), _animate, 60);
                break;
        }
    }
}
