package tiralabra;

import java.util.BitSet;

public class Map {

    private int width, height;
    private BitSet map;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        map = new BitSet(width * height);
    }

    public boolean isCellBlocked(Cell cell) {
        return isCellBlocked(cell.getX(), cell.getY());
    }

    public boolean isCellBlocked(int x, int y) {
        return map.get(x + y * width);
    }

    public boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public void setBlock(int x, int y, boolean blocked) {
        map.set(x + y * width);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
