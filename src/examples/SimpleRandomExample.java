package examples;

import core.CustomExceptions.AstarGridFactoryIllegalArgumentException;
import core.CustomExceptions.AstarNodeNotOnGridException;
import core.CustomExceptions.AstarPathFinderFactoryIllegalArgumentException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.FunctionalTesting.FunctionalTest;
import core.FunctionalTesting.FunctionalTestFactory;
import core.Grid.AstarGridFactory;
import core.Interfaces.*;
import core.PathFinding.AstarPathFinder;
import core.PathFinding.AstarPathFinderFactory;
import core.Plot.AstarPlot;
import core.Plot.AstarPlotFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class SimpleRandomExample extends Application {
    private static final int MAX_COLS = 160;
    private static final int MAX_ROWS = 90;
    private static final int MAX_OBSTACLE_CHANCE = 30;
    /*
     * In this example we use a IFunc1 to check the neighbor is a wall.
     * This example simulates simple walls.
     * Walls are placed at random and their value will be 1:
     * You can see that there will never be a jump from 0 to a wall.
     *
     * */
    @Override
    public void start(Stage primaryStage) {
        try {
            IAstarGridFactoryResult astarGridFactoryResult =  AstarGridFactory.createRandomGrid(MAX_COLS, MAX_ROWS, MAX_OBSTACLE_CHANCE,999,false);
            IFunctionalTest functionalTest = FunctionalTestFactory.createFunctionalTest((node) -> node.getObstacleValue() == 0);
            IAstarPathFinderFactoryResult astarPathFinderFactoryResult = AstarPathFinderFactory.createPathFinder(astarGridFactoryResult,functionalTest);

            primaryStage.setMaximized(true);
            primaryStage.setScene(AstarPlotFactory.createAstarPlot(astarPathFinderFactoryResult));
            primaryStage.titleProperty().setValue(String.format("Total nodes in path: %d", astarPathFinderFactoryResult.getOptimalPath().size()));
            primaryStage.show();
        } catch (AstarPathNotFoundException APNFE) {
            System.out.println("Path not found");
        } catch (AstarPathFinderFactoryIllegalArgumentException APFFIAE) {

        }catch (AstarGridFactoryIllegalArgumentException AGFIAE){

        }
    }


}
