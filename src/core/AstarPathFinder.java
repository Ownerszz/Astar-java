package core;

import core.CustomExceptions.AstarPathNotFoundException;
import core.Grid.AstarGrid;

import java.util.ArrayList;
import java.util.function.Predicate;

public class AstarPathFinder {
    private AstarNode start;
    private AstarNode end;
    private ArrayList<AstarNode> optimalPath;
    private ArrayList<AstarNode> openSet;
    private ArrayList<AstarNode> closedSet;
    private AstarGrid grid;
    private boolean pathFound;

    public AstarPathFinder(AstarNode start, AstarNode end, AstarGrid grid) {
        this.start = start;
        this.end = end;
        this.optimalPath = new ArrayList<>();
        this.openSet = new ArrayList<>();
        this.closedSet = new ArrayList<>();
        this.grid = grid;

    }


    public void findPath(Predicate<AstarNode> conditionForAddingNeighbors, int jumpUpTo) throws AstarPathNotFoundException {
        //TODO: finish this
        openSet.add(start);
        start.setPreviousNode(start);
        while (!openSet.isEmpty()) {
            AstarNode currentNode = openSet.get(0);
            for (AstarNode node : openSet) {
                if (node.getCost() < currentNode.getCost()) {
                    currentNode = node;
                }
            }

            if (currentNode.equals(end)) {
                pathFound = true;
                return;
            }
            closedSet.add(currentNode);
            currentNode.addNeighbors(grid, end, openSet, closedSet, conditionForAddingNeighbors, jumpUpTo);
            openSet.remove(currentNode);

            for (AstarNode neighbor : currentNode.neighbors) {
                if (!openSet.contains(neighbor) && !closedSet.contains(neighbor)) {

                    neighbor.setPreviousNode(currentNode);
                    openSet.add(neighbor);
                }
            }
        }
        throw new AstarPathNotFoundException();
    }

    public ArrayList<AstarNode> getOptimalPath() throws AstarPathNotFoundException {
        if (pathFound) {
            AstarNode prev = end;
            optimalPath.add(prev);
            while (prev != start) {
                prev = prev.getPreviousNode();
                optimalPath.add(prev);
            }

            return optimalPath;
        }
        throw new AstarPathNotFoundException();
    }


}
