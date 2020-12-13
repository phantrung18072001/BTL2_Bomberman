package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.entities.character.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class Doria extends Enemy {
    //@todo: thuat toan tim duong di
    Random rd = new Random();
    public Doria(double x, double y, Sprite sprite) {
        super(x, y, sprite, 100, 0.03);
    }
    @Override
    public int calculateDirection() {
        int random = rd.nextInt(4);
        return calculateDirection(random);
    }

    public int calculateDirection(int random) {
        if (random == 0 || random == 2) {
            int calculateCol = calculateColDirection();
            if (calculateCol != -1) return calculateCol;
            int calculateRow = calculateRowDirection();
            if (calculateRow != -1) return calculateRow;
        }

        if (random == 1 ) {
            int calculateRow = calculateRowDirection();
            if (calculateRow != -1) return calculateRow;
            int calculateCol = calculateColDirection();
            if (calculateCol != -1) return calculateCol;
        }
        return rd.nextInt(4);
    }

    // return 1, 3, -1.
    protected int calculateColDirection() {
        if (bomberman.getX() < this.getX()) return 3;
        if (bomberman.getX() > this.getX()) return 1;

        return -1;
    }

    // return 0, 2, -1.
    protected int calculateRowDirection() {
        if (bomberman.getY() < this.getY()) return 0;
        if (bomberman.getY() > this.getY()) return 2;

        return -1;
    }

    @Override
    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                img = Sprite.movingSprite(Sprite.kondoria_right1.getFxImage(),
                        Sprite.kondoria_right2.getFxImage(),
                        Sprite.kondoria_right3.getFxImage(), _animate, 60);
                break;
            case 2:
            case 3:
                img = Sprite.movingSprite(Sprite.kondoria_left1.getFxImage(),
                        Sprite.kondoria_left2.getFxImage(),
                        Sprite.kondoria_left3.getFxImage(), _animate, 60);
                break;
        }
    }
}
