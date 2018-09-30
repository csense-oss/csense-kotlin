package csense.kotlin.test

import kotlinx.coroutines.experimental.*

fun testAsync(method: suspend () -> Unit) {
    GlobalScope.launch(Dispatchers.Unconfined) {
        method()
    }
}