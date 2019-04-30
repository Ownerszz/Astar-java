package core.Node;

import core.CustomExceptions.AstarNodeFactoryIllegalArgumentException;
import core.Interfaces.IAstarNode;

import java.util.Random;

public final class AstarNodeFactory {
    public static IAstarNode createNode(int x, int y){
        return createNode(x,y,0);
    }
    public static IAstarNode createNode(int x, int y, int obstacleValue){
        return new AstarNode(x,y,obstacleValue);
    }

    public static IAstarNode createRandomNode(int maxX, int maxY, int maxObstacleValue) throws AstarNodeFactoryIllegalArgumentException {
        Random random = new Random();
        if (maxX < 0 || maxY < 0 || maxObstacleValue < 0){
            throw new AstarNodeFactoryIllegalArgumentException();
        }
        return createNode(random.nextInt(maxX),random.nextInt(maxY),random.nextInt(maxObstacleValue+1));
    }
}
