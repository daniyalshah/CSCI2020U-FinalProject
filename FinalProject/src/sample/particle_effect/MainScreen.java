/*
 * Final Project 2020 - Particle effect
 * Group Members -
 * Syed Daniyal Shah
 * Mustafa Al-Azzawe
 * Shane Rego
 */

package sample.particle_effect;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import sample.client.Client;

public class MainScreen extends BorderPane {

    //animation variables
    private Timeline gameLoop;

    //main variables
    private final Client client;
    private final DrawScreen center;

    public MainScreen(Client client) {
        this.client = client;
        this.center = new DrawScreen();
        setCenter();
        runGameLoop();
    }

    private void setCenter() {
        this.setCenter(center);
    }

    private void runGameLoop() {
        EventHandler<ActionEvent> gameUpdate = event -> {
            center.update();
            center.draw();
        };
        //center.update();
        //center.draw();
        gameLoop = new Timeline(new KeyFrame(Duration.millis(33.3), gameUpdate));
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.play();
    }

    public void stopGameLoop() {
        gameLoop.stop();
    }

}
