package examples;

import core.AstarNode;
import core.AstarPathFinder;
import core.CustomExceptions.AstarPathNotFoundException;
import core.Grid.AstarGrid;
import core.Plot.AstarPlot;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class SimpleExample extends Application {
    private static final int COLS = 35;
    private static final int ROWS = 35;
    private static final int OBSTACLE_CHANCE = 30;
    private static Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        ArrayList<ArrayList<AstarNode>> grid = new ArrayList<>();
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
        AstarGrid astarGrid = new AstarGrid(grid);
        AstarPathFinder pathFinder = new AstarPathFinder(astarGrid.getNode(0, 0), astarGrid.getNode(ROWS - 1, COLS - 1), astarGrid);
        try {
            pathFinder.findPath(node -> node.getObstacleValue() == 0, 0);
            AstarPlot plot = new AstarPlot(astarGrid);
            plot.drawPath(pathFinder);
            primaryStage.setMaximized(true);
            primaryStage.setScene(plot.drawPath(pathFinder));
            primaryStage.show();

        } catch (AstarPathNotFoundException APNFE) {
            System.out.println("Path not found");
        }
    }


}
