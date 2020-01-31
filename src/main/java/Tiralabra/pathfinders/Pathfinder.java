package tiralabra.pathfinders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import tiralabra.Cell;
import tiralabra.Map;

public abstract class Pathfinder {

    protected Map map;

    /**
     * Finds shortest path between two cells, if such path exists.
     *
     * @param start
     * @param goal
     * @return Array of cells forming the shortest path. Starting cell is not
     * included.
     */
    public abstract Cell[] findPath(Cell start, Cell goal);

    protected List<Cell> getAdjacentCells(Cell cell) {
        List<Cell> adjacentCells = new ArrayList<>();
        if (!isBlockedOrOutOfBounds(cell.getX() + 1, cell.getY())) {
            adjacentCells.add(new Cell(cell.getX() + 1, cell.getY()));
        }
        if (!isBlockedOrOutOfBounds(cell.getX() - 1, cell.getY())) {
            adjacentCells.add(new Cell(cell.getX() - 1, cell.getY()));
        }
        if (!isBlockedOrOutOfBounds(cell.getX(), cell.getY() + 1)) {
            adjacentCells.add(new Cell(cell.getX(), cell.getY() + 1));
        }
        if (!isBlockedOrOutOfBounds(cell.getX(), cell.getY() - 1)) {
            adjacentCells.add(new Cell(cell.getX(), cell.getY() - 1));
        }
        return adjacentCells;
    }

    protected boolean isBlockedOrOutOfBounds(int x, int y) {
        return !map.isInBounds(x, y) || map.isCellBlocked(x, y);
    }
    
    protected Cell[] reconstructPath(Cell start, Cell goal, HashMap<Cell, Cell> prev) {
        // prev allows us to traverse the path backwards, but adding to start of array is O(n) operation
        // so we first calculate path length and use indices to set cells
        int pathLength = calculatePathLength(start, goal, prev);
        Cell[] path = new Cell[pathLength];
        Cell currentCell = goal;
        for (int i = pathLength - 1; i >= 0; i--) {
            path[i] = currentCell;
            currentCell = prev.get(currentCell);
        }
        return path;
    }

    protected int calculatePathLength(Cell start, Cell goal, HashMap<Cell, Cell> prev) {
        int length = 0;
        Cell currentCell = goal;
        while (!currentCell.equals(start)) {
            currentCell = prev.get(currentCell);
            length++;
        }
        return length;
    }

}
