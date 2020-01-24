package tiralabra.pathfinders;

import java.util.ArrayList;
import tiralabra.Cell;
import tiralabra.Map;
import tiralabra.MapLoader;

public  class TestCases {

    public static PathfinderTestCase simpleTestCase;
    public static PathfinderTestCase simpleNoPath;
    public static PathfinderTestCase smallMaze;

    static {
        createSimpleTestCase();
        createSimpleNoPath();
        createSmallMaze();
    }

    private static void createSimpleTestCase() {
        Cell[] answer = new Cell[]{
            new Cell(0, 1),
            new Cell(0, 2),
            new Cell(0, 3),
            new Cell(0, 4)
        };

        simpleTestCase = new PathfinderTestCase(new Map(5, 5),
                new Cell(0, 0),
                new Cell(0, 4),
                answer
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
                null
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

        Cell[] answer = new Cell[]{
            new Cell(0, 2),
            new Cell(0, 1),
            new Cell(0, 0),
            new Cell(1, 0),
            new Cell(2, 0),
            new Cell(3, 0),
            new Cell(4, 0),
            new Cell(5, 0),
            new Cell(6, 0),
            new Cell(7, 0),
            new Cell(8, 0),
            new Cell(9, 0),
            new Cell(9, 1),
            new Cell(9, 2)};

        smallMaze = new PathfinderTestCase(map,
                new Cell(1, 2),
                new Cell(9, 2),
                answer
        );
    }
}
