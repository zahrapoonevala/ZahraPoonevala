package byow.lab12;

import byow.TileEngine.TETile;

import java.util.ArrayList;
import java.util.List;

public class Hexagon {

    private Position position;
    private int side;
    private TETile tile;

    public Hexagon(Position position, int side, TETile tile ) {
        this.position = position;
        this.side = side;
        this.tile = tile;
    }

    public List<Position> getHExPositions(){
        List<Position> positions = new ArrayList<>();

        int height = this.side * 2;
        for (int y =0; y < height; y+=1){
            int startRow = this.getRowStart(y);
            int width = this.getRowWidth(y);

            for(int x =0; x < width; x+=1) {
                positions.add(new Position(startRow + x, this.position.getY() - y));
            }
        }

        return positions;
    }

    public int getRowWidth(int rowNumber) {
        if (rowNumber < this.side) {
            return this.side + 2 * rowNumber;
        } else {
            return this.side +(this.side -1) * 2 - (rowNumber % this.side) * 2;
        }
    }

    public int getRowStart (int row) {
        if (row < this.side) {
            return this.position.getX() - row;
        } else {
            return this.position.getX() - (this.side - 1) + (row % this.side);
        }
    }

}
