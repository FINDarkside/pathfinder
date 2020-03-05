package tiralabra.benchmark;

public class BenchmarkResult {

    public final long averageTime;
    public final long p75;
    public final long median;
    public final long p25;

    public BenchmarkResult(long averageTime, long p75, long p25, long median) {
        this.averageTime = averageTime;
        this.p75 = p75;
        this.p25 = p25;
        this.median = median;
    }

}
