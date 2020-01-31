package tiralabra.pathfinders;

import java.util.ArrayList;
import org.junit.Assert;
import tiralabra.Cell;
import tiralabra.Map;
import tiralabra.MapLoader;

public class TestCases {

    public static PathfinderTestCase simpleTestCase;
    public static PathfinderTestCase simpleNoPath;
    public static PathfinderTestCase smallMaze;
    public static PathfinderTestCase jpsCornerCase;

    public static void test(Pathfinder pathfinder, PathfinderTestCase testCase) {
        Cell[] result = pathfinder.findPath(testCase.getStart(), testCase.getGoal());
        if (testCase.getBestDistance() == -1) {
            Assert.assertArrayEquals(null, result);
        } else {
            Assert.assertNotNull(result);
            Assert.assertEquals(testCase.getBestDistance(), result.length);
            validatePath(testCase.getMap(), result, testCase.getStart(), testCase.getGoal());
        }
    }

    static {
        createSimpleTestCase();
        createSimpleNoPath();
        createSmallMaze();
        createJPSCornerCase();
    }

    private static void createSimpleTestCase() {
        simpleTestCase = new PathfinderTestCase(new Map(5, 5),
                new Cell(0, 0),
                new Cell(0, 4),
                4
        );
    }

    private static void createSimpleNoPath() {
        Map map = new Map(5, 5);
        for (int i = 0; i < 5; i++) {
            map.setCell(2, i, true);
        }
        simpleNoPath = new PathfinderTestCase(map,
                new Cell(0, 0),
                new Cell(4, 0),
                -1
        );
    }

    private static void createSmallMaze() {
        ArrayList<String> mapString = new ArrayList<String>();
        mapString.add("type octile");
        mapString.add("height 7");
        mapString.add("width 10");
        mapString.add("map");
        mapString.add("..........");
        mapString.add(".XXXXXXXX.");
        mapString.add("........X.");
        mapString.add(".XXXXXX.X.");
        mapString.add(".X......X.");
        mapString.add(".X.XXXXXX.");
        mapString.add("..........");

        Map map = MapLoader.constructMap(mapString);

        smallMaze = new PathfinderTestCase(map,
                new Cell(1, 2),
                new Cell(9, 2),
                14
        );
    }

    private static void createJPSCornerCase() {
        ArrayList<String> mapString = new ArrayList<>();
        mapString.add("type octile");
        mapString.add("height 7");
        mapString.add("width 10");
        mapString.add("map");
        mapString.add("XXXXX.....");
        mapString.add("XXXXX.....");
        mapString.add("..........");
        mapString.add("..........");
        mapString.add("..........");
        mapString.add("..........");
        mapString.add("..........");

        Map map = MapLoader.constructMap(mapString);
        jpsCornerCase = new PathfinderTestCase(map,
                new Cell(2, 4),
                new Cell(6, 0),
                8
        );
    }

    public static void validatePath(Map map, Cell[] path, Cell start, Cell goal) {
        Assert.assertEquals("Last cell is goal", goal, path[path.length - 1]);
        Cell prevCell = start;
        for (Cell cell : path) {
            int dist = Math.abs(prevCell.getX() - cell.getX()) + Math.abs(prevCell.getY() - cell.getY());
            Assert.assertEquals("Contiguous cells in path must be adjacent in map", 1, dist);
            Assert.assertEquals("Cells in path must not be blocked", false, map.isCellBlocked(cell));
            prevCell = cell;
        }
    }
}
