/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package tiralabra;

import tiralabra.benchmark.ScenarioLoader;
import tiralabra.benchmark.BenchmarkRunner;
import tiralabra.pathfinders.BFSPathfinder;
import java.io.IOException;
import java.nio.file.Paths;

public class App {
    
    public static void main(String[] args) throws IOException {
        BenchmarkRunner.runBencmark(Paths.get("C:\\Users\\FINDarkside\\Downloads\\benchmark"));
    }
    
}
