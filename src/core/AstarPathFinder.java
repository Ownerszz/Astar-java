package core;

import core.CustomExceptions.AstarPathNotFoundException;
import core.Grid.AstarGrid;
import core.interfaces.IAstarGrid;
import core.interfaces.IAstarNode;
import core.interfaces.IAstarPathFinder;

import java.util.ArrayList;
import java.util.function.Predicate;

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
    public AstarPathFinder(IAstarNode start, IAstarNode end, IAstarGrid grid) {
        this.start = start;
        this.end = end;
        this.openSet = new ArrayList<>();
        this.closedSet = new ArrayList<>();
        this.grid = grid;
    }

/*
Method findPath:
Method that gets called for the pathfinding.
                                            Parameters: - Predicate that defines the condition for adding the neighbors
                                                        - jumpUpTo that defines how far the nodes for the pathfinding can be.

                                            Throws:     - AstarPathNotFoundException
 */
    @Override
    public void findPath(Predicate<IAstarNode> conditionForAddingNeighbors, int jumpUpTo) throws AstarPathNotFoundException {
        //TODO: finish this
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


}
