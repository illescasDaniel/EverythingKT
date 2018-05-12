/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

fun <T> Collection<T>.toStringWithoutBrackets(): String {
	return this.toString().replace("[", "").replace("]", "")
}

// FIXME: not working
fun String.replaceMultipleSpaces(): String {
	return this//.trim().replace(" +".toRegex(), " ")
}

// FIXME: not working
fun String.replaceMultipleNewLinesOrTabs(): String {
	return this//.replace("\n+\t*\n+".toRegex(),"\n")
}

fun String.propertyOf(attribute: String): Pair<String, String> {
	return if (this.isNotEmpty()) (attribute to this) else ("" to "")
}

fun Boolean.propertyOf(attribute: String): Pair<String, String> {
	return if (this) (attribute to "") else ("" to "")
}

//
fun String.attribute(tag: String): String {
	return if (this.isNotEmpty()) "$tag=\"$this\"" else ""
}

fun Boolean.attribute(tag: String): String {
	return if (this) tag else ""
}

fun <K,V> HashMap<K,V>.plus(pair: Pair<K,V>): HashMap<K,V> {
	return (this + hashMapOf(pair.first to pair.second)) as HashMap<K, V>
}

fun <K,V> HashMap<K,V>.plus(hashMap: HashMap<K,V>): HashMap<K,V> {
	return (this + hashMap) as HashMap<K,V>
}

val String.beautifiedJS: String
	get() {
		class Options {
			var indent_size: Number = 4
			var indent_with_tabs: Boolean = true
			var preserve_newlines: Boolean = false
		}

		val beautifyHTML = require("js-beautify")

		if (beautifyHTML !== null && beautifyHTML !== undefined) {
			return beautifyHTML(this, Options()).toString()
		}
		return this.trim()
	}

val String.beautifiedHTML: String
	get() {
		class Options {
			var indent_size: Number = 4
			var indent_with_tabs: Boolean = true
			var preserve_newlines: Boolean = false
		}

		val beautifyHTML = require("js-beautify").html

		if (beautifyHTML !== null && beautifyHTML !== undefined) {
			return beautifyHTML(this, Options()).toString()
		}
		return this.trim()
	}

val String.uglyfiedHTML: String
	get() {
		return this.replaceMultipleSpaces().replace("(\n+)|(\t+)".toRegex(), "")
	}

// Element Size

enum class LengthType(val value: String) {
	px("px"), percent("%"), em("em"), mm("mm"), q("q"), cm("cm"), `in`("in"), pt("pt"), pc("pc"), mozmm("mozmm"),
	ch("ch"), ex("ex"), cap("cap"), ic("ic"), rem("rem"), lh("lh"), rlh("rlh"),
	vh("vh"), vw("vw"), vi("vi"), vb("vb"), vmin("vmin"), vmax("vmax");
	override fun toString() = this.value
}

class Size(val element: Pair<Number, LengthType>) {
	val inHTML: String by lazy {
		"${this.element.first}${this.element.second}"
	}
	override fun toString(): String {
		return inHTML
	}
}

val <Type,Type2> Pair<Type,Type2>.value: String
	get() = "${this.first}${this.second}"

val Int.px: Pair<Int, LengthType>
	get() = Pair(this, LengthType.px)

val Number.pt: Pair<Number, LengthType>
	get() = Pair(this, LengthType.pt)

val Number.percent: Pair<Number, LengthType>
	get() = Pair(this, LengthType.percent)

val Number.em: Pair<Number, LengthType>
	get() = Pair(this, LengthType.em)

// ...