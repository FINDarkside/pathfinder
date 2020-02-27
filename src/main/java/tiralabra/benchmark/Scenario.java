package tiralabra.benchmark;

import tiralabra.Cell;
import tiralabra.Map;

public class Scenario {

    private final Map map;
    private final Cell start;
    private final Cell goal;

    public Scenario(Map map, Cell start, Cell goal) {
        this.map = map;
        this.start = start;
        this.goal = goal;
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

}
