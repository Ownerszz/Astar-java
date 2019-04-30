package core.Interfaces;

import core.CustomExceptions.AstarPathNotFoundException;

import java.util.ArrayList;

public interface IAstarPathFinder {
    /*
        Method findPath:
        Method that gets called for the pathfinding.
                                                    Parameters: - Predicate that defines the condition for adding the neighbors
                                                                - jumpUpTo that defines how far the nodes for the pathfinding can be.

                                                    Throws:     - AstarPathNotFoundException
         */
    void findPath(IFunctionalTest conditionForAddingNeighbors, int jumpUpTo) throws AstarPathNotFoundException;
    /*
        Method getOptimalPath:
        Method that gives the optimal path.
                                                    Returns:    - A ArrayList containing the nodes from END to START

                                                    Throws:     - AstarPathNotFoundException
         */
    ArrayList<IAstarNode> getOptimalPath() throws AstarPathNotFoundException;

    IAstarNode getStart();

    IAstarNode getEnd();
}
