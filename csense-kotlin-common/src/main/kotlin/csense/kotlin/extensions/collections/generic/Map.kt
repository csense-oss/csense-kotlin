package csense.kotlin.extensions.collections.generic

///**
// * Asynchronusly computes a list of mappings
// * @receiver Collection<T>
// * @param coroutineScope CoroutineScope
// * @param coroutineContext CoroutineContext
// * @param mapper Function1<T, U>
// * @return List<Deferred<U>>
// */
//@Suppress("DeferredIsResult")
//fun <T, U> GenericCollectionExtensions.MapAsync(
//        coroutineScope: CoroutineScope,
//        coroutineContext: CoroutineContext,
//        mapper: Function1<T, U>
//): List<Deferred<U>> {
//    return map {
//        coroutineScope.async(coroutineContext) {
//            mapper(it)
//        }
//    }
//}