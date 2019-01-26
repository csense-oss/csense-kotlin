package csense.kotlin.test

import kotlinx.coroutines.*


fun testAsync(method: suspend () -> Unit) {
    @Suppress("EXPERIMENTAL_API_USAGE")
    GlobalScope.launch(Dispatchers.Unconfined) {
        method()
    }
}