package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.bomb.FlameSegment;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.graphics.RendervsUpdate;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity implements RendervsUpdate {
    protected boolean checkFlame = true;
    protected static int RADIUS = 1;
    protected static double SPEED = 0.07;
    protected static int numberOfBombs = 0;
    protected static int bomMax = 1;
    protected double x;
    protected double y;
    protected Sprite sprite;
    public Image img;
    public static int countEnemy = 0;
    public static int points = 0;
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Flame> listFlame = new ArrayList<>();
    public static List<FlameSegment> listFlameSegment = new ArrayList<>();
    public static List<String> map = new ArrayList<>();
    public boolean _removed = false;
    public boolean _alive = true;
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }




    @Override
    public void render(GraphicsContext gc) {
        /*SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        Image base = iv.snapshot(params, null);*/

        gc.drawImage(img, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }

    @Override
    public abstract void update();




}
