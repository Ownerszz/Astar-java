package examples;

import core.CustomExceptions.AstarNodeNotOnGridException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.Grid.AstarGrid;
import core.FunctionalTesting.FunctionalTest;
import core.Interfaces.*;
import core.Node.AstarNode;
import core.PathFinding.AstarPathFinder;
import core.Plot.AstarPlot;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class AdvancedExample extends Application {
    private static final int COLS = 160;
    private static final int ROWS = 90;
    private static final int OBSTACLE_CHANCE = 80;
    private static Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        ArrayList<ArrayList<IAstarNode>> grid = new ArrayList<>();
        for (int i = 0; i < COLS; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < ROWS; j++) {
                AstarNode node = new AstarNode(i, j, 0);
                if (random.nextInt(100) < OBSTACLE_CHANCE) {
                    if (i == 0 && j == 0) {
                    } else if (i == COLS - 1 && j == ROWS - 1) {

                    } else {
                        node = new AstarNode(i, j, random.nextInt(3));
                    }
                }
                grid.get(i).add(node);
            }
        }


        try {
            IAstarGrid astarGrid = new AstarGrid(grid);
            IAstarPathFinder pathFinder = new AstarPathFinder(astarGrid.getNode(0, 0), astarGrid.getNode(COLS - 1, ROWS - 1), astarGrid);
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

        }
    }


}
