package core.Grid;

import core.AstarNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class AstarGrid {
    private core.AstarNode[][] grid;
    public AstarGrid(core.AstarNode[][] grid){
        this.grid = grid;
    }
    public AstarGrid(ArrayList<ArrayList<AstarNode>> grid){
        this.grid = grid.stream().map(u -> u.toArray(new core.AstarNode[grid.size()])).toArray(core.AstarNode[][]::new);
    }

    public AstarNode getNode(int x, int y){
        return  grid[x][y];
    }

    public static <T> AstarGrid convert2DArrayListToGrid(ArrayList<ArrayList<T>> inputList){
        throw new NotImplementedException();
    }

    public AstarNode[][] getGrid(){
        return grid;
    }
}
