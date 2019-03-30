package core;

import core.Grid.AstarGrid;
import core.Grid.AstarGridSpot;

import java.util.ArrayList;
import java.util.function.Predicate;

public class AstarNode extends AstarGridSpot {
    private int cost;
    private AstarGridSpot end;
    private int heuristic;
    public ArrayList<AstarNode> neighbors = new ArrayList<>();
    public AstarNode(int x,int y, int cost,AstarGridSpot end){
        super(x,y);
        this.heuristic = Math.abs(x - end.getX()) + Math.abs(y - end.getY());
        this.cost = cost + 1 + heuristic;
        this.end = end;
    }

    @Override
    public boolean equals(Object obj) {
        AstarNode in = (AstarNode) obj;
        return super.getX() == in.getX() && super.getY() == in.getY();
    }

    public void addNeighbors(AstarGrid grid, AstarGridSpot end , ArrayList<AstarNode> openSet, ArrayList<AstarNode> closedSet, Predicate<AstarGridSpot> conditionToAdd){

                        AstarNode neighbor = new AstarNode(i,j,cost,end);
                        if (!openSet.contains(neighbor) || !closedSet.contains(neighbor)) {
                            neighbors.add(neighbor);
                        }else {
                            AstarNode found = openSet.stream().filter(node -> node.equals(neighbor)).findFirst().get();
                            if (neighbor.cost < found.cost){
                                found.cost = neighbor.cost;
                            }
                        }
                    }


    }
