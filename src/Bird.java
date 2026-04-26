import edu.macalester.graphics.Ellipse;

import java.awt.Color;

public class Bird {
    public static final int BIRD_DIAMETER = 55;
    private double x;
    private double y;
    private double vy = 0;
    private double ay = 0.5;
    private Ellipse bird;

    public Bird() {
        this.x = 200;
        this.y = 350;
        bird = new Ellipse(x,y,BIRD_DIAMETER,BIRD_DIAMETER);
        bird.setFillColor(Color.YELLOW);
    }

    public void move() {
        vy += ay;
        y += vy;
        bird.setPosition(x,y);
    }

    public void flap() {
        vy = -10;
    }

    public void die() {
        vy = 0;
    }
    
    public void stop() {
        vy = 0;
        ay = 0;
    }

    public Ellipse getGraphics() {
        return bird;
    }

}
