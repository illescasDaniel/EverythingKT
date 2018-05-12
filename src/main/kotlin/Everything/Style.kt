/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

// Only used internally
class RawStyle(vararg var properties: Pair<String, String>) {
	val inHTML: String by lazy {
		val finalProperties = getAllValidProperties(properties)
		if (finalProperties.isEmpty()) "" else " style=\"$finalProperties\""
	}
}

class InlineStyle(vararg var properties: Pair<Css, String>, var font: Font = Font()) {

	val toRawStyle: RawStyle by lazy {
		var finalProperties = arrayOf<Pair<String,String>>()
		for (property in properties) {
			finalProperties += Pair(property.first.value, property.second)
		}
		RawStyle(*finalProperties)
	}

	val allPropertiesAsString: String by lazy {
		(getAllValidProperties(this.properties) + getAllValidProperties(this.font.inHTML)).trim()
	}

	val inHTML: String by lazy {
		"style=\"$allPropertiesAsString\""
	}
}

enum class PseudoElement(val value: String) {
	after("after"), before("before"), firstLine("first-line"), selection("selection"), backdrop("backdrop"), placeholder("placeholder"), marker("marker"),
	spellingError("spelling-error"), grammarError("grammar-error"), none("")
}

enum class PseudoClass(val value: String) {
	active("active"), any("any"), checked("checked"), default("default"), disabled("disabled"), empty("emtpy"),
	enabled("enabled"), first("first"), firstChild("first-child"), firstOfType("first-of-mimeType"), fullscreen("fullscreen"),
	focus("focus"), hover("hover"), indeterminate("indeterminate"), inRange("in-range"), invalid("invalid"),
	lastChild("last-child"), lastOfType("last-of-mimeType"), left("left"), link("link"), optional("optional"),
	outOfRange("out-of-range"), readOnly("read-only"), readWrite("read-write"), required("required"),
	right("right"), root("root"), scope("scope"), target("target"), valid("valid"), visited("visited"), none("");

	fun dir(value: String): String { return "dir($value)" }
	fun not(value: String): String { return "not($value)" }
	fun lang(value: String): String { return "lang($value)" }
	fun nthChild(value: String): String { return "nth-child($value)" }
	fun onlyChild(value: String): String { return "only-child($value)" }
	fun nthOfType(value: String): String { return "nth-of-mimeType($value)" }
	fun nthLastChild(value: String): String { return "nth-last-child($value)" }
	fun nthLastOfType(value: String): String { return "nth-last-of-mimeType($value)" }
}

class NestedElement(val name: String) {
	constructor(type: ElementType): this(type.value)
}

class Style(vararg var properties: Pair<Css, String>,
            var nestedElement: NestedElement = NestedElement(""),
            var pseudoElement: PseudoElement = PseudoElement.none,
            var pseudoClass: PseudoClass = PseudoClass.none) {

	val inHTML: String by lazy {
		val finalProperties = getAllValidProperties(properties)
		if (finalProperties.isEmpty()) "" else finalProperties
	}

	override fun toString(): String {
		return inHTML
	}
	val getNestedElement: String by lazy {
		if (this.nestedElement.name.isNotEmpty()) " > ${this.nestedElement.name}" else ""
	}
	val getPseudoElement: String by lazy {
		if (this.pseudoElement.value.isNotEmpty()) "::${this.pseudoElement.value}" else ""
	}
	val getPseudoClass: String by lazy {
		if (this.pseudoClass.value.isNotEmpty()) ":${this.pseudoClass.value}" else ""
	}
	companion object {
		fun from(style: InlineStyle?): Style {
			return Style(*(style?.properties).orEmpty())
		}
	}
}

enum class ElementType(val value: String) {
	Paragraph("p"), HyperLink("a"),
	HeadingH1("h1"), HeadingH2("h2"), HeadingH3("h3"), HeadingH4("h4"), HeadingH5("h5"), HeadingH6("h6"),
	Span("span"),
	ItemList("li"), OrderedList("ol"), UnorderedList("ul"),
	Label("label"), Input("input"),
	Table("table"), TableRow("tr"), TableColumn("td"), TableHead("thead"), TableBody("tbody"),
	Address("address"), Article("article"), Aside("aside"), Footer("footer"), Header("header"), HeadingGroup("hgroup"), Section("section"), Container("div"),
	Nav("nav"),
	Picture("picture"), Image("img"), Video("video"), Audio("audio"), Track("track"), Source("source"),
	BlockQuote("blockquote"), Quote("q"),
	Code("code"),
	Preformatted("pre"),
	Canvas("canvas"),
	Button("button"),
	Form("form"),
	Html("html"),
	Body("body"),
	Head("head"),
	Link("link"),
	Script("script"),
	Metadata("meta"),
	Style("style"),
	Title("title"),
	TextArea("textarea")
}

