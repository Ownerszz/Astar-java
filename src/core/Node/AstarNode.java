package core.Node;

import core.CustomExceptions.AstarNodeNotOnGridException;
import core.Interfaces.IAstarGrid;
import core.Interfaces.IAstarNode;
import core.Interfaces.IFunctionalTest;

import java.util.ArrayList;

public class AstarNode implements IAstarNode {
    private ArrayList<IAstarNode> neighbors = new ArrayList<>();
    private int x;
    private int y;
    private int cost;
    private IAstarNode previousNode;
    private int heuristic;
    private int obstacleValue;


    AstarNode(int x, int y, int obstacleValue) {
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
        try {
            IAstarNode o = (IAstarNode) obj;
            if (o == null) return false;
            return (this.getX() == o.getX()) && (this.getY() == o.getY());
        }catch(ClassCastException CCE){
            System.out.println(CCE.getMessage());
            return false;
        }    
    }

    @Override
    public void addNeighbors(IAstarGrid grid, IAstarNode end, ArrayList<IAstarNode> openSet, ArrayList<IAstarNode> closedSet, IFunctionalTest conditionToAdd, int jumpUpTo) {
        addNeighbors(grid,end,openSet,closedSet,conditionToAdd,jumpUpTo,true);
    }

    @Override
    public void addNeighbors(IAstarGrid grid, IAstarNode end, ArrayList<IAstarNode> openSet, ArrayList<IAstarNode> closedSet, IFunctionalTest conditionToAdd, int jumpUpTo, boolean allowDiagonalMoves) {
        for (int i = this.getX() - 1 - jumpUpTo; i < this.getX() + 2 + jumpUpTo; i++) {
            for (int j = this.getY() - 1 - jumpUpTo; j < this.getY() + 2 + jumpUpTo; j++) {
                try {
                    Boolean neighborPassedTest = false;
                    IAstarNode neighbor = grid.getNode(i, j);
                    if (!(!allowDiagonalMoves && (!(neighbor.getX() == this.getX() || neighbor.getY() == this.getY())))) {
                        neighbor.calculateCost(this.getCost(), end);
                        if (conditionToAdd.getArgumentCounter() == 1) {
                            neighborPassedTest = conditionToAdd.getFunc1().apply(neighbor);
                        } else if (conditionToAdd.getArgumentCounter() == 2) {
                            neighborPassedTest = conditionToAdd.getFunc2().apply(this, neighbor);
                        } else if (conditionToAdd.getArgumentCounter() == 3) {
                            neighborPassedTest = conditionToAdd.getFunc3().apply(this.getPreviousNode(), this, neighbor);
                        }

                        if (neighborPassedTest) {
                            if (!(openSet.contains(neighbor) || closedSet.contains(neighbor))) {
                                neighbors.add(neighbor);
                            } else {
                                if (openSet.stream().anyMatch(node -> node.equals(neighbor))) {
                                    IAstarNode found = openSet.stream().filter(node -> node.equals(neighbor)).findFirst().get();
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
                    }
                    } catch(ArrayIndexOutOfBoundsException | AstarNodeNotOnGridException AIOOBE){


                }


                }

        }


    }

    @Override
    public String toString() {
        return String.format("X: %d, Y: %d, cost: %d, obstacleVal: %d", this.x, this.y, this.cost,this.obstacleValue);
    }
}
