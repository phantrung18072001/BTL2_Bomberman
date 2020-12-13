package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.character.enemy.Ballom;
import uet.oop.bomberman.entities.character.enemy.Doll;
import uet.oop.bomberman.entities.character.enemy.Doria;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.destroyable.Brick;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.entities.Entity.countEnemy;
import static uet.oop.bomberman.entities.Entity.stillObjects;


public class BombermanGame extends Application  {
    public static int Level;
    public final String fileName = "res//levels//Level1.txt";
    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_down);
    public GraphicsContext gc;
    private Canvas canvas;
    public static  List<Entity> entities = new ArrayList<>();
    public Group root = new Group();
    public Scene scene = new Scene(root);


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }
    @Override
    public void start(Stage stage) {
        readLevelFromFile();
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * bomberman.WIDTH, Sprite.SCALED_SIZE * bomberman.HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        root.getChildren().add(canvas);

        // Tao scene
        scene.setOnKeyPressed(bomberman.keyPressed);
        scene.setOnKeyReleased(bomberman.keyReleased);

        // Them scene vao stage
        stage.setScene(scene);
        Image icon = new Image("/sprites/bomber_down.png");
        stage.getIcons().add(icon);
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(root.getChildren().size() > 2) root.getChildren().remove(2);
                if(!bomberman._alive) {
                    root.getChildren().add(Mess.lose());
                    AudioGame.playGameOver();
                }
                if(portalHandle()) {
                    root.getChildren().add(Mess.win());
                    AudioGame.playEnd();
                }
                render();
                update();
            }
        };
        timer.start();
        createMap();
        entities.add(bomberman);
    }

    public void readLevelFromFile() {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fr);
            String currLine = null;
            currLine = bufferedReader.readLine();

            Level = Integer.parseInt(currLine.substring(0, 1));
            bomberman.HEIGHT = Integer.parseInt(currLine.substring(2, 4));
            bomberman.WIDTH = Integer.parseInt(currLine.substring(5, 7));

            while((currLine = bufferedReader.readLine()) != null) {
                Entity.map.add(currLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createMap() {
        for (int i = 0; i < bomberman.WIDTH; i++) {
            for (int j = 0; j < bomberman.HEIGHT; j++) {
                Entity object;
                switch (Entity.map.get(j).charAt(i)) {
                    case '#':
                        object = new Wall(i, j, Sprite.wall);
                        break;
                    case 'x':
                        stillObjects.add( new Portal(i, j, Sprite.portal));
                        object = new Brick(i, j, Sprite.brick);
                        break;
                    case '1':
                        object = new Grass(i, j, Sprite.grass);
                        entities.add(new Ballom(i, j, Sprite.balloom_left1));
                        countEnemy ++;
                        break;
                    case '*': case 'b': case 'f': case 's': case 'i': case 'd': case 'w':
                        object = new Brick(i, j, Sprite.brick);
                        break;
                    case '2':
                        object = new Grass(i, j, Sprite.grass);
                        entities.add(new Oneal(i, j, Sprite.oneal_left1));
                        countEnemy ++;
                        break;
                    case '3':
                        object = new Grass(i, j, Sprite.grass);
                        entities.add(new Doll(i, j, Sprite.doll_left1));
                        countEnemy ++;
                        break;
                    case '4':
                        object = new Grass(i, j, Sprite.grass);
                        entities.add(new Doria(i, j, Sprite.kondoria_left1));
                        countEnemy ++;
                        break;
                    default:
                        object = new Grass(i, j, Sprite.grass);
                        break;
                }
                stillObjects.add(object);
            }
        }
    }

    public static Portal getPortal() {
        for(int i = 0; i < stillObjects.size(); i++) {
            if(stillObjects.get(i) instanceof Portal) {
                return (Portal) stillObjects.get(i);
            }
        }
        return null;
    }

    public boolean portalHandle() {
        if((int) Math.round(bomberman.getX()) == (int) BombermanGame.getPortal().getX()
                && (int) Math.round(bomberman.getY()) == (int) BombermanGame.getPortal().getY()) {
            if(BombermanGame.getPortal().isOpen && bomberman.enter) {
                return true;
            }
        }
        return false;
    }

    public void update() {
        for(int i = 0; i < entities.size(); i++) {
           entities.get(i).update();
        }
        for(int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).render(gc);
        }
    }

}
