@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.testHelpers

import csense.kotlin.patterns.lists.*
import csense.kotlin.tests.assertions.*
import kotlin.contracts.*


inline fun Lists<*>.assertEmpty() {
    contract {
        returns() implies (this@assertEmpty is Lists.Empty)
    }
    assertIs<Lists.Empty>("Expected Empty list but got: $this")
}

inline fun Lists<*>.assertContent() {
    contract {
        returns() implies (this@assertContent is Lists.Content<*>)
    }
    assertIs<Lists.Content<*>>("Expected Content but got empty instead")
}