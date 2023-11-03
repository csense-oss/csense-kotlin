@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.*


/**
 *
 * @receiver [Collection]<[Element]>
 * @param action [ReceiverFunctionUnit]<[Element]>
 */
public inline fun <Element> Collection<Element>.forEachWith(
    action: ReceiverFunctionUnit<Element>
): Unit = forEach { element: Element ->
    with(element) {
        action()
    }
}