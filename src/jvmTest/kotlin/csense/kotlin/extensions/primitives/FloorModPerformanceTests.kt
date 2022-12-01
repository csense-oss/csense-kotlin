    //package csense.kotlin.extensions.primitives
    //
    //import csense.kotlin.extensions.primitives.number.*
    //import csense.kotlin.extensions.primitives.operations.*
    //import kotlinx.benchmark.*
    //
    //import java.util.concurrent.*
    //
    //@State(Scope.Benchmark)
    //@BenchmarkMode(Mode.Throughput)
    //@OutputTimeUnit(BenchmarkTimeUnit.NANOSECONDS)
    //@Warmup(iterations = 10, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    //@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    //open class FloorModPerformanceTests {
    //
    //    @Benchmark
    //    open fun javaBuiltIn(): Int {
    //        return Math.floorMod(-40, 30)
    //    }
    //
    //    @Benchmark
    //    open fun customImpl(): Int {
    //        return (-40).floorMod(30)
    //    }
    //
    //}
