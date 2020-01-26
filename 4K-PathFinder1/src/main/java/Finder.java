import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Finder {

    static boolean pathFinder(String maze) {

        int mazeSize = (int) Math.sqrt(maze.length()) - 1;
        if (mazeSize == 0) {
            //System.out.println("Returning True");
            return true;
        }
       // System.out.println("Maze size: " + mazeSize ); //Debugging
        Point targetPoint = new Point(mazeSize, mazeSize);

        int[][] mazeMap = constructMaze(maze);
        List<Point> pointsToExplore = new ArrayList<>();

        pointsToExplore.add(new Point(0,0));
        mazeMap[0][0] = 1;
        mazeMap[mazeSize][mazeSize] = 2;

        while (!(pointsToExplore.isEmpty())) {

//            System.out.println("Testing Point: (" + pointsToExplore.get(0).x + ", " +pointsToExplore.get(0).y +")");
//            if (pointsToExplore.get(0).equals(targetPoint)) {
//                return true;
//            }

            List<Point> newPoints = accessiblePoints(pointsToExplore.get(0), mazeMap);
            pointsToExplore.remove(0);
            for (Point newPoint : newPoints) {
                if (mazeMap[newPoint.x][newPoint.y] == 2) {
                    return true;
                }
                if (mazeMap[newPoint.x][newPoint.y] != 1) {
                    pointsToExplore.add(newPoint);
                    mazeMap[newPoint.x][newPoint.y] = 1;
                }
            }
        }

        return false;
    }

    public static int[][] constructMaze(String mazeString) {

        int mazeSize = (int) Math.sqrt(mazeString.length());
        int[][] outputMap = new int[mazeSize][mazeSize];
        String[] mazeLines = mazeString.split("\n");

        for (int i = 0; i < mazeLines.length; i++) {
            char[] mazeLine = mazeLines[i].toCharArray();
            for (int j = 0; j < mazeLine.length; j++) {
                if (mazeLine[j] == 'W') {
                    outputMap[i][j] = -1;
                }
                else if (mazeLine[j] == '.') {
                    outputMap[i][j] = 0;
                }
                else {
                    throw new IllegalArgumentException("Unrecognized character in maze string: " + mazeLine[j]);
                }
            }

        }


        return outputMap;

    }

    public static List<Point> accessiblePoints(Point start, int[][] mazeMap) {

        List<Point> outputList = new ArrayList<>();

        if (start.x + 1 < mazeMap.length) {
            if (mazeMap[start.x + 1][start.y] != -1) {
                outputList.add(new Point(start.x + 1, start.y));
            }
        }

        if (start.y + 1 < mazeMap.length) {
            if (mazeMap[start.x][start.y + 1] != -1) {
                outputList.add(new Point(start.x, start.y + 1));
            }
        }

        if (start.x - 1 > -1) {
            if (mazeMap[start.x - 1][start.y] != -1) {
                outputList.add(new Point(start.x - 1 , start.y));
            }
        }

        if (start.y - 1 > -1) {
            if (mazeMap[start.x][start.y - 1] != -1) {
                outputList.add(new Point(start.x , start.y - 1));
            }
        }

        return outputList;

    }


}


