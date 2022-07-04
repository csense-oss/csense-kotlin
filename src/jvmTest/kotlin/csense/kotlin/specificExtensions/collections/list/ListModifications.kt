package csense.kotlin.specificExtensions.collections.list

import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.list.*
import csense.kotlin.specificExtensions.list.*
import csense.kotlin.specificExtensions.list.ListModifications
import kotlinx.benchmark.*
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.Warmup
import java.util.concurrent.*

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 100, time = 10, timeUnit = TimeUnit.MICROSECONDS)
@Measurement(iterations = 100, time = 10, timeUnit = TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(BenchmarkTimeUnit.MICROSECONDS)
open class ListModifications {

    private val lst = listOf<String>(
        "1",
        "a",
        "test",
        "1234"
    ).repeatToSize(1000)

    @Benchmark
    fun optimzedWithChange(): List<String> {
        return lst.modification.replaceAllWithLeastMemory(
            predicate = { it in setOf("1", "1234") },
            replaceWith = { "q" }
        )
    }

    @Benchmark
    fun simpleWithChange(): List<String> {
        return lst.modification.replaceAllWithSimple(
            predicate = { it in setOf("1", "1234") },
            replaceWith = { "q" }
        )
    }

//    @Benchmark
//    fun optimzedWithNoChange(): List<String> {
//        return lst.modification.replaceAllWithLesserMemory(
//            predicate = { false },
//            replaceWith = { "q" }
//        )
//    }
//
//    @Benchmark
//    fun simpleWithNoChange(): List<String> {
//        return lst.modification.replaceAllWithSimple(
//            predicate = { false },
//            replaceWith = { "q" }
//        )
//    }

}

public fun <T> ListModifications<T>.replaceAllWithLeastMemory(
    predicate: (item: T) -> Boolean,
    replaceWith: (item: T) -> T
): List<T> {
    var replaced: MutableMap<Int, T>? = null
    list.forEachIndexed { index, item ->
        if (predicate(item)) {
            replaced = (replaced ?: mutableMapOf()).apply {
                val replacedWith = replaceWith(item)
                put(index, replacedWith)
            }
        }
    }
    if (replaced.isNull()) {
        return list
    }
    return list.mapIndexed { index, item ->
        replaced?.get(index) ?: item
    }
}


public fun <T> ListModifications<T>.replaceAllWithLesserMemory(
    predicate: (item: T) -> Boolean,
    replaceWith: (item: T) -> T
): List<T> {
    val replaced: MutableMap<Int, T> = mutableMapOf()
    list.forEachIndexed { index, item ->
        if (predicate(item)) {
            replaced[index] = replaceWith(item)
        }
    }
    if (replaced.isEmpty()) {
        return list
    }
    return list.mapIndexed { index, item ->
        replaced[index] ?: item
    }
}


public fun <T> ListModifications<T>.replaceAllWithSimple(
    predicate: (item: T) -> Boolean,
    replaceWith: (item: T) -> T
): List<T> {
    return list.map {
        if (predicate(it)) {
            replaceWith(it)
        } else {
            it
        }
    }
}