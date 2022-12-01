//package csense.kotlin.performance
//
//import kotlinx.benchmark.*
//import org.openjdk.jmh.annotations.BenchmarkMode
//import org.openjdk.jmh.annotations.Fork
//import org.openjdk.jmh.annotations.Measurement
//import org.openjdk.jmh.annotations.Mode
//import org.openjdk.jmh.annotations.OutputTimeUnit
//import org.openjdk.jmh.annotations.Warmup
//import java.util.concurrent.TimeUnit
//
//
//@State(Scope.Benchmark)
//@Fork(1)
//@Warmup(iterations = 100, time = 10, timeUnit = TimeUnit.MICROSECONDS)
//@Measurement(iterations = 100, time = 10, timeUnit = TimeUnit.MICROSECONDS)
//@BenchmarkMode(Mode.Throughput)
//@OutputTimeUnit(BenchmarkTimeUnit.MICROSECONDS)
//open class StringBuilderPerf {
//    private val str: String = "1234a boring string"
//
//    @Benchmark
//    fun substring(): StringBuilder {
//        val data = StringBuilder()
//        data.append(str.substring(4))
//        return data
//    }
//
//    @Benchmark
//    fun overload(): StringBuilder {
//        val data = StringBuilder()
//        data.append(str, 4, str.length)
//        return data
//    }
//}