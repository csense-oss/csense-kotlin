@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin

import kotlinx.coroutines.*

typealias EmptyFunction = () -> Unit

typealias EmptyReceiver<T> = T.() -> Unit
typealias EmptyFunctionResult<T> = () -> T

typealias AsyncEmptyFunction = suspend () -> Unit
typealias AsyncFunctionUnit<T> = suspend (T) -> Unit
typealias AsyncEmptyFunctionResult<T> = suspend () -> T


/**
 * Function with 1 parameter that returns unit
 */
typealias FunctionUnit<E> = (E) -> Unit

typealias SuspendFunctionUnit<E> = suspend (E) -> Unit


//region Function arity
/**
For library functions
 */

typealias Function0<I1> = (I1) -> Unit

/**
 * Function with 1 input and potential output
 */
typealias Function1<I1, O> = (I1) -> O


/**
 * Function with 2 inputs and potential output
 */
typealias Function2<I1, I2, O> = (I1, I2) -> O

/**
 * Function with 3 inputs and potential output
 */
typealias Function3<I1, I2, I3, O> = (I1, I2, I3) -> O

/**
 * Function with 4 inputs and potential output
 */
typealias Function4<I1, I2, I3, I4, O> = (I1, I2, I3, I4) -> O

/**
 * Function with 5 inputs and potential output
 */
typealias Function5<I1, I2, I3, I4, I5, O> = (I1, I2, I3, I4, I5) -> O

/**
 * Function with 6 inputs and potential output
 */
typealias Function6<I1, I2, I3, I4, I5, I6, O> = (I1, I2, I3, I4, I5, I6) -> O
//endregion

//region suspend function arity
typealias AsyncFunction0<O> = suspend () -> O

typealias AsyncFunction1<I, O> = suspend (I) -> O
typealias AsyncFunction2<I1, I2, O> = suspend (I1, I2) -> O
typealias AsyncFunction3<I1, I2, I3, O> = suspend (I1, I2, I3) -> O
typealias AsyncFunction4<I1, I2, I3, I4, O> = suspend (I1, I2, I3, I4) -> O
typealias AsyncFunction5<I1, I2, I3, I4, I5, O> = suspend (I1, I2, I3, I4, I5) -> O
typealias AsyncFunction6<I1, I2, I3, I4, I5, I6, O> = suspend (I1, I2, I3, I4, I5, I6) -> O
//endregion


typealias CoroutineScopeFunction = suspend CoroutineScope.() -> Unit
typealias CoroutineScopeFunction0<O> = suspend CoroutineScope.() -> O
