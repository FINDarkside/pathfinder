package tiralabra.pathfinders;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class AstarPathfinderTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);
    
    @Test
    public void returnsNullWhenNoPathExists() {
        var testCase = TestCases.simpleNoPath;
        TestCases.test(new AstarPathfinder(testCase.getMap()), testCase);
    }

    @Test
    public void returnsShortestPathOnSimpleMap() {
        var testCase = TestCases.simpleTestCase;
        TestCases.test(new AstarPathfinder(testCase.getMap()), testCase);
    }

    @Test
    public void returnsShortestPathOnSmallMaze() {
        var testCase = TestCases.smallMaze;
        TestCases.test(new AstarPathfinder(testCase.getMap()), testCase);
    }

    @Test
    public void worksOnJPSCornerCase() {
        var testCase = TestCases.jpsCornerCase;
        TestCases.test(new AstarPathfinder(testCase.getMap()), testCase);
    }
    
    @Test
    public void worksOnJPSCornerCase2() {
        var testCase = TestCases.jpsCornerCase2;
        TestCases.test(new AstarPathfinder(testCase.getMap()), testCase);
    }

    @Test
    public void worksOnJPSCornerCase3() {
        var testCase = TestCases.jpsCornerCase3;
        TestCases.test(new AstarPathfinder(testCase.getMap()), testCase);
    }
}
