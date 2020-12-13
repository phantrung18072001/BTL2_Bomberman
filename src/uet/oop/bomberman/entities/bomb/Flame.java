package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.destroyable.Brick;
import uet.oop.bomberman.entities.tile.Wall;

public class Flame extends Entity {
    private int radius;
    protected int _direction;
    protected FlameSegment[] _flameSegments = new FlameSegment[2];

    public Flame(int x, int y, int direction, int radius) {
        this.x = x;
        this.y = y;
        this._direction = direction;
        this.radius = radius;
        createFlameSegments();
    }

    private void createFlameSegments() {
        _flameSegments = new FlameSegment[calculatePermitedDistance()];
        boolean last;

        int xa = 0;
        int ya = 0;
        if (_direction == 0) ya = -1;
        if (_direction == 1) xa = 1;
        if (_direction == 2) ya = 1;
        if (_direction == 3) xa = -1;
        for (int i = 0; i < _flameSegments.length; i++) {
            int xf = (int) (x + xa * (i + 1));
            int yf = (int) (y + ya * (i + 1));
            if (i == _flameSegments.length - 1) {
                _flameSegments[i] = new FlameSegment(xf, yf, _direction, true);
                listFlameSegment.add(_flameSegments[i]);
            } else {
                _flameSegments[i] = new FlameSegment(xf, yf, _direction, false);
                listFlameSegment.add(_flameSegments[i]);
            }
        }
    }

    public FlameSegment flameSegmentAt(int x, int y) {
        for (int i = 0; i < _flameSegments.length; i++) {
            if (_flameSegments[i].getX() == x && _flameSegments[i].getY() == y)
                return _flameSegments[i];
        }
        return null;
    }




    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        for (int i = 0; i < _flameSegments.length; i++) {
            _flameSegments[i].render(gc);
        }
        super.render(gc);
    }

    public void collide(Entity e) {
        if (Math.abs(e.getX() - Math.round(getX())) < 1 || Math.abs(getY() - e.getY()) < 1) e._alive = false;
    }


    private int calculatePermitedDistance() {
        // @todo: thực hiện tính toán độ dài của Flame
        int xa = 0;
        int ya = 0;
        if (_direction == 0) ya = -1;
        if (_direction == 1) xa = 1;
        if (_direction == 2) ya = 1;
        if (_direction == 3) xa = -1;

        for (int i = 0; i < radius; i++) {
            int xf = (int) (x + xa * (i + 1));
            int yf = (int) (y + ya * (i + 1));
//            Entity entity = _board.getEntityAt(xf, yf);
            Entity entity = null;
            for (Entity e : stillObjects) {
                if ((int)e.getX() == xf && (int)e.getY() == yf) {
                    entity = e;
                    break;
                }
            }
            if (entity instanceof Wall) return i;
            if (entity instanceof Brick) {
                ((Brick) entity)._destroyed = true;
                return i;
            }
        }
        return radius;
    }
}


