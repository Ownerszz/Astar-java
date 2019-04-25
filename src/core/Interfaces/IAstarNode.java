package core.Interfaces;
import core.Interfaces.FunctionalInterfaces.FunctionalTest;

import java.util.ArrayList;

public interface IAstarNode {
    int getCost();

    void setCost(int val);

    IAstarNode getPreviousNode();

    void setPreviousNode(IAstarNode previousNode);

    void calculateCost(int costUntilNow, IAstarNode end);

    int getX();

    int getY();

    int getObstacleValue();

    void setObstacleValue(int value);

    ArrayList<IAstarNode> getNeighbors();

    @Override
    boolean equals(Object obj);

    void addNeighbors(IAstarGrid grid, IAstarNode end, ArrayList<IAstarNode> openSet, ArrayList<IAstarNode> closedSet, FunctionalTest conditionToAdd, int jumpUpTo);
}
