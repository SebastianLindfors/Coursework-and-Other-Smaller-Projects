import java.util.ArrayList;
import java.util.List;

public class PathFinder2 {

    public static int pathFinder(String maze) {
        return 0;
    }

    private static class mazeNode {

        List<mazeNode> edgeTo = new ArrayList<>();

        int stepsToThisNode;
        mazeNode shortestPathHere;

        boolean isTargetNode;
        boolean visited;

        String nodeID;

        public mazeNode(String nodeID) {

            this.nodeID = nodeID;

            stepsToThisNode = -1;

            this.visited = false;

        }

        void addLinkToNode(mazeNode newLink) {
            edgeTo.add(newLink);
        }

        mazeNode getShortestPathHere() {
            return shortestPathHere;
        }

        boolean isVisited() {
            return visited;
        }





    }


}
