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

    /**
     * Returns if cell is blocked
     *
     * @param cell
     * @return true if cell is blocked
     */
    public boolean isCellBlocked(Cell cell) {
        return isCellBlocked(cell.getX(), cell.getY());
    }

    /**
     * Returns if cell is blocked in given x and y
     *
     * @param x
     * @param y
     * @return true if cell is blocked
     */
    public boolean isCellBlocked(int x, int y) {
        return map.get(x + y * width);
    }

    /**
     * Determines if given coordinates are within map bounds
     *
     * @param x
     * @param y
     * @return true if coordinates fit inside map
     */
    public boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    /**
     * Sets state of cell
     *
     * @param x
     * @param y
     * @param blocked
     */
    public void setCell(int x, int y, boolean blocked) {
        map.set(x + y * width);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
