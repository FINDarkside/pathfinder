package tiralabra.pathfinders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import tiralabra.Cell;
import tiralabra.Map;

public class JPSPathfinder extends Pathfinder {

    private class PathNode implements Comparable<PathNode> {

        public final Cell cell;
        public final int dist;
        public final int estimatedDist;
        public final int dx;
        public final int dy;

        public PathNode(Cell cell, int dist, int estimatedDist, int dx, int dy) {
            this.cell = cell;
            this.dist = dist;
            this.estimatedDist = estimatedDist;
            this.dx = dx;
            this.dy = dy;
        }

        @Override
        public int compareTo(PathNode node) {
            return estimatedDist - node.estimatedDist;
        }

    }

    public JPSPathfinder(Map map) {
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

        queue.add(new PathNode(start, 0, manhattanDistance(start, goal), 1, 1));
        queue.add(new PathNode(start, 0, manhattanDistance(start, goal), 1, -1));
        queue.add(new PathNode(start, 0, manhattanDistance(start, goal), -1, 1));
        queue.add(new PathNode(start, 0, manhattanDistance(start, goal), -1, -1));

        while (!queue.isEmpty()) {
            PathNode currentNode = queue.poll();
            if (bestDist.containsKey(currentNode.cell) && bestDist.get(currentNode.cell) <= currentNode.dist) {
                continue;
            }
            System.out.println(currentNode.cell);

            if (currentNode.cell.equals(goal)) {
                System.out.println(currentNode.dist);
                // TODO return result;
            }

            searchDiagonal(map, currentNode, queue, prev, bestDist, goal, currentNode.dx, currentNode.dy);

        }
        return null;
    }

    private void searchDiagonal(Map map, PathNode node, PriorityQueue<PathNode> queue, HashMap<Cell, Cell> prev, HashMap<Cell, Integer> bestDist, Cell goal, int dx, int dy) {
        int dist = node.dist;
        Cell currentCell = node.cell;

        while (!map.isCellBlocked(currentCell.getX(), currentCell.getY())) {
            if (bestDist.containsKey(currentCell) && bestDist.get(currentCell) < dist) {
                break;
            }
            bestDist.put(currentCell, dist);

            var verticalJumpPoints = jumpStraight(map, currentCell, dist, bestDist, goal, 0, dy);
            if (!verticalJumpPoints.isEmpty()) {
                int estimatedDistance = dist + manhattanDistance(currentCell, goal);
                queue.add(new PathNode(currentCell, dist, estimatedDistance, dx, dy));
                queue.addAll(verticalJumpPoints);
                prev.put(verticalJumpPoints.get(0).cell, currentCell);
                return;
            }
            var horizontalJumpPoints = jumpStraight(map, currentCell, dist, bestDist, goal, dx, 0);
            if (!horizontalJumpPoints.isEmpty()) {
                int estimatedDistance = dist + manhattanDistance(currentCell, goal);
                queue.add(new PathNode(currentCell, dist, estimatedDistance, dx, dy));
                queue.addAll(verticalJumpPoints);
                prev.put(horizontalJumpPoints.get(0).cell, currentCell);
                return;
            }

            currentCell = new Cell(node.cell.getX() + dx, node.cell.getY() + dy);
            // Diagonal moves are not allowed, so if we can't make it with 2 straight moves
            // currentCell is inaccessible
            if (!map.isCellBlocked(currentCell.getX() - dx, currentCell.getY())
                    || !map.isCellBlocked(currentCell.getX(), currentCell.getY() - dy)) {
                return;
            }
            dist += 2;
        }
    }

    /**
     * Searches the map horizontally or vertically for new jump points.
     *
     * @param map
     * @param node
     * @param queue
     * @param dx -1, 0 or 1. Should be 0 if dy != 0
     * @param dy -1, 0 or 1. Should be 0 if dx != 0
     */
    private ArrayList<PathNode> jumpStraight(Map map, Cell cell, int dist, HashMap<Cell, Integer> bestDist, Cell goal, int dx, int dy) {
        // FIXME: Don't move one block before checking for jump points. 
        // And don't break if the first cell has been visited before with same distance.
        dist++;
        Cell currentCell = new Cell(cell.getX() + dx, cell.getY() + dy);

        while (!map.isCellBlocked(currentCell.getX(), currentCell.getY())) {
            if (bestDist.containsKey(currentCell) && bestDist.get(currentCell) <= dist) {
                break;
            }
            bestDist.put(currentCell, dist);

            if (currentCell.equals(goal)) {
                ArrayList<PathNode> result = new ArrayList<>();
                int estimatedDistance = dist + manhattanDistance(currentCell, goal);
                result.add(new PathNode(currentCell, dist, estimatedDistance, 0, 0));
                return result;
            }
            var newJumpPoints = getJumpPoints(map, currentCell, dx, dy, dist, goal);
            if (!newJumpPoints.isEmpty()) {
                return newJumpPoints;
            }
            currentCell = new Cell(currentCell.getX() + dx, currentCell.getY() + dy);
            dist++;
        }
        return new ArrayList<>();
    }

    /**
     * Returns new jump points in direction of forced neighbors if they exist.
     * Returned pathNode.cell isn't the forced neighbor, but (pathNode.cell.x +
     * pathNode.dx, pathNode.cell.y + pathNode.dy) is.
     *
     * @param map
     * @param cell
     * @param dx
     * @param dy
     * @return pathnodes expanding in direction of forced neighbors or empty
     * list
     */
    private ArrayList<PathNode> getJumpPoints(Map map, Cell cell, int dx, int dy, int dist, Cell goal) {
        ArrayList<PathNode> result = new ArrayList<>();
        // Horizontal movement
        int x = cell.getX();
        int y = cell.getY();
        if ((dx == 1 || dx == -1) && dy == 0 && !map.isCellBlocked(x + dx, y)) {
            // Horizontal movement
            if (map.isCellBlocked(x, y + 1) && !map.isCellBlocked(x + dx, y + 1)) {
                // (x + dx, y + 1) is the forced neighbor, add PathNode expanding in its direction
                result.add(new PathNode(cell, dist, dist + manhattanDistance(cell, goal), dx, 1));
            }
            if (map.isCellBlocked(x, y - 1) && !map.isCellBlocked(x + dx, y - 1)) {
                // (x + dx, y - 1) is the forced neighbor
                result.add(new PathNode(cell, dist, dist + manhattanDistance(cell, goal), dx, -1));
            }
        }
        if (dx == 0 && !map.isCellBlocked(x, y + dy)) {
            if (map.isCellBlocked(x + 1, y) && !map.isCellBlocked(x + 1, y + dy)) {
                // (x + 1, y + dy) is the forced neighbor
                result.add(new PathNode(cell, dist, dist + manhattanDistance(cell, goal), 1, dy));
            }
            if (map.isCellBlocked(x - 1, y) && !map.isCellBlocked(x - 1, y + dy)) {
                // (x - 1, y + dy) is the forced neighbor
                result.add(new PathNode(cell, dist, dist + manhattanDistance(cell, goal), -1, dy));
            }
        }
        return result;
    }

    private int manhattanDistance(Cell start, Cell goal) {
        return Math.abs(start.getX() - goal.getX())
                + Math.abs(start.getY() - goal.getY());
    }

}
