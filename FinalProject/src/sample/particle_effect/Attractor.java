/*
 * Final Project 2020 - Particle effect
 * Group Members -
 * Syed Daniyal Shah
 * Mustafa Al-Azzawe
 * Shane Rego
 */

package sample.particle_effect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Attractor {

    private final Vector pos;

    public Attractor(double x, double y) {
        pos = new Vector(x, y);
    }

    public Vector getPos() {
        return pos;
    }

    public void setX(double x) {
        this.pos.setX(x);
    }

    public void setY(double y) {
        this.pos.setY(y);
    }

    public void draw(GraphicsContext gtx) {
        gtx.setFill(Color.LIGHTPINK);
        gtx.fillOval(pos.getX(), pos.getY(), 5, 5);
    }
}