package tests;

import core.CustomExceptions.AstarNodeNotOnGridException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.Grid.AstarGrid;
import core.FunctionalTesting.FunctionalTest;
import core.Interfaces.IAstarGrid;
import core.Interfaces.IAstarNode;
import core.Interfaces.IAstarPathFinder;
import core.Interfaces.IFunctionalTest;
import core.Node.AstarNode;
import core.PathFinding.AstarPathFinder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class AstarTests {
    private IAstarGrid grid;
    private IAstarPathFinder pathFinder;
    private IAstarNode start;
    private IAstarNode end;
    private Random random = new Random();
    private boolean oneTimeSetUp;
    private int rows;
    private int cols;

    @Before
    public void SetUp() {
        if (!oneTimeSetUp) {
            while (cols == 0 || rows == 0){
                cols = random.nextInt(20);
                rows = random.nextInt(20);
            }
            ArrayList<ArrayList<IAstarNode>> grid = new ArrayList<>();
            for (int i = 0; i < cols; i++) {
                grid.add(new ArrayList<>());
                for (int j = 0; j < rows; j++) {
                    AstarNode node = new AstarNode(i, j, 0);
                    grid.get(i).add(node);
                }
            }
            this.grid = new AstarGrid(grid);
            while (start == null || end == null) {
                try {
                    if (start == null) {
                        start = this.grid.getNode(random.nextInt(cols), random.nextInt(rows));
                    }
                    if (end == null) {
                        end = this.grid.getNode(random.nextInt(cols), random.nextInt(rows));
                    }
                    if (start.equals(end)) {
                        start = null;
                        end = null;
                    }
                } catch (AstarNodeNotOnGridException e) {

                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
            try {
                this.pathFinder = new AstarPathFinder(start, end, this.grid);
            }catch (AstarNodeNotOnGridException e){

            }

            oneTimeSetUp = true;
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
        pathFinder = new AstarPathFinder(new AstarNode(999,999,999),end,grid);
    }
}
