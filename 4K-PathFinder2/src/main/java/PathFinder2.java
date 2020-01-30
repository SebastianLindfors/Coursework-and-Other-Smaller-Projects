import java.util.ArrayList;
import java.util.List;

public class PathFinder2 {

    public static int pathFinder(String maze) {

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

            this.nodeID = nodeID;

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
