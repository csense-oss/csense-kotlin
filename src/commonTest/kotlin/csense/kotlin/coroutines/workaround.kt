package csense.kotlin.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.*

//see https://github.com/Kotlin/kotlinx.coroutines/issues/1996#issuecomment-699606571

expect fun runBlockingTest(block: suspend CoroutineScope.() -> Unit)
expect val testCoroutineContext: CoroutineContext

