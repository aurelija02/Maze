import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    Maze maze;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        maze = new Maze();
    }

    @Test
    void findPath() {
        Integer countedSteps = maze.findPath();
        assertEquals(13, countedSteps);
    }

    @Test
    void createMazeGridFromFile () throws FileNotFoundException {
        String title = "map2.txt";
        Integer mazeSize = maze.createMazeGridFromFile(title).length;
        assertEquals(11, mazeSize);
    }
    @Test
    void findStartingPosition() {
        Position position = maze.findStartingPosition();
        assertEquals(5, position.y);
    }

}