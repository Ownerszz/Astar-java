package core.Interfaces;
import core.Interfaces.IAstarNode;

import core.CustomExceptions.AstarNodeNotOnGridException;

public interface IAstarGrid {
    IAstarNode getNode(int x, int y) throws AstarNodeNotOnGridException;

    IAstarNode[][] getGrid();
}
