package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.entities.character.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Ballom extends Enemy {
    //@todo: tang toc bat ngo
    public Ballom(double x, double y, Sprite sprite) {
        super(x, y, sprite, 100, 0.03);
        _direction = calculateDirection();
    }

    @Override
    public int calculateDirection() {
        Random random = new Random();
        int value = random.nextInt(4);
        speed += 0.02;
        if(speed == 0.07){
            speed = 0.03;
        }
        return value;
    }

    @Override
    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                img = Sprite.movingSprite(Sprite.balloom_right1.getFxImage(),
                        Sprite.balloom_right2.getFxImage(),
                        Sprite.balloom_right3.getFxImage(), _animate, 60);
                break;
            case 2:
            case 3:
                    img = Sprite.movingSprite(Sprite.balloom_left1.getFxImage(),
                            Sprite.balloom_left2.getFxImage(),
                            Sprite.balloom_left3.getFxImage(), _animate, 60);
                break;
        }
    }
}
