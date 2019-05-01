package core.PathFinding;

import core.Interfaces.IAstarGrid;
import core.Interfaces.IAstarNode;
import core.Interfaces.IAstarPathFinder;
import core.Interfaces.IAstarPathFinderFactoryResult;

import java.util.ArrayList;

public class AstarPathFinderFactoryResult implements IAstarPathFinderFactoryResult {
    private IAstarPathFinder pathFinder;
    private ArrayList<IAstarNode> optimalPath;
    private IAstarGrid grid;
    private IAstarNode start;
    private IAstarNode end;
    private int cols;
    private int rows;
    private int obstacleChance;

    public AstarPathFinderFactoryResult(IAstarPathFinder pathFinder,ArrayList<IAstarNode> optimalPath, IAstarGrid grid, IAstarNode start, IAstarNode end, int cols, int rows, int obstacleChance) {
        this.pathFinder =pathFinder;
        this.optimalPath = optimalPath;
        this.grid = grid;
        this.start = start;
        this.end = end;
        this.cols = cols;
        this.rows = rows;
        this.obstacleChance = obstacleChance;
    }

    @Override
    public ArrayList<IAstarNode> getOptimalPath() {
        return optimalPath;
    }

    @Override
    public IAstarGrid getGrid() {
        return grid;
    }

    @Override
    public IAstarNode getStart() {
        return start;
    }

    @Override
    public IAstarNode getEnd() {
        return end;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getObstacleChance() {
        return obstacleChance;
    }

    @Override
    public IAstarPathFinder getPathFinder() {
        return pathFinder;
    }
}
