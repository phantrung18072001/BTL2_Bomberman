package uet.oop.bomberman.entities.character;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.AudioGame;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;


public abstract class Enemy extends Character {

    protected double speed;
    protected int point;
    protected int timeToDead = 0;
    public Enemy(double x, double y, Sprite sprite, int point, double speed) {
        super(x, y, sprite);
        this.point = point;
        this.speed = speed;
    }

    @Override
    public void update() {
        if (!_alive) timeToDead++;
        else {
            checkDistanceToFlame();
            animate();
            calculateMove();
        }
        if(bomberman.p) {
            this.kill();
            countEnemy = 0;
        }
    }

    public void checkDistanceToFlame() {
        for (Entity obstacle : listFlame) {
            if (Math.abs(obstacle.getX() - x) < 0.8 && Math.abs(obstacle.getY() - y) < 0.8) {
                this.kill();
            }
        }
        for (Entity obstacle : listFlameSegment) {
            if (Math.abs(obstacle.getX() - x) < 0.8 && Math.abs(obstacle.getY() - y) < 0.8) {
                this.kill();
            }
        }
    }

    public void kill() {
        _alive = false;
        countEnemy --;
        points += this.point;
        AudioGame.playKillEnemy();
    }

    public void calculateMove() {
        double xa = 0, ya = 0;

        if (_direction == 0) ya--;
        if (_direction == 2) ya++;
        if (_direction == 3) xa--;
        if (_direction == 1) xa++;

            if (canMove(String.valueOf(_direction))) {
                x += xa * speed;
                y += ya * speed;
                if (canTurn() > 2 && round1(x) == Math.round(x) && round1(y) == Math.round(y)) {
                    _direction = calculateDirection();
                }
            } else {
                _direction = calculateDirection() ;
            }
    }

    public int canTurn() {
        int count = 0;
        for(int i = 0; i<4 ; i++) {
            if(canMove(String.valueOf(i))) {
                count++;
            }
        }
        return count;
    }


    @Override
    public void render(GraphicsContext gc) {
        if (_alive)
            chooseSprite();
        else {
            if (timeToDead < 40) {
                img = Sprite.movingSprite(Sprite.mob_dead1.getFxImage(),
                        Sprite.mob_dead2.getFxImage(),
                        Sprite.mob_dead3.getFxImage(), _animate, 60);
            } else {
                BombermanGame.entities.remove(this);
            }
        }
        super.render(gc);
    }
    protected abstract void chooseSprite();
    public abstract int calculateDirection();
}
