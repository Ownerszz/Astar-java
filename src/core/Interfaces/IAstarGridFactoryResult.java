package core.Interfaces;

public interface IAstarGridFactoryResult {
    IAstarGrid getGrid();

    IAstarNode getStart();

    IAstarNode getEnd();

    int getCols();

    int getRows();

    int getObstacleChance();
}
