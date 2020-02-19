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
                if (mazeLine[j] == '.') {
                    Point currentPoint = new Point(i,j);
                    mazeMap.put(currentPoint, new mazeNode());
                }

            }

        }

        mazeNode startNode = new mazeNode();
        mazeNode targetNode = new mazeNode();

        mazeMap.put(new Point(0,0), startNode);
        mazeMap.put(new Point(mazeLines.length - 1,mazeLines.length -1), targetNode);
        for (Point p: mazeMap.keySet()) {
            Point otherPoint = new Point(p.x - 1, p.y);
            if (mazeMap.containsKey(otherPoint)) {
                mazeMap.get(p).addLinkToNode(mazeMap.get(otherPoint));
            }
            otherPoint = new Point(p.x + 1, p.y);
            if (mazeMap.containsKey(otherPoint)) {
                mazeMap.get(p).addLinkToNode(mazeMap.get(otherPoint));
            }
            otherPoint = new Point(p.x, p.y - 1);
            if (mazeMap.containsKey(otherPoint)) {
                mazeMap.get(p).addLinkToNode(mazeMap.get(otherPoint));
            }
            otherPoint = new Point(p.x, p.y + 1);
            if (mazeMap.containsKey(otherPoint)) {
                mazeMap.get(p).addLinkToNode(mazeMap.get(otherPoint));
            }

        }






        return targetNode.getStepsToThisNode();



    }

    static List<mazeNode> getAdjacentNodes(mazeNode centerNode) {

        List<mazeNode> output = new ArrayList<>();
        return output;
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
