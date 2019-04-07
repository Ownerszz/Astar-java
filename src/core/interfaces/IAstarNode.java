package core.interfaces;

import core.AstarNode;
import core.Grid.AstarGrid;

import java.util.ArrayList;
import java.util.function.Predicate;

public interface IAstarNode {
    int getCost();
    void setCost(int val);

    IAstarNode getPreviousNode();
    void calculateCost(int costUntilNow,IAstarNode end);
    void setPreviousNode(IAstarNode previousNode);

    int getX();
    int getY();

    int getObstacleValue();
    void setObstacleValue(int value);

    ArrayList<IAstarNode> getNeighbors();
    @Override
    boolean equals(Object obj);

    void addNeighbors(IAstarGrid grid, IAstarNode end, ArrayList<IAstarNode> openSet, ArrayList<IAstarNode> closedSet, Predicate<IAstarNode> conditionToAdd, int jumpUpTo);
}
