package uet.oop.bomberman.entities;

public abstract class AnimatedEntities extends Entity{
    protected boolean space = false;
    protected int _direction = 1;
    protected int postDir = _direction;
    protected boolean _moving = true;
    protected int _animate = 0;
    protected final int MAX_ANIMATE = 7500;
    protected void animate() {
        if(_animate < MAX_ANIMATE) _animate++; else _animate = 0;
    }

    @Override
    public void update() {

    }
}
