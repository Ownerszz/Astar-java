package core.Grid;

public class AstarGridSpot {
    private int x;
    private int y;


    public AstarGridSpot(int x,int y){
       this.x = x;
       this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }




    @Override
    public boolean equals(Object obj) {
        //Check if nodes are in same position in the grid.
        AstarGridSpot in = (AstarGridSpot) obj;
        return x == in.getX() && y == in.getY();
    }
}
