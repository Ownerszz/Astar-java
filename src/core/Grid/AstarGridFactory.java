package core.Grid;

import core.CustomExceptions.AstarGridFactoryIllegalArgumentException;
import core.CustomExceptions.AstarNodeFactoryIllegalArgumentException;
import core.CustomExceptions.AstarNodeNotOnGridException;
import core.Interfaces.IAstarGridFactoryResult;
import core.Interfaces.IAstarNode;
import core.Node.AstarNodeFactory;

import java.util.*;

public final class AstarGridFactory {
    public static IAstarGridFactoryResult createGrid(IAstarNode startNode, IAstarNode endNode, int columns, int rows, int obstacleChance, int maxObstacleValue) throws AstarGridFactoryIllegalArgumentException{
        if (columns < 0 || rows < 0 || obstacleChance < 0){
            throw new AstarGridFactoryIllegalArgumentException();
        }

       Random random = new Random();
        ArrayList<ArrayList<IAstarNode>> grid = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < rows; j++) {
                IAstarNode node = AstarNodeFactory.createNode(i,j);
                if (node.equals(startNode)) {
                    node = startNode;
                }else if ( node.equals(endNode)){
                    node = endNode;
                }else {
                    if (random.nextInt(100) < obstacleChance) {
                            node = AstarNodeFactory.createNode(i, j, random.nextInt(maxObstacleValue+1));
                    }
                }
                grid.get(i).add(node);
            }
        }
        return new AstarGridFactoryResult(new AstarGrid(grid),startNode,endNode,columns,rows,obstacleChance);
    }

    public static IAstarGridFactoryResult createRandomGrid(int maxColumns, int maxRows, int maxObstacleChance, int maxObstacleValue, boolean allowStartAndEndWithObstacleValues) throws AstarGridFactoryIllegalArgumentException{
        if (maxColumns < 0 || maxRows < 0 || maxObstacleChance < 0){
            throw new AstarGridFactoryIllegalArgumentException();
        }

        Random random = new Random();
        int cols = random.nextInt(maxColumns);
        int rows = random.nextInt(maxRows);
        IAstarNode startNode = AstarNodeFactory.createNode(0,0);
        IAstarNode endNode = AstarNodeFactory.createNode(0,0);

        int obstacleChance = random.nextInt(maxObstacleChance);
        while (startNode.equals(endNode)){
            startNode= initialiseRandomNode(cols,rows,maxObstacleValue,allowStartAndEndWithObstacleValues);
            endNode = initialiseRandomNode(cols,rows,maxObstacleValue,allowStartAndEndWithObstacleValues);
        }
        return createGrid(startNode,endNode,cols,rows,obstacleChance,maxObstacleValue);
    }
    private static IAstarNode initialiseRandomNode(int cols, int rows,int maxObstacleValue , boolean allowStartAndEndWithObstacleValues) throws AstarGridFactoryIllegalArgumentException{
        try {
            if (allowStartAndEndWithObstacleValues){
                return AstarNodeFactory.createRandomNode(cols,rows,maxObstacleValue);
            }else {
                return AstarNodeFactory.createRandomNode(cols,rows,0);
            }
        }catch (AstarNodeFactoryIllegalArgumentException ANFIAE){
            throw new AstarGridFactoryIllegalArgumentException();
        }


    }


    public static IAstarGridFactoryResult createGrid(ArrayList<ArrayList<Boolean>> booleanGrid, IAstarNode startNode, IAstarNode endNode) throws AstarGridFactoryIllegalArgumentException{
        Boolean[][] grid =  booleanGrid.stream().map(u -> u.toArray(new Boolean[booleanGrid.size()])).toArray(Boolean[][]::new);
        return createGrid(grid,startNode,endNode);
    }
    public static IAstarGridFactoryResult createGrid(Boolean[][] booleanGrid, IAstarNode startNode, IAstarNode endNode) throws AstarGridFactoryIllegalArgumentException{
        int cols = calculateCols(booleanGrid);
        int rows = calculateRows(booleanGrid);
        IAstarGridFactoryResult result = createGrid(startNode,endNode,cols,rows,0,0);
        for (int i = 0; i < cols ; i++) {
            for (int j = 0; j < rows; j++) {
                if (i == cols - 1){
                    int a = 1;
                }
                if (booleanGrid[i][j]){
                    try {
                        result.getGrid().getNode(i,j).setObstacleValue(1);
                    }catch (AstarNodeNotOnGridException ANNOGE){
                        throw new AstarGridFactoryIllegalArgumentException();
                    }
                }
            }
        }
        return result;
    }
    private static int calculateCols(Boolean[][] grid){
        return (int) Arrays.stream(grid).filter(Objects::nonNull).count();
    }
    private static int calculateRows(Boolean[][]  grid){
        return (int) Arrays.stream(grid[0]).filter(Objects::nonNull).count();
    }
}
