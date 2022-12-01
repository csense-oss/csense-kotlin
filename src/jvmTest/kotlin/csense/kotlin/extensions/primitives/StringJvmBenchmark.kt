//@file:Suppress("NOTHING_TO_INLINE", "RedundantVisibilityModifier", "unused")
//
//package csense.kotlin.extensions.primitives
//
//import kotlinx.benchmark.*
//import org.openjdk.jmh.annotations.Benchmark
//import org.openjdk.jmh.annotations.BenchmarkMode
//import org.openjdk.jmh.annotations.Measurement
//import org.openjdk.jmh.annotations.Mode
//import org.openjdk.jmh.annotations.OutputTimeUnit
//import org.openjdk.jmh.annotations.Scope
//import org.openjdk.jmh.annotations.State
//import org.openjdk.jmh.annotations.Warmup
//import java.util.concurrent.*
//
//
//public inline fun String.isNewLine1(): Boolean {
//    return this == "\n" || this == "\r\n"
//}
//
////This is somehow faster on almost all jvms, either by a lot or by a very tiny amount, but very consistent(almost always)
//public inline fun String.isNewLine2(): Boolean = when (this.length) {
//    1 -> this == "\n"
//    2 -> this == "\r\n"
//    else -> false
//}
//
//@State(Scope.Benchmark)
////@BenchmarkMode(Mode.Throughput)
//@BenchmarkMode(Mode.AverageTime)
//@OutputTimeUnit(BenchmarkTimeUnit.NANOSECONDS)
//@Warmup(iterations = 100, time = 10, timeUnit = TimeUnit.MICROSECONDS)
//@Measurement(iterations = 100, time = 10, timeUnit = TimeUnit.MICROSECONDS)
//open class StringBenchmarks {
//    @Benchmark
//    open fun stringIsNewLineNot(): Boolean {
//        return "".isNewLine1()
//    }
//
//    @Benchmark
//    open fun stringIsNewLineNot2(): Boolean {
//        return "".isNewLine2()
//    }
//
//    @Benchmark
//    open fun stringIsNewLine1(): Boolean {
//        return "\r\n".isNewLine1()
//    }
//
//    @Benchmark
//    open fun stringIsNewLine2(): Boolean {
//        return "\r\n".isNewLine2()
//    }
//
//    @Benchmark
//    open fun stringIsNewLine1Long(): Boolean {
//        return "\r\nasdasdasd".isNewLine1()
//    }
//
//    @Benchmark
//    open fun stringIsNewLine2Long(): Boolean {
//        return "\r\nasdasdasd".isNewLine2()
//    }
//}