import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class FlappyBirdGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private Bird bird;
    private Rectangle ground;

    public FlappyBirdGame() {
        canvas = new CanvasWindow("Flappy Bird!", CANVAS_WIDTH, CANVAS_HEIGHT);
        newGame();
        
    }

    public void newGame() {
        ground = new Rectangle(0,700,600,100);
        ground.setFillColor(Color.BLACK);
        canvas.add(ground);

        bird = new Bird();
        canvas.add(bird.getGraphics());

        canvas.onMouseDown(event -> {
                    bird.flap();
                });

        canvas.animate(() -> {
            bird.move();
            checkCollisions();
        });

    }

    public void checkCollisions() {
        double x = bird.getGraphics().getX();
        double y = bird.getGraphics().getY();
        checkWallCollisions(x,y);
    }

    private void checkWallCollisions(double x, double y) {
        if (y > 700 - Bird.BIRD_DIAMETER) {
            bird.stop();
        }
    }
    public static void main(String[] args){
        new FlappyBirdGame();
    }
}
