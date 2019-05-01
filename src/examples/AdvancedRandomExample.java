package examples;

import core.CustomExceptions.AstarGridFactoryIllegalArgumentException;
import core.CustomExceptions.AstarPathFinderFactoryIllegalArgumentException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.FunctionalTesting.FunctionalTestFactory;
import core.Grid.AstarGridFactory;
import core.Interfaces.*;
import core.PathFinding.AstarPathFinderFactory;
import core.Plot.AstarPlotFactory;
import javafx.application.Application;
import javafx.stage.Stage;

/*
 * In this example we use a IFunc2 to check whether the difference in the obstaclevalue is less than 1.
 * This example simulates walls and climbable/jumpable walls.
 * Walls are placed at random and their value will be between 1,2:
 *                                                                   - 1 is wall of "height" 1
 *                                                                   - 2 is wall of "height" 2
 * You can see that there will never be a jump from 0 to 2 or vice versa.
 *
 * */



public class AdvancedRandomExample extends Application {
    private static final int MAX_COLS = 160;
    private static final int MAX_ROWS = 90;
    private static final int MAX_OBSTACLE_CHANCE = 50;

    @Override
    public void start(Stage primaryStage) {
        try {
            IAstarGridFactoryResult astarGridFactoryResult = AstarGridFactory.createRandomGrid(MAX_COLS, MAX_ROWS, MAX_OBSTACLE_CHANCE,2,true);
            IFunctionalTest functionalTest = FunctionalTestFactory.createFunctionalTest((current, neighbor) -> Math.abs(current.getObstacleValue() - neighbor.getObstacleValue()) <= 1);
            IAstarPathFinderFactoryResult astarPathFinderFactoryResult = AstarPathFinderFactory.createPathFinder(astarGridFactoryResult,functionalTest);
            primaryStage.setMaximized(true);
            primaryStage.setScene(AstarPlotFactory.createAstarPlot(astarPathFinderFactoryResult));
            primaryStage.titleProperty().setValue(String.format("Total nodes in path: %d", astarPathFinderFactoryResult.getOptimalPath().size()));
            primaryStage.show();
        } catch (AstarPathNotFoundException APNFE) {
            System.out.println("Path not found");
        } catch (AstarPathFinderFactoryIllegalArgumentException | AstarGridFactoryIllegalArgumentException APFFIAE) {

        }
    }


}
