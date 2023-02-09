@file:Suppress("unused")

package csense.kotlin

import kotlinx.coroutines.*

public typealias EmptyFunction = () -> Unit

public typealias EmptyFunctionResult<T> = () -> T

public typealias AsyncEmptyFunction = suspend () -> Unit
public typealias AsyncFunctionUnit<T> = suspend (T) -> Unit
public typealias AsyncEmptyFunctionResult<T> = suspend () -> T


/**
 * Function with 1 parameter that returns unit
 */
public typealias FunctionUnit<E> = (E) -> Unit

public typealias SuspendFunctionUnit<E> = suspend (E) -> Unit


public typealias Function0R<O> = () -> O

/**
For library functions
 */

public typealias Function0<I1> = (I1) -> Unit

/**
 * Function with 1 input and potential output
 */
public typealias Function1<I1, O> = (I1) -> O


/**
 * Function with 2 inputs and potential output
 */
public typealias Function2<I1, I2, O> = (I1, I2) -> O

/**
 * Function with 3 inputs and potential output
 */
public typealias Function3<I1, I2, I3, O> = (I1, I2, I3) -> O

/**
 * Function with 4 inputs and potential output
 */
public typealias Function4<I1, I2, I3, I4, O> = (I1, I2, I3, I4) -> O

/**
 * Function with 5 inputs and potential output
 */
public typealias Function5<I1, I2, I3, I4, I5, O> = (I1, I2, I3, I4, I5) -> O

/**
 * Function with 6 inputs and potential output
 */
public typealias Function6<I1, I2, I3, I4, I5, I6, O> = (I1, I2, I3, I4, I5, I6) -> O

public typealias AsyncFunction0<O> = suspend () -> O

public typealias AsyncFunction1<I, O> = suspend (I) -> O
public typealias AsyncFunction2<I1, I2, O> = suspend (I1, I2) -> O
public typealias AsyncFunction3<I1, I2, I3, O> = suspend (I1, I2, I3) -> O
public typealias AsyncFunction4<I1, I2, I3, I4, O> = suspend (I1, I2, I3, I4) -> O
public typealias AsyncFunction5<I1, I2, I3, I4, I5, O> = suspend (I1, I2, I3, I4, I5) -> O
public typealias AsyncFunction6<I1, I2, I3, I4, I5, I6, O> = suspend (I1, I2, I3, I4, I5, I6) -> O


public typealias CoroutineScopeFunction = suspend CoroutineScope.() -> Unit

public typealias CoroutineScopeFunction0<O> = suspend CoroutineScope.() -> O


public typealias ReceiverFunctionUnit<T> = T.() -> Unit
public typealias ReceiverFunction0<T, O> = T.() -> O
public typealias ReceiverFunction1<T, I1, O> = T.(I1) -> O
public typealias ReceiverFunction2<T, I1, I2, O> = T.(I1, I2) -> O
