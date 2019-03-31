package core;

import core.Grid.AstarGrid;
import core.Grid.AstarGridSpot;

import java.util.ArrayList;
import java.util.function.Predicate;

public class AstarNode extends AstarGridSpot {
    private int cost;
    private AstarGridSpot end;
    private int heuristic;
    private int obstacleValue;
    public ArrayList<AstarNode> neighbors = new ArrayList<>();

    public AstarNode(int x, int y, int cost, AstarGridSpot end) {
        super(x, y);
        this.heuristic = Math.abs(x - end.getX()) + Math.abs(y - end.getY());
        this.cost = cost + 1 + heuristic;
        this.end = end;
    }



    public void addNeighbors(AstarGrid grid, AstarGridSpot end, ArrayList<AstarNode> openSet, ArrayList<AstarNode> closedSet, Predicate<AstarGridSpot> conditionToAdd) {
        for (int i = this.getX() - 1; i < this.getX() + 1; i++) {
            for (int j = this.getY() - 1; j < this.getY() + 1; j++) {

                AstarNode neighbor = grid.getSpot(i,j);

                if (!openSet.contains(neighbor) || !closedSet.contains(neighbor)) {
                    if (conditionToAdd.test(neighbor)){
                        neighbors.add(neighbor);
                    }
                } else {
                    AstarNode found = openSet.stream().filter(node -> node.equals(neighbor)).findFirst().get();
                    if (neighbor.cost < found.cost) {
                        found.cost = neighbor.cost;
                    }else {
                        found.cost = neighbor.cost;
                    }
                }
            }
        }

    }


}
