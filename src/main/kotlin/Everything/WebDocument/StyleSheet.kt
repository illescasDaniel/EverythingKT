/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything.WebDocument

import Everything.*
import Everything.Common.*

class classListOf(vararg var cssClass: CSSClass): CSSClass {
	override fun toString(): String {
		return cssClass.joinToString(" ")
	}
}

/// You can use your custom external CSS Clases or Bootstrap CSS...
class classesFrom(vararg var cssClasses: String): CSSClass {
	override fun toString(): String {
		return cssClasses.joinToString(" ")
	}
}

open class StyleSheet(var globalStyles: HashMap<ElementType, Style> = hashMapOf(),
                 var idStyles: HashMap<CSSID, Style> = hashMapOf(),
                      var classStyles: HashMap<CSSClass, Style> = hashMapOf()):
		Element(ElementType.Style, "", null, null, null, hashMapOf(), Script({}), arrayListOf()) {

	override val inHTML: String by lazy {

		var globalStyles = ""
		for ((elementType, globalStyle) in this.globalStyles) {
			globalStyles += "${elementType.value}${globalStyle.getNestedElement}${globalStyle.getPseudoClass}${globalStyle.getPseudoElement} { ${globalStyle.inHTML} }\n"
		}

		var globalIDStyles = ""
		for ((id_, idStyle) in this.idStyles) {
			if (id_.toString().isNotEmpty()) {
				globalIDStyles += "#$id_${idStyle.getNestedElement}${idStyle.getPseudoClass}${idStyle.getPseudoElement} { ${idStyle.inHTML} }\n"
			}
		}

		var globalClassStyles = ""
		for ((className, classStyle) in this.classStyles) {
			if (className.toString().isNotEmpty()) {
				globalClassStyles += ".$className${classStyle.getNestedElement}${classStyle.getPseudoClass}${classStyle.getPseudoElement}{ ${classStyle.inHTML} }\n"
			}
		}

		if ((globalClassStyles + globalIDStyles + globalStyles).isNotEmpty()) {
			this.content = "$globalStyles\n$globalIDStyles\n$globalClassStyles"
		}

		super.inHTML
	}
}