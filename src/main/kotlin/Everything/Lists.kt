/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

import Everything.Common.*

class ListItem(content: String, var type: ListItem.Type = ListItem.Type.default,
               id: CSSID? = null, inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
               properties: HashMap<String, String> = hashMapOf(),
               script: Script = Script({}),
               nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.ItemList, content, id, inlineStyle, cssClass, properties, script, nested) {

	enum class Type {
		disc,
		circle,
		square,
		decimal,
		georgian,
		ideographic,
		kannada,
		none,
		default
	}

	override val inHTML: String by lazy {
		if (type.name != ListItem.Type.default.name) {
			this.inlineStyle = InlineStyle(*this.inlineStyle?.properties.orEmpty(), Css.listStyleType to type.name, font = this.inlineStyle?.font ?: Font())
		}
		super.inHTML
	}
}

class List(var items: ArrayList<ListItem>,
           val type: Type = List.Type.unordered,
           var styleType: String = List.StyleType.default,
           id: CSSID? = null,
           inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
           properties: HashMap<String, String> = hashMapOf(),
           script: Script = Script({}),
           nested: ArrayList<Element> = arrayListOf()):
		Element(if (type == List.Type.unordered || StyleType.Unordered.all.contains(styleType)) ElementType.UnorderedList else ElementType.OrderedList,
				"", id, inlineStyle, cssClass, properties, script, nested) {

	companion object {

		fun from(vararg items: String,
		         type: List.Type = List.Type.unordered,
		         styleType: String = List.StyleType.default,
		         id: CSSID? = null,
		         inlineStyle: InlineStyle? = null,
		         cssClass: CSSClass? = null): List {

			val itemsList: ArrayList<ListItem> = arrayListOf()
			items.forEach { itemsList.add(ListItem(it)) }

			return List(itemsList, type = type, styleType = styleType, id = id, inlineStyle = inlineStyle, cssClass = cssClass)
		}
	}

	enum class Type(val value: String) {
		ordered("ol"),
		unordered("ul")
	}

	class StyleType {

		companion object {
			val default = ""
		}

		enum class Unordered {
			disc, circle, square;
			companion object {
				val all: Array<String> = arrayOf(Unordered.disc.name, Unordered.circle.name, Unordered.square.name)
			}
		}
		enum class Ordered(val value: String) {
			decimal("decimal"),
			decimalLeadingZero("decimal-leading-zero"),
			lowerRoman("lower-roman"),
			upperRoman("upper-roman"),
			lowerGreek("lower-greek"),
			lowerAlpha("lower-alpha"),
			lowerLatin("lower-latin"),
			upperAlpha("upper-alpha"),
			upperLatin("upper-latin"),

			cjkDecimal("cjk-decimal "),
			arabicIndic("arabic-indic"),
			armenian("armenian"),
			bengali("bengali"),
			cambodian("cambodian"),
			cjkEarthlyBranch("cjk-earthly-branch"),
			cjkHeavenlyStem("cjk-heavenly-stem"),
			cjkIdeographic("cjk-ideographic"),
			devanagari("devanagari"),
			ethiopicNumeric("ethiopic-numeric"),
			georgian("georgian"),
			gujarati("gujarati"),
			gurmukhi("gurmukhi"),
			hebrew("hebrew"),
			hiragana("hiragana"),
			hiraganaIroha("hiragana-iroha"),
			japaneseFormal("japanese-formal"),
			japaneseInformal("japanese-informal"),
			kannada("kannada")
			// ... https://developer.mozilla.org/en-US/docs/Web/CSS/list-style-type#Specifications
		}
	}

	override val inHTML: String by lazy {

		if (styleType != List.StyleType.default) {
			this.inlineStyle = InlineStyle(*this.inlineStyle?.properties.orEmpty(), Css.listStyleType to styleType, font = this.inlineStyle?.font ?: Font())
		}
		this.nested = ArrayList(items + this.nested)

		super.inHTML
	}
}
