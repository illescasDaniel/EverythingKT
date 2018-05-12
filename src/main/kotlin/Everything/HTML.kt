/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

import Everything.Common.*
import Everything.WebDocument.StyleSheet

class HTML(var fileName: String = "index",
           var metadata: Metadata = Metadata(),
           var styleSheet: StyleSheet = StyleSheet(),
           var body: Body,
           inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
           properties: HashMap<String, String> = hashMapOf(),
           script: Script = Script({}),
           nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.Html, "", null, inlineStyle, cssClass, properties, Script({}), nested) {

	companion object {
		fun toFile(pages: ArrayList<HTML>) {

			FileSystem.makeDir("./public")
			//FileSystem.copyFile("./src/main/kotlin/Everything/minifiedKotlin.js", "./public")
			FileSystem.makeDir("public/kotlin2JS")
			FileSystem.copyFile("node_modules/kotlin/kotlin.js", "public/kotlin2JS/index.js")

			for (page in pages) {
				val fileNameToWrite = "public/${page.fileName}.html"
				FileSystem.writeFile(fileNameToWrite, page.inHTML)
			}
		}
	}

	val hasScripts: Boolean
		get() {
			var hasSomeScript = false
			this.body.nested.forEach { if (it.script.inJS.isNotEmpty()) hasSomeScript = true }
			if (this.script.inJS.isNotEmpty()) hasSomeScript = true
			return hasSomeScript
		}

	fun toFile() {

		//FileSystem.makeDirHere("public")
		FileSystem.makeDir("./public")

		if (this.hasScripts) {
			//FileSystem.copyFile("src/main/kotlin/Everything/minifiedKotlin.js", "public/minifiedKotlin.js")
			//FileSystem.copyFile("Kotlin2JavaScript/out/production/Kotlin2JavaScript/lib/kotlin.js", "public/index.js")
			FileSystem.makeDir("public/kotlin2JS")
			FileSystem.copyFile("node_modules/kotlin/kotlin.js", "public/kotlin2JS/index.js")
		}

		val fileNameToWrite = "public/${this.fileName}.html"
		FileSystem.writeFile(fileNameToWrite, this.inHTML)
	}

	fun writeOutputToPageFolder() {
		val fileNameToWrite = "public/${this.fileName}.html"
		FileSystem.writeFile(fileNameToWrite, this.inHTML)
	}

	// FIXME: not working!
	fun elementWith(id: String, container: ArrayList<Element> = this.body.nested): Element? {

		// var allElements = ArrayList<Element>()

		for (element in container) {
			if (element.id.toString() == id) {
				return element
			} else if (element.nested.isNotEmpty()) {
				elementWith(id, element.nested)//element.elements.find { it.id == id }
			}
		}

		//println("wtf???")
		return null
	}

	override val inHTML: String by lazy {
		this.nested = arrayListOf(metadata, styleSheet, this.body, this.script.asElement)
		super.inHTML
	}
}

