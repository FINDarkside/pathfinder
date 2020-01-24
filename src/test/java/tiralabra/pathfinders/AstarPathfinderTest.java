package tiralabra.pathfinders;

import tiralabra.Map;
import tiralabra.Cell;
import org.junit.Assert;
import org.junit.Test;

public class AstarPathfinderTest {

    @Test
    public void returnsNullWhenNoPathExists() {
        test(TestCases.simpleNoPath);
    }

    @Test
    public void returnsShortestPathOnSimpleMap() {
        test(TestCases.simpleTestCase);
    }

    @Test
    public void returnsShortestPathOnSmallMaze() {
        test(TestCases.smallMaze);
    }

    private void test(PathfinderTestCase testCase) {
        Map map = testCase.getMap();
        AstarPathfinder pathfinder = new AstarPathfinder(map);
        Cell[] result = pathfinder.findPath(testCase.getStart(), testCase.getGoal());
        //System.out.println(Arrays.toString(result));
        Assert.assertArrayEquals(testCase.getAnswer(), result);
    }
}
