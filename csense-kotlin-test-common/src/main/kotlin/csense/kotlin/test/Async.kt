package csense.kotlin.test

import kotlinx.coroutines.experimental.Unconfined
import kotlinx.coroutines.experimental.launch

fun testAsync(method: suspend () -> Unit) {
    launch(Unconfined) {
        method()
    }
}