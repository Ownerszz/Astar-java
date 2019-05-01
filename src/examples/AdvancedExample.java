package examples;

import core.CustomExceptions.AstarGridFactoryIllegalArgumentException;
import core.CustomExceptions.AstarNodeNotOnGridException;
import core.CustomExceptions.AstarPathFinderFactoryIllegalArgumentException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.FunctionalTesting.FunctionalTest;
import core.FunctionalTesting.FunctionalTestFactory;
import core.Grid.AstarGridFactory;
import core.Interfaces.*;
import core.Node.AstarNode;
import core.Node.AstarNodeFactory;
import core.PathFinding.AstarPathFinder;
import core.PathFinding.AstarPathFinderFactory;
import core.Plot.AstarPlot;
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



public class AdvancedExample extends Application {
    private static final int COLS = 160;
    private static final int ROWS = 90;
    private static final int OBSTACLE_CHANCE = 50;

    @Override
    public void start(Stage primaryStage) {
        try {
            IAstarGridFactoryResult astarGridFactoryResult = AstarGridFactory.createGrid(AstarNodeFactory.createNode(0,0), AstarNodeFactory.createNode(COLS-1,ROWS-1),COLS,ROWS,OBSTACLE_CHANCE,2);
            IFunctionalTest functionalTest = FunctionalTestFactory.createFunctionalTest((current,neighbor) -> Math.abs(current.getObstacleValue() - neighbor.getObstacleValue()) <= 1);
            IAstarPathFinderFactoryResult astarPathFinderFactoryResult = AstarPathFinderFactory.createPathFinder(astarGridFactoryResult,functionalTest);

            primaryStage.setMaximized(true);
            primaryStage.setScene(AstarPlotFactory.createAstarPlot(astarPathFinderFactoryResult));
            primaryStage.titleProperty().setValue(String.format("Total nodes in path: %d", astarPathFinderFactoryResult.getOptimalPath().size()));
            primaryStage.show();


        } catch (AstarPathNotFoundException APNFE) {
            System.out.println("Path not found");
        }  catch (AstarGridFactoryIllegalArgumentException AGFIAE){

        } catch (AstarPathFinderFactoryIllegalArgumentException APFFIAE){

        }
    }


}
