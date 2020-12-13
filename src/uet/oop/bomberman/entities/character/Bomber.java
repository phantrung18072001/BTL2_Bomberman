package uet.oop.bomberman.entities.character;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.AudioGame;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Item.*;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Character {
    private int timeItem1 = 0;
    private int timeItem2 = 0;
    public int WIDTH;
    public int HEIGHT;
    private boolean passFlame = false;
    public boolean w, s, d, a, enter, p;
    public Bomber(double x, double y, Sprite sprite) {
        super(x, y, sprite);
    }

    public EventHandler<KeyEvent> keyReleased = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case W:
                    w = false;
                    break;
                case A:
                    a = false;
                    break;
                case S:
                    s = false;
                    break;
                case D:
                    d = false;
                    break;
                case SPACE:
                    space = false;
                    break;
                case ENTER:
                    enter = false;
                    break;
                case P:
                    p = false;
                    break;
            }
        }

    };

    public EventHandler<KeyEvent> keyPressed = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case W:
                    w = true;
                    break;
                case S:
                    s = true;
                    break;
                case A:
                    a = true;
                    break;
                case D:
                    d = true;
                    break;
                case SPACE:
                    space = true;
                    break;
                case ENTER:
                    enter = true;
                    break;
                case P:
                    p = true;
                    break;
            }
        }
    };

    public void move() {
        if (w) {
            animate();
            _direction = 0;
            if (canMove(String.valueOf(_direction))) {
                setY(getY() - SPEED);
                if (getY() < 1) setY(1);
            }
        }
        if (a) {
            _direction = 3;
            animate();
            if (canMove(String.valueOf(_direction))) {
                setX(getX() - SPEED);
                if (getX() < 1) setX(1);
            }
        }
        if (s) {
            animate();
            _direction = 2;
            if (canMove(String.valueOf(_direction))) {
                setY(getY() + SPEED);
                if (getY() >= HEIGHT - 2) setY(HEIGHT - 2);
            }

        }
        if (d) {
            animate();
            _direction = 1;
            if (canMove(String.valueOf(_direction))) {
                setX(getX() + SPEED);
                if (getX() >= WIDTH - 2) setX(WIDTH - 2);
            }
        }
    }

    private void chooseAliveSprite() {
        switch (_direction) {
            case 0:
                img = Sprite.player_down.getFxImage();
                if (_moving) {
                    if (!passFlame)
                        img = Sprite.movingSprite(Sprite.player_up_1.getFxImage(), Sprite.player_up_2.getFxImage(), _animate, 15);
                }
                break;
            case 2:
                img = Sprite.player_down.getFxImage();
                if (_moving) {
                   img = Sprite.movingSprite(Sprite.player_down_1.getFxImage(), Sprite.player_down_2.getFxImage(), _animate, 15);
                }
                break;
            case 3:
                img = Sprite.player_left.getFxImage();
                if (_moving) {
                    img = Sprite.movingSprite(Sprite.player_left_1.getFxImage(), Sprite.player_left_2.getFxImage(), _animate, 15);
                }
                break;
            default:
                img = Sprite.player_right.getFxImage();
                if (_moving) {
                    img = Sprite.movingSprite(Sprite.player_right_1.getFxImage(), Sprite.player_right_2.getFxImage(), _animate, 15);
                }
                break;
        }
    }

    @Override
    public void update() {
        limitTimeItem();
        checkDistanceToEnemy();
        if (!passFlame)
            checkDistanceToFlame();
        move();
        detectPlaceBomb();
        checkEatItem();
    }

    public void limitTimeItem() {
        if (passFlame) timeItem1++;
        if (passWall) timeItem2++;
        if (timeItem1 > 600) {
            passFlame = false;
        }
        if (timeItem2 > 600) {
            passWall = false;
        }
    }

    public void checkEatItem() {
        for (Entity item : stillObjects) {
            if (Math.abs(item.getX() - x) < 0.9 && Math.abs(item.getY() - y) < 0.9) {
                if (item instanceof flameItem) {
                    RADIUS++;
                    stillObjects.remove(item);
                    AudioGame.playGetItem();
                    break;
                } else if (item instanceof bombItem) {
                    stillObjects.remove(item);
                    AudioGame.playGetItem();
                    bomMax++;
                    break;
                } else if (item instanceof speedItem) {
                    SPEED += 0.02;
                    stillObjects.remove(item);
                    AudioGame.playGetItem();
                    break;
                } else if (item instanceof flamepassItem) {
                    passFlame = true;
                    stillObjects.remove(item);
                    AudioGame.playGetItem();
                    break;
                } else if (item instanceof wallpassItem) {
                    passWall = true;
                    stillObjects.remove(item);
                    break;
                }
            }
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

    public void checkDistanceToEnemy() {
        for (Entity obstacle : BombermanGame.entities) {
            if (obstacle instanceof Enemy)
            if (Math.abs(obstacle.getX() - x) < 0.8 && Math.abs(obstacle.getY() - y) < 0.8) {
                this.kill();
            }
        }

    }

    @Override
    public void render(GraphicsContext gc) {
        if (_alive) {
            chooseAliveSprite();
        } else {
            chooseDeadSprite();
        }
        super.render(gc);
    }

    private void chooseDeadSprite() {
        img  = Sprite.movingSprite(Sprite.player_dead1.getFxImage(), Sprite.player_dead2.getFxImage(), Sprite.player_dead3.getFxImage(), _animate, 15);
    }

    // @todo: thiet ke vi tri dat bomb
    private void detectPlaceBomb() {
        double min = 100;
        double _x = 1;
        double _y = 1;
        for (Entity object : stillObjects) {
            if (object instanceof Grass) {
                if (Math.abs(object.getX() - x) + Math.abs(object.getY() - y) < min) {
                    min = Math.abs(object.getX() - x) + Math.abs(object.getY() - y);
                    _x = object.getX();
                    _y = object.getY();
                }
            }
        }
        if (space) {
           placeBomb((int)_x, (int)_y);
           space = false;
        }
    }

    protected void placeBomb(int x, int y) {
        Bomb bomb = new Bomb(x, y, Sprite.bomb);
        if (numberOfBombs < bomMax) {
            stillObjects.add(bomb);
            numberOfBombs++;
        }
    }

    public void kill() {
        _alive = false;
    }

}
