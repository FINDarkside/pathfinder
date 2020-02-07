package tiralabra.pathfinders;

import tiralabra.Cell;
import tiralabra.Map;
import tiralabra.datastructure.MyArrayDeque;
import tiralabra.datastructure.MyBitSet;
import tiralabra.datastructure.MyHashMap;

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
        MyBitSet used = new MyBitSet(map.getHeight() * map.getWidth());
        MyHashMap<Cell, Cell> prev = new MyHashMap<>();

        queue.add(start);
        used.set(start.getX() + start.getY() * map.getWidth());

        while (queue.size() != 0) {
            Cell cell = queue.removeFirst();
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
