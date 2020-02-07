package tiralabra.pathfinders.jps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import tiralabra.Cell;
import tiralabra.Map;
import tiralabra.pathfinders.Pathfinder;

public class JPSPathfinder extends Pathfinder {

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
        HashMap<Cell, Cell> prev = new HashMap<>();
        HashMap<Cell, Integer> bestDist = new HashMap<>();
        var context = new JPSSearchContext(queue, prev, bestDist, start, goal);

        queue.add(new PathNode(start, 0, manhattanDistance(start, goal), 1, 1));
        queue.add(new PathNode(start, 0, manhattanDistance(start, goal), 1, -1));
        queue.add(new PathNode(start, 0, manhattanDistance(start, goal), -1, 1));
        queue.add(new PathNode(start, 0, manhattanDistance(start, goal), -1, -1));

        while (!queue.isEmpty()) {
            PathNode currentNode = queue.poll();
            if (bestDist.containsKey(currentNode.cell) && bestDist.get(currentNode.cell) < currentNode.dist) {
                continue;
            }
            if (currentNode.cell.equals(goal)) {
                return reconstructPath(start, goal, prev);
            }
            searchDiagonal(currentNode, currentNode.dx, currentNode.dy, context);
        }

        return null;
    }

    private void searchDiagonal(PathNode node, int dx, int dy, JPSSearchContext context) {
        int dist = node.dist;
        Cell currentCell = node.cell;

        while (!map.isCellBlocked(currentCell.getX(), currentCell.getY())) {
            if (context.bestDist.containsKey(currentCell) && context.bestDist.get(currentCell) < dist) {
                break;
            }
            context.bestDist.put(currentCell, dist);
            if (currentCell.equals(context.goal)) {
                context.queue.add(new PathNode(context.goal, dist, dist, 0, 0));
                context.prev.put(context.goal, node.cell);
            }

            var verticalJumpPoints = jumpStraight(currentCell, dist, 0, dy, context);
            var horizontalJumpPoints = jumpStraight(currentCell, dist, dx, 0, context);

            if (!verticalJumpPoints.isEmpty() || !horizontalJumpPoints.isEmpty()) {
                context.queue.add(new PathNode(currentCell, dist, dist + manhattanDistance(currentCell, context.goal), dx, dy));
                context.queue.addAll(verticalJumpPoints);
                context.queue.addAll(horizontalJumpPoints);
                if (!verticalJumpPoints.isEmpty()) {
                    context.prev.put(verticalJumpPoints.get(0).cell, currentCell);
                }
                if (!horizontalJumpPoints.isEmpty()) {
                    context.prev.put(horizontalJumpPoints.get(0).cell, currentCell);
                }
                if (!currentCell.equals(node.cell)) {
                    context.prev.put(currentCell, node.cell);
                }
                return;
            }

            var diagonalJumpPoints = getJumpPoints(currentCell, dx, dy, dist, context);
            if (!diagonalJumpPoints.isEmpty()) {
                if (diagonalJumpPoints.get(0).dist <= context.bestDist.getOrDefault(diagonalJumpPoints.get(0).cell, Integer.MAX_VALUE)) {
                    context.queue.addAll(diagonalJumpPoints);
                    context.prev.put(diagonalJumpPoints.get(0).cell, currentCell);
                    context.bestDist.put(diagonalJumpPoints.get(0).cell, diagonalJumpPoints.get(0).dist);
                    if (!currentCell.equals(node.cell)) {
                        context.prev.put(currentCell, node.cell);
                    }
                }
                return;
            }

            currentCell = new Cell(currentCell.getX() + dx, currentCell.getY() + dy);
            // Diagonal moves are not allowed, so if we can't make it with 2 straight moves
            // currentCell is inaccessible
            if (map.isCellBlocked(currentCell.getX() - dx, currentCell.getY())
                    && map.isCellBlocked(currentCell.getX(), currentCell.getY() - dy)) {
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
    private ArrayList<PathNode> jumpStraight(Cell cell, int dist, int dx, int dy, JPSSearchContext context) {
        Cell currentCell = new Cell(cell.getX() + dx, cell.getY() + dy);
        dist++;
        while (!map.isCellBlocked(currentCell.getX(), currentCell.getY())) {
            if (context.bestDist.containsKey(currentCell) && context.bestDist.get(currentCell) <= dist) {
                break;
            }
            context.bestDist.put(currentCell, dist);

            if (currentCell.equals(context.goal)) {
                ArrayList<PathNode> result = new ArrayList<>();
                result.add(new PathNode(currentCell, dist, dist, 0, 0));
                return result;
            }
            var newJumpPoints = getJumpPoints(currentCell, dx, dy, dist, context);
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
    private ArrayList<PathNode> getJumpPoints(Cell cell, int dx, int dy, int dist, JPSSearchContext context) {
        ArrayList<PathNode> result = new ArrayList<>();
        // Horizontal movement
        int x = cell.getX();
        int y = cell.getY();
        if ((dx == 1 || dx == -1) && dy == 0 && !map.isCellBlocked(x + dx, y)) {
            // Horizontal movement
            if (map.isCellBlocked(x, y + 1) && !map.isCellBlocked(x + dx, y + 1)) {
                // (x + dx, y + 1) is the forced neighbor, add PathNode expanding in its direction
                result.add(new PathNode(cell, dist, dist + manhattanDistance(cell, context.goal), dx, 1));
            }
            if (map.isCellBlocked(x, y - 1) && !map.isCellBlocked(x + dx, y - 1)) {
                // (x + dx, y - 1) is the forced neighbor
                result.add(new PathNode(cell, dist, dist + manhattanDistance(cell, context.goal), dx, -1));
            }
        }
        if ((dy == 1 || dy == -1) && dx == 0 && !map.isCellBlocked(x, y + dy)) {
            if (map.isCellBlocked(x + 1, y) && !map.isCellBlocked(x + 1, y + dy)) {
                // (x + 1, y + dy) is the forced neighbor
                result.add(new PathNode(cell, dist, dist + manhattanDistance(cell, context.goal), 1, dy));
            }
            if (map.isCellBlocked(x - 1, y) && !map.isCellBlocked(x - 1, y + dy)) {
                // (x - 1, y + dy) is the forced neighbor
                result.add(new PathNode(cell, dist, dist + manhattanDistance(cell, context.goal), -1, dy));
            }
        } else if (dy != 0 && dx != 0 && !map.isCellBlocked(new Cell(cell.getX() + dx, cell.getY() + dy))) {
            boolean xAxisBlocked = map.isCellBlocked(new Cell(cell.getX() + dx, cell.getY()));
            boolean yAxisBlocked = map.isCellBlocked(new Cell(cell.getX(), cell.getY() + dy));
            Cell diagonalCell = new Cell(cell.getX() + dx, cell.getY() + dy);
            dist += 2;
            int estimatedDist = dist + manhattanDistance(diagonalCell, context.goal);
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
            if (currentCell.equals(nextCell)) {
                throw new RuntimeException("Loop in path");
            }
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
