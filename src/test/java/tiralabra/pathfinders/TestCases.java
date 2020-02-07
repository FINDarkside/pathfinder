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
    public static PathfinderTestCase jpsCornerCase2;
    public static PathfinderTestCase jpsCornerCase3;
    public static PathfinderTestCase jpsCornerCase4;

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
        createJPSCornerCase2();
        createJPSCornerCase3();
        createJPSCornerCase4();
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

    private static void createJPSCornerCase2() {
        ArrayList<String> mapString = new ArrayList<>();
        mapString.add("type octile");
        mapString.add("height 7");
        mapString.add("width 10");
        mapString.add("map");
        mapString.add("..........");
        mapString.add("..X...X...");
        mapString.add("..........");
        mapString.add("..X.X..X..");
        mapString.add("..X...X...");
        mapString.add("....X...X.");
        mapString.add("......X...");

        Map map = MapLoader.constructMap(mapString);
        jpsCornerCase2 = new PathfinderTestCase(map,
                new Cell(4, 4),
                new Cell(4, 0),
                6
        );
    }

    private static void createJPSCornerCase3() {
        ArrayList<String> mapString = new ArrayList<>();
        mapString.add("type octile");
        mapString.add("height 3");
        mapString.add("width 10");
        mapString.add("map");
        mapString.add("..........");
        mapString.add(".XXXXXXX..");
        mapString.add("..........");

        Map map = MapLoader.constructMap(mapString);
        jpsCornerCase3 = new PathfinderTestCase(map,
                new Cell(5, 2),
                new Cell(5, 0),
                8
        );
    }

    private static void createJPSCornerCase4() {
        ArrayList<String> mapString = new ArrayList<>();
        mapString.add("type octile");
        mapString.add("height 12");
        mapString.add("width 15");
        mapString.add("map");
        mapString.add("X.............X");
        mapString.add("X.............X");
        mapString.add("X.............X");
        mapString.add("X.............X");
        mapString.add("X.............X");
        mapString.add("X....XXXX.....X");
        mapString.add("XX...XXXX.....X");
        mapString.add("X....XXXX.....X");
        mapString.add("X....XXX......X");
        mapString.add("X.............X");
        mapString.add("X.............X");
        mapString.add("X.............X");

        Map map = MapLoader.constructMap(mapString);
        jpsCornerCase4 = new PathfinderTestCase(map,
                new Cell(7, 11),
                new Cell(6, 1),
                15
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
