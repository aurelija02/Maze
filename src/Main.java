import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Maze maze = new Maze();
            maze.findPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