class Metadata(var title: String = "Blank",
               var charset: Charset = Charset.UTF_8,
               var externalCSS: Array<Path> = arrayOf(),
               var externalScripts: Array<Path> = arrayOf(),
               var MobileIconPath: Path? = null,
               var featuresOptions: Set<Metadata.Features> = setOf(),
               properties: HashMap<String, String> = hashMapOf(),
               nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.Head, "", null, null, null, properties, Script({}), nested) {

	enum class Features {
		jQuery, Bootstrap, mobileOptimizations
	}

	override val inHTML: String by lazy {

		val isJQueryEnabled = featuresOptions.contains(Metadata.Features.jQuery)
		val isBootstrapEnabled = featuresOptions.contains(Metadata.Features.Bootstrap)
		val areMobileOptimizationsEnabled = featuresOptions.contains(Metadata.Features.mobileOptimizations)

		val titleElement = Element(ElementType.Title, content = this.title)

		val metadataElements = arrayListOf<Element>()
		metadataElements += Element(ElementType.Metadata, content = null, properties = hashMapOf("charset" to this.charset.value))
		if (areMobileOptimizationsEnabled) {
			metadataElements += Element(ElementType.Metadata, content = null, properties = hashMapOf(
					"name" to "HandheldFriendly",
					"content" to "True"
			))
			metadataElements += Element(ElementType.Metadata, content = null, properties = hashMapOf(
					"name" to "MobileOptimized",
					"content" to "320"
			))
			metadataElements += Element(ElementType.Metadata, content = null, properties = hashMapOf(
					"name" to "viewport",
					"content" to "width=device-width, initial-scale=1"
			))
		}

		val cssStyles = arrayListOf<Element>()
		for (css in this.externalCSS) {
			cssStyles.add(Element(ElementType.Link, content = null, properties = hashMapOf(
					"rel" to "stylesheet",
					"href" to css.filePath
			)))
		}
		/// Compatible with iOS right now, will be updated if Android introduces a similar feature
		if (!MobileIconPath?.filePath.isNullOrBlank()) {
			cssStyles += Element(ElementType.Link, content = null, properties = hashMapOf(
					"rel" to "apple-touch-icon-precomposed",
					"href" to this.MobileIconPath?.filePath.orEmpty()
			))
		}

		val allScriptsElements = arrayListOf<Element>()
		for (script in this.externalScripts) {
			cssStyles.add(Element(ElementType.Script, properties = hashMapOf(
					"src" to script.filePath,
					"charset" to "UTF-8"
			)))
		}
		if (isJQueryEnabled) {
			allScriptsElements += Element(ElementType.Script, properties = hashMapOf(
					"src" to "https://code.jquery.com/jquery-3.2.1.min.js",
					"charset" to "utf-8"
			))
		}
		if (isBootstrapEnabled) {
			cssStyles += Element(ElementType.Link, content = null, properties = hashMapOf(
					"rel" to "stylesheet",
					"href" to "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css",
					"integrity" to "sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u",
					"crossorigin" to "anonymous"
			))
			cssStyles += Element(ElementType.Link, content = null, properties = hashMapOf(
					"rel" to "stylesheet",
					"href" to "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css",
					"integrity" to "sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp",
					"crossorigin" to "anonymous"
			))
			allScriptsElements += Element(ElementType.Script, properties = hashMapOf(
					"src" to "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js",
					"integrity" to "sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa",
					"crossorigin" to "anonymous"
			))
		}

		this.nested = ArrayList(metadataElements + titleElement + cssStyles + allScriptsElements + nested)

		super.inHTML
	}
}

class Body(nested: ArrayList<Element>,
           content: String = "",
           id: CSSID? = null,
           inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
           properties: HashMap<String, String> = hashMapOf(),
           script: Script = Script({}),
           var startScripts: Array<Path> = arrayOf(),
           var endScripts: Array<Path> = arrayOf()):
		Element(ElementType.Body, content, id, inlineStyle, cssClass, properties, script, nested) {

	override val inHTML: String by lazy {

		val allEndScripts = this.endScripts.map {
			Element(ElementType.Script, properties = hashMapOf(
					"src" to it.filePath,
					"charset" to Charset.UTF_8.value
			))
		}

		val allBegginingScripts = this.startScripts.map {
			Element(ElementType.Script, properties = hashMapOf(
					"src" to it.filePath,
					"charset" to Charset.UTF_8.value
			))
		}

		this.nested = (allBegginingScripts + this.nested.filter { it !is HTML && it !is Body && it !is Metadata } + allEndScripts) as ArrayList<Element>
		super.inHTML
	}
}

