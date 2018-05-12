/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

import Everything.Common.*

class Source(var source: Path, var type: Type = Type.auto,
             var media: String = "", var sizes: String = "",
             var mimeType: MIMEType = MIMEType(""),
             var sourceSet: SourceSet? = null,
             id: CSSID? = null,
             inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
             properties: HashMap<String, String> = hashMapOf(),
             script: Script = Script({}),
             nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.Source, null, id, inlineStyle, cssClass, properties, script, nested) {

	enum class Type { auto, image, audio, video }

	override val inHTML: String by lazy {

		val finalSource =
				if (type == Type.image) {
					if (sourceSet != null) {
						sourceSet?.inHTML ?: "" to ""
					} else {
						SourceSet(this.source.filePath).inHTML
					}
				} else {
					source.filePath.propertyOf("src")
				}

		this.properties = properties.plus(hashMapOf(
				finalSource,
				media.propertyOf("media"),
				sizes.propertyOf("sizes"),
				mimeType.type.propertyOf("type")
		))

		super.inHTML
	}
}

class SourceSet(imagesWithDensity: LinkedHashMap<String, PixelDensityDescriptor> = linkedMapOf()) {

	constructor(imageLink: String): this(linkedMapOf(imageLink to PixelDensityDescriptor.default))

	enum class PixelDensityDescriptor(val value: String) {
		default(""),
		bigger("1.5x"),
		biggest("2x")
	}

	val inHTML: Pair<String,String> by lazy {

		var images = ""

		if (imagesWithDensity.count() > 1) {
			for ((imageLink, density) in imagesWithDensity) {
				images += "$imageLink ${density.value},"
			}
			images.removeSuffix(",")
		}
		else if (imagesWithDensity.count() == 1) {
			for ((imageLink, density) in imagesWithDensity) {
				images = "$imageLink ${density.value}".trim()
			}
		}
		images.propertyOf("srcset")
	}
}

class Audio(var sources: ArrayList<Source>,
            var tracks: ArrayList<Track> = arrayListOf(),
            var volumeLevel: Int = 50,
            var autoplay: Boolean = false,
            var controls: Boolean = true,
            var loop: Boolean = false,
            var muted: Boolean = false,
            var preload: Audio.Preload = Audio.Preload.metadata,
            val defaultUnsupportedMessage: String = "",
            id: CSSID? = null,
            inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
            properties: HashMap<String, String> = hashMapOf(),
            script: Script = Script({}),
            nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.Audio, defaultUnsupportedMessage, id, inlineStyle, cssClass, properties, script, nested) {

	enum class Preload { auto, none, metadata }

	fun addSources(vararg sources: String) {
		for (source in sources) {
			this.sources.add(Source(source = Path(source)))
		}
	}

	fun addTracks(vararg tracks: String) {
		for (track in tracks) {
			this.tracks.add(Track(source = Path(track)))
		}
	}

	override val inHTML: String by lazy {

		val finalVolume =
				if (IntRange(0,100).contains(volumeLevel)) {
					volumeLevel.toDouble() / 100.0
				} else {
					0.5
				}

		val finalSources = if (sources.isNotEmpty()) "${sources.joinToString(" ")}\n" else ""
		val finalTracks = if (tracks.isNotEmpty()) "${tracks.joinToString(" ")}\n" else ""
		this.content = "$finalSources$finalTracks${this.content}"

		sources.forEach { it.type = Source.Type.audio }

		this.properties = properties.plus(hashMapOf(
				preload.name.propertyOf("preload"),
				finalVolume.toString().propertyOf("volume"),
				autoplay.propertyOf("autoplay"),
				controls.propertyOf("controls"),
				loop.propertyOf("loop"),
				muted.propertyOf("muted")
		))

		super.inHTML
	}
}

class Video(var sources: ArrayList<Source> = arrayListOf(),
            var tracks: ArrayList<Track> = arrayListOf(),
            var poster: String = "",
            var width: Int = -1,
            var height: Int = -1,
            var autoplay: Boolean = false,
            var controls: Boolean = true,
            var loop: Boolean = false,
            var muted: Boolean = false,
            var preload: Video.Preload = Video.Preload.metadata,
            val defaultUnsupportedMessage: String = "",
            id: CSSID? = null,
            inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
            properties: HashMap<String, String> = hashMapOf(),
            script: Script = Script({}),
            nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.Video, defaultUnsupportedMessage, id, inlineStyle, cssClass, properties, script, nested) {

	enum class Preload { auto, none, metadata }

	fun addSources(vararg sources: String) {
		for (source in sources) {
			this.sources.add(Source(source = Path(source)))
		}
	}

	fun addTracks(vararg tracks: String) {
		for (track in tracks) {
			this.tracks.add(Track(source = Path(track)))
		}
	}

	override val inHTML: String by lazy {

		val finalWidth = if (width > 0) width.toString().propertyOf("width") else "" to ""
		val finalHeight = if (height > 0) height.toString().propertyOf("height") else "" to ""

		val finalSources = if (sources.isNotEmpty()) "${sources.joinToString(" ")}\n" else ""
		val finalTracks = if (tracks.isNotEmpty()) "${tracks.joinToString(" ")}\n" else ""
		this.content = "$finalSources$finalTracks${this.content}"

		sources.forEach { it.type = Source.Type.video }

		this.properties = properties.plus(hashMapOf(
				preload.name.propertyOf("preload"),
				finalWidth,
				finalHeight,
				poster.propertyOf("poster"),
				autoplay.propertyOf("autoplay"),
				controls.propertyOf("controls"),
				loop.propertyOf("loop"),
				muted.propertyOf("muted")
		))

		super.inHTML
	}
}

