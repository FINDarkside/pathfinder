package tiralabra.pathfinders.jps;

import tiralabra.Cell;

class PathNode implements Comparable<PathNode> {

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
