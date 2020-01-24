package tiralabra.pathfinders;

import tiralabra.Cell;
import tiralabra.Map;
import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.HashMap;

public class BFSPathfinder extends Pathfinder {

    public BFSPathfinder(Map map) {
        this.map = map;
    }

    @Override
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

}
