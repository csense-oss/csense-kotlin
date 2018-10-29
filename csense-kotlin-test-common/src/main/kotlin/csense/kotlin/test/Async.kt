package csense.kotlin.test

import kotlinx.coroutines.*

fun testAsync(method: suspend () -> Unit) {
    GlobalScope.launch(Dispatchers.Unconfined) {
        method()
    }
}