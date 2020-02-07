package tiralabra.pathfinders;

import tiralabra.Cell;
import tiralabra.Map;
import tiralabra.datastructure.MyHashMap;
import tiralabra.datastructure.MyHashSet;
import tiralabra.datastructure.MyPriorityQueue;

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
            int estimatedDiff = estimatedDist - node.estimatedDist;
            if (estimatedDiff == 0) {
                return node.dist - dist;
            }
            return estimatedDiff;
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
        MyPriorityQueue<PathNode> queue = new MyPriorityQueue<>();
        MyHashSet<Cell> closed = new MyHashSet();
        MyHashMap<Cell, Cell> prev = new MyHashMap<>();
        MyHashMap<Cell, Integer> bestDist = new MyHashMap<>();

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
                if (dist < bestDist.getOrDefault(nextCell, Integer.MAX_VALUE)) {
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
