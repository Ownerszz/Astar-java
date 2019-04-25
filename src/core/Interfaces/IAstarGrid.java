package Interfaces;
import Interfaces.IAstarNode;

import CustomExceptions.AstarNodeNotOnGridException;

public interface IAstarGrid {
    IAstarNode getNode(int x, int y) throws AstarNodeNotOnGridException;

    IAstarNode[][] getGrid();
}
