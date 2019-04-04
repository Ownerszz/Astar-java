package core.Grid;

import core.AstarNode;
import core.CustomExceptions.AstarNodeNotOnGridException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class AstarGrid {
    private core.AstarNode[][] grid;

    public AstarGrid(core.AstarNode[][] grid) {
        this.grid = grid;
    }

    public AstarGrid(ArrayList<ArrayList<AstarNode>> grid) {
        this.grid = grid.stream().map(u -> u.toArray(new core.AstarNode[grid.size()])).toArray(core.AstarNode[][]::new);
    }

    public static <T> AstarGrid convert2DArrayListToGrid(ArrayList<ArrayList<T>> inputList) {
        throw new NotImplementedException();
    }

    public AstarNode getNode(int x, int y) throws AstarNodeNotOnGridException {
        if (x > grid.length || y > Arrays.stream(grid[0]).filter(Objects::nonNull).count() -1){
            throw new AstarNodeNotOnGridException();
        }
        return grid[x][y];
    }

    public AstarNode[][] getGrid() {
        return grid;
    }
}
