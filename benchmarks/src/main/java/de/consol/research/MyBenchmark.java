package de.consol.research;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static de.consol.research.State.BY;
import static java.time.LocalDate.now;

@org.openjdk.jmh.annotations.State(Scope.Benchmark)
public class MyBenchmark {

    private Dashboard dashboard10;
    private Dashboard dashboard100;
    private Dashboard dashboard5000;
    private Dashboard dashboard10000;

    @Setup
    public void setUp() {
        dashboard10 = new Dashboard(makeContracts(10));
        dashboard100 = new Dashboard(makeContracts(100));
        dashboard5000 = new Dashboard(makeContracts(5000));
        dashboard10000 = new Dashboard(makeContracts(10000));
    }

    private List<Contract> makeContracts(int nContracts) {
        return new Random(0)
                .ints(nContracts, 1, 1000)
                .mapToObj(i -> new Contract(BY, i, now()))
                .collect(Collectors.toList());
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public int totalSalesSequential010() {
        return dashboard10.totalSales();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public int totalSalesSequential0100() {
        return dashboard100.totalSales();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public int totalSalesSequential05000() {
        return dashboard5000.totalSales();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public int totalSalesSequential10000() {
        return dashboard10000.totalSales();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public int totalSalesParallel010() {
        return dashboard10.parallelTotalSales();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public int totalSalesParallel0100() {
        return dashboard100.parallelTotalSales();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public int totalSalesParallel05000() {
        return dashboard5000.parallelTotalSales();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public int totalSalesParallel10000() {
        return dashboard10000.parallelTotalSales();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + MyBenchmark.class.getSimpleName() + ".*")
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
