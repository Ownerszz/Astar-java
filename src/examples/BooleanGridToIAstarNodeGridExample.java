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

import java.util.ArrayList;
import java.util.Random;

public class BooleanGridToIAstarNodeGridExample extends Application {
    private static final int COLS = 160;
    private static final int ROWS = 90;
    private static final int OBSTACLE_CHANCE = 30;
    private static final Random random = new Random();
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
            ArrayList<ArrayList<Boolean>> booleanGrid = new ArrayList<>();
            for (int i = 0; i < COLS ; i++) {
                booleanGrid.add(new ArrayList<>());
                for (int j = 0; j < ROWS; j++) {
                    if ((i == 0 && j == 0) || (i == COLS-1 && j == ROWS -1)) {
                        booleanGrid.get(i).add(Boolean.FALSE);
                    } else {
                        if (random.nextInt(100) < OBSTACLE_CHANCE) {
                            booleanGrid.get(i).add(Boolean.TRUE);
                        } else {
                            booleanGrid.get(i).add(Boolean.FALSE);
                        }
                    }
                }
            }
            IAstarGridFactoryResult astarGridFactoryResult = AstarGridFactory.createGrid(booleanGrid,AstarNodeFactory.createNode(0,0),AstarNodeFactory.createNode(COLS-1,ROWS-1));
            //Create a FunctionalTest instance
            IFunctionalTest functionalTest = FunctionalTestFactory.createFunctionalTest((node) -> node.getObstacleValue() == 0);
            //Create a AstarPathFinderFactoryResult which will contain a IAstarGridFactoryResult equivalent, the optimalPath, the pathfinder.
            IAstarPathFinderFactoryResult astarPathFinderFactoryResult = AstarPathFinderFactory.createPathFinder(astarGridFactoryResult,functionalTest);
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
