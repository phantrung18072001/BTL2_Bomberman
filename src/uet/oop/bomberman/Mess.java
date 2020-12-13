package uet.oop.bomberman;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;


public class Mess {

    public static Pane lose() {
        Pane res = new Pane();
        res.setPrefSize(31 * Sprite.SCALED_SIZE, 13 * Sprite.SCALED_SIZE);
        res.setStyle("-fx-background-color: rgba(42,48,57,0.99)");

        ImageView lose = new ImageView(Mess.class.getResource("/sprites/lose.png").toString());
        lose.relocate((res.getPrefWidth() - 160) / 2,
                (res.getPrefHeight() - 31) / 2);
        ImageView lose1 = new ImageView(Mess.class.getResource("/sprites/lose1.png").toString());
        lose1.relocate((res.getPrefWidth() - 700) / 2,
                (res.getPrefHeight() - 200) / 2);
        res.getChildren().add(lose);
        res.getChildren().add(lose1);
        return res;
    }

    public static Pane win() {
        Pane res = new Pane();
        res.setPrefSize(31 * Sprite.SCALED_SIZE, 13 * Sprite.SCALED_SIZE);
        res.setStyle("-fx-background-color: rgba(42,48,57,0.99)");

        ImageView win = new ImageView(Mess.class.getResource("/sprites/win.png").toString());
        win.relocate((res.getPrefWidth() - 400) / 2,
                (res.getPrefHeight() - 400) / 2);
        res.getChildren().add(win);
        ImageView win1 = new ImageView(Mess.class.getResource("/sprites/win1.png").toString());
        win1.relocate((res.getPrefWidth() - 800) / 2,
                (res.getPrefHeight() - 200) / 2);
        res.getChildren().add(win1);
        return res;
    }

}
