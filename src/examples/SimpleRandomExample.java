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
            IAstarGridFactoryResult result =  AstarGridFactory.createRandomGrid(MAX_COLS, MAX_ROWS, MAX_OBSTACLE_CHANCE,999,false);
            IAstarGrid astarGrid = result.getGrid();
            IAstarPathFinder pathFinder = new AstarPathFinder(result);
            IFunctionalTest functionalTest = new FunctionalTest();
            functionalTest.setFunc1((node) -> node.getObstacleValue() == 0);
            pathFinder.findPath(functionalTest, 0);
            IAstarPlot plot = new AstarPlot(astarGrid);
            primaryStage.setMaximized(true);
            primaryStage.setScene(plot.drawPath(pathFinder));
            primaryStage.titleProperty().setValue(String.format("Total nodes in path: %d", pathFinder.getOptimalPath().size()));
            primaryStage.show();


        } catch (AstarPathNotFoundException APNFE) {
            System.out.println("Path not found");
        } catch (AstarNodeNotOnGridException ANNOGE) {

        }catch (AstarGridFactoryIllegalArgumentException AGFIAE){

        }
    }


}
