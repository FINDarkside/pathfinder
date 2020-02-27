package tiralabra.benchmark;

public class ScenarioGroupResult {

    public final String scenarioGroupName;

    public final BenchmarkResult bfs;
    public final BenchmarkResult astar;
    public final BenchmarkResult jps;

    public ScenarioGroupResult(String scenarioGroupName, BenchmarkResult bfs, BenchmarkResult astar, BenchmarkResult jps) {
        this.scenarioGroupName = scenarioGroupName;
        this.bfs = bfs;
        this.astar = astar;
        this.jps = jps;
    }

}
