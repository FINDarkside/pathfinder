package tiralabra.pathfinders.jps;

import java.util.HashMap;
import java.util.PriorityQueue;
import tiralabra.Cell;

class JPSSearchContext {

    public final PriorityQueue<PathNode> queue;
    public final HashMap<Cell, Cell> prev;
    public final HashMap<Cell, Integer> bestDist;
    public final Cell start;
    public final Cell goal;

    public JPSSearchContext(PriorityQueue<PathNode> queue, HashMap<Cell, Cell> prev, HashMap<Cell, Integer> bestDist, Cell start, Cell goal) {
        this.queue = queue;
        this.prev = prev;
        this.bestDist = bestDist;
        this.start = start;
        this.goal = goal;
    }

}
