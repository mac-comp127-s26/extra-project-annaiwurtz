import java.awt.Color;
import java.util.Random;

import edu.macalester.graphics.Rectangle;

public class Pipe {
    public static final int PIPE_WIDTH = 100;
    private static final int GAP_HEIGHT = 200;
    private static final int CANVAS_HEIGHT = 800;
    private static final Color PIPE_COLOR = Color.GRAY;
    private static Random random = new Random();

    Rectangle bottomPipe;
    Rectangle topPipe;
    int x;

    public Pipe() {
        x = 600;
        int gapY = randomInt(50,475);
        int bottomHeight = CANVAS_HEIGHT - (gapY + GAP_HEIGHT) - 100;
        bottomPipe = new Rectangle(x,gapY + GAP_HEIGHT,PIPE_WIDTH,bottomHeight);
        bottomPipe.setFillColor(PIPE_COLOR);
        int topHeight = gapY;
        topPipe = new Rectangle(x,0,PIPE_WIDTH,topHeight);
        topPipe.setFillColor(PIPE_COLOR);
    }

    private static int randomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public Rectangle getTopPipe() {
        return topPipe;
    }

    public Rectangle getBottomPipe() {
        return bottomPipe;
    }

    public void moveLeft(int dx) {
        topPipe.moveBy(-dx,0);
        bottomPipe.moveBy(-dx,0);
    }
}
