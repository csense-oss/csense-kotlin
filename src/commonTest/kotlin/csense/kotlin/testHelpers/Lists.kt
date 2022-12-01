@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.testHelpers

import csense.kotlin.patterns.lists.*
import csense.kotlin.tests.assertions.*
import kotlin.contracts.*


inline fun Lists<*>.assertIsEmpty() {
    contract {
        returns() implies (this@assertIsEmpty is Lists.Empty)
    }
    assertIs<Lists.Empty>("Expected Empty list but got: $this")
}

inline fun Lists<*>.assertIsContent() {
    contract {
        returns() implies (this@assertIsContent is Lists.Content<*>)
    }
    assertIs<Lists.Content<*>>("Expected Content but got empty instead")
}