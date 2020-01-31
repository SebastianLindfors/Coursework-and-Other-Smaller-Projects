import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathFinder2 {

    public static int pathFinder(String maze) {

        String[] mazeLines = maze.split("\n");
        Map<Point, mazeNode> mazeMap = new HashMap<>();
        for (int i = 0; i < mazeLines.length; i++) {
            char[] mazeLine = mazeLines[i].toCharArray();
            for (int j = 0; j < mazeLine.length; j++) {
                
            }

        }
        mazeNode startNode = new mazeNode();
        mazeNode targetNode = new mazeNode();




        return targetNode.getStepsToThisNode();
    }

    private static class mazeNode {

        List<mazeNode> edgeTo = new ArrayList<>();

        int stepsToThisNode;
        mazeNode shortestPathHere;

        boolean isTargetNode;
        boolean visited;

        String nodeID;

        public mazeNode() {


            stepsToThisNode = -1;

            this.visited = false;

        }

        void addLinkToNode(mazeNode newLink) {
            edgeTo.add(newLink);
            if (newLink.getStepsToThisNode() != -1) {
                if (newLink.getStepsToThisNode() + 1 < this.getStepsToThisNode()) {
                    this.stepsToThisNode = newLink.getStepsToThisNode() + 1;
                    shortestPathHere = newLink;
                }
            }
        }

        mazeNode getShortestPathHere() {
            return shortestPathHere;
        }

        int getStepsToThisNode() {
            return stepsToThisNode;
        }

        boolean isVisited() {
            return visited;
        }





    }


}