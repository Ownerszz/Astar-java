package core.Grid;

import core.Interfaces.IAstarGrid;
import core.Interfaces.IAstarNode;

public class AstarGridFactoryResult implements core.Interfaces.IAstarGridFactoryResult {
    private IAstarGrid grid;
    private IAstarNode start;
    private IAstarNode end;
    private int cols;
    private int rows;
    private int obstacleChance;

    AstarGridFactoryResult(IAstarGrid grid, IAstarNode start, IAstarNode end, int cols, int rows, int obstacleChance){
        this.grid = grid;
        this.start = start;
        this.end = end;
        this.cols =cols;
        this.rows = rows;
        this.obstacleChance = obstacleChance;
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


}
