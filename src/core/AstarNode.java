package core;

import core.Grid.AstarGrid;

import java.util.ArrayList;
import java.util.function.Predicate;

public class AstarNode {
    private int x;
    private int y;
    private int cost;
    private AstarNode previousNode;
    private int heuristic;
    private int obstacleValue;
    public ArrayList<AstarNode> neighbors = new ArrayList<>();

    public AstarNode(int x, int y, int obstacleValue) {
        this.x = x;
        this.y = y;
        this.obstacleValue = obstacleValue;
    }
    public void calculateCost(int costUntilNow, AstarNode end){
        this.heuristic = Math.abs(x - end.getX()) + Math.abs(y - end.getY());
        this.cost = costUntilNow + 1 + heuristic;
    }

    public int getHeuristic() {
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
        boolean test = x == o.x && y == o.y;
        return  x == o.x && y == o.y;
    }

    public void addNeighbors(AstarGrid grid, AstarNode end , ArrayList<AstarNode> openSet, ArrayList<AstarNode> closedSet, Predicate<AstarNode> conditionToAdd) {

            for (int i = this.getX() - 1; i < this.getX() + 2; i++) {
                for (int j = this.getY() - 1; j < this.getY() + 2; j++) {
                    try {
                        AstarNode neighbor = grid.getNode(i, j);
                        neighbor.calculateCost(cost,end);
                        if (conditionToAdd.test(neighbor)) {
                            if (!openSet.contains(neighbor) || !closedSet.contains(neighbor)) {
                                neighbors.add(neighbor);
                            } else {
                                AstarNode found = openSet.stream().filter(node -> node.equals(neighbor)).findFirst().get();

                                    if (neighbor.getCost() < found.getCost()) {
                                        found.cost = neighbor.cost;
                                        found.setPreviousNode(neighbor.previousNode);
                                    } else {
                                        neighbor.cost = found.cost;
                                        neighbor.setPreviousNode(found.previousNode);
                                    }


                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException AIOOBE){

                }

                }
            }



    }


}