// https://www.iana.org/assignments/character-sets/character-sets.xhtml
enum class Charset(val value: String) {
	US_ASCII ("US-ASCII"), ISO_8859_1_1987 ("ISO_8859-1:1987"), ISO_8859_2_1987 ("ISO_8859-2:1987"), ISO_8859_3_1988 ("ISO_8859-3:1988"), ISO_8859_4_1988 ("ISO_8859-4:1988"), ISO_8859_5_1988 ("ISO_8859-5:1988"), ISO_8859_6_1987 ("ISO_8859-6:1987"), ISO_8859_7_1987 ("ISO_8859-7:1987"), ISO_8859_8_1988 ("ISO_8859-8:1988"), ISO_8859_9_1989 ("ISO_8859-9:1989"), ISO_8859_10 ("ISO-8859-10"), ISO_6937_2_add ("ISO_6937-2-add"), JIS_X0201 ("JIS_X0201"), JIS_Encoding ("JIS_Encoding"), Shift_JIS ("Shift_JIS"), ExtendedUNIXCodePackedFormatForJapanese ("Extended_UNIX_Code_Packed_Format_for_Japanese"), ExtendedUNIXCodeFixedWidthForJapanese ("Extended_UNIX_Code_Fixed_Width_for_Japanese"), BS_4730 ("BS_4730"), SEN_850200_C ("SEN_850200_C"), IT ("IT"), ES ("ES"), DIN_66003 ("DIN_66003"), NS_4551_1 ("NS_4551-1"), NF_Z_62_010 ("NF_Z_62-010"), ISO_10646_UTF_1 ("ISO-10646-UTF-1"), ISO_646_basic_1983 ("ISO_646.basic:1983"), INVARIANT ("INVARIANT"), ISO_646_irv_1983 ("ISO_646.irv:1983"), NATS_SEFI ("NATS-SEFI"), NATS_SEFI_ADD ("NATS-SEFI-ADD"), NATS_DANO ("NATS-DANO"), NATS_DANO_ADD ("NATS-DANO-ADD"), SEN_850200_B ("SEN_850200_B"), KS_C_5601_1987 ("KS_C_5601-1987"), ISO_2022_KR ("ISO-2022-KR"), EUC_KR ("EUC-KR"), ISO_2022_JP ("ISO-2022-JP"), ISO_2022_JP_2 ("ISO-2022-JP-2"), JIS_C6220_1969_jp ("JIS_C6220-1969-jp"), JIS_C6220_1969_ro ("JIS_C6220-1969-ro"), PT ("PT"), greek7_old ("greek7-old"), latinGreek ("latin-greek"), NF_Z_62_010__1973_ ("NF_Z_62-010_(1973)"), LatinGreek1 ("Latin-greek-1"), ISO_5427 ("ISO_5427"), JIS_C6226_1978 ("JIS_C6226-1978"), BS_viewdata ("BS_viewdata"), INIS ("INIS"), INIS_8 ("INIS-8"), INIS_cyrillic ("INIS-cyrillic"), ISO_5427_1981 ("ISO_5427:1981"), ISO_5428_1980 ("ISO_5428:1980"), GB_1988_80 ("GB_1988-80"), GB_2312_80 ("GB_2312-80"), NS_4551_2 ("NS_4551-2"), videotex_suppl ("videotex-suppl"), PT2 ("PT2"), ES2 ("ES2"), MSZ_7795_3 ("MSZ_7795.3"), JIS_C6226_1983 ("JIS_C6226-1983"), greek7 ("greek7"), ASMO_449 ("ASMO_449"), iso_ir_90 ("iso-ir-90"), JIS_C6229_1984_a ("JIS_C6229-1984-a"), JIS_C6229_1984_b ("JIS_C6229-1984-b"), JIS_C6229_1984_b_add ("JIS_C6229-1984-b-add"), JIS_C6229_1984_hand ("JIS_C6229-1984-hand"), JIS_C6229_1984_hand_add ("JIS_C6229-1984-hand-add"), JIS_C6229_1984_kana ("JIS_C6229-1984-kana"), ISO_2033_1983 ("ISO_2033-1983"), ANSI_X3_110_1983 ("ANSI_X3.110-1983"), T_61_7bit ("T.61-7bit"), T_61_8bit ("T.61-8bit"), ECMAcyrillic ("ECMA-cyrillic"), CSA_Z243_4_1985_1 ("CSA_Z243.4-1985-1"), CSA_Z243_4_1985_2 ("CSA_Z243.4-1985-2"), CSA_Z243_4_1985_gr ("CSA_Z243.4-1985-gr"), ISO_8859_6_E ("ISO_8859-6-E"), ISO_8859_6_I ("ISO_8859-6-I"), T_101_G2 ("T.101-G2"), ISO_8859_8_E ("ISO_8859-8-E"), ISO_8859_8_I ("ISO_8859-8-I"), CSN_369103 ("CSN_369103"), JUS_I_B1_002 ("JUS_I.B1.002"), IEC_P27_1 ("IEC_P27-1"), JUS_I_B1_003_serb ("JUS_I.B1.003-serb"), JUS_I_B1_003_mac ("JUS_I.B1.003-mac"), greek_ccitt ("greek-ccitt"), NC_NC00_10_81 ("NC_NC00-10:81"), ISO_6937_2_25 ("ISO_6937-2-25"), GOST_19768_74 ("GOST_19768-74"), ISO_8859_supp ("ISO_8859-supp"), ISO_10367_box ("ISO_10367-box"), latin_lap ("latin-lap"), JIS_X0212_1990 ("JIS_X0212-1990"), DS_2089 ("DS_2089"), us_dk ("us-dk"), dk_us ("dk-us"), KSC5636 ("KSC5636"), UNICODE_1_1_UTF_7 ("UNICODE-1-1-UTF-7"), ISO_2022_CN ("ISO-2022-CN"), ISO_2022_CN_EXT ("ISO-2022-CN-EXT"), UTF_8 ("UTF-8"), ISO_8859_13 ("ISO-8859-13"), ISO_8859_14 ("ISO-8859-14"), ISO_8859_15 ("ISO-8859-15"), ISO_8859_16 ("ISO-8859-16"), GBK ("GBK"), GB18030 ("GB18030"), OSD_EBCDIC_DF04_15 ("OSD_EBCDIC_DF04_15"), OSD_EBCDIC_DF03_IRV ("OSD_EBCDIC_DF03_IRV"), OSD_EBCDIC_DF04_1 ("OSD_EBCDIC_DF04_1"), ISO_11548_1 ("ISO-11548-1"), KZ_1048 ("KZ-1048"), ISO_10646_UCS_2 ("ISO-10646-UCS-2"), ISO_10646_UCS_4 ("ISO-10646-UCS-4"), ISO_10646_UCS_Basic ("ISO-10646-UCS-Basic"), ISO_10646_Unicode_Latin1 ("ISO-10646-Unicode-Latin1"), ISO_10646_J_1 ("ISO-10646-J-1"), ISO_Unicode_IBM_1261 ("ISO-Unicode-IBM-1261"), ISO_Unicode_IBM_1268 ("ISO-Unicode-IBM-1268"), ISO_Unicode_IBM_1276 ("ISO-Unicode-IBM-1276"), ISO_Unicode_IBM_1264 ("ISO-Unicode-IBM-1264"), ISO_Unicode_IBM_1265 ("ISO-Unicode-IBM-1265"), UNICODE_1_1 ("UNICODE-1-1"), SCSU ("SCSU"), UTF_7 ("UTF-7"), UTF_16BE ("UTF-16BE"), UTF_16LE ("UTF-16LE"), UTF_16 ("UTF-16"), CESU_8 ("CESU-8"), UTF_32 ("UTF-32"), UTF_32BE ("UTF-32BE"), UTF_32LE ("UTF-32LE"), BOCU_1 ("BOCU-1"), ISO_8859_1_Windows_3_0_Latin_1 ("ISO-8859-1-Windows-3.0-Latin-1"), ISO_8859_1_Windows_3_1_Latin_1 ("ISO-8859-1-Windows-3.1-Latin-1"), ISO_8859_2_Windows_Latin_2 ("ISO-8859-2-Windows-Latin-2"), ISO_8859_9_Windows_Latin_5 ("ISO-8859-9-Windows-Latin-5"), hp_roman8 ("hp-roman8"), AdobeStandardEncoding ("Adobe-Standard-Encoding"), Ventura_US ("Ventura-US"), VenturaInternational ("Ventura-International"), DEC_MCS ("DEC-MCS"), IBM850 ("IBM850"), PC8DanishNorwegian ("PC8-Danish-Norwegian"), IBM862 ("IBM862"), PC8_Turkish ("PC8-Turkish"), IBMSymbols ("IBM-Symbols"), IBMThai ("IBM-Thai"), HPLegal ("HP-Legal"), HP_Pi_font ("HP-Pi-font"), HPMath8 ("HP-Math8"), AdobeSymbolEncoding ("Adobe-Symbol-Encoding"), HPDeskTop ("HP-DeskTop"), VenturaMath ("Ventura-Math"), MicrosoftPublishing ("Microsoft-Publishing"), Windows_31J ("Windows-31J"), GB2312 ("GB2312"), Big5 ("Big5"), macintosh ("macintosh"), IBM037 ("IBM037"), IBM038 ("IBM038"), IBM273 ("IBM273"), IBM274 ("IBM274"), IBM275 ("IBM275"), IBM277 ("IBM277"), IBM278 ("IBM278"), IBM280 ("IBM280"), IBM281 ("IBM281"), IBM284 ("IBM284"), IBM285 ("IBM285"), IBM290 ("IBM290"), IBM297 ("IBM297"), IBM420 ("IBM420"), IBM423 ("IBM423"), IBM424 ("IBM424"), IBM437 ("IBM437"), IBM500 ("IBM500"), IBM851 ("IBM851"), IBM852 ("IBM852"), IBM855 ("IBM855"), IBM857 ("IBM857"), IBM860 ("IBM860"), IBM861 ("IBM861"), IBM863 ("IBM863"), IBM864 ("IBM864"), IBM865 ("IBM865"), IBM868 ("IBM868"), IBM869 ("IBM869"), IBM870 ("IBM870"), IBM871 ("IBM871"), IBM880 ("IBM880"), IBM891 ("IBM891"), IBM903 ("IBM903"), IBM904 ("IBM904"), IBM905 ("IBM905"), IBM918 ("IBM918"), IBM1026 ("IBM1026"), EBCDIC_AT_DE ("EBCDIC-AT-DE"), EBCDIC_AT_DE_A ("EBCDIC-AT-DE-A"), EBCDIC_CA_FR ("EBCDIC-CA-FR"), EBCDIC_DK_NO ("EBCDIC-DK-NO"), EBCDIC_DK_NO_A ("EBCDIC-DK-NO-A"), EBCDIC_FI_SE ("EBCDIC-FI-SE"), EBCDIC_FI_SE_A ("EBCDIC-FI-SE-A"), EBCDIC_FR ("EBCDIC-FR"), EBCDIC_IT ("EBCDIC-IT"), EBCDIC_PT ("EBCDIC-PT"), EBCDIC_ES ("EBCDIC-ES"), EBCDIC_ES_A ("EBCDIC-ES-A"), EBCDIC_ES_S ("EBCDIC-ES-S"), EBCDIC_UK ("EBCDIC-UK"), EBCDIC_US ("EBCDIC-US"), UNKNOWN_8BIT ("UNKNOWN-8BIT"), MNEMONIC ("MNEMONIC"), MNEM ("MNEM"), VISCII ("VISCII"), VIQR ("VIQR"), KOI8_R ("KOI8-R"), HZ_GB_2312 ("HZ-GB-2312"), IBM866 ("IBM866"), IBM775 ("IBM775"), KOI8_U ("KOI8-U"), IBM00858 ("IBM00858"), IBM00924 ("IBM00924"), IBM01140 ("IBM01140"), IBM01141 ("IBM01141"), IBM01142 ("IBM01142"), IBM01143 ("IBM01143"), IBM01144 ("IBM01144"), IBM01145 ("IBM01145"), IBM01146 ("IBM01146"), IBM01147 ("IBM01147"), IBM01148 ("IBM01148"), IBM01149 ("IBM01149"), Big5_HKSCS ("Big5-HKSCS"), IBM1047 ("IBM1047"), PTCP154 ("PTCP154"), Amiga1251 ("Amiga-1251"), KOI7_switched ("KOI7-switched"), BRF ("BRF"), TSCII ("TSCII"), CP51932 ("CP51932"), windows874 ("windows-874"), windows_1250 ("windows-1250"), windows_1251 ("windows-1251"), windows1252 ("windows-1252"), windows_1253 ("windows-1253"), windows1254 ("windows-1254"), windows1255 ("windows-1255"), windows1256 ("windows-1256"), windows1257 ("windows-1257"), windows1258 ("windows-1258"), TIS_620 ("TIS-620"), CP50220 ("CP50220")
}
