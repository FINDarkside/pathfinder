package tiralabra.pathfinders;

import tiralabra.Cell;
import tiralabra.Map;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

public class BFSPathfinder {

    Map map;

    public BFSPathfinder(Map map) {
        this.map = map;
    }

    public Cell[] findPath(Cell start, Cell goal) {
        if (start.getX() == goal.getX() && start.getY() == goal.getY()) {
            return new Cell[0];
        }
        if (map.isCellBlocked(start) || map.isCellBlocked(goal)) {
            return null;
        }
        ArrayDeque<Cell> queue = new ArrayDeque<>();
        BitSet used = new BitSet(map.getHeight() * map.getWidth());
        HashMap<Cell, Cell> prev = new HashMap<>();

        queue.add(start);
        used.set(start.getX() + start.getY() * map.getWidth());

        while (!queue.isEmpty()) {
            Cell cell = queue.pop();
            System.out.println(cell);
            for (Cell nextCell : getAdjacentCells(cell)) {
                int bitSetIndex = nextCell.getX() + nextCell.getY() * map.getWidth();
                if (used.get(bitSetIndex)) {
                    continue;
                }
                prev.put(nextCell, cell);
                if (nextCell.equals(goal)) {
                    return reconstructPath(start, goal, prev);
                }
                queue.add(nextCell);
                used.set(bitSetIndex);
            }
        }
        return null;
    }

    private List<Cell> getAdjacentCells(Cell cell) {
        List<Cell> adjacentCells = new ArrayList<>();
        if (map.isInBounds(cell.getX() + 1, cell.getY())) {
            adjacentCells.add(new Cell(cell.getX() + 1, cell.getY()));
        }
        if (map.isInBounds(cell.getX() - 1, cell.getY())) {
            adjacentCells.add(new Cell(cell.getX() - 1, cell.getY()));
        }
        if (map.isInBounds(cell.getX(), cell.getY() + 1)) {
            adjacentCells.add(new Cell(cell.getX(), cell.getY() + 1));
        }
        if (map.isInBounds(cell.getX(), cell.getY() - 1)) {
            adjacentCells.add(new Cell(cell.getX(), cell.getY() - 1));
        }
        return adjacentCells;
    }

    private Cell[] reconstructPath(Cell start, Cell goal, HashMap<Cell, Cell> prev) {
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

    private int calculatePathLength(Cell start, Cell goal, HashMap<Cell, Cell> prev) {
        int length = 0;
        Cell currentCell = goal;
        while (!currentCell.equals(start)) {
            currentCell = prev.get(currentCell);
            length++;
        }
        return length;
    }

}
