package tiralabra.pathfinders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
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

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 89 * hash + Objects.hashCode(this.cell);
            hash = 89 * hash + this.dx;
            hash = 89 * hash + this.dy;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            PathNode other = (PathNode) obj;
            if (this.dx != other.dx || this.dy != other.dy || !this.cell.equals(other.cell)) {
                return false;
            }
            return true;
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
        HashSet<PathNode> closed = new HashSet();
        HashMap<Cell, Cell> prev = new HashMap<>();
        HashMap<Cell, Integer> bestDist = new HashMap<>();

        queue.add(new PathNode(start, 0, manhattanDistance(start, goal), 1, 1));
        queue.add(new PathNode(start, 0, manhattanDistance(start, goal), 1, -1));
        queue.add(new PathNode(start, 0, manhattanDistance(start, goal), -1, 1));
        queue.add(new PathNode(start, 0, manhattanDistance(start, goal), -1, -1));

        while (!queue.isEmpty()) {
            PathNode currentNode = queue.poll();
            if (closed.contains(currentNode)) {
                continue;
            }
            closed.add(currentNode);
            if (bestDist.containsKey(currentNode.cell) && bestDist.get(currentNode.cell) < currentNode.dist) {
                continue;
            }

            if (currentNode.cell.equals(goal)) {
                return reconstructPath(start, goal, prev);
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

            var verticalJumpPoints = jumpStraight(map, currentCell, dist, bestDist, prev, goal, 0, dy);
            if (!verticalJumpPoints.isEmpty()) {
                int estimatedDistance = dist + manhattanDistance(currentCell, goal);
                queue.add(new PathNode(currentCell, dist, estimatedDistance, dx, dy));
                queue.addAll(verticalJumpPoints);
                prev.put(verticalJumpPoints.get(0).cell, currentCell);
                if (!currentCell.equals(node.cell)) {
                    prev.put(currentCell, node.cell);
                }
                return;
            }
            var horizontalJumpPoints = jumpStraight(map, currentCell, dist, bestDist, prev, goal, dx, 0);
            if (!horizontalJumpPoints.isEmpty()) {
                int estimatedDistance = dist + manhattanDistance(currentCell, goal);
                queue.add(new PathNode(currentCell, dist, estimatedDistance, dx, dy));
                queue.addAll(horizontalJumpPoints);
                prev.put(horizontalJumpPoints.get(0).cell, currentCell);
                if (!currentCell.equals(node.cell)) {
                    prev.put(currentCell, node.cell);
                }
                return;
            }

            var diagonalJumpPoints = getJumpPoints(map, currentCell, dx, dy, dist, goal);
            if (!diagonalJumpPoints.isEmpty()) {
                queue.addAll(diagonalJumpPoints);
                prev.put(diagonalJumpPoints.get(0).cell, currentCell);
                if (!currentCell.equals(node.cell)) {
                    prev.put(currentCell, node.cell);
                }
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
    private ArrayList<PathNode> jumpStraight(Map map, Cell cell, int dist, HashMap<Cell, Integer> bestDist, HashMap<Cell, Cell> prev, Cell goal, int dx, int dy) {
        Cell currentCell = new Cell(cell.getX() + dx, cell.getY() + dy);
        dist++;
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
        if ((dy == 1 || dy == -1) && dx == 0 && !map.isCellBlocked(x, y + dy)) {
            if (map.isCellBlocked(x + 1, y) && !map.isCellBlocked(x + 1, y + dy)) {
                // (x + 1, y + dy) is the forced neighbor
                result.add(new PathNode(cell, dist, dist + manhattanDistance(cell, goal), 1, dy));
            }
            if (map.isCellBlocked(x - 1, y) && !map.isCellBlocked(x - 1, y + dy)) {
                // (x - 1, y + dy) is the forced neighbor
                result.add(new PathNode(cell, dist, dist + manhattanDistance(cell, goal), -1, dy));
            }
        } else if (dy != 0 && dx != 0 && !map.isCellBlocked(new Cell(cell.getX() + dx, cell.getY() + dy))) {
            boolean xAxisBlocked = map.isCellBlocked(new Cell(cell.getX() + dx, cell.getY()));
            boolean yAxisBlocked = map.isCellBlocked(new Cell(cell.getX(), cell.getY() + dy));
            Cell diagonalCell = new Cell(cell.getX() + dx, cell.getY() + dy);
            dist += 2;
            int estimatedDist = dist + manhattanDistance(diagonalCell, goal);
            if (xAxisBlocked && !yAxisBlocked) {
                result.add(new PathNode(diagonalCell, dist, estimatedDist, dx, dy));
                result.add(new PathNode(diagonalCell, dist, estimatedDist, dx, -dy));
            } else if (!xAxisBlocked && yAxisBlocked) {
                result.add(new PathNode(diagonalCell, dist, estimatedDist, dx, dy));
                result.add(new PathNode(diagonalCell, dist, estimatedDist, -dx, dy));
            }
        }
        return result;
    }

    @Override
    protected Cell[] reconstructPath(Cell start, Cell goal, HashMap<Cell, Cell> prev) {
        int length = calculatePathLength(start, goal, prev);
        Cell[] path = new Cell[length];

        int i = length - 1;
        Cell currentCell = goal;

        while (!currentCell.equals(start)) {
            Cell nextCell = prev.get(currentCell);
            int xDiff = Math.abs(currentCell.getX() - nextCell.getX());
            int yDiff = Math.abs(currentCell.getY() - nextCell.getY());
            int xDirection = currentCell.getX() < nextCell.getX() ? 1 : -1;
            int yDirection = currentCell.getY() < nextCell.getY() ? 1 : -1;

            if (xDiff == 0) {
                // Vertical line
                for (int j = 0; j < yDiff; j++) {
                    path[i--] = new Cell(nextCell.getX(), currentCell.getY() + j * yDirection);
                }
            } else if (yDiff == 0) {
                // Horizontal line
                for (int j = 0; j < xDiff; j++) {
                    path[i--] = new Cell(currentCell.getX() + j * xDirection, nextCell.getY());
                }
            } else {
                if (xDiff != yDiff) {
                    throw new RuntimeException("Kakkaa tuulettimessa");
                }
                int x = currentCell.getX(), y = currentCell.getY();
                for (int j = 0; j < xDiff; j++) {
                    path[i--] = new Cell(x, y);
                    if (!map.isCellBlocked(x + xDirection, y)) {
                        path[i--] = new Cell(x + xDirection, y);
                    } else {
                        path[i--] = new Cell(x, y + yDirection);
                    }
                    x += xDirection;
                    y += yDirection;
                }
            }
            currentCell = nextCell;
        }
        return path;
    }

    @Override
    protected int calculatePathLength(Cell start, Cell goal, HashMap<Cell, Cell> prev) {
        int length = 0;
        Cell currentCell = goal;
        while (!currentCell.equals(start)) {
            Cell nextCell = prev.get(currentCell);
            length += Math.abs((currentCell.getX() - nextCell.getX()));
            length += Math.abs((currentCell.getY() - nextCell.getY()));
            currentCell = nextCell;
        }
        return length;
    }

    private int manhattanDistance(Cell start, Cell goal) {
        return Math.abs(start.getX() - goal.getX())
                + Math.abs(start.getY() - goal.getY());
    }

}
