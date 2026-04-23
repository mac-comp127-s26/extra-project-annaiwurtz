import edu.macalester.graphics.CanvasWindow;

public class FlappyBirdGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;

    public FlappyBirdGame() {
        canvas = new CanvasWindow("Tic-Tac-Toe!", CANVAS_WIDTH, CANVAS_HEIGHT);
        newGame();
    }
    public static void main(String[] args){
        new FlappyBirdGame();
    }
}
