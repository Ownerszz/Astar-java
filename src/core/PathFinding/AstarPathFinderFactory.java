package core.PathFinding;

import core.CustomExceptions.AstarGridFactoryIllegalArgumentException;
import core.CustomExceptions.AstarNodeNotOnGridException;
import core.CustomExceptions.AstarPathFinderFactoryIllegalArgumentException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.Grid.AstarGridFactory;
import core.Interfaces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class AstarPathFinderFactory {
    public static IAstarPathFinderFactoryResult createPathFinder(IFunctionalTest functionalTest,IAstarNode start, IAstarNode end, int cols, int rows, int obstacleChance, int maxObstacleValue) throws AstarPathFinderFactoryIllegalArgumentException, AstarPathNotFoundException {
        return createPathFinder(functionalTest,start,end, cols, rows, obstacleChance, maxObstacleValue,0);
    }
    public static IAstarPathFinderFactoryResult createPathFinder(IFunctionalTest functionalTest,IAstarNode start, IAstarNode end, int cols, int rows, int obstacleChance, int maxObstacleValue ,int jumpUpTo)  throws AstarPathFinderFactoryIllegalArgumentException, AstarPathNotFoundException {
            try {
                IAstarGridFactoryResult gridFactoryResult = AstarGridFactory.createGrid(start,end,cols,rows,obstacleChance,maxObstacleValue);
                return createPathFinder(gridFactoryResult,functionalTest,jumpUpTo);
            }catch (AstarGridFactoryIllegalArgumentException AGFIAE){
                throw new AstarPathFinderFactoryIllegalArgumentException();
            }
    }

    public static IAstarPathFinderFactoryResult createPathFinder(IAstarGrid astarGrid,IAstarNode start, IAstarNode end, IFunctionalTest functionalTest) throws AstarPathFinderFactoryIllegalArgumentException, AstarPathNotFoundException {
       return createPathFinder(astarGrid,start,end,functionalTest,0);
    }
    public static IAstarPathFinderFactoryResult createPathFinder(IAstarGrid astarGrid,IAstarNode start, IAstarNode end, IFunctionalTest functionalTest,int jumpUpTo) throws AstarPathFinderFactoryIllegalArgumentException, AstarPathNotFoundException {
                try {
                    IAstarPathFinder pathFinder = new AstarPathFinder(start,end,astarGrid);
                    ArrayList<IAstarNode> optimalPath = buildOptimalPath(pathFinder,functionalTest,jumpUpTo);
                    return new AstarPathFinderFactoryResult(
                            pathFinder,
                            optimalPath,
                            astarGrid,
                            start,
                            end,
                            calculateCols(astarGrid),
                            calculateRows(astarGrid),
                            calculateObstacleChance(astarGrid)
                    );
                }catch (AstarNodeNotOnGridException ANNOGE){
                    throw new AstarPathFinderFactoryIllegalArgumentException();
                }

    }
    private static int calculateCols(IAstarGrid grid){
        return (int) Arrays.stream(grid.getGrid()).filter(Objects::nonNull).count() - 1;
    }
    private static int calculateRows(IAstarGrid grid){
        return (int) Arrays.stream(grid.getGrid()[0]).filter(Objects::nonNull).count() - 1;
    }
    private static int calculateObstacleChance(IAstarGrid grid){
        int amountOfObstacleNodes = 0;
        for (IAstarNode[] col: grid.getGrid()) {
          amountOfObstacleNodes +=  Arrays.stream(col).filter(node -> node.getObstacleValue() > 0).count();
        }
        return (amountOfObstacleNodes/(calculateCols(grid) * calculateRows(grid)))*100;
    }

    public static IAstarPathFinderFactoryResult createPathFinder(IAstarGridFactoryResult astarGridFactoryResult, IFunctionalTest functionalTest) throws AstarPathFinderFactoryIllegalArgumentException, AstarPathNotFoundException{
        return createPathFinder(astarGridFactoryResult, functionalTest,0);
    }
    public static IAstarPathFinderFactoryResult createPathFinder(IAstarGridFactoryResult astarGridFactoryResult, IFunctionalTest functionalTest, int jumpUpTo) throws AstarPathFinderFactoryIllegalArgumentException, AstarPathNotFoundException{
            try {
                IAstarPathFinder pathFinder = new AstarPathFinder(astarGridFactoryResult);
                ArrayList<IAstarNode> optimalPath = buildOptimalPath(pathFinder,functionalTest,jumpUpTo);
                return new AstarPathFinderFactoryResult(
                        pathFinder,
                        optimalPath,
                        astarGridFactoryResult.getGrid(),
                        astarGridFactoryResult.getStart(),
                        astarGridFactoryResult.getEnd(),
                        astarGridFactoryResult.getCols(),
                        astarGridFactoryResult.getRows(),
                        astarGridFactoryResult.getObstacleChance()
                );
            }catch (AstarNodeNotOnGridException ANNOGE){
                throw new AstarPathFinderFactoryIllegalArgumentException();
            }
    }
    private static ArrayList<IAstarNode> buildOptimalPath(IAstarPathFinder pathFinder,IFunctionalTest functionalTest, int jumpUpTo) throws AstarPathNotFoundException{
        pathFinder.findPath(functionalTest,jumpUpTo);
        return pathFinder.getOptimalPath();
    }
}
