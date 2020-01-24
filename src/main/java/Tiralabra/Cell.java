package Tiralabra;

public class Cell {

    private int x, y;

    public Cell(int x, int y) {
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
        if (!obj.getClass().equals(Cell.class)) {
            return false;
        }
        Cell cell = (Cell) obj;
        return cell.x == x && cell.getY() == y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.x;
        hash = 59 * hash + this.y;
        return hash;
    }

}
