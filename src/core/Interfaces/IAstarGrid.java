package Interfaces;

import CustomExceptions.AstarNodeNotOnGridException;

public interface IAstarGrid {
    IAstarNode getNode(int x, int y) throws AstarNodeNotOnGridException;

    IAstarNode[][] getGrid();
}
