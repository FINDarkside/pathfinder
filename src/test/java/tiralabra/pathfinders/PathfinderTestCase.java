package tiralabra.pathfinders;

import tiralabra.Cell;
import tiralabra.Map;

public class PathfinderTestCase {

    private final Map map;
    private final Cell start;
    private final Cell goal;
    private final int bestDistance;

    public PathfinderTestCase(Map map, Cell start, Cell goal, int bestDistance) {
        this.map = map;
        this.start = start;
        this.goal = goal;
        this.bestDistance = bestDistance;
    }

    public Map getMap() {
        return map;
    }

    public Cell getStart() {
        return start;
    }

    public Cell getGoal() {
        return goal;
    }

    public int getBestDistance() {
        return bestDistance;
    }

}
