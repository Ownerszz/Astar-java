package tests;

import core.CustomExceptions.AstarGridFactoryIllegalArgumentException;
import core.CustomExceptions.AstarNodeNotOnGridException;
import core.CustomExceptions.AstarPathFinderFactoryIllegalArgumentException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.FunctionalTesting.FunctionalTest;
import core.FunctionalTesting.FunctionalTestFactory;
import core.Grid.AstarGridFactory;
import core.Interfaces.*;

import core.Node.AstarNodeFactory;
import core.PathFinding.AstarPathFinder;
import core.PathFinding.AstarPathFinderFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AstarTests {
    private IAstarGrid grid;
    private IAstarGridFactoryResult result;
    private IAstarPathFinder pathFinder;
    private IAstarNode start;
    private IAstarNode end;
    private boolean oneTimeSetUp;
    private int rows;
    private int cols;

    @Before
    public void SetUp() {
        if (!oneTimeSetUp) {

            try {
                cols = 20;
                rows = 20;
                this.result = AstarGridFactory.createRandomGrid(cols,rows,30,999,false);
                this.grid = result.getGrid();
                this.start = result.getStart();
                this.end = result.getEnd();

                oneTimeSetUp = true;
            }catch (AstarGridFactoryIllegalArgumentException e){

            }


        }
    }

    @Test
    public void TestPathFinding() {
        try {
            IFunctionalTest functionalTest = FunctionalTestFactory.createFunctionalTest((node) -> node.getObstacleValue() == 0);
            IAstarPathFinderFactoryResult astarPathFinderFactoryResult = AstarPathFinderFactory.createPathFinder(this.result,functionalTest);
            System.out.println("Cols: "+ cols );
            System.out.println("Rows: "+ rows);
            System.out.println("Start: "+start);
            System.out.println("End: " +end);
            astarPathFinderFactoryResult.getOptimalPath().forEach(System.out::println);
            Assert.assertNotNull(astarPathFinderFactoryResult.getOptimalPath());
        } catch (AstarPathNotFoundException e) {
            Assert.fail("Couldn't find a path with no obstacles");
        }catch (AstarPathFinderFactoryIllegalArgumentException e){

        }
    }

    @Test(expected = AstarNodeNotOnGridException.class)
    public void TestNodeNotOnGrid() throws AstarNodeNotOnGridException {
        IAstarNode notValid = grid.getNode(999, 999);
        Assert.fail(String.format("Node (x: %d, y: %d) is not on the grid!!!", notValid.getX(), notValid.getY()));
    }

    @Test(expected = AstarPathNotFoundException.class)
    public void TestPathNotFound() throws AstarPathNotFoundException {
        for (IAstarNode[] col : grid.getGrid()) {
            for (IAstarNode node : col) {
                if (node != null)
                    node.setObstacleValue(999);
            }
        }
        try {
            IFunctionalTest functionalTest = FunctionalTestFactory.createFunctionalTest((node) -> node.getObstacleValue() == 0);
            pathFinder = AstarPathFinderFactory.createPathFinder(result,functionalTest).getPathFinder();
        } catch (AstarPathFinderFactoryIllegalArgumentException e){

        }
        Assert.fail("Path was found but shouldn't be possible");

    }

    @Test(expected = AstarNodeNotOnGridException.class)
    public void TestBadStartOrEndNode() throws AstarNodeNotOnGridException{
        try {
            IFunctionalTest functionalTest = FunctionalTestFactory.createFunctionalTest((node) -> node.getObstacleValue() == 0);
            pathFinder = AstarPathFinderFactory.createPathFinder(grid,AstarNodeFactory.createNode(999,999),end,functionalTest).getPathFinder();
        }catch (AstarPathFinderFactoryIllegalArgumentException e){
            throw new AstarNodeNotOnGridException();
        }catch (AstarPathNotFoundException e){
            Assert.fail("Not failed on validation");
        }

    }
}
