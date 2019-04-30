package examples;

import core.CustomExceptions.AstarGridFactoryIllegalArgumentException;
import core.CustomExceptions.AstarNodeNotOnGridException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.FunctionalTesting.FunctionalTest;
import core.Grid.AstarGridFactory;
import core.Interfaces.*;
import core.PathFinding.AstarPathFinder;
import core.Plot.AstarPlot;
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
            IAstarGridFactoryResult result = AstarGridFactory.createRandomGrid(MAX_COLS, MAX_ROWS, MAX_OBSTACLE_CHANCE,2,true);
            IAstarGrid astarGrid = result.getGrid();
            IAstarPathFinder pathFinder = new AstarPathFinder(result);
            IFunctionalTest functionalTest = new FunctionalTest();
            functionalTest.setFunc2((current,neighbor) -> Math.abs(current.getObstacleValue() - neighbor.getObstacleValue()) <= 1);
            pathFinder.findPath(functionalTest, 0);
            IAstarPlot plot = new AstarPlot(astarGrid);
            primaryStage.setMaximized(true);
            primaryStage.setScene(plot.drawPath(pathFinder));
            primaryStage.titleProperty().setValue(String.format("Total nodes in path: %d", pathFinder.getOptimalPath().size()));
            primaryStage.show();


        } catch (AstarPathNotFoundException APNFE) {
            System.out.println("Path not found");
        } catch (AstarNodeNotOnGridException ANNOGE) {

        } catch (AstarGridFactoryIllegalArgumentException AGFIAE){

        }
    }


}
