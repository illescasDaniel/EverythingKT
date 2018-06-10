/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

import Everything.Common.*

open class Element(protected val name: String,
                   var content: String? = "",
                   var id: CSSID? = null,
                   var inlineStyle: InlineStyle? = null,
                   var cssClass: CSSClass? = null,
                   var properties: HashMap<String, String> = hashMapOf(),
                   var script: Script = Script({}),
                   var nested: ArrayList<Element> = arrayListOf(),
                   var escapeHTML: Boolean = true) {

	constructor(name: ElementType,
	            content: String? = "",
	            id: CSSID? = null,
	            inlineStyle: InlineStyle? = null,
	            cssClass: CSSClass? = null,
	            properties: HashMap<String, String> = hashMapOf(),
	            script: Script = Script({}),
	            nested: ArrayList<Element> = arrayListOf()):
			this(name.value, content, id, inlineStyle, cssClass, properties, script, nested)

	constructor(name: String,
	            content: String? = "",
	            id: CSSID? = null,
	            inlineStyle: InlineStyle? = null,
	            cssClass: CSSClass? = null,
	            properties: HashMap<String, Any>,
	            script: Script = Script({}),
	            nested: ArrayList<Element> = arrayListOf()):
			this(name, content, id, inlineStyle, cssClass, HashMap<String,String>(properties.mapValues { it.toString() }), script, nested)

	companion object {
		val empty: Element
			get() = Element("")
	}

	open val inHTML: String by lazy {

		if (name.isBlank()) { return@lazy "" }

		val otherProperties = hashMapOf<String,String>()
		if (!id?.toString().isNullOrEmpty()) otherProperties += "id" to id.toString()
		val allStyles = inlineStyle?.allPropertiesAsString.orEmpty()
		if (allStyles.isNotBlank()) otherProperties += "style" to allStyles
		if (cssClass?.toString().orEmpty().isNotBlank()) otherProperties += "class" to cssClass?.toString().orEmpty()
		properties = otherProperties.plus(properties)

		var allPropertiesInHTML = ""
		val validProperties = properties.filter { it.key.isNotBlank() }
		validProperties.forEach {
			allPropertiesInHTML += if (it.value.isBlank()) {
				" ${it.key}"
			} else {
				" ${it.key}=\"${it.value}\""
			}
		}

		var allNestedElementsInHTML = ""
		val validNestedElements = nested.filter { it.inHTML.isNotBlank() }
		validNestedElements.forEach { allNestedElementsInHTML += "\n${it.inHTML}" }

		val endTag = if (this.content != null) "</$name>" else ""
		val realContent = if (this.content != null) content!!.trim() else ""

		var escapedContent = realContent
		if (this.name != ElementType.Preformatted.value && this.name != ElementType.Code.value) {
			escapedContent = realContent.replace("&", "&amp;")
		}
		if (this.escapeHTML) {
			escapedContent = realContent.replace("<", "&lt;")
										.replace(">", "&gt;")
		}

		var element = """
			${"\n"}<$name${allPropertiesInHTML.trimEnd()}>
				$escapedContent${allNestedElementsInHTML.trim()}
			$endTag
		""".trimIndent()

		if (name != ElementType.Html.value) {
			element += script.inHTML.trimEnd()
		}

		if (name == ElementType.Html.value) {
			"<!DOCTYPE html>\n" + element.beautifiedHTML.trimIndent().replaceMultipleSpaces().replaceMultipleNewLinesOrTabs()
		} else  {
			element
		}
	}

	final override fun toString(): String {
		return this.inHTML
	}
}

class Code(content: String = "", id: CSSID? = null,
           inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
           properties: HashMap<String, String> = hashMapOf(),
           script: Script = Script({}),
           nested: ArrayList<Element> = arrayListOf()):
		Element(
				ElementType.Code,
				content.trimIndent().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;"),
				id, inlineStyle, cssClass, properties, script, nested) {

	override val inHTML: String by lazy {
		val preformatted = Element(ElementType.Preformatted, super.inHTML, id, inlineStyle, cssClass, properties, script, nested)
		preformatted.inHTML
	}
}

class Quote(content: String,
            val lengthType: LengthType,
            var cite: String = "",
            id: CSSID? = null,
            inlineStyle: InlineStyle? = null,
            cssClass: CSSClass? = null,
            properties: HashMap<String, String> = hashMapOf(),
            script: Script = Script({}),
            nested: ArrayList<Element> = arrayListOf()):

		Element(if (lengthType == LengthType.long) ElementType.BlockQuote else ElementType.Quote,
				content, id, inlineStyle, cssClass, properties, script, nested) {

	enum class LengthType { short, long }

	override val inHTML: String by lazy {
		this.properties = properties.plus(cite.propertyOf("cite"))
		super.inHTML
	}
}

class Paragraph(content: String = "", id: CSSID? = null,
                inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
                properties: HashMap<String, String> = hashMapOf(),
                script: Script = Script({}),
                nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.Paragraph, content, id, inlineStyle, cssClass, properties, script, nested)

class Span(content: String = "", id: CSSID? = null,
           inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
           properties: HashMap<String, String> = hashMapOf(),
           script: Script = Script({}),
           nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.Span, content, id, inlineStyle, cssClass, properties, script, nested)

class HyperLink(content: String = "",
                var link: URL = URL("#"),
                var target: Target = Target.self,
                id: CSSID? = null,
                inlineStyle: InlineStyle? = null,
                cssClass: CSSClass? = null,
                properties: HashMap<String, String> = hashMapOf(),
                script: Script = Script({}),
                nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.HyperLink, content, id, inlineStyle, cssClass, properties, script, nested) {

	override val inHTML: String by lazy {
		this.properties = properties.plus(hashMapOf(link.url.trim().propertyOf("href"), "target" to target.value))
		super.inHTML
	}
}

