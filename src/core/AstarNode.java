package core;

import core.Grid.AstarGrid;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

public class AstarNode {
    private int x;
    private int y;
    private long cost;
    private AstarNode previousNode;
    private long heuristic;
    private int obstacleValue;
    public ArrayList<AstarNode> neighbors = new ArrayList<>();



    public AstarNode(int x, int y, int obstacleValue) {
        this.x = x;
        this.y = y;
        this.obstacleValue = obstacleValue;
    }


    public void calculateCost(long costUntilNow, AstarNode end){
        this.heuristic = Double.doubleToLongBits(Math.sqrt(Math.abs(x - end.getX())^2 + Math.abs(y - end.getY())^2));
        this.cost = costUntilNow + 1 + heuristic;
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

    public long getCost() {
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
        return  (x == o.x) && (y == o.y);
    }

    public void addNeighbors(AstarGrid grid, AstarNode end , ArrayList<AstarNode> openSet, ArrayList<AstarNode> closedSet, Predicate<AstarNode> conditionToAdd, int jumpUpTo) {

            for (int i = this.getX() - jumpUpTo - 1; i < this.getX() + jumpUpTo + 1; i++) {
                for (int j = this.getY() - jumpUpTo -1 ; j < this.getY() +jumpUpTo +1 ; j++) {
                    try {
                        AstarNode neighbor = grid.getNode(i, j);
                        neighbor.calculateCost(cost,end);

                        if (conditionToAdd.test(neighbor)) {
                            if (!(openSet.contains(neighbor) || closedSet.contains(neighbor))) {
                                neighbors.add(neighbor);
                            } else {
                                if (openSet.stream().filter(node -> node.equals(neighbor)).findFirst().isPresent()){
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
                    } catch (ArrayIndexOutOfBoundsException AIOOBE){

                }

                }
            }



    }


}
