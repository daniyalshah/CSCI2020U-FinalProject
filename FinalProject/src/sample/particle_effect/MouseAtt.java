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

public class MouseAtt {

    private final Vector positions;

    public MouseAtt(double x, double y) {
        positions = new Vector(x, y);
    }

    public Vector getPositions() {
        return positions;
    }

    public void setX(double x) {
        this.positions.setX(x);
    }

    public void setY(double y) {
        this.positions.setY(y);
    }

    public void draw(GraphicsContext gtx) {
        gtx.setFill(Color.LIGHTPINK);
        gtx.fillOval(positions.getX(), positions.getY(), 5, 5);
    }
}