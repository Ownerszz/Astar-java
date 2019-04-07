package core;

import core.CustomExceptions.AstarNodeNotOnGridException;
import core.interfaces.IAstarGrid;
import core.interfaces.IAstarNode;

import java.util.ArrayList;
import java.util.function.Predicate;

public class AstarNode implements IAstarNode {
    public ArrayList<IAstarNode> neighbors = new ArrayList<>();
    private int x;
    private int y;
    private int cost;
    private IAstarNode previousNode;
    private int heuristic;
    private int obstacleValue;


    public AstarNode(int x, int y, int obstacleValue) {
        this.x = x;
        this.y = y;
        this.obstacleValue = obstacleValue;
        this.cost = 0;

    }

    public ArrayList<IAstarNode> getNeighbors() {
        return neighbors;
    }

    @Override
    public void calculateCost(int costUntilNow, IAstarNode end) {
        this.heuristic = Math.abs(x - end.getX()) + Math.abs(y - end.getY());
        this.cost = costUntilNow + heuristic;
    }

    public long getHeuristic() {
        return heuristic;
    }

    @Override
    public int getObstacleValue() {
        return obstacleValue;
    }

    @Override
    public void setObstacleValue(int obstacleValue) {
        this.obstacleValue = obstacleValue;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public IAstarNode getPreviousNode() {
        return previousNode;
    }

    @Override
    public void setPreviousNode(IAstarNode previousNode) {
        this.previousNode = previousNode;
    }

    @Override
    public boolean equals(Object obj) {
        AstarNode o = (AstarNode) obj;
        if (o == null) return false;
        return (x == o.x) && (y == o.y);
    }

    @Override
    public void addNeighbors(IAstarGrid grid, IAstarNode end, ArrayList<IAstarNode> openSet, ArrayList<IAstarNode> closedSet, Predicate<IAstarNode> conditionToAdd, int jumpUpTo) {
        for (int i = this.getX() - 1 - jumpUpTo; i < this.getX() + 2 + jumpUpTo; i++) {
            for (int j = this.getY() - 1 - jumpUpTo; j < this.getY() + 2 + jumpUpTo; j++) {
                try {
                    IAstarNode neighbor = grid.getNode(i, j);

                    neighbor.calculateCost(this.getCost(), end);
                    if (conditionToAdd.test(neighbor)) {
                        if (!(openSet.contains(neighbor) || closedSet.contains(neighbor))) {
                            neighbors.add(neighbor);
                        } else {
                            if (openSet.stream().filter(node -> node.equals(neighbor)).findFirst().isPresent()) {
                                IAstarNode found = openSet.stream().filter(node -> node.equals(neighbor)).findFirst().get();
                                //  neighbor.calculateCost(previousNode.getCost(),end);
                                // found.calculateCost(previousNode.getCost(),end);
                                if (neighbor.getCost() < found.getCost()) {
                                    found.setCost(neighbor.getCost());
                                    found.setPreviousNode(neighbor.getPreviousNode());
                                } else if (neighbor.getCost() > found.getCost()) {
                                    neighbor.setCost(found.getCost());
                                    neighbor.setPreviousNode(found.getPreviousNode());
                                }
                            }


                        }
                    } else {
                        closedSet.add(neighbor);
                    }
                } catch (ArrayIndexOutOfBoundsException AIOOBE) {

                } catch (AstarNodeNotOnGridException ANNOGE) {

                }

            }
        }


    }

    @Override
    public String toString() {
        return String.format("X: %d, Y: %d, cost: %d", this.x, this.y, this.cost);
    }
}
