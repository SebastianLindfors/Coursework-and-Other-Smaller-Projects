import java.awt.Point;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;


public class ConwaysGameOfLife {

    public static int[][] getGeneration(int[][] cells, int generations) {

        Map<Integer, HashMap<Integer,Boolean>> xCoordinate = new HashMap<>();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == 1) {
                    if (xCoordinate.keySet().contains(i)) {
                        xCoordinate.get(i).put(j,true);
                    }
                    else {
                        xCoordinate.put(i,new HashMap<Integer, Boolean>());
                        xCoordinate.get(i).put(j,true);
                    }
                }
            }
        }




        return null;
    }

    private static ArrayList<Point> nextGeneration(Map<Integer, HashMap<Integer,Integer>> liveCells) {

        Map<Integer, HashMap<Integer,Integer>> deadNeighbourCells = new HashMap<>();

        int[][] NEIGHBOURS = {
                {-1, -1}, {-0, -1}, {+1, -1},
                {-1, 0},            {+1, 0},
                {-1, +1}, {0, +1},  {+1, +1}};

        for (int i : liveCells.keySet()) {
            for (int j : liveCells.get(i).keySet()) {
                for (int[] offset : NEIGHBOURS) {
                    if (!liveCells.containsKey(i + offset[0])) {
                        if (!liveCells.get(i + offset[0]).containsKey(j + offset[1])) {
                            if (deadNeighbourCells.containsKey(i + offset[0])) {
                                if (deadNeighbourCells.get(i + offset[0]).containsKey(j + offset[1]) ) {
                                    deadNeighbourCells.get(i).put(j, deadNeighbourCells.get(i).get(j) + 1);
                                }
                                else {
                                    deadNeighbourCells.get(i).put(j,1);
                                }
                            }
                            else {
                                deadNeighbourCells.put(i,new HashMap<Integer,Integer>());
                                deadNeighbourCells.get(i).put(j,1);
                            }
                        }
                    }
                }

            }
        }

    }


}
