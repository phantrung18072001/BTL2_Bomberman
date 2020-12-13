package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.entities.character.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Doll extends Enemy {
    //@todo: di xuyen tuong toc do trung binh
    public Doll(double x, double y, Sprite sprite) {
        super(x, y, sprite, 100, 0.05);
        _direction = calculateDirection();
        brickPass = true;
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
                img = Sprite.movingSprite(Sprite.doll_right1.getFxImage(),
                        Sprite.doll_right2.getFxImage(),
                        Sprite.doll_right3.getFxImage(), _animate, 60);
                break;
            case 2:
            case 3:
                img = Sprite.movingSprite(Sprite.doll_left1.getFxImage(),
                        Sprite.doll_left2.getFxImage(),
                        Sprite.doll_left3.getFxImage(), _animate, 60);
                break;
        }
    }
}
