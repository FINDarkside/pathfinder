package tiralabra;

import tiralabra.datastructure.MyArrayDeque;
import tiralabra.pathfinders.AstarPathfinder;
import tiralabra.pathfinders.BFSPathfinder;
import tiralabra.pathfinders.Pathfinder;
import tiralabra.pathfinders.jps.JPSPathfinder;

public class BenchmarkRunner {

    public static void runBencmark(MyArrayDeque<Scenario> scenarios) {

        long bfsTime = 0;
        long aStarTime = 0;
        long jpsTime = 0;
        for (int i = 0; i < scenarios.size(); i++) {
            var scenario = scenarios.get(i);
            Cell start = scenario.getStart(), goal = scenario.getGoal();
            int correctLength = new BFSPathfinder(scenario.getMap()).findPath(start, goal).length;

            bfsTime += runPathFinder(new BFSPathfinder(scenario.getMap()), start, goal, correctLength);
            aStarTime += runPathFinder(new AstarPathfinder(scenario.getMap()), start, goal, correctLength);
            jpsTime += runPathFinder(new JPSPathfinder(scenario.getMap()), start, goal, correctLength);
        }

        System.out.println("Total time: ");
        System.out.println("BFS: " + bfsTime / 1000000 + " ms");
        System.out.println("A*: " + aStarTime / 1000000 + " ms");
        System.out.println("JPS: " + jpsTime / 1000000 + " ms");
    }

    private static long runPathFinder(Pathfinder pathfinder, Cell start, Cell goal, int correctLength) {
        long alku = System.nanoTime();
        var path = pathfinder.findPath(start, goal);
        if (path.length != correctLength) {
            throw new RuntimeException("asd");
        }
        long loppu = System.nanoTime();
        return loppu - alku;
    }

}
