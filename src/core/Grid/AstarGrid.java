package core.Grid;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class AstarGrid {
    private AstarGridSpot[][] grid;
    public AstarGrid(AstarGridSpot[][] grid){
        this.grid = grid;
    }
    public AstarGrid(ArrayList<ArrayList<AstarGridSpot>> grid){
        this.grid = grid.stream().map(u -> u.toArray(new AstarGridSpot[grid.size()])).toArray(AstarGridSpot[][]::new);
    }


    public static <T> AstarGrid convert2DArrayListToGrid(ArrayList<ArrayList<T>> inputList){
        throw new NotImplementedException();
    }
}
