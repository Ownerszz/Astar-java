package core.Node;

import core.Interfaces.IAstarNode;

import java.util.Random;

public final class AstarNodeFactory {
    public static IAstarNode createNode(int x, int y){
        return createNode(x,y,0);
    }
    public static IAstarNode createNode(int x, int y, int obstacleValue){
        return new AstarNode(x,y,obstacleValue);
    }

    public static IAstarNode createRandomNode(int maxX, int maxY, int maxObstacleValue){
        Random random = new Random();
        return createNode(random.nextInt(maxX),random.nextInt(maxY),random.nextInt(maxObstacleValue+1));
    }
}
