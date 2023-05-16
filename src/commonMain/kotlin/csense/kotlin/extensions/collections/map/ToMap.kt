package csense.kotlin.extensions.collections.map

import csense.kotlin.classes.map.*
import csense.kotlin.specificExtensions.collections.map.*


/**
 * creates a new map by mapping this map
 * @receiver [Map]<OrgKey, OrgValue>
 * @param mapEntry [Function1]<[Map.Entry]<OrgKey, OrgValue>, [MapEntry]<NewKey, NewValue>>
 * @return [Map]<NewKey, NewValue>
 */
@OverloadResolutionByLambdaReturnType
public inline fun <OrgKey, OrgValue, NewKey, NewValue> Map<OrgKey, OrgValue>.toMapViaMapEntry(
    mapEntry: Function1<Map.Entry<OrgKey, OrgValue>, MapEntry<NewKey, NewValue>>
): Map<NewKey, NewValue> = mappings.mapEachEntryWith(LinkedHashMap(size)) { entry: Map.Entry<OrgKey, OrgValue> ->
    this += mapEntry(entry)
}

/**
 *
 * Notice the [Pair.first] is the new key and [Pair.second] is the value
 * @receiver [Map]<OrgKey, OrgValue>
 * @param mapEntryToPair [Function1]<[Map.Entry]<OrgKey, OrgValue>, [Pair]<NewKey, NewValue>>
 * @return [Map]<NewKey, NewValue>
 */
@OverloadResolutionByLambdaReturnType
public inline fun <OrgKey, OrgValue, NewKey, NewValue> Map<OrgKey, OrgValue>.toMapViaKeyValuePair(
    mapEntryToPair: Function1<Map.Entry<OrgKey, OrgValue>, Pair<NewKey, NewValue>>
): Map<NewKey, NewValue> = mappings.mapEachEntryWith(LinkedHashMap(size)) { entry: Map.Entry<OrgKey, OrgValue> ->
    this += mapEntryToPair(entry)
}