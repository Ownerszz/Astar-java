package core.Plot;

import core.Interfaces.IAstarGrid;
import core.Interfaces.IAstarPathFinder;
import core.Interfaces.IAstarPathFinderFactoryResult;
import core.Interfaces.IAstarPlot;
import javafx.scene.Scene;

public final class AstarPlotFactory {
    public static Scene createAstarPlot(IAstarGrid grid, IAstarPathFinder pathFinder){
        IAstarPlot astarPlot = new AstarPlot();
        return astarPlot.drawPath(grid,pathFinder);
    }
    public static Scene createAstarPlot(IAstarPathFinderFactoryResult result) {
        IAstarPlot astarPlot = new AstarPlot();
        return astarPlot.drawPath(result.getGrid(),result.getPathFinder());
    }

}
