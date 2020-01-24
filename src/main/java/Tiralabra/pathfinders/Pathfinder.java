package tiralabra.pathfinders;

import tiralabra.Cell;

public interface Pathfinder {

    public Cell[] findPath(Cell start, Cell goal);

}
