package tiralabra.pathfinders;

import tiralabra.Cell;
import tiralabra.Map;
import tiralabra.datastructure.MyArrayDeque;
import tiralabra.datastructure.MyHashMap;
import tiralabra.datastructure.MyHashSet;

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
        MyArrayDeque<Cell> queue = new MyArrayDeque<>();
        MyHashSet<Cell> used = new MyHashSet();
        MyHashMap<Cell, Cell> prev = new MyHashMap<>();

        queue.add(start);
        used.add(start);

        while (queue.size() != 0) {
            Cell cell = queue.removeFirst();
            for (Cell nextCell : getAdjacentCells(cell)) {
                if (used.contains(nextCell)) {
                    continue;
                }
                prev.put(nextCell, cell);
                if (nextCell.equals(goal)) {
                    return reconstructPath(start, goal, prev);
                }
                queue.add(nextCell);
                used.add(nextCell);
            }
        }
        return null;
    }

}
