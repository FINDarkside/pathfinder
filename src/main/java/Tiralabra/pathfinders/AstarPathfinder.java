package tiralabra.pathfinders;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import tiralabra.Cell;
import tiralabra.Map;

public class AstarPathfinder extends Pathfinder {

    private class PathNode implements Comparable<PathNode> {

        public final Cell cell;
        public final int dist;
        public final int estimatedDist;

        public PathNode(Cell cell, int dist, int estimatedDist) {
            this.cell = cell;
            this.dist = dist;
            this.estimatedDist = estimatedDist;
        }

        @Override
        public int compareTo(PathNode node) {
            return estimatedDist - node.estimatedDist;
        }

    }

    public AstarPathfinder(Map map) {
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
        PriorityQueue<PathNode> queue = new PriorityQueue<>();
        HashSet<Cell> closed = new HashSet();
        HashMap<Cell, Cell> prev = new HashMap<>();
        HashMap<Cell, Integer> bestDist = new HashMap<>();

        queue.add(new PathNode(start, 0, manhattanDistance(start, goal)));

        while (!queue.isEmpty()) {
            PathNode currentNode = queue.poll();
            if (closed.contains(currentNode.cell)) {
                continue;
            }
            closed.add(currentNode.cell);
            if (currentNode.cell.equals(goal)) {
                return reconstructPath(start, goal, prev);
            }
            for (Cell nextCell : getAdjacentCells(currentNode.cell)) {
                int dist = currentNode.dist + 1;
                if (!bestDist.containsKey(nextCell) || bestDist.get(nextCell) > dist) {
                    prev.put(nextCell, currentNode.cell);
                    bestDist.put(nextCell, dist);
                    int estimatedDist = dist + manhattanDistance(nextCell, goal);
                    queue.add(new PathNode(nextCell, dist, estimatedDist));
                }
            }
        }
        return null;
    }

    private int manhattanDistance(Cell start, Cell goal) {
        return Math.abs(start.getX() - goal.getX())
                + Math.abs(start.getY() - goal.getY());
    }
}
