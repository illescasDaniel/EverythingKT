/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

external fun require(module: String): dynamic

internal fun allStyleInHTML(globalStyle: RawStyle?, style: RawStyle?, font: Font? = null): String {
	val validFont = font?.inHTML ?: arrayOf()
	return RawStyle(*(globalStyle?.properties.orEmpty() + style?.properties.orEmpty()) + validFont).inHTML
}

internal fun allStyleInHTML(globalStyle: InlineStyle?, style: InlineStyle?, font: Font? = null): String {
	val validFont = font?.inHTML ?: arrayOf()
	return RawStyle(*(globalStyle?.toRawStyle?.properties.orEmpty() + style?.toRawStyle?.properties.orEmpty()) + validFont).inHTML
}

// TODO: delete
internal fun getAllValidProperties(properties: Array<out Pair<String, String>>): String {

	val nonRepeatedProperties = LinkedHashMap<String, String>()

	var finalProperties = ""

	val nonEmptyProperties = properties.filter { it.first.isNotEmpty() && it.second.isNotEmpty() }
	for ((attribute, value) in nonEmptyProperties) {
		nonRepeatedProperties[attribute] = value
	}
	for ((attribute, value) in nonRepeatedProperties) {
		finalProperties += "$attribute: $value; "
	}

	return finalProperties
}

internal fun getAllValidProperties(properties: Array<out Pair<Css, String>>): String {

	val nonRepeatedProperties = LinkedHashMap<String, String>()

	var finalProperties = ""

	val nonEmptyProperties = properties.filter { it.first.value.isNotEmpty() && it.second.isNotEmpty() }
	for ((attribute, value) in nonEmptyProperties) {
		nonRepeatedProperties[attribute.value] = value
	}

	for ((attribute, value) in nonRepeatedProperties) {
		finalProperties += "$attribute: $value; "
	}

	return finalProperties
}
