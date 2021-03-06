package examples;

import core.CustomExceptions.AstarGridFactoryIllegalArgumentException;
import core.CustomExceptions.AstarPathFinderFactoryIllegalArgumentException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.FunctionalTesting.FunctionalTestFactory;
import core.Grid.AstarGridFactory;
import core.Interfaces.*;
import core.Node.AstarNodeFactory;
import core.PathFinding.AstarPathFinderFactory;
import core.Plot.AstarPlotFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class NoDiagonalMovesExample extends Application {
    private static final int COLS = 160;
    private static final int ROWS = 90;
    private static final int OBSTACLE_CHANCE = 20;
    /*
     * In this example we use a IFunc1 to check the neighbor is a wall.
     * This example simulates simple walls.
     * Walls are placed at random and their value will be bigger than 0:
     * You can see that there will never be a jump from 0 to a wall.
     *
     * */
    @Override
    public void start(Stage primaryStage) {
        try {
            //Create a FunctionalTest instance
            IFunctionalTest functionalTest = FunctionalTestFactory.createFunctionalTest((node) -> node.getObstacleValue() == 0);
            IAstarGridFactoryResult result = AstarGridFactory.createGrid(AstarNodeFactory.createNode(0,0),AstarNodeFactory.createNode(COLS-1,ROWS-1),COLS,ROWS,OBSTACLE_CHANCE,999);

            //Create a AstarPathFinderFactoryResult which will contain a IAstarGridFactoryResult equivalent, the optimalPath, the pathfinder.
            IAstarPathFinderFactoryResult astarPathFinderFactoryResult = AstarPathFinderFactory.createPathFinder(result,functionalTest, 0,false);
            primaryStage.setMaximized(true);
            primaryStage.setScene(AstarPlotFactory.createAstarPlot(astarPathFinderFactoryResult));
            //Show the node count in the optimalPath. The optimalPath comes from the astarPathFinderFactoryResult.
            primaryStage.titleProperty().setValue(String.format("Grid of size (%d x %d) .Total nodes in path: %d",astarPathFinderFactoryResult.getCols(),astarPathFinderFactoryResult.getRows() ,astarPathFinderFactoryResult.getOptimalPath().size()));
            primaryStage.show();

        } catch (AstarPathNotFoundException APNFE) {
            System.out.println("Path not found");
        }catch (AstarPathFinderFactoryIllegalArgumentException | AstarGridFactoryIllegalArgumentException APFFIAE){

        }
    }


}
