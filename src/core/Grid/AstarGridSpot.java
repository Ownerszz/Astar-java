package core.Grid;

public class AstarGridSpot {
    private int x;
    private int y;
    private int obstacleValue;

    public AstarGridSpot(int x,int y){
        new  AstarGridSpot(x,y,0);
    }
    public AstarGridSpot(int x, int y, int obstacleValue){
        this.x = x;
        this.y = y;
        this.obstacleValue = obstacleValue;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



    public int getObstacleValue() {
        return obstacleValue;
    }
    @Override
    public boolean equals(Object obj) {
        //Check if nodes are in same position in the grid.
        AstarGridSpot in = (AstarGridSpot) obj;
        return x == in.getX() && y == in.getY();
    }
}
