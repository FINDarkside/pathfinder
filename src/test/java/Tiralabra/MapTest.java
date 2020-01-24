package Tiralabra;

import static junit.framework.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MapTest {

    Map map;

    @Before
    public void setUp() throws Exception {
        map = new Map(10, 5);
    }

    @Test
    public void allCellsAreUnblockedByDefault() {
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                assertEquals(false, map.isCellBlocked(x, y));
            }
        }
    }

    @Test
    public void cellIsBlockedAfterSetCell() {
        map.setBlock(4, 2, true);
        assertEquals(true, map.isCellBlocked(new Cell(4, 2)));
    }

    @Test
    public void isCellInBoundsReturnsTrueWhenCellIsInBounds() {
        assertEquals(true, map.isInBounds(4, 2));
        assertEquals(true, map.isInBounds(0, 0));
        assertEquals(true, map.isInBounds(9, 4));
    }

    @Test
    public void isCellInBoundsReturnsFalseWhenCellIsNotInBounds() {
        assertEquals(false, map.isInBounds(-1, 2));
        assertEquals(false, map.isInBounds(2, -1));
        assertEquals(false, map.isInBounds(-1, -1));
        assertEquals(false, map.isInBounds(10, 2));
        assertEquals(false, map.isInBounds(2, 5));
        assertEquals(false, map.isInBounds(10, 5));
    }

}
