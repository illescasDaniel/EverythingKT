/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

enum class FontStyle(val value: Pair<String, String>) {
	default("" to ""),
	normal(Css.fontStyle.value to "normal"),
	italic(Css.fontStyle.value to "italic"),
	oblique(Css.fontStyle.value to "oblique"),
	bold(Css.fontWeight.value to "bold"),
	thin(Css.fontWeight.value to "lighter")
}

class Font(var size: Font.Size? = null, var textColor: String = "", var family: String = "", var style: FontStyle = FontStyle.default) {

	class Size(var size: Number? = null, var absoluteSize: Font.Size.Unit) {

		constructor(size: Font.Size.Unit): this(null, size)
		constructor(size: Pair<Number, LengthType>): this(size.first, Font.Size.Unit.valueOf(size.second.value))

		enum class Unit {
			smaller, small, medium,
			large, larger, px, em, percent, pt
		}

		companion object {
			val smaller = Font.Size(Font.Size.Unit.smaller)
			val small = Font.Size(Font.Size.Unit.small)
			val medium = Font.Size(Font.Size.Unit.medium)
			val large = Font.Size(Font.Size.Unit.large)
			val larger = Font.Size(Font.Size.Unit.larger)
		}

		val inHTML: String by lazy {
			if (size != null && (this.absoluteSize == Unit.percent || this.absoluteSize == Unit.em || this.absoluteSize == Unit.px || this.absoluteSize == Unit.pt)) {
				"${this.size}${this.absoluteSize}"
			} else {
				this.absoluteSize.toString()
			}
		}
	}

	val asStyle: InlineStyle by lazy {
		InlineStyle(font = Font(size, textColor, family, style))
	}

	val asProperties: Array<Pair<Css,String>> by lazy {
		arrayOf(
				Css.fontSize to (this.size?.inHTML ?: ""),
				Css.fontFamily to this.family,
				Css.color to this.textColor
				)//this.style.value)
	}

	enum class Safe(val value: String) {
		arial("Arial"), helvetica("Helvetica"),
		timesNewRoman("Times New Roman"), times("Times"),
		courierNew("Courier New"), courier("Courier"),
		default("")
	}

	constructor(size: Pair<Number, LengthType>, textColor: String = "", family: String = "", style: FontStyle = FontStyle.default):
			this(Font.Size(size.first, Font.Size.Unit.valueOf(size.second.value)), textColor, family, style)

	constructor(size: Pair<Number, LengthType>, textColor: String = "", family: Font.Safe, style: FontStyle = FontStyle.default):
			this(Font.Size(size.first, Font.Size.Unit.valueOf(size.second.value)), textColor, family.value, style)

	val inHTML: Array<Pair<String, String>> by lazy {

		var outputFontStyle: Array<Pair<String, String>> = arrayOf()

		if (this.textColor.isNotEmpty()) {
			outputFontStyle += (Css.color.value to this.textColor)
		}

		this.size?.let { validSize ->
			outputFontStyle += (Css.fontSize.value to validSize.inHTML)
		}

		if (this.family.isNotEmpty()) {
			outputFontStyle += (Css.fontFamily.value to this.family)
		}

		outputFontStyle += (this.style.value)
		outputFontStyle
	}
}
