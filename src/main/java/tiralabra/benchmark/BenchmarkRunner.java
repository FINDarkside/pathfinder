package tiralabra.benchmark;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import tiralabra.Cell;
import tiralabra.Map;
import tiralabra.datastructure.MyArrayDeque;
import tiralabra.datastructure.MyArrays;
import tiralabra.pathfinders.AstarPathfinder;
import tiralabra.pathfinders.BFSPathfinder;
import tiralabra.pathfinders.Pathfinder;
import tiralabra.pathfinders.jps.JPSPathfinder;

public class BenchmarkRunner {

    public static void runBencmark(Path path) throws IOException {

        File dir = Paths.get(path.toString(), "scenarios").toFile();
        File[] scenarioGroups = dir.listFiles();

        for (File scenarioGroup : scenarioGroups) {
            var result = runScenarioGroup(scenarioGroup);
            System.out.println(result.scenarioGroupName);
            System.out.println("BFS: ");
            System.out.println("Average time: " + (double) result.bfs.averageTime / 1000000 + " ms");
            System.out.println("P25: " + (double) result.bfs.p25 / 1000000 + " ms");
            System.out.println("Median: " + (double) result.bfs.median / 1000000 + " ms");
            System.out.println("P75: " + (double) result.bfs.p75 / 1000000 + " ms");

            System.out.println("A*: ");
            System.out.println("Average time: " + (double) result.astar.averageTime / 1000000 + " ms");
            System.out.println("P25: " + (double) result.astar.p25 / 1000000 + " ms");
            System.out.println("Median: " + (double) result.astar.median / 1000000 + " ms");
            System.out.println("P75: " + (double) result.astar.p75 / 1000000 + " ms");

            System.out.println("JPS: ");
            System.out.println("Average time: " + (double) result.jps.averageTime / 1000000 + " ms");
            System.out.println("P25: " + (double) result.jps.p25 / 1000000 + " ms");
            System.out.println("Median: " + (double) result.jps.median / 1000000 + " ms");
            System.out.println("P75: " + (double) result.jps.p75 / 1000000 + " ms");

            System.out.println("");
        }

    }

    private static ScenarioGroupResult runScenarioGroup(File dir) throws IOException {
        File[] scenarioFiles = dir.listFiles();

        MyArrayDeque<Long> bfsTimes = new MyArrayDeque<>();
        MyArrayDeque<Long> astarTimes = new MyArrayDeque<>();
        MyArrayDeque<Long> jpsTimes = new MyArrayDeque<>();

        Path mapFolder = Paths.get(dir.getParentFile().getParent(), "maps");

        for (File scenarioFile : scenarioFiles) {
            var scenarios = ScenarioLoader.loadScenarios(scenarioFile.toPath(), mapFolder);
            try {
                bfsTimes.addAll(runScenarios(scenarios, BFSPathfinder.class));
                astarTimes.addAll(runScenarios(scenarios, AstarPathfinder.class));
                jpsTimes.addAll(runScenarios(scenarios, JPSPathfinder.class));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        var bfsResult = createResultFromTimes(bfsTimes);
        var astarResult = createResultFromTimes(astarTimes);
        var jpsResult = createResultFromTimes(jpsTimes);

        return new ScenarioGroupResult(dir.getName(), bfsResult, astarResult, jpsResult);
    }

    private static MyArrayDeque<Long> runScenarios(MyArrayDeque<Scenario> scenarios, Class pathfinderClass) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        var constructor = pathfinderClass.getConstructor(Map.class);
        var times = new MyArrayDeque<Long>();

        for (int i = 0; i < scenarios.size(); i++) {
            var scenario = scenarios.get(i);
            var correctResult = new BFSPathfinder(scenario.getMap()).findPath(scenario.getStart(), scenario.getGoal());
            if (correctResult == null) {
                continue;
            }
            var pathfinder = (Pathfinder) constructor.newInstance(scenario.getMap());
            var time = runPathFinder(pathfinder, scenario.getStart(), scenario.getGoal(), correctResult.length);
            times.add(time);
        }
        return times;
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

    private static BenchmarkResult createResultFromTimes(MyArrayDeque<Long> arrayDeque) {
        long[] arr = new long[arrayDeque.size()];
        long total = 0;
        for (int i = 0; i < arrayDeque.size(); i++) {
            total += arrayDeque.get(i);
            arr[i] = arrayDeque.get(i);
        }
        MyArrays.sort(arr);

        return new BenchmarkResult(
                total / arrayDeque.size(),
                arr[arrayDeque.size() / 4 * 3],
                arr[arrayDeque.size() / 4],
                arr[arrayDeque.size() / 2]
        );
    }

}
