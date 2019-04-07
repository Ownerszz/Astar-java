package core.interfaces;

import javafx.scene.Scene;

public interface IAstarPlot {
    /*
        Method drawPath:
        Method that gets called for drawing the grid and the path.
                                                                    Returns:    - A Scene made from an instance of AstarPlot

                                                                    Parameters: - A AstarPathFinder instance
         */
    Scene drawPath(IAstarPathFinder pathFinder);
}