// Discrete types: https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types y algunos más...
class MIMEType(val type: String) {

	enum class Text(val value: MIMEType) {
		plain(MIMEType("text/plain")), html(MIMEType("text/html")), css(MIMEType("text/css")),
		javascript(MIMEType("text/javascript"))
	}

	enum class Image(val value: MIMEType) {
		gif(MIMEType("image/gif")), png(MIMEType("image/png")), jpeg(MIMEType("image/jpeg")),
		bmp(MIMEType("image/bmp")), webp(MIMEType("image/webp"))
	}

	enum class Audio(val value: MIMEType) {
		midi(MIMEType("audio/midi")), mpeg(MIMEType("audio/mpeg")), webm(MIMEType("audio/webm")),
		ogg(MIMEType("audio/ogg")), wav(MIMEType("audio/wav")), wave(MIMEType("audio/wave")), flac(MIMEType("audio/flac"))
	}

	enum class Video(val value: MIMEType) {
		webm(MIMEType("video/webm")), ogg(MIMEType("video/ogg")), mp4(MIMEType("video/mp4"))
	}

	enum class Application(val value: MIMEType) {
		octetStream(MIMEType("application/octet-stream")),
		pkcs12(MIMEType("application/pkcs12")),
		powerpoint(MIMEType("application/vnd.mspowerpoint")),
		xhtml(MIMEType("application/xhtml+xml")),
		xml(MIMEType("application/xml")),
		pdf(MIMEType("application/pdf"))
	}

	enum class Multipart(val value: MIMEType) {
		alternative(MIMEType("multipart/alternative")),
		byteranges(MIMEType("multipart/byteranges")),
		digest(MIMEType("multipart/digest")),
		formData(MIMEType("multipart/form-data")),
		mixed(MIMEType("multipart/mixed")),
		parallel(MIMEType("multipart/parallel")),
		related(MIMEType("multipart/related"))
	}
}

class Picture(var defaultSource: Path,
              var width: Int = -1,
              var height: Int = -1,
              var alternativeText: String = "",
              var sources: ArrayList<Source> = arrayListOf(),
              id: CSSID? = null,
              inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
              properties: HashMap<String, String> = hashMapOf(),
              script: Script = Script({}),
              nested: ArrayList<Element> = arrayListOf()):
		Element(if (sources.isNotEmpty()) ElementType.Picture else ElementType.Image, "", id, inlineStyle, cssClass, properties, script, nested) {

	fun addSources(vararg sources: String) {
		for (source in sources) {
			this.sources.add(Source(source = Path(source)))
		}
	}

	override val inHTML: String by lazy {

		val finalWidth = if (width > 0) width.toString().propertyOf("width") else "" to ""
		val finalHeight = if (height > 0) height.toString().propertyOf("height") else "" to ""

		if (name == ElementType.Image.value) {
			this.content = null
			this.properties = properties.plus(hashMapOf(
					finalWidth,
					finalHeight,
					alternativeText.propertyOf("alt"),
					defaultSource.filePath.propertyOf("src")
			))
			super.inHTML
		}
		else {

			sources.forEach { it.type = Source.Type.image }

			val image = Element(ElementType.Image, content = null, id = id, inlineStyle = inlineStyle, cssClass = cssClass, script = script, nested = nested,
					properties = hashMapOf(
						finalWidth,
						finalHeight,
						alternativeText.propertyOf("alt"),
						defaultSource.filePath.propertyOf("src")
			))
			val picture = Element(ElementType.Picture, nested = arrayListOf(image))
			picture.nested = ArrayList((sources as ArrayList<Element>) + picture.nested)
			picture.inHTML
		}
	}
}

class Track(var source: Path,
            var kind: Kind = Track.Kind.subtitles,
            var language: Languages = Languages.english,
            var isDefaultTrack: Boolean = false,
            var label: String = "", id: CSSID? = null,
            inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
            properties: HashMap<String, String> = hashMapOf(),
            script: Script = Script({}),
            nested: ArrayList<Element> = arrayListOf()):
			Element(ElementType.Track, null, id, inlineStyle, cssClass, properties, script, nested) {

	enum class Kind {
		subtitles,
		captions,
		descriptions,
		chapters,
		metadata
	}

	override val inHTML: String by lazy {

		this.properties = properties.plus(hashMapOf(
				source.filePath.propertyOf("src"),
				kind.name.propertyOf("kind"),
				language.value.propertyOf("srclang"),
				label.propertyOf("label"),
				isDefaultTrack.propertyOf("default")
		))

		super.inHTML
	}
}

