package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.AudioGame;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntities;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;



public class Bomb extends AnimatedEntities {
    protected double _timeToExplode = 120;
    public int _timeAfter = 20;
    protected boolean _exploded = false;
    protected Flame[] _flames;
    public Bomb(double x, double y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.img = sprite.getFxImage();
    }

    @Override
    public void update() {
        checkDistanceToFlame();
        if (_timeToExplode > 0)
            _timeToExplode--;
        else {
            if (!_exploded) {
                explode();
            } else {
                updateFlames();
            }
            if (_timeAfter > 0)
                _timeAfter--;
            else {
                listFlame.clear();
                listFlameSegment.clear();
                stillObjects.remove(this);
                checkFlame = false;
                img = Sprite.grass.getFxImage();
            }
        }
        animate();
    }

    public void checkDistanceToFlame() {
        for (Entity obstacle : listFlameSegment) {
            if (Math.abs(obstacle.getX() - x) < 0.8 && Math.abs(obstacle.getY() - y) < 0.8) {
                _timeToExplode = -1;
                break;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (_exploded) {
            if (checkFlame) img = Sprite.bomb_exploded2.getFxImage();
            renderFlames(gc);
        } else {
            img = Sprite.movingSprite(Sprite.bomb.getFxImage(), Sprite.bomb_1.getFxImage(), Sprite.bomb_2.getFxImage(), _animate, 50);
        }
        super.render(gc);
    }

    public void renderFlames(GraphicsContext gc) {
        if (checkFlame) {
            for (int i = 0; i < _flames.length; i++) {
                _flames[i].render(gc);
            }
        }
    }

    protected void explode() {
        _exploded = true;
        AudioGame.playExplosion();
        int radius = RADIUS;
        Flame flame0 = new Flame((int) x, (int) y, 0, radius);
        Flame flame1 = new Flame((int) x, (int) y, 1, radius);
        Flame flame2 = new Flame((int) x, (int) y, 2, radius);
        Flame flame3 = new Flame((int) x, (int) y, 3, radius);
        _flames = new Flame[]{flame0, flame1, flame2, flame3};
        listFlame.add(flame0);
        listFlame.add(flame1);
        listFlame.add(flame2);
        listFlame.add(flame3);
        numberOfBombs--;
    }

    protected void updateFlames() {
        for (int i = 0; i < _flames.length; i++) {
            _flames[i].update();
        }
    }

    public FlameSegment flameAt(int x, int y) {
        if (!_exploded) return null;
        for (int i = 0; i < _flames.length; i++) {
            if (_flames[i] == null) return null;
            FlameSegment e = _flames[i].flameSegmentAt(x, y);
            if (e != null) return e;
        }
        return null;
    }



}
