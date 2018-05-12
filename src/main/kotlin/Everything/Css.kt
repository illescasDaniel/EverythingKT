/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

enum class Css(var value: String) {

	additiveSymbols("additive-symbols"),
	alignContent ("align-content"), alignItems ("align-items"), alignSelf ("align-self"),
	all ("all"),
	animation ("animation"), animationDelay ("animation-delay"), animationDirection ("animation-direction"), animationDuration ("animation-duration"),
		animationFillMode ("animation-fill-mode"), animationIterationCount ("animation-iteration-count"), animationName ("animation-name"),
		animationPlayState ("animation-play-state"), animationTimingFunction ("animation-timing-function"), backfaceVisibility ("backface-visibility"),
	background ("background"), backgroundAttachment ("background-attachment"), backgroundBlendMode ("background-blend-mode"),
		backgroundClip ("background-clip"), backgroundColor ("background-color"), backgroundImage ("background-image"), backgroundOrigin ("background-origin"),
		backgroundPosition ("background-position"), backgroundRepeat ("background-repeat"), backgroundSize ("background-size"),
	blockSize ("block-size"),
	border ("border"), borderBlockEnd ("border-block-end"), borderBlockEndColor ("border-block-end-color"), borderBlockEndStyle ("border-block-end-style"),
		borderBlockEndWidth ("border-block-end-width"), borderBlockStart ("border-block-start"), borderBlockStartColor ("border-block-start-color"),
		borderBlockStartStyle ("border-block-start-style"), borderBlockStartWidth ("border-block-start-width"), borderBottom ("border-bottom"),
		borderBottomColor ("border-bottom-color"), borderBottomLeftRadius ("border-bottom-left-radius"), borderBottomRightRadius ("border-bottom-right-radius"),
		borderBottomStyle ("border-bottom-style"), borderBottomWidth ("border-bottom-width"), borderCollapse ("border-collapse"), borderColor ("border-color"),
		borderImage ("border-image"), borderImageOutset ("border-image-outset"), borderImageRepeat ("border-image-repeat"), borderImageSlice ("border-image-slice"),
		borderImageSource ("border-image-source"), borderImageWidth ("border-image-width"), borderInlineEnd ("border-inline-end"), borderInlineEndColor ("border-inline-end-color"),
		borderInlineEndStyle ("border-inline-end-style"), borderInlineEndWidth ("border-inline-end-width"), borderInlineStart ("border-inline-start"),
		borderInlineStartColor ("border-inline-start-color"), borderInlineStartStyle ("border-inline-start-style"), borderInlineStartWidth ("border-inline-start-width"),
		borderLeft ("border-left"), borderLeftColor ("border-left-color"), borderLeftStyle ("border-left-style"), borderLeftWidth ("border-left-width"),
		borderRadius ("border-radius"), borderRight ("border-right"), borderRightColor ("border-right-color"), borderRightStyle ("border-right-style"),
		borderRightWidth ("border-right-width"), borderSpacing ("border-spacing"), borderStyle ("border-style"), borderTop ("border-top"),
		borderTopColor ("border-top-color"), borderTopLeftRadius ("border-top-left-radius"), borderTopRightRadius ("border-top-right-radius"),
		borderTopStyle ("border-top-style"), borderTopWidth ("border-top-width"), borderWidth ("border-width"),
	bottom ("bottom"),
	boxDecorationBreak ("box-decoration-break"), boxShadow ("box-shadow"), boxSizing ("box-sizing"),
	breakAfter ("break-after"), breakBefore ("break-before"), breakInside ("break-inside"),
	captionSide ("caption-side"), caretColor ("caret-color"), ch ("ch"), clear ("clear"),
	clip ("clip"), clipPath ("clip-path"), cm ("cm"), color ("color"),
	columnCount ("column-count"), columnFill ("column-fill"), columnGap ("column-gap"), columnRule ("column-rule"), columnRuleColor ("column-rule-color"),
		columnRuleStyle ("column-rule-style"), columnRuleWidth ("column-rule-width"), columnSpan ("column-span"), columnWidth ("column-width"), columns ("columns"),
	content ("content"),
	counterIncrement ("counter-increment"), counterReset ("counter-reset"), cursor ("cursor"), deg ("deg"), direction ("direction"), display ("display"), dpcm ("dpcm"), dpi ("dpi"),
	dppx ("dppx"), em ("em"), emptyCells ("empty-cells"), ex ("ex"), fallback("fallback"), filter ("filter"),
	flexBasis ("flex-basis"), flexDirection ("flex-direction"), flexFlow ("flex-flow"), flexGrow ("flex-grow"), flexShrink ("flex-shrink"), flexWrap ("flex-wrap"),
	float ("float"),
	font ("font"), fontFamily ("font-family"), fontFeatureSettings ("font-feature-settings"), fontKerning ("font-kerning"), fontLanguageOverride ("font-language-override"),
		fontSize ("font-size"), fontSizeAdjust ("font-size-adjust"), fontStretch ("font-stretch"), fontStyle ("font-style"), fontSynthesis ("font-synthesis"),
		fontVariant ("font-variant"), fontVariantAlternates ("font-variant-alternates"), fontVariantCaps ("font-variant-caps"), fontVariantEastAsian ("font-variant-east-asian"),
		fontVariantLigatures ("font-variant-ligatures"), fontVariantNumeric ("font-variant-numeric"), fontVariantPosition ("font-variant-position"), fontWeight ("font-weight"),
		fr ("fr"), grad ("grad"),
	grid ("grid"), gridArea ("grid-area"), gridAutoColumns ("grid-auto-columns"), gridAutoFlow ("grid-auto-flow"), gridAutoRows ("grid-auto-rows"), gridColumn ("grid-column"),
	gridColumnEnd ("grid-column-end"), gridColumnGap ("grid-column-gap"), gridColumnStart ("grid-column-start"), gridGap ("grid-gap"), gridRow ("grid-row"), gridRowEnd ("grid-row-end"),
	gridRowGap ("grid-row-gap"), gridRowStart ("grid-row-start"), gridTemplate ("grid-template"), gridTemplateAreas ("grid-template-areas"), gridTemplateColumns ("grid-template-columns"),
	gridTemplateRows ("grid-template-rows"),
	height ("height"), hyphens ("hyphens"),
	hz ("hz"), imageOrientation ("image-orientation"), imageRendering ("image-rendering"), imageResolution ("image-resolution"),
	imeMode ("ime-mode"), `in` ("in"), inherit ("inherit"), initial ("initial"), inlineSize ("inline-size"), isolation ("isolation"), justifyContent ("justify-content"), khz ("khz"),
	left ("left"), letterSpacing ("letter-spacing"), lineBreak ("line-break"), lineHeight ("line-height"),
	listStyle ("list-style"), listStyleImage ("list-style-image"), listStylePosition ("list-style-position"), listStyleType ("list-style-type"),
	margin ("margin"), marginBlockEnd ("margin-block-end"), marginBlockStart ("margin-block-start"), marginBottom ("margin-bottom"), marginInlineEnd ("margin-inline-end"),
		marginInlineStart ("margin-inline-start"), marginLeft ("margin-left"), marginRight ("margin-right"), marginTop ("margin-top"),
	mask ("mask"), maskClip ("mask-clip"), maskComposite ("mask-composite"), maskImage ("mask-image"), maskMode ("mask-mode"), maskOrigin ("mask-origin"),
		maskPosition ("mask-position"), maskRepeat ("mask-repeat"), maskSize ("mask-size"),
		maskType ("mask-mimeType"), maxHeight ("max-height"), maxWidth ("max-width"),
	minBlockSize ("min-block-size"), minHeight ("min-height"), minInlineSize ("min-inline-size"), minWidth ("min-width"), mixBlendMode ("mix-blend-mode"),
	mm ("mm"), ms ("ms"), negative("negative"), objectFit ("object-fit"), objectPosition ("object-position"),
	offsetBlockEnd ("offset-block-end"), offsetBlockStart ("offset-block-start"), offsetInlineEnd ("offset-inline-end"), offsetInlineStart ("offset-inline-start"),
	opacity ("opacity"), order ("order"), orientation("orientation"), orphans ("orphans"),
	outline ("outline"), outlineColor ("outline-color"), outlineOffset ("outline-offset"), outlineStyle ("outline-style"), outlineWidth ("outline-width"),
	overflow ("overflow"), overflowWrap ("overflow-wrap"), overflowX ("overflow-x"), overflowY ("overflow-y"),
	pad("pad"),
	padding ("padding"), paddingBlockEnd ("padding-block-end"), paddingBlockStart ("padding-block-start"), paddingBottom ("padding-bottom"), paddingInlineEnd ("padding-inline-end"),
		paddingInlineStart ("padding-inline-start"), paddingLeft ("padding-left"), paddingRight ("padding-right"), paddingTop ("padding-top"),
	pageBreakAfter ("page-break-after"), pageBreakBefore ("page-break-before"), pageBreakInside ("page-break-inside"),
	pc ("pc"),
	perspective ("perspective"), perspectiveOrigin ("perspective-origin"),
	pointerEvents ("pointer-events"), position ("position"), pt ("pt"), px ("px"), quotes ("quotes"), rad ("rad"), range("range"), rem ("rem"), resize ("resize"), revert ("revert"), right ("right"),
	rubyAlign ("ruby-align"), rubyMerge ("ruby-merge"), rubyPosition ("ruby-position"),
	scrollBehavior ("scroll-behavior"), scrollSnapCoordinate ("scroll-snap-coordinate"), scrollSnapDestination ("scroll-snap-destination"), scrollSnapType ("scroll-snap-mimeType"),
	shapeImageThreshold ("shape-image-threshold"), shapeMargin ("shape-margin"), shapeOutside ("shape-outside"),
	speakAs("speak-as"), src("src"), suffix("suffix"), system("system"),
	tabSize ("tab-size"), tableLayout ("table-layout"),
	textAlign ("text-align"), textAlignLast ("text-align-last"), textCombineUpright ("text-combine-upright"), textDecoration ("text-decoration"),
	textDecorationColor ("text-decoration-color"), textDecorationLine ("text-decoration-line"), textDecorationStyle ("text-decoration-style"),
	textEmphasis ("text-emphasis"), textEmphasisColor ("text-emphasis-color"), textEmphasisPosition ("text-emphasis-position"), textEmphasisStyle ("text-emphasis-style"),
	textIndent ("text-indent"), textJustify ("text-justify"), textOrientation ("text-orientation"), textOverflow ("text-overflow"), textRendering ("text-rendering"),
	textShadow ("text-shadow"), textTransform ("text-transform"), textUnderlinePosition ("text-underline-position"),
	top ("top"), touchAction ("touch-action"), transform ("transform"), transformBox ("transform-box"), transformOrigin ("transform-origin"), transformStyle ("transform-style"),
	transition ("transition"), transitionDelay ("transition-delay"), transitionDuration ("transition-duration"), transitionProperty ("transition-property"),
	transitionTimingFunction ("transition-timing-function"),
	turn ("turn"), unicodeBidi ("unicode-bidi"), unicodeRange("unicode-range"), unset ("unset"), userZoom("user-zooom"), verticalAlign ("vertical-align"), vh ("vh"), visibility ("visibility"), vmax ("vmax"), vmin ("vmin"), vw ("vw"),
	whiteSpace ("white-space"), widows ("widows"), width ("width"), willChange ("will-change"), wordBreak ("word-break"), wordSpacing ("word-spacing"), wordWrap ("word-wrap"),
	writingMode ("writing-mode"), zIndex ("z-index"), zoom("zoom"), custom("");