class Heading(content: String,
              val level: Level = Level.default,
              id: CSSID? = null,
              inlineStyle: InlineStyle? = null,
              cssClass: CSSClass? = null,
              properties: HashMap<String, String> = hashMapOf(),
              script: Script = Script({}),
              nested: ArrayList<Element> = arrayListOf()):
		Element(when(level) {
					Level.default -> ElementType.HeadingH3
					Level.highest -> ElementType.HeadingH1
					Level.higher -> ElementType.HeadingH2
					Level.high -> ElementType.HeadingH3
					Level.low -> ElementType.HeadingH4
					Level.lower -> ElementType.HeadingH5
					Level.lowest -> ElementType.HeadingH6
				},
				content, id, inlineStyle, cssClass, properties, script, nested) {

	enum class Level(val value: String) {
		default("h3"),
		highest("h1"),
		higher("h2"),
		high("h3"),
		low("h4"),
		lower("h5"),
		lowest("h6")
	}
}

class Canvas(var width: Int = 300, var height: Int = 150,
             content: String = "", id: CSSID? = null,
             inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
             properties: HashMap<String, String> = hashMapOf(),
             script: Script = Script({}),
             nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.Canvas, content, id, inlineStyle, cssClass, properties, script, nested) {

	override val inHTML: String by lazy {
		this.properties = properties.plus(hashMapOf("width" to width.toString(), "height" to height.toString()))
		super.inHTML
	}
}

class Equation(var preContent: String = "",
               content: String = "", id: CSSID? = null,
               inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
               properties: HashMap<String, String> = hashMapOf(),
               script: Script = Script({}),
               nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.Paragraph, content, id, inlineStyle, cssClass, properties, script, nested) {

	override val inHTML: String by lazy {

		var newContent = this.content?.replace("([a-zA-Z])".toRegex(), "<var>\$1</var>")
		newContent = newContent?.replace("(\\D)(\\^)(\\d)".toRegex(), "\$1<sup>\$3</sup>")

		if (preContent.isNotEmpty()) {
			this.content = "${this.preContent} $newContent"
		}
		super.inHTML
	}
}

enum class Target(val value: String) {
	self("_self"), blank("_blank"), parent("_parent"), top("_top")
}

class ImageHyperLink(val image: Picture,
                     val link: URL,
                     content: String = "", id: CSSID? = null,
                     inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
                     properties: HashMap<String, String> = hashMapOf(),
                     script: Script = Script({}),
                     nested: ArrayList<Element> = arrayListOf()):
			Element(ElementType.HyperLink, content, id, inlineStyle, cssClass, properties, script, nested) {

	override val inHTML: String by lazy {
		HyperLink(content = "", link = this.link, nested = arrayListOf(image) as ArrayList<Element>, id = this.id, inlineStyle = inlineStyle, cssClass = this.cssClass, properties = properties, script = script).inHTML
	}
}

class Container(vararg var elements: Element,
                val type: Container.Type = Container.Type.div,
                content: String = "", id: CSSID? = null,
                inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
                properties: HashMap<String, String> = hashMapOf(),
                script: Script = Script({})):
		Element(type.inElementType, content, id, inlineStyle, cssClass, properties, script, nested = arrayListOf(*elements)) {

	companion object {
		fun address(vararg elements: Element, id: CSSID? = null, inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null): Container {
			return Container(*elements, type = Type.address, id = id, inlineStyle = inlineStyle, cssClass = cssClass)
		}

		fun article(vararg elements: Element, id: CSSID? = null, inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null): Container {
			return Container(*elements, type = Type.article, id = id, inlineStyle = inlineStyle, cssClass = cssClass)
		}

		fun aside(vararg elements: Element, id: CSSID? = null, inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null): Container {
			return Container(*elements, type = Type.aside, id = id, inlineStyle = inlineStyle, cssClass = cssClass)
		}

		fun footer(vararg elements: Element, id: CSSID? = null, inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null): Container {
			return Container(*elements, type = Type.footer, id = id, inlineStyle = inlineStyle, cssClass = cssClass)
		}

		fun header(vararg elements: Element, id: CSSID? = null, inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null): Container {
			return Container(*elements, type = Type.header, id = id, inlineStyle = inlineStyle, cssClass = cssClass)
		}

		fun hgroup(vararg elements: Element, id: CSSID? = null, inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null): Container {
			return Container(*elements, type = Type.hgroup, id = id, inlineStyle = inlineStyle, cssClass = cssClass)
		}

		fun nav(vararg elements: Element, id: CSSID? = null, inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null): Container {
			return Container(*elements, type = Type.nav, id = id, inlineStyle = inlineStyle, cssClass = cssClass)
		}

		fun section(vararg elements: Element, id: CSSID? = null, inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null): Container {
			return Container(*elements, type = Type.section, id = id, inlineStyle = inlineStyle, cssClass = cssClass)
		}
	}

	fun addElements(vararg elements: Element) {
		this.elements += elements
	}

	enum class Type(val inElementType: ElementType) {
		address(ElementType.Address),
		article(ElementType.Article),
		aside(ElementType.Aside),
		div(ElementType.Container),
		footer(ElementType.Footer),
		`header`(ElementType.Header),
		hgroup(ElementType.HeadingGroup),
		nav(ElementType.Nav),
		section(ElementType.Section)
	}
}
