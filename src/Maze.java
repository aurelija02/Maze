import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Maze {

    private String [][] maze;
    private Position startingPosition;
    private LinkedList<Position> path = new LinkedList<Position>();

    public Maze() throws FileNotFoundException {

        createMazeGridFromFile("map2.txt");

    }
    public Integer findPath (){
        startingPosition = findStartingPosition();
        path.push(startingPosition);
        boolean flag = true;

        while(flag) {
            int y = path.peek().y;
            int x = path.peek().x;

            maze[y][x] = "1";

            //down
            if(isValid(y+1, x)) {
                if(maze[y+1][x].equals(" ") && y+1 == maze.length-1) {
                    return path.size();
                } else if(maze[y+1][x].equals(" ")) {
                    path.push(new Position(y+1, x));
                    continue;
                }
            }
            //right
            if(isValid(y, x+1)) {
                if(maze[y][x+1].equals(" ") && x+1 == maze[y].length-1) {
                    return path.size();
                } else if(maze[y][x+1].equals(" ")) {
                    path.push(new Position(y, x+1));
                    continue;
                }
            }
            //left
            if(isValid(y, x-1)) {
                if(maze[y][x-1].equals(" ") && x-1 == 0) {
                    return path.size();
                } else if(maze[y][x-1].equals(" ")) {
                    path.push(new Position(y, x-1));
                    continue;
                }
            }
            //up
            if(isValid(y-1, x)) {
                if(maze[y-1][x].equals(" ") && y-1 == 0) {
                    return path.size();
                } else if(maze[y-1][x].equals(" ")) {
                    path.push(new Position(y-1, x));
                    continue;
                }
            }

            path.pop();
            if(path.size() <= 0) {
                flag = false;
                //System.out.println(path.size());
                return path.size();
            }
        }
        return path.size();
    }

    public String [][] createMazeGridFromFile(String mapTitle) throws FileNotFoundException {
        int numbersOfRows=0;
        int numbersOfCollumns=0;
        List<String> labyrinth = new ArrayList<>();

        File file = new File("src/" + mapTitle);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String [] fields = line.split("");
            numbersOfCollumns = fields.length;
            labyrinth.add(line);
            numbersOfRows = labyrinth.size();
        }

        maze = new String[numbersOfRows][numbersOfCollumns];
        for (int i = 0; i < numbersOfRows; i++){
            for (int j = 0; j < numbersOfCollumns; j++){
                String [] lineOfLabyrinth = labyrinth.get(i).split("");
                maze[i][j] = lineOfLabyrinth[j];
            }
        }
        scanner.close();
        return maze;
    }

    public Position findStartingPosition (){
        int y = 0;
        int x = 0;
        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[i].length; j++){
                if (maze[i][j].equals("X")){
                    y = i;
                    x = j;
                }
            }
        }
        return new Position(y,x);
    }

    public boolean isValid(int y, int x) {
        if(y < 0 ||
                y >= maze.length ||
                x < 0 ||
                x >= maze[y].length
        ) {
            return false;
        }
        return true;
    }

    public String[][] getMaze() {
        return maze;
    }

    public Position getStartingPosition() {
        return startingPosition;
    }
}
