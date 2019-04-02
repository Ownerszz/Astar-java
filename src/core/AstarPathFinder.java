package core;

import core.CustomExceptions.AstarPathNothFoundException;
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

    public AstarPathFinder(AstarNode start, AstarNode end, AstarGrid grid){
        this.start = start;
        this.end = end;
        optimalPath = new ArrayList<>();
        this.grid = grid;
    }

    public void findPath(Predicate<AstarNode> conditionForAddingNeighbors) throws AstarPathNothFoundException {
        //TODO: finish this
        openSet.add(start);
        while (!openSet.isEmpty()){
            AstarNode currentNode = openSet.get(0);
            for (AstarNode node: openSet) {
                if (node.getCost() < currentNode.getCost()){
                    currentNode = node;
                }
            }
            currentNode.calculateCost(0,end);
            currentNode.addNeighbors(grid,end,openSet,closedSet,conditionForAddingNeighbors);
            if (currentNode.equals(end)){

                return;
            }
            openSet.remove(currentNode);
            closedSet.add(currentNode);
            for (AstarNode neighbor : currentNode.neighbors) {
                if (!openSet.contains(neighbor) && !closedSet.contains(neighbor)){
                    openSet.add(neighbor);
                }
            }
        }
        throw new AstarPathNothFoundException();
    }

    public ArrayList<AstarNode> getOptimalPath() {
        return optimalPath;
    }
}
