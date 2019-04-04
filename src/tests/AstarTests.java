package tests;
import core.AstarNode;
import core.AstarPathFinder;
import core.CustomExceptions.AstarNodeNotOnGridException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.Grid.AstarGrid;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;
public class AstarTests {
    private AstarGrid grid;
    private AstarPathFinder pathFinder;
    private AstarNode start;
    private AstarNode end;
    private Random random = new Random();


    @Before
    public void SetUp(){
        int cols = random.nextInt(10) +1;
        int rows = random.nextInt(10) +1;
        ArrayList<ArrayList<AstarNode>> grid = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j <rows; j++) {
                AstarNode node = new AstarNode(i, j, 0);
                grid.get(i).add(node);
            }
        }
        this.grid = new AstarGrid(grid);
        while (start == null || end == null){
            try {
                if (start == null) {
                    start = this.grid.getNode(random.nextInt(10) + 1, random.nextInt(10) + 1);
                }
                if (end == null) {
                    end = this.grid.getNode(random.nextInt(10) + 1, random.nextInt(10) + 1);
                }
                if (start.equals(end)){
                    start = null;
                    end = null;
                }
            }catch (AstarNodeNotOnGridException e){

            }catch (ArrayIndexOutOfBoundsException e){

            }
        }
        this.pathFinder = new AstarPathFinder(start,end,this.grid);
    }

    @Test
    public void TestPathFinding(){
        try {
            pathFinder.findPath(astarNode -> astarNode.getObstacleValue() == 0,0);
            Assert.assertNotNull(pathFinder.getOptimalPath());
        }catch (AstarPathNotFoundException e){
            Assert.fail("Couldn't find a path with no obstacles");
        }
    }

    @Test (expected = AstarNodeNotOnGridException.class)
    public void TestNodeNotOnGrid() throws AstarNodeNotOnGridException{
            AstarNode notValid = grid.getNode(999,999);
            Assert.fail("Node is not on the grid!!!");
    }

    @Test (expected = AstarPathNotFoundException.class)
    public void TestPathNotFound() throws AstarPathNotFoundException{
        for (AstarNode[] col: grid.getGrid()) {
            for (AstarNode node: col) {
                if (node != null)
                node.setObstacleValue(999);
            }
        }
        pathFinder.findPath(astarNode -> astarNode.getObstacleValue() == 0,0);
    }
}
