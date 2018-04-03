/*
 * Final Project 2020 - Particle effect
 * Group Members -
 * Syed Daniyal Shah
 * Mustafa Al-Azzawe
 * Shane Rego
 */

package sample.particle_effect;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ParticleSc extends Canvas {

    private GraphicsContext graphicsCon;

    private int amountOfPar = 0;
    private int particlesClick = 150;

    private final List<Particle> particles;
    private MouseAtt mouseAtt;

    public ParticleSc() {
        particles = new ArrayList<>();
        init();
        build();
    }

    private void init() {
        this.setWidth(400);
        this.setHeight(400);
        graphicsCon = this.getGraphicsContext2D();
    }


    private void build() {
        mouseAtt = new MouseAtt(this.getWidth() / 2, this.getHeight() / 2);

        this.setOnMouseDragged(e -> {
            if(e.getButton() == MouseButton.PRIMARY){
                mouseAtt.setX(e.getX());
                mouseAtt.setY(e.getY());
            }

        });

        graphicsCon.setFill(Color.rgb(0, 0, 0, 1));
        graphicsCon.fillRect(0, 0, this.getWidth(), this.getHeight());

        this.setOnMouseClicked(e -> {

            MouseButton button = e.getButton();

            if (button == MouseButton.PRIMARY) {
                mouseAtt.setX(e.getX());
                mouseAtt.setY(e.getY());

            } else if (button == MouseButton.SECONDARY) {


                if (amountOfPar + particlesClick <= 10000) {
                    amountOfPar += particlesClick;
                    for (int i = 0; i < particlesClick; i++) {
                        createParticle(e.getX(), e.getY(), mouseAtt);
                    }
                }

            }
        });
    }

    private void createParticle(double x, double y, MouseAtt a) {
        Particle p = new Particle(x, y, a);
        particles.add(p);
    }

    public void draw() {
        graphicsCon.setFill(Color.rgb(0, 0, 0, 0.5));
        graphicsCon.fillRect(0, 0, this.getWidth(), this.getHeight());

        particles.forEach(p -> {
            p.draw(graphicsCon);
        });

        mouseAtt.draw(graphicsCon);
    }

    public void update() {
        particles.forEach(p -> {
            p.update();
        });
    }

}
