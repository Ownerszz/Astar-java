package core.Interfaces;

import java.util.ArrayList;

public interface IAstarPathFinderFactoryResult extends IAstarGridFactoryResult {
    ArrayList<IAstarNode> getOptimalPath();

    IAstarPathFinder getPathFinder();

    @Override
    IAstarGrid getGrid();

    @Override
    IAstarNode getStart();

    @Override
    IAstarNode getEnd();

    @Override
    int getCols();

    @Override
    int getRows();

    @Override
    int getObstacleChance();
}
