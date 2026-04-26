import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class FlappyBirdGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private Bird bird;
    private Rectangle ground;
    private List<Pipe> pipes;
    private Set<Pipe> scoredPipes = new HashSet<>();

    private int frameCount = 0;
    private boolean isGameRunning;
    private int score;
    private GraphicsText scoreCount;

    public FlappyBirdGame() {
        canvas = new CanvasWindow("Flappy Bird!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.decode("#94E6FF"));
        newGame();    
    }

    public void newGame() {
        isGameRunning = true;

        score = 0;
        showScore(score);
        scoredPipes.clear();


        ground = new Rectangle(0,700,600,100);
        ground.setFillColor(Color.decode("#029C25"));
        canvas.add(ground);

        bird = new Bird();
        canvas.add(bird.getGraphics());

        pipes = new ArrayList<>();

        canvas.onMouseDown(event -> {
            if (isGameRunning) {
                bird.flap();
            }
        });

        canvas.animate(() -> {
            double x = bird.getGraphics().getX();
            double y = bird.getGraphics().getY();
            bird.move();
            frameCount++;

            if (frameCount % 80 == 0) {
                Pipe pipe = new Pipe();
                pipes.add(pipe);
                canvas.add(pipe.getTopPipe());
                canvas.add(pipe.getBottomPipe());
                canvas.remove(bird.getGraphics());
                canvas.add(bird.getGraphics());
                canvas.remove(scoreCount);
                canvas.add(scoreCount);

            }

            if (isGameRunning) {
                movePipes();
                updateScore();
                checkPipeCollisions(x,y);
            }

            checkGroundCollisions(y);

        });

    }

    private void movePipes() {
        for (Pipe pipe:pipes) {
            pipe.moveLeft(5);
        }
    }

    public void checkPipeCollisions(double x,double y) {
        List<GraphicsObject> objects = getObjectsAtPoints(getBirdSamplePoints(x,y));

        for (GraphicsObject object : objects) {
            if (object == null) {
                continue;
            }

            for (Pipe pipe : pipes) {
                if (object == pipe.getBottomPipe() || object == pipe.getTopPipe()) {
                    bird.die();
                    isGameRunning = false;
                    showGameOver();
                    return;
                }
            }
        }

    }

    private void checkGroundCollisions(double y) {
        if (y > 700 - Bird.BIRD_DIAMETER) {
            bird.stop();
            isGameRunning = false;
            showGameOver();
        }
    }

    private List<Point> getBirdSamplePoints(double x,double y) {
        List<Point> points = new ArrayList<>();

        points.add(new Point(x,y));
        points.add(new Point(x + Bird.BIRD_DIAMETER,y));
        points.add(new Point(x,y + Bird.BIRD_DIAMETER));
        points.add(new Point(x + Bird.BIRD_DIAMETER,y + Bird.BIRD_DIAMETER));
        points.add(new Point(x + Bird.BIRD_DIAMETER/2,y));
        points.add(new Point(x + Bird.BIRD_DIAMETER/2,y + Bird.BIRD_DIAMETER));
        points.add(new Point(x,y + Bird.BIRD_DIAMETER/2));
        points.add(new Point(x + Bird.BIRD_DIAMETER,y + Bird.BIRD_DIAMETER/2));

        return points;
    }

    private List<GraphicsObject> getObjectsAtPoints(List<Point> points) {
        List<GraphicsObject> objects = new ArrayList<>();

        for (Point point:points) {
            objects.add(canvas.getElementAt(point));
        }
        
        return objects;
    }

    private void updateScore() {
        for (Pipe pipe: pipes) {
            double pipeX = pipe.getTopPipe().getX();
            double birdX = bird.getGraphics().getX();
            if (pipeX + Pipe.PIPE_WIDTH < birdX && !scoredPipes.contains(pipe)) {
                score++;
                scoredPipes.add(pipe);
                showScore(score);
            }
        }
    }

    private void showGameOver() {
        GraphicsText message = new GraphicsText("GAME OVER",185,400);
        message.setFontSize(40);
        message.setFillColor(Color.BLACK);
        canvas.add(message);
    }

    private void showScore(int score) {
        String text = String.valueOf(score);
        if (scoreCount == null) {
            scoreCount = new GraphicsText(text, 290, 70);
            scoreCount.setFontSize(50);
            scoreCount.setFillColor(Color.BLACK);
            canvas.add(scoreCount);
        } else {
            scoreCount.setText(text);
        };
    }
    public static void main(String[] args){
        new FlappyBirdGame();
    }
}
