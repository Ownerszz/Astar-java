package core.PathFinding;

import core.CustomExceptions.AstarNodeNotOnGridException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.Interfaces.*;

import java.util.ArrayList;

/*
This class is can be used for 2D or more grids.
 */
public class AstarPathFinder implements IAstarPathFinder {
    private IAstarNode start;
    private IAstarNode end;
    private ArrayList<IAstarNode> optimalPath;
    private ArrayList<IAstarNode> openSet;
    private ArrayList<IAstarNode> closedSet;
    private IAstarGrid grid;
    private boolean pathFound;

    /*
    Constructor:
    New instance of AstarPathFinder will initialise with:   - A start AstarNode
                                                            - A end AstarNode
                                                            - A AstarGrid were the pathfinding has to happen
                                                            - A new ArrayList (openSet) containing the nodes that we want to evaluate (is empty at start)
                                                            - A new ArrayList (closedSet) containing the nodes that we already evaluated (is empty at start)
     */
    public AstarPathFinder(IAstarNode start, IAstarNode end, IAstarGrid grid) throws AstarNodeNotOnGridException {
        this.start = start;
        this.end = end;
        this.grid = grid;
        validateStartAndEndNode();
        this.openSet = new ArrayList<>();
        this.closedSet = new ArrayList<>();
    }

    public AstarPathFinder(IAstarGridFactoryResult result) throws AstarNodeNotOnGridException{
       this(result.getStart(),result.getEnd(),result.getGrid());
    }

    private void validateStartAndEndNode() throws AstarNodeNotOnGridException {
        IAstarNode found;
        found = grid.getNode(start);
        found = grid.getNode(end);
    }

    /*
    Method findPath:
    Method that gets called for the pathfinding.
                                                Parameters: - Predicate that defines the condition for adding the neighbors
                                                            - jumpUpTo that defines how far the nodes for the pathfinding can be.

                                                Throws:     - AstarPathNotFoundException
     */

    @Override
    public void findPath(IFunctionalTest conditionForAddingNeighbors, int jumpUpTo) throws AstarPathNotFoundException {

        openSet.add(start);
        start.setPreviousNode(start);
        while (!openSet.isEmpty()) {
            IAstarNode currentNode = openSet.get(0);
            for (IAstarNode node : openSet) {
                if (node.getCost() < currentNode.getCost()) {
                    currentNode = node;
                }
            }

            if (currentNode.equals(end)) {
                end = currentNode;
                pathFound = true;
                return;
            }
            closedSet.add(currentNode);
            currentNode.addNeighbors(grid, end, openSet, closedSet, conditionForAddingNeighbors, jumpUpTo);
            openSet.remove(currentNode);

            for (IAstarNode neighbor : currentNode.getNeighbors()) {
                if (!openSet.contains(neighbor) && !closedSet.contains(neighbor)) {
                    neighbor.setPreviousNode(currentNode);
                    openSet.add(neighbor);
                }
            }
        }
        throw new AstarPathNotFoundException();
    }

    /*
    Method getOptimalPath:
    Method that gives the optimal path.
                                                Returns:    - A ArrayList containing the nodes from END to START

                                                Throws:     - AstarPathNotFoundException
     */
    @Override
    public ArrayList<IAstarNode> getOptimalPath() throws AstarPathNotFoundException {
        if (pathFound) {
            this.optimalPath = new ArrayList<>();
            IAstarNode prev = end;
            optimalPath.add(prev);
            while (prev != start) {
                prev = prev.getPreviousNode();
                optimalPath.add(prev);
            }
            return optimalPath;
        }
        throw new AstarPathNotFoundException();
    }

    @Override
    public IAstarNode getStart(){
        return start;
    }

    @Override
    public IAstarNode getEnd(){
        return end;
    }


}