	// Css.custom.value = customProperty("test")

	// ¿? fallback
	// fun annotation() {}

	// fun radialGradient()
	// fun repeat()

	companion object {
		fun attr(attribute: Attribute, fallback: Attribute.TypeOrUnit = Attribute.TypeOrUnit.none): String {
			if (fallback == Attribute.TypeOrUnit.none) {
				return "attr($attribute)"
			}

			return "attr($attribute, ${fallback.value})"
		}

		// ~image ?
		fun url(value: String) = "url(\"$value\")"

		fun customProperty(name: String) = "--$name"

		fun `var`(customPropertyName: String, declarationValue: String = ""): String? {

			if (!customPropertyName.startsWith("--")) {
				return null
			}
			else if (declarationValue.isEmpty()) {
				return "var($customPropertyName)"
			}
			return "var($customPropertyName, $declarationValue)"
		}

		fun blur(pixels: Int) = "blur(${pixels}px)"
		fun dropShadow(values: String) = "drop-shadow($values%)"
		fun hueRotate(angle: Angle) = "hue-rotate(${angle.value})"

		fun brightness(value: Number) = "brightness($value)"
		fun brightness(percent: Pair<Number, LengthType>): String {
			return if (percent.second != LengthType.percent) "brightness()" else "brightness($percent%)"
		}

		fun contrast(value: Number) = "contrast($value)"
		fun contrast(percent: Pair<Number, LengthType>): String {
			return if (percent.second != LengthType.percent) "contrast()" else "contrast($percent%)"
		}

		fun grayscale(value: Number) = "grayscale($value)"
		fun grayscale(percent: Pair<Number, LengthType>): String {
			return if (percent.second != LengthType.percent) "grayscale()" else "grayscale($percent%)"
		}

		fun invert(value: Number) = "invert($value)"
		fun invert(percent: Pair<Number, LengthType>): String {
			return if (percent.second != LengthType.percent) "invert()" else "invert($percent%)"
		}

		fun opacity(value: Number) = "opacity($value)"
		fun opacity(percent: Pair<Number, LengthType>): String {
			return if (percent.second != LengthType.percent) "opacity()" else "opacity($percent%)"
		}

		fun saturate(value: Number) = "saturate($value)"
		fun saturate(percent: Pair<Number, LengthType>): String {
			return if (percent.second != LengthType.percent) "saturate()" else "saturate($percent%)"
		}

		fun sepia(value: Number) = "sepia($value)"
		fun sepia(percent: Pair<Number, LengthType>): String {
			return if (percent.second != LengthType.percent) "sepia()" else "sepia($percent%)"
		}

		// TODO: improve? -  Basic shape
		fun inset(values: String) = "inset($values)"
		fun circle(values: String) = "circle($values)"
		fun ellipse(values: String) = "ellipse($values)"
		fun polygon(values: String) = "polygon($values)"

		fun fitContent(value: Pair<Number, LengthType>) = "fit-content(${Size(value).inHTML})"

		// TODO: can improve?
		fun calc(expression: String) = "calc($expression)"

		fun format(fontFormat: String) = "format($fontFormat)"
		fun element(id: String) = "element($id)"

		fun hsl(angle: Angle, saturation: Pair<Number, LengthType>, lightness: Pair<Number, LengthType>, alpha: Number? = null): String {
			return if (saturation.second != LengthType.percent || lightness.second != LengthType.percent) {
				if (alpha == null) "hsl(${angle.value}, ${saturation.first}%, ${lightness.first}%)"
				else "hsl(${angle.value}, ${saturation.first}%, ${lightness.first}%, $alpha)"
			} else {
				if (alpha == null) "hsl(${angle.value}, $saturation, $lightness)"
				else "hsl(${angle.value}, ${Size(saturation).inHTML}, ${Size(lightness).inHTML}, $alpha)"
			}
		}

		fun hsl(angle: Angle, saturation: Pair<Number, LengthType>, lightness: Pair<Number, LengthType>, alpha: Pair<Number, LengthType>): String {
			return if (saturation.second != LengthType.percent || lightness.second != LengthType.percent || alpha.second != LengthType.percent) {
				"hsl(${angle.value}, ${saturation.first}%, ${lightness.first}%, ${alpha.first}%)"
			} else {
				"hsl(${angle.value}, ${Size(saturation).inHTML}, ${Size(lightness).inHTML}, ${Size(alpha).inHTML})"
			}
		}

		fun hsla(angle: Angle, saturation: Pair<Number, LengthType>, lightness: Pair<Number, LengthType>, alpha: Number): String {
			return hsl(angle, saturation, lightness, alpha)
		}
		fun hsla(angle: Angle, saturation: Pair<Number, LengthType>, lightness: Pair<Number, LengthType>, alpha: Pair<Number, LengthType>): String {
			return hsl(angle, saturation, lightness, alpha)
		}

		// TODO: test
		fun linearGradient(angle: Angle? = null, SideOrCorner: SideOrCorner? = null, colors: ArrayList<CSSColor>): String {
			return if (angle == null) {
				if (SideOrCorner == null) "linear-gradient(${colors.toStringWithoutBrackets()})"
				else "linear-gradient(${SideOrCorner.value}, ${colors.toStringWithoutBrackets()})"
			} else {
				if (SideOrCorner == null) "linear-gradient(${angle.value}, ${colors.toStringWithoutBrackets()})"
				else "linear-gradient(${angle.value}, ${SideOrCorner.value}, ${colors.toStringWithoutBrackets()})"
			}
		}

		fun linearGradient(angle: Angle? = null, SideOrCorner: SideOrCorner? = null, colors: ArrayList<String>): String {
			return if (angle == null) {
				if (SideOrCorner == null) "linear-gradient(${colors.toStringWithoutBrackets()})"
				else "linear-gradient(${SideOrCorner.value}, ${colors.toStringWithoutBrackets()})"
			} else {
				if (SideOrCorner == null) "linear-gradient(${angle.value}, ${colors.toStringWithoutBrackets()})"
				else "linear-gradient(${angle.value}, ${SideOrCorner.value}, ${colors.toStringWithoutBrackets()})"
			}
		}

		fun local(value: String) = "local($value)"

		fun matrix(a: Number, b: Number,
		           c: Number, d: Number,
		           tx: Number, ty: Number): String {
			return "matrix($a, $b, $c, $d, $tx, $ty)"
		}
		fun matrix3D(a1: Number, b1: Number, c1: Number, d1: Number,
		             a2: Number, b2: Number, c2: Number, d2: Number,
		             a3: Number, b3: Number, c3: Number, d3: Number,
		             a4: Number, b4: Number, c4: Number, d4: Number): String {
			return "matrix3d($a1, $b1, $c1, $d1, $a2, $b2, $c2, $d2, $a3, $b3, $c3, $d3, $a4, $b4, $c4, $d4)"
		}

		fun perspective(length: Pair<Number, LengthType>) = "perspective(${Size(length).inHTML})"

		fun rect(top: Pair<Number, LengthType>, right: Pair<Number, LengthType>, bottom: Pair<Number, LengthType>, left: Pair<Number, LengthType>): String {
			return "rect(${Size(top).inHTML}, ${Size(right).inHTML}, ${Size(bottom).inHTML}, ${Size(left).inHTML})"
		}

		fun repeatingLinerGradient(angle: Angle? = null, SideOrCorner: SideOrCorner? = null, colors: ArrayList<CSSColor>): String {
			return if (angle == null) {
				if (SideOrCorner == null) "repeating-linear-gradient(${colors.toStringWithoutBrackets()})"
				else "repeating-linear-gradient(${SideOrCorner.value}, ${colors.toStringWithoutBrackets()})"
			} else {
				if (SideOrCorner == null) "repeating-linear-gradient(${angle.value}, ${colors.toStringWithoutBrackets()})"
				else "repeating-linear-gradient(${angle.value}, ${SideOrCorner.value}, ${colors.toStringWithoutBrackets()})"
			}
		}

		fun rgb(red: Int, green: Int, blue: Int, alpha: Float? = null): String {
			return if (alpha == null) "rgb($red, $green, $blue)" else "rgb($red, $green, $blue, $alpha)"
		}
		fun rgba(red: Int, green: Int, blue: Int, alpha: Float) = rgb(red, green, blue, alpha)

		fun rotate(angle: Angle) = "rotate(${angle.value})"
		fun rotate3D(x: Number, y: Number, z: Number, angle: Angle) = "rotate3d($x, $y, $z, ${angle.value})"

		fun rotateX(angle: Angle) = "rotateX(${angle.value})"
		fun rotateY(angle: Angle) = "rotateY(${angle.value})"
		fun rotateZ(angle: Angle) = "rotateZ(${angle.value})"

		fun scale(sx: Number) = "scale($sx)"
		fun scale(sx: Number, sy: Number) = "scale($sx, $sy)"

		fun scale3D(sx: Number, sy: Number, sz: Number) = "scale3d($sx, $sy, $sz)"

		fun scaleX(scale: Number) = "scaleX($scale)"
		fun scaleY(scale: Number) = "scaleY($scale)"
		fun scaleZ(scale: Number) = "scaleZ($scale)"

		fun skew(angleX: Angle) = "skew(${angleX.value}("
		fun skew(angleX: Angle, angleY: Angle) = "skew(${angleX.value}, ${angleY.value})"

		fun skewX(angle: Angle) = "skewX(${angle.value})"
		fun skewY(angle: Angle) = "skewY(${angle.value})"

		fun translate(translateX: Pair<Number, LengthType>) = "translate(${Size(translateX).inHTML})"
		fun translate(translateX: Pair<Number, LengthType>, translateY: Pair<Number, LengthType>): String {
			return "translate(${Size(translateX).inHTML}, ${Size(translateY).inHTML})"
		}
		fun translate3D(translateX: Pair<Number, LengthType>, translateY: Pair<Number, LengthType>, translateZ: Pair<Number, LengthType>): String {
			return "translate3d(${Size(translateX).inHTML}, ${Size(translateY).inHTML}, ${Size(translateZ).inHTML})"
		}
		fun translateX(length: Pair<Number, LengthType>) = "translateX(${Size(length).inHTML}"
		fun translateY(length: Pair<Number, LengthType>) = "translateY(${Size(length).inHTML}"
		fun translateZ(length: Pair<Number, LengthType>) = "translateZ(${Size(length).inHTML}"

		// TODO: improve?
		fun symbols(value: String) = "symbols($value)"
	}
}

