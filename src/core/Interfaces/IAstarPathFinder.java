package core.Interfaces;

import core.CustomExceptions.AstarPathNotFoundException;
import core.Interfaces.IAstarNode;
import java.util.ArrayList;
import java.util.function.Predicate;

public interface IAstarPathFinder {
    /*
        Method findPath:
        Method that gets called for the pathfinding.
                                                    Parameters: - Predicate that defines the condition for adding the neighbors
                                                                - jumpUpTo that defines how far the nodes for the pathfinding can be.

                                                    Throws:     - AstarPathNotFoundException
         */
    void findPath(Predicate<IAstarNode> conditionForAddingNeighbors, int jumpUpTo) throws AstarPathNotFoundException;

    /*
        Method getOptimalPath:
        Method that gives the optimal path.
                                                    Returns:    - A ArrayList containing the nodes from END to START

                                                    Throws:     - AstarPathNotFoundException
         */
    ArrayList<IAstarNode> getOptimalPath() throws AstarPathNotFoundException;
}
