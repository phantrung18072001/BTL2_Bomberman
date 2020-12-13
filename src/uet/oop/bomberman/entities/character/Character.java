package uet.oop.bomberman.entities.character;

import uet.oop.bomberman.entities.AnimatedEntities;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.bomb.FlameSegment;
import uet.oop.bomberman.entities.destroyable.Brick;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Character extends AnimatedEntities {
    public boolean brickPass = false;
    public boolean passWall = false;
    public Character(double x, double y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.img = sprite.getFxImage();
        this.sprite = sprite;
    }
    @Override
    public abstract void update();

    // @todo: Check xem có găp vật cản ko
    public boolean canMove(String direction) {
        double space = 1.0;
        if (direction.equals("0")) {
            for (int i = 0; i < stillObjects.size(); i++) {
                Entity obstacle = stillObjects.get(i);
                if (Math.abs(obstacle.getX() - round1(getX())) < space && round1(getY()) == round1(obstacle.getY() + space)) {
                    if (obstacle instanceof Wall) {
                        if (!passWall) {
                            return false;
                        }
                    }
                    else if (obstacle instanceof Bomb) {
                        return false;
                    }
                    else if (obstacle instanceof Brick) {
                        if(!brickPass) {
                            return false;
                        }
                    }

                }
            }
        } else if (direction.equals("2")) {
            for (int i = 0; i < stillObjects.size(); i++) {
                Entity obstacle = stillObjects.get(i);
                if (Math.abs(obstacle.getX() - round1(getX())) < space && round1(getY()) == round1(obstacle.getY() - space)) {
                    if (obstacle instanceof Wall) {
                        if (!passWall) {
                            return false;
                        }
                    }
                    else if (obstacle instanceof Bomb) {
                        return false;
                    }
                    else if (obstacle instanceof Brick) {
                        if(!brickPass) {
                            return false;
                        }
                    }

                }
            }
        } else if (direction.equals("3")) {
            for (int i = 0; i < stillObjects.size(); i++) {
                Entity obstacle = stillObjects.get(i);
                if (Math.abs(obstacle.getY() - round1(getY())) < space && round1(getX()) == round1(obstacle.getX() + space)) {
                    if (obstacle instanceof Wall) {
                        if (!passWall) {
                            return false;
                        }
                    }
                    else if (obstacle instanceof Bomb) {
                        return false;
                    }
                    else if (obstacle instanceof Brick) {
                        if(!brickPass) {
                            return false;
                        }
                    }

                }
            }
        } else if (direction.equals("1")) {
            for (int i = 0; i < stillObjects.size(); i++) {
                Entity obstacle = stillObjects.get(i);
                if (Math.abs(obstacle.getY() - round1(getY())) < space && round1(getX()) == round1(obstacle.getX() - space)) {
                    if (obstacle instanceof Wall) {
                        if (!passWall) {
                            return false;
                        }
                    }
                    else if (obstacle instanceof Bomb) {
                        return false;
                    }
                    else if (obstacle instanceof Brick) {
                        if(!brickPass) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public double round1(double n) {
        return (double) Math.round(n * 10) / 10;
    }
}
