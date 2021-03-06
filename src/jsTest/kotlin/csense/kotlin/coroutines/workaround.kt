package csense.kotlin.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.*

//see https://github.com/Kotlin/kotlinx.coroutines/issues/1996#issuecomment-699606571

val testScope = MainScope()
actual val testCoroutineContext: CoroutineContext = testScope.coroutineContext
actual fun runBlockingTest(block: suspend CoroutineScope.() -> Unit): dynamic = testScope.promise { this.block() }
