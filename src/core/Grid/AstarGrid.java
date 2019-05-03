package core.Grid;

import core.CustomExceptions.AstarNodeNotOnGridException;
import core.Interfaces.IAstarGrid;
import core.Interfaces.IAstarNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/*
This class contains the nodes. This class is for the 2D grids
 */
public class AstarGrid implements IAstarGrid {
    private IAstarNode[][] grid;

    protected AstarGrid(IAstarNode[][] grid) {
        this.grid = grid;
    }

    protected AstarGrid(ArrayList<ArrayList<IAstarNode>> grid) {
        this.grid = grid.stream().map(u -> u.toArray(new IAstarNode[grid.size()])).toArray(IAstarNode[][]::new);
    }

    @Override
    public IAstarNode getNode(int x, int y) throws AstarNodeNotOnGridException {
        if (x > Arrays.stream(grid).filter(Objects::nonNull).count() -1  || y > Arrays.stream(grid[0]).filter(Objects::nonNull).count() -1) {
            throw new AstarNodeNotOnGridException();
        }
        return grid[x][y];
    }
    @Override
    public IAstarNode getNode(IAstarNode node) throws AstarNodeNotOnGridException {
        return getNode(node.getX(),node.getY());
    }

    @Override
    public IAstarNode[][] getGrid() {
        return grid;
    }
}