class Flexbox(var flow: Pair<Direction, Wrap> = Flexbox.rowWrap,
              var justifyContent: JustifyContent = JustifyContent.start,
              var alignItems: AlignItems = AlignItems.stretch,
              var alignContent: AlignContent = AlignContent.stretch) {

	enum class Direction(val value: String) {
		row("row"),
		reversedRow("row-reverse"),
		column("column"),
		reversedColumn("column-reverse")
	}

	enum class Wrap(val value: String) {
		wrap("wrap"),
		nowrap("no-wrap"),
		reversedWrap("wrap-reverse")
	}

	enum class JustifyContent(val value: String) {
		start("flex-start"),
		end("flex-end"),
		center("center"),
		spaceBetween("space-between"),
		spaceAround("space-around")
	}

	enum class AlignItems(val value: String) {
		start("flex-start"),
		end("flex-end"),
		center("center"),
		baseline("baseline"),
		stretch("stretch")
	}

	enum class AlignContent(val value: String) {
		start("flex-start"),
		end("flex-end"),
		center("center"),
		spaceBetween("space-between"),
		spaceAround("space-around"),
		stretch("stretch")
	}

	companion object {
		val rowWrap = Pair(Flexbox.Direction.row, Flexbox.Wrap.wrap)
		val rowNoWrap = Pair(Flexbox.Direction.row, Flexbox.Wrap.nowrap)
		val columnWrap = Pair(Flexbox.Direction.column, Flexbox.Wrap.wrap)
		val columnNoWrap = Pair(Flexbox.Direction.row, Flexbox.Wrap.nowrap)
	}

	val properties: Array<Pair<Css, String>> by lazy {
		arrayOf(Css.display to "flex",
				Css.flexFlow to "${flow.first.value} ${flow.second.value}",
				Css.justifyContent to justifyContent.value,
				Css.alignItems to alignItems.value,
				Css.alignContent to alignContent.value)
	}
}

enum class PreStyle(val value: InlineStyle) {
	centeredRow(InlineStyle(*Flexbox(
			flow = Flexbox.rowWrap,
			justifyContent = Flexbox.JustifyContent.center,
			alignItems = Flexbox.AlignItems.center
	).properties)),
	centeredColumn(InlineStyle(*Flexbox(
			flow = Flexbox.columnWrap,
			justifyContent = Flexbox.JustifyContent.center,
			alignItems = Flexbox.AlignItems.center
	).properties));
	val properties: Array<out Pair<Css,String>> by lazy { this.value.properties }
}

// Based on Material Design Color Palette (https://material.io/guidelines/style/color.html#color-color-palette)
enum class Color(val hexValue: String) {

	black("#000000"),
	gray("#9E9E9E"), lightGray("#E0E0E0"), darkGray("#616161"),
	blueGray("#607D8B"), lightBlueGray("#90A4AE"), darkBlueGray("#455A64"),
	white("#ffffff"),
	red("#F44336"), lightRed("#FF8A80"), darkRed("#C62828"),
	pink("#E91E63"), lightPink("#FF80AB"), darkPink("#D81B60"),
	purple("#AB47BC"), lightPurple("#CE93D8"),
	deepPurple("#673AB7"), deepLightPurple("#9575CD"),
	green("#4CAF50"), lightGreen("#8BC34A"), darkGreen("#2E7D32"), lime("#CDDC39"),
	teal("#009688"), lightTeal("#4DB6AC"), darkTeal("#00796B"),
	blue("#2196F3"), lightBlue("#03A9F4"), darkBlue("#1565C0"), cyan("#00BCD4"), indigo("#3F51B5"),
	yellow("#FFEB3B"), lightYellow("#FFF176"), darkYellow("#FDD835"),
	orange("#FF9800"), amber("#FFC107"), deepOrange("#FF5722");

	val rawValue: Pair<String, String> by lazy {
		"color" to this.hexValue
	}

	val value: Pair<Css, String> by lazy {
		Css.color to this.hexValue
	}
}
