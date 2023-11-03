@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*

/**
 * converts the given arguments either to a [Expected.Success] (if the value is present), or a [Expected.Failed] if not.
 * @receiver Expected.Companion
 * @param potentialSuccess [Value]?
 * @param potentialErrorOrFallback [Error]
 * @return [Expected]<[Value], [Error]>
 */
public inline fun <Value, Error> Expected.Companion.successOrFailed(
    potentialSuccess: Value?,
    potentialErrorOrFallback: Error
): Expected<Value, Error> = expected {
    potentialSuccess?.asSuccess() ?: potentialErrorOrFallback.asFailed()
}