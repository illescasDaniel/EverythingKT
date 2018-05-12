/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything


class Script(function: () -> Unit, source: String = "") {

	val inJS: String by lazy {
		val functionString = function.toString().trimIndent()
		functionString.substringAfter('{').substringBefore("return").replace("Kotlin.", "kotlin.")
	}

	val asElement: Element by lazy {
		val javascriptCode = this.inJS

		if (!javascriptCode.isBlank() || source.isNotEmpty()) {
			Element(ElementType.Script, content = javascriptCode, properties = hashMapOf(source.propertyOf("src")))
		} else Element("")
	}

	val inHTML: String by lazy {
		this.asElement.inHTML.trimIndent()
	}

	override fun toString(): String = this.inHTML
}
