package tiralabra.pathfinders;

import tiralabra.Map;
import tiralabra.Cell;
import org.junit.Assert;
import org.junit.Test;

public class BFSPathfinderTest {

    @Test
    public void returnsNullWhenNoPathExists() {
        var testCase = TestCases.simpleNoPath;
        TestCases.test(new BFSPathfinder(testCase.getMap()), testCase);
    }

    @Test
    public void returnsShortestPathOnSimpleMap() {
        var testCase = TestCases.simpleTestCase;
        TestCases.test(new BFSPathfinder(testCase.getMap()), testCase);
    }

    @Test
    public void returnsShortestPathOnSmallMaze() {
        var testCase = TestCases.smallMaze;
        TestCases.test(new BFSPathfinder(testCase.getMap()), testCase);
    }

    @Test
    public void worksOnJPSCornerCase() {
        var testCase = TestCases.jpsCornerCase;
        TestCases.test(new BFSPathfinder(testCase.getMap()), testCase);
    }
}
