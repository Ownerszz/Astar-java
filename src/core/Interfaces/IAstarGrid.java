package core.Interfaces;

import core.CustomExceptions.AstarNodeNotOnGridException;

public interface IAstarGrid {
    IAstarNode getNode(int x, int y) throws AstarNodeNotOnGridException;

    IAstarNode getNode(IAstarNode node) throws  AstarNodeNotOnGridException;

    IAstarNode[][] getGrid();
}
