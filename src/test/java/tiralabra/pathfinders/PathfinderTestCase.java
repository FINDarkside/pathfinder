package tiralabra.pathfinders;

import tiralabra.Cell;
import tiralabra.Map;

public class PathfinderTestCase {

    private final Map map;
    private final Cell start;
    private final Cell goal;
    private final Cell[] answer;

    public PathfinderTestCase(Map map, Cell start, Cell goal, Cell[] answer) {
        this.map = map;
        this.start = start;
        this.goal = goal;
        this.answer = answer;
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

    public Cell[] getAnswer() {
        return answer;
    }

}
