package examples;

import core.Node.AstarNode;
import core.PathFinding.AstarPathFinder;
import core.CustomExceptions.AstarNodeNotOnGridException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.Grid.AstarGrid;
import core.Plot.AstarPlot;
import core.interfaces.IAstarGrid;
import core.interfaces.IAstarNode;
import core.interfaces.IAstarPathFinder;
import core.interfaces.IAstarPlot;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class SimpleExample extends Application {
    private static final int COLS = 160;
    private static final int ROWS = 90;
    private static final int OBSTACLE_CHANCE = 30;
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
                        node = new AstarNode(i, j, 999);
                    }
                }
                grid.get(i).add(node);
            }
        }


        try {
            IAstarGrid astarGrid = new AstarGrid(grid);
            IAstarPathFinder pathFinder = new AstarPathFinder(astarGrid.getNode(0, 0), astarGrid.getNode(COLS - 1, ROWS - 1), astarGrid);
            pathFinder.findPath(node -> node.getObstacleValue() == 0, 0);
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
