package tiralabra.pathfinders.jps;

import tiralabra.Cell;
import tiralabra.datastructure.MyHashMap;
import tiralabra.datastructure.MyPriorityQueue;

class JPSSearchContext {

    public final MyPriorityQueue<PathNode> queue;
    public final MyHashMap<Cell, Cell> prev;
    public final MyHashMap<Cell, Integer> bestDist;
    public final Cell start;
    public final Cell goal;

    public JPSSearchContext(MyPriorityQueue<PathNode> queue, MyHashMap<Cell, Cell> prev, MyHashMap<Cell, Integer> bestDist, Cell start, Cell goal) {
        this.queue = queue;
        this.prev = prev;
        this.bestDist = bestDist;
        this.start = start;
        this.goal = goal;
    }

}
