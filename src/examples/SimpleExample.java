package examples;

import core.AstarNode;
import core.AstarPathFinder;
import core.CustomExceptions.AstarNodeNotOnGridException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.Grid.AstarGrid;
import core.Plot.AstarPlot;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class SimpleExample extends Application {
    private static final int COLS = 40;
    private static final int ROWS = 90;
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


        try {
            AstarGrid astarGrid = new AstarGrid(grid);
            AstarPathFinder pathFinder = new AstarPathFinder(astarGrid.getNode(0, 0), astarGrid.getNode(COLS-1, ROWS-1), astarGrid);
            pathFinder.findPath(node -> node.getObstacleValue() == 0, 0);
            AstarPlot plot = new AstarPlot(astarGrid);
            plot.drawPath(pathFinder);
            primaryStage.setMaximized(true);
            primaryStage.setScene(plot.drawPath(pathFinder));
            primaryStage.show();

        } catch (AstarPathNotFoundException APNFE) {
            System.out.println("Path not found");
        }catch (AstarNodeNotOnGridException ANNOGE){

        }
    }


}
