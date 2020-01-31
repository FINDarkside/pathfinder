package tiralabra.pathfinders;

import tiralabra.Map;
import tiralabra.Cell;
import org.junit.Assert;
import org.junit.Test;

public class JPSPathfinderTest {

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
        JPSPathfinder pathfinder = new JPSPathfinder(map);
        Cell[] result = pathfinder.findPath(testCase.getStart(), testCase.getGoal());
        //System.out.println(Arrays.toString(result));
        if (testCase.getBestDistance() == -1) {
            Assert.assertArrayEquals(null, result);
        } else {
            Assert.assertEquals(testCase.getBestDistance(), result.length);
            TestCases.validatePath(map, result, testCase.getStart(), testCase.getGoal());
        }
    }
}
