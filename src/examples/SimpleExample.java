package examples;

import core.CustomExceptions.AstarGridFactoryIllegalArgumentException;
import core.CustomExceptions.AstarNodeNotOnGridException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.FunctionalTesting.FunctionalTest;
import core.Grid.AstarGridFactory;
import core.Interfaces.*;
import core.Node.AstarNodeFactory;
import core.PathFinding.AstarPathFinder;
import core.Plot.AstarPlot;
import javafx.application.Application;
import javafx.stage.Stage;

public class SimpleExample extends Application {
    private static final int COLS = 160;
    private static final int ROWS = 90;
    private static final int OBSTACLE_CHANCE = 30;
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
            IAstarGridFactoryResult result =  AstarGridFactory.createGrid(AstarNodeFactory.createNode(0,0),AstarNodeFactory.createNode(COLS-1,ROWS-1),COLS,ROWS,OBSTACLE_CHANCE,999);
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