// <angles>

class Angle(val value: String)

val Number.degrees: Angle
	get() = Angle("${this}deg")

val Number.gradians: Angle
	get() = Angle("${this}grad")

val Number.radians: Angle
	get() = Angle("${this}rad")

val Number.turn: Angle
	get() = Angle("${this}turn")

// <side-or-corner>

enum class SideOrCorner(val value: String) {
	toTop("to top"), toBottom("to bottom"),
	toLeft("to left"), toRight("to right"),
	toTopLeft("to top left"), toTopRight("to top right"),
	toBottomLeft("to bottom left"), toBottomRight("to bottom right"),
}

// <type-or-unit>

class Attribute(var attributeName: String, var typeOrUnit: TypeOrUnit = TypeOrUnit.none) {

	enum class TypeOrUnit(val value: String) {
		string("string"), color("color"), url("url"), integer("integer"), number("number"), length_("length"),
		em("em"), ex("ex"), px("px"), rem("rem"), vw("vw"), vh("vh"), vmin("vmin"), vmax("vmax"), mm("mm"), cm("cm"), `in`("in"), pt("pt"), or("or"), pc("pc"),
		angle("angle"), deg("deg"), grad("grad"), rad("rad"), time("time"), s("s"), ms("ms"), frequency("frequency"), Hz("Hz"), kHz("kHz"),
		percent("%"),
		none ("")
	}

