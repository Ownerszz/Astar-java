package core.Plot;

import core.CustomExceptions.AstarNodeNotOnGridException;
import core.CustomExceptions.AstarPathNotFoundException;
import core.Interfaces.IAstarGrid;
import core.Interfaces.IAstarNode;
import core.Interfaces.IAstarPathFinder;
import core.Interfaces.IAstarPlot;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
Standard class. You can make your own class for doing this.
This class draws the 2D grid.
 */
public class AstarPlot extends BorderPane implements IAstarPlot {
    private GridPane gridPane;

    /*
    Constructor:
    New instance of AstarPlot will initialise with: - A AstarGrid
     */
    protected AstarPlot() {
        super();

    }

    /*
    Method drawPath:
    Method that gets called for drawing the grid and the path.
                                                                Returns:    - A Scene made from an instance of AstarPlot

                                                                Parameters: - A AstarPathFinder instance
     */
    @Override
    public Scene drawPath(IAstarGrid grid,IAstarPathFinder pathFinder) {
        gridPane = new GridPane();
        this.getChildren().add(gridPane);
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setGridLinesVisible(true);
        int xIndex = 0;
        int yIndex = 0;
        for (IAstarNode[] x : grid.getGrid()) {
            for (IAstarNode y : x) {
                try {
                    final int _indexX = xIndex;
                    final int _indexY = yIndex;
                    Rectangle rectangle = new Rectangle();
                    rectangle.setHeight(10);
                    rectangle.setWidth(10);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                IAstarNode clickedNode = grid.getNode(_indexX, _indexY);
                                String nodeIsInOptimalPath = new String();
                                if (pathFinder.getOptimalPath().contains(clickedNode)){
                                    nodeIsInOptimalPath = ". Node is in optimal path";
                                }

                                Alert alert = new Alert(Alert.AlertType.INFORMATION, clickedNode.toString() + nodeIsInOptimalPath);
                                alert.show();
                            } catch (AstarNodeNotOnGridException ANNOGE) {

                            }catch ( AstarPathNotFoundException APNFE){

                            }

                        }
                    });
                    if (grid.getNode(xIndex, yIndex).getObstacleValue() > 0) {
                        rectangle.setFill(Color.BLACK);
                    } else {
                        rectangle.setFill(Color.WHITE);
                    }


                    gridPane.add(rectangle, xIndex, yIndex);
                } catch (AstarNodeNotOnGridException ANNOGE) {

                }

                yIndex++;
            }
            yIndex = 0;
            xIndex++;
        }
        try {
            for (IAstarNode node : pathFinder.getOptimalPath()) {
                for (Node e : gridPane.getChildren()) {
                    if (e.hasProperties())
                        if (GridPane.getColumnIndex(e) == node.getX() && GridPane.getRowIndex(e) == node.getY()) {
                            Rectangle r = (Rectangle) e;
                            if (node.getObstacleValue() > 0) {
                                r.setFill(Color.PURPLE);
                            } else {
                                r.setFill(Color.GREEN);
                            }
                            if (node.equals(pathFinder.getStart())){
                                r.setFill(Color.DARKRED);
                            }
                            if (node.equals(pathFinder.getEnd())){
                                r.setFill(Color.DARKBLUE);
                            }

                        }
                }
            }
        } catch (AstarPathNotFoundException a) {

        }

        return new Scene(this);
    }


}
