package csense.kotlin.coroutines

import kotlinx.coroutines.*
import java.util.concurrent.*
import kotlin.coroutines.*

//see https://github.com/Kotlin/kotlinx.coroutines/issues/1996#issuecomment-699606571

actual val testCoroutineContext: CoroutineContext =
    Executors.newSingleThreadExecutor().asCoroutineDispatcher()

actual fun runBlockingTest(block: suspend CoroutineScope.() -> Unit) =
    runBlocking(testCoroutineContext) { this.block() }
