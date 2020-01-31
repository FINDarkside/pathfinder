package tiralabra.pathfinders;

import org.junit.Test;

public class JPSPathfinderTest {

    @Test
    public void returnsNullWhenNoPathExists() {
        var testCase = TestCases.simpleNoPath;
        TestCases.test(new JPSPathfinder(testCase.getMap()), testCase);
    }

    @Test
    public void returnsShortestPathOnSimpleMap() {
        var testCase = TestCases.simpleTestCase;
        TestCases.test(new JPSPathfinder(testCase.getMap()), testCase);
    }

    @Test
    public void returnsShortestPathOnSmallMaze() {
        var testCase = TestCases.smallMaze;
        TestCases.test(new JPSPathfinder(testCase.getMap()), testCase);
    }

    @Test
    public void worksOnJPSCornerCase() {
        var testCase = TestCases.jpsCornerCase;
        TestCases.test(new JPSPathfinder(testCase.getMap()), testCase);
    }
}
