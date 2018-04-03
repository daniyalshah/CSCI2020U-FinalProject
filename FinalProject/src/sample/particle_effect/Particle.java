/*
 * Final Project 2020 - Particle effect
 * Group Members -
 * Syed Daniyal Shah
 * Mustafa Al-Azzawe
 * Shane Rego
 */

package sample.particle_effect;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class Particle {

    private final double particleForce = 350;
    private final double vectorStr = 1;

    private final MouseAtt mouseAtt;
    private final Vector positionP;
    private Vector vel;

    private Vector vecc = new Vector(0, 0);

    public Particle(double x, double y, MouseAtt mouseAtt) {
        positionP = new Vector(x, y);
        this.mouseAtt = mouseAtt;
        detVel();
    }

    private void detVel(){
        this.vel = new Vector(Math.random() * vectorStr * 2 - vectorStr, Math.random() * vectorStr * 2 - vectorStr);
    }

    public Vector getPositionP() {
        return positionP;
    }

    public void setX(double x) {
        this.positionP.setX(x);
    }

    public void setY(double y) {
        this.positionP.setY(y);
    }

    public void attracted() {
        Vector dir = Vector2.sub(mouseAtt.getPositions(), positionP);
        double dsquared = dir.magSq();
        dsquared = constrain(dsquared, 100, 400);
        double strength = particleForce / dsquared;
        dir.setMag(strength);
        this.vecc = dir;
    }

    private double constrain(double getal, double min, double max) {
        if (getal > max) {
            return max;
        } else if (getal < min) {
            return min;
        } else {
            return getal;
        }
    }

    public void update() {
        positionP.add(vel);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        if(positionP.getX() <= 20){
            vel.touchObject();
        }

        if(positionP.getX() >= primaryScreenBounds.getWidth() - 20){
            vel.touchObject();
        }

        if(positionP.getY() <= 20){
            vel.touchObject();
        }

        if(positionP.getY() >= primaryScreenBounds.getHeight() - 50){
            vel.touchObject();
        }

        vel.add(vecc);
        attracted();
    }

    public void draw(GraphicsContext gtx) {
        gtx.setFill(Color.rgb(255, 134, 150, 1));
        gtx.fillOval(this.positionP.getX(), this.positionP.getY(), 2, 2);
    }
}