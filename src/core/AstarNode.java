package core;

import core.CustomExceptions.AstarNodeNotOnGridException;
import core.Grid.AstarGrid;

import java.util.ArrayList;
import java.util.function.Predicate;

public class AstarNode {
    public ArrayList<AstarNode> neighbors = new ArrayList<>();
    private int x;
    private int y;
    private int cost;
    private AstarNode previousNode;
    private int heuristic;
    private int obstacleValue;


    public AstarNode(int x, int y, int obstacleValue) {
        this.x = x;
        this.y = y;
        this.obstacleValue = obstacleValue;
        this.cost = 0;

    }

    public void setObstacleValue(int obstacleValue) {
        this.obstacleValue = obstacleValue;
    }

    public void calculateCost(int costUntilNow, AstarNode end) {
        this.heuristic = Math.abs(x - end.getX()) + Math.abs(y - end.getY());
        this.cost = costUntilNow + heuristic;
    }

    public long getHeuristic() {
        return heuristic;
    }

    public int getObstacleValue() {
        return obstacleValue;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCost() {
        return cost;
    }

    public AstarNode getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(AstarNode previousNode) {
        this.previousNode = previousNode;
    }

    @Override
    public boolean equals(Object obj) {
        AstarNode o = (AstarNode) obj;
        if (o == null) return false;
        return (x == o.x) && (y == o.y);
    }

    public void addNeighbors(AstarGrid grid, AstarNode end, ArrayList<AstarNode> openSet, ArrayList<AstarNode> closedSet, Predicate<AstarNode> conditionToAdd, int jumpUpTo) {

        for (int i = this.getX() - 1 - jumpUpTo; i < this.getX() + 2 + jumpUpTo; i++) {
            for (int j = this.getY() - 1 - jumpUpTo; j < this.getY() + 2 + jumpUpTo; j++) {
                try {
                    AstarNode neighbor = grid.getNode(i, j);

                    neighbor.calculateCost(cost, end);
                    if (conditionToAdd.test(neighbor)) {
                        if (!(openSet.contains(neighbor) || closedSet.contains(neighbor))) {
                            neighbors.add(neighbor);
                        } else {
                            if (openSet.stream().filter(node -> node.equals(neighbor)).findFirst().isPresent()) {
                                AstarNode found = openSet.stream().filter(node -> node.equals(neighbor)).findFirst().get();
                                //  neighbor.calculateCost(previousNode.getCost(),end);
                                // found.calculateCost(previousNode.getCost(),end);
                                if (neighbor.getCost() < found.getCost()) {
                                    found.cost = neighbor.cost;
                                    found.setPreviousNode(neighbor.previousNode);
                                } else if (neighbor.getCost() > found.getCost()) {
                                    neighbor.cost = found.cost;
                                    neighbor.setPreviousNode(found.previousNode);
                                }
                            }


                        }
                    } else {
                        closedSet.add(neighbor);
                    }
                } catch (ArrayIndexOutOfBoundsException AIOOBE) {

                } catch (AstarNodeNotOnGridException ANNOGE){

                }

            }
        }


    }

    @Override
    public String toString() {
        return String.format("X: %d, Y: %d, cost: %d", this.x, this.y, this.cost);
    }
}