// ISO 639-1 [maybe put inside MEDIA?]
enum class Languages(val value: String) {
	afrikaans("af"),
	akan("ak"),
	albanian("sq"),
	amharic("am"),
	arabic("ar"),
	aragonese("an"),
	armenian("hy"),
	assamese("as"),
	avaric("av"),
	avestan("ae"),
	aymara("ay"),
	azerbaijani("az"),
	bashkir("ba"),
	bambara("bm"),
	basque("eu"),
	belarusian("be"),
	bengali("bn"),
	bihariLanguages("bh"),
	bislama("bi"),
	tibetan("bo"),
	bosnian("bs"),
	breton("br"),
	bulgarian("bg"),
	burmese("my"),
	catalan("ca"),
	valencian("ca"),
	czech("cs"),
	chamorro("ch"),
	chechen("ce"),
	chinese("zh"),
	churchSlavic("cu"),
	chuvash("cv"),
	cornish("kw"),
	corsican("co"),
	cree("cr"),
	welsh("cy"),
	danish("da"),
	german("de"),
	divehi("dv"),
	dutch("nl"),
	flemish("nl"),
	dzongkha("dz"),
	modernGreek("el"),
	english("en"),
	esperanto("eo"),
	estonian("et"),
	ewe("ee"),
	faroese("fo"),
	persian("fa"),
	fijian("fj"),
	finnish("fi"),
	french("fr"),
	westernFrisian("fy"),
	fulah("ff"),
	georgian("ka"),
	gaelic("gd"),
	irish("ga"),
	galician("gl"),
	manx("gv"),
	guarani("gn"),
	gujarati("gu"),
	haitian("ht"),
	hausa("ha"),
	hebrew("he"),
	herero("hz"),
	hindi("hi"),
	hiriMotu("ho"),
	croatian("hr"),
	hungarian("hu"),
	igbo("ig"),
	ido("io"),
	sichuanYi("ii"),
	inuktitut("iu"),
	interlingue("ie"),
	occidental("ie"),
	interlingua("ia"),
	indonesian("id"),
	inupiaq("ik"),
	icelandic("is"),
	italian("it"),
	javanese("jv"),
	japanese("ja"),
	kalaallisut("kl"),
	kannada("kn"),
	kashmiri("ks"),
	kanuri("kr"),
	kazakh("kk"),
	centralKhmer("km"),
	kikuyu("ki"),
	kinyarwanda("rw"),
	kirghiz("ky"),
	komi("kv"),
	kongo("kg"),
	korean("ko"),
	kuanyama("kj"),
	kurdish("ku"),
	lao("lo"),
	latin("la"),
	latvian("lv"),
	limburgan("li"),
	lingala("ln"),
	lithuanian("lt"),
	luxembourgish("lb"),
	lubaKatanga("lu"),
	ganda("lg"),
	marshallese("mh"),
	malayalam("ml"),
	marathi("mr"),
	macedonian("mk"),
	malagasy("mg"),
	maltese("mt"),
	mongolian("mn"),
	maori("mi"),
	malay("ms"),
	nauru("na"),
	navajo("nv"),
	navaho("nv"),
	southNdebele("nr"),
	northNdebele("nd"),
	ndonga("ng"),
	nepali("ne"),
	norwegian("nn"),
	norwegian2("no"),
	chichewa("ny"),
	occitan("oc"),
	ojibwa("oj"),
	oriya("or"),
	oromo("om"),
	ossetian("os"),
	panjabi("pa"),
	pali("pi"),
	polish("pl"),
	portuguese("pt"),
	pushto("ps"),
	quechua("qu"),
	romansh("rm"),
	romanian("ro"),
	rundi("rn"),
	russian("ru"),
	sango("sg"),
	sanskrit("sa"),
	sinhala("si"),
	slovak("sk"),
	slovenian("sl"),
	northernSami("se"),
	samoan("sm"),
	shona("sn"),
	sindhi("sd"),
	somali("so"),
	southern("st"),
	spanish("es"),
	castilian("es"),
	sardinian("sc"),
	serbian("sr"),
	swati("ss"),
	sundanese("su"),
	swahili("sw"),
	swedish("sv"),
	tahitian("ty"),
	tamil("ta"),
	tatar("tt"),
	telugu("te"),
	tajik("tg"),
	tagalog("tl"),
	thai("th"),
	tigrinya("ti"),
	tonga("to"),
	tswana("tn"),
	tsonga("ts"),
	turkmen("tk"),
	turkish("tr"),
	twi("tw"),
	uighur("ug"),
	ukrainian("uk"),
	urdu("ur"),
	uzbek("uz"),
	venda("ve"),
	vietnamese("vi"),
	volapuk("vo"),
	walloon("wa"),
	wolof("wo"),
	xhosa("xh"),
	yiddish("yi"),
	yoruba("yo"),
	zhuang("za"),
	zulu("zu"),
	unknown("")
}