	val inHTML: String by lazy {
		"$attributeName ${typeOrUnit.value}"
	}

}

enum class CSSColor(val hexValue: String) {
	black ("#000000"),
	silver ("#c0c0c0"),
	gray ("#808080"),
	white ("#ffffff"),
	maroon ("#800000"),
	red ("#ff0000"),
	purple ("#800080"),
	fuchsia ("#ff00ff"),
	green ("#008000"),
	lime ("#00ff00"),
	olive ("#808000"),
	yellow ("#ffff00"),
	navy ("#000080"),
	blue ("#0000ff"),
	teal ("#008080"),
	aqua ("#00ffff"),
	orange ("#ffa500"),
	aliceblue ("#f0f8ff"),
	antiquewhite ("#faebd7"),
	aquamarine ("#7fffd4"),
	azure ("#f0ffff"),
	beige ("#f5f5dc"),
	bisque ("#ffe4c4"),
	blanchedalmond ("#ffebcd"),
	blueviolet ("#8a2be2"),
	brown ("#a52a2a"),
	burlywood ("#deb887"),
	cadetblue ("#5f9ea0"),
	chartreuse ("#7fff00"),
	chocolate ("#d2691e"),
	coral ("#ff7f50"),
	cornflowerblue ("#6495ed"),
	cornsilk ("#fff8dc"),
	crimson ("#dc143c"),
	cyan ("#00ffff"),
	darkblue ("#00008b"),
	darkcyan ("#008b8b"),
	darkgoldenrod ("#b8860b"),
	darkgray ("#a9a9a9"),
	darkgreen ("#006400"),
	darkgrey ("#a9a9a9"),
	darkkhaki ("#bdb76b"),
	darkmagenta ("#8b008b"),
	darkolivegreen ("#556b2f"),
	darkorange ("#ff8c00"),
	darkorchid ("#9932cc"),
	darkred ("#8b0000"),
	darksalmon ("#e9967a"),
	darkseagreen ("#8fbc8f"),
	darkslateblue ("#483d8b"),
	darkslategray ("#2f4f4f"),
	darkslategrey ("#2f4f4f"),
	darkturquoise ("#00ced1"),
	darkviolet ("#9400d3"),
	deeppink ("#ff1493"),
	deepskyblue ("#00bfff"),
	dimgray ("#696969"),
	dimgrey ("#696969"),
	dodgerblue ("#1e90ff"),
	firebrick ("#b22222"),
	floralwhite ("#fffaf0"),
	forestgreen ("#228b22"),
	gainsboro ("#dcdcdc"),
	ghostwhite ("#f8f8ff"),
	gold ("#ffd700"),
	goldenrod ("#daa520"),
	greenyellow ("#adff2f"),
	grey ("#808080"),
	honeydew ("#f0fff0"),
	hotpink ("#ff69b4"),
	indianred ("#cd5c5c"),
	indigo ("#4b0082"),
	ivory ("#fffff0"),
	khaki ("#f0e68c"),
	lavender ("#e6e6fa"),
	lavenderblush ("#fff0f5"),
	lawngreen ("#7cfc00"),
	lemonchiffon ("#fffacd"),
	lightblue ("#add8e6"),
	lightcoral ("#f08080"),
	lightcyan ("#e0ffff"),
	lightgoldenrodyellow ("#fafad2"),
	lightgray ("#d3d3d3"),
	lightgreen ("#90ee90"),
	lightgrey ("#d3d3d3"),
	lightpink ("#ffb6c1"),
	lightsalmon ("#ffa07a"),
	lightseagreen ("#20b2aa"),
	lightskyblue ("#87cefa"),
	lightslategray ("#778899"),
	lightslategrey ("#778899"),
	lightsteelblue ("#b0c4de"),
	lightyellow ("#ffffe0"),
	limegreen ("#32cd32"),
	linen ("#faf0e6"),
	magenta ("#ff00ff"),
	mediumaquamarine ("#66cdaa"),
	mediumblue ("#0000cd"),
	mediumorchid ("#ba55d3"),
	mediumpurple ("#9370db"),
	mediumseagreen ("#3cb371"),
	mediumslateblue ("#7b68ee"),
	mediumspringgreen ("#00fa9a"),
	mediumturquoise ("#48d1cc"),
	mediumvioletred ("#c71585"),
	midnightblue ("#191970"),
	mintcream ("#f5fffa"),
	mistyrose ("#ffe4e1"),
	moccasin ("#ffe4b5"),
	navajowhite ("#ffdead"),
	oldlace ("#fdf5e6"),
	olivedrab ("#6b8e23"),
	orangered ("#ff4500"),
	orchid ("#da70d6"),
	palegoldenrod ("#eee8aa"),
	palegreen ("#98fb98"),
	paleturquoise ("#afeeee"),
	palevioletred ("#db7093"),
	papayawhip ("#ffefd5"),
	peachpuff ("#ffdab9"),
	peru ("#cd853f"),
	pink ("#ffc0cb"),
	plum ("#dda0dd"),
	powderblue ("#b0e0e6"),
	rosybrown ("#bc8f8f"),
	royalblue ("#4169e1"),
	saddlebrown ("#8b4513"),
	salmon ("#fa8072"),
	sandybrown ("#f4a460"),
	seagreen ("#2e8b57"),
	seashell ("#fff5ee"),
	sienna ("#a0522d"),
	skyblue ("#87ceeb"),
	slateblue ("#6a5acd"),
	slategray ("#708090"),
	slategrey ("#708090"),
	snow ("#fffafa"),
	springgreen ("#00ff7f"),
	steelblue ("#4682b4"),
	tan ("#d2b48c"),
	thistle ("#d8bfd8"),
	tomato ("#ff6347"),
	turquoise ("#40e0d0"),
	violet ("#ee82ee"),
	wheat ("#f5deb3"),
	whitesmoke ("#f5f5f5"),
	yellowgreen ("#9acd32"),
	rebeccapurple ("#663399");

	fun with(percent: Pair<Number, LengthType>): String {
		return "$this ${Size(percent).inHTML}"
	}

	val value: Pair<Css, String> by lazy {
		Css.color to this.hexValue
	}
}
