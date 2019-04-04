package core.Plot;

import core.AstarNode;
import core.AstarPathFinder;
import core.CustomExceptions.AstarPathNotFoundException;
import core.Grid.AstarGrid;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AstarPlot extends BorderPane {
    private AstarGrid grid;
    private GridPane gridPane;

    public AstarPlot(AstarGrid grid) {
        super();
        this.grid = grid;
    }


    public Scene drawPath(AstarPathFinder pathFinder) {
        Label test = new Label("test");
        test.setAlignment(Pos.TOP_CENTER);
        test.setTextFill(Color.BLACK);
        gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        int xIndex = 0;
        int yIndex = 0;
        for (AstarNode[] x : grid.getGrid()) {
            for (AstarNode y : x) {
                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(20);
                rectangle.setWidth(20);
                rectangle.setStroke(Color.BLACK);
                if (grid.getNode(xIndex, yIndex).getObstacleValue() > 0) {
                    rectangle.setFill(Color.BLACK);
                } else {
                    rectangle.setFill(Color.WHITE);
                }

                gridPane.add(rectangle, xIndex, yIndex);
                yIndex++;
            }
            yIndex = 0;
            xIndex++;
        }
        try {
            for (AstarNode node : pathFinder.getOptimalPath()) {
                for (Node e : gridPane.getChildren()) {
                    if (e.hasProperties())
                        if (GridPane.getColumnIndex(e) == node.getX() && GridPane.getRowIndex(e) == node.getY()) {
                            Rectangle r = (Rectangle) e;
                            if (node.getObstacleValue() > 0) {
                                r.setFill(Color.PURPLE);
                            } else {
                                r.setFill(Color.GREEN);
                            }

                        }
                }
            }
        } catch (AstarPathNotFoundException a) {

        }

        return new Scene(gridPane);
    }


}
