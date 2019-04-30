package tests;

import core.CustomExceptions.AstarGridFactoryIllegalArgumentException;
import core.CustomExceptions.AstarNodeNotOnGridException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.FunctionalTesting.FunctionalTest;
import core.Grid.AstarGridFactory;
import core.Interfaces.*;

import core.Node.AstarNodeFactory;
import core.PathFinding.AstarPathFinder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AstarTests {
    private IAstarGrid grid;
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
                IAstarGridFactoryResult result = AstarGridFactory.createRandomGrid(cols,rows,30,999,false);
                this.grid = result.getGrid();
                this.start = result.getStart();
                this.end = result.getEnd();

                this.pathFinder = new AstarPathFinder(result);
                oneTimeSetUp = true;
            }catch (AstarNodeNotOnGridException e){

            }catch (AstarGridFactoryIllegalArgumentException e){

            }


        }
    }

    @Test
    public void TestPathFinding() {
        try {
            IFunctionalTest functionalTest = new FunctionalTest();
            functionalTest.setFunc1((node) -> node.getObstacleValue() == 0);
            pathFinder.findPath(functionalTest, 0);
            System.out.println("Cols: "+ cols );
            System.out.println("Rows: "+ rows);
            System.out.println("Start: "+start);
            System.out.println("End: " +end);
            pathFinder.getOptimalPath().forEach(System.out::println);
            Assert.assertNotNull(pathFinder.getOptimalPath());
        } catch (AstarPathNotFoundException e) {
            Assert.fail("Couldn't find a path with no obstacles");
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
        IFunctionalTest functionalTest = new FunctionalTest();
        functionalTest.setFunc1((node) -> node.getObstacleValue() == 0);
        pathFinder.findPath(functionalTest, 0);
    }

    @Test(expected = AstarNodeNotOnGridException.class)
    public void TestBadStartOrEndNode() throws AstarNodeNotOnGridException{
        IFunctionalTest functionalTest = new FunctionalTest();
        functionalTest.setFunc1((node) -> node.getObstacleValue() == 0);
        pathFinder = new AstarPathFinder(AstarNodeFactory.createNode(999,999),end,grid);
    }
}
