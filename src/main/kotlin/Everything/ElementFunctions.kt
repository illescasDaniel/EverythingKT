/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

import Everything.Common.*
import Everything.WebDocument.StyleSheet

fun script(source: String = "", function: () -> Unit = {}): Script {
	return Script(source = source, function = function)
}

fun element(name: ElementType, content: String = "", id: CSSID? = null, inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
            op: (Element.() -> Unit)? = null): Element {
	return makeElement(Element(name = name, content = content, id = id, inlineStyle = inlineStyle, cssClass = cssClass, properties = properties, script = script), op)
}

fun paragraph(content: String, id: CSSID? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), inlineStyle: InlineStyle? = null, script: Script = Script({}),
              op: (Paragraph.() -> Unit)? = null): Paragraph {
	return makeElement(Paragraph(content = content, id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script), op)
}

fun paragraph(id: CSSID? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), inlineStyle: InlineStyle? = null, script: Script = Script({}), content: () -> String): Paragraph {
	return Paragraph(content = content(), id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script)
}

fun heading(level: Heading.Level = Heading.Level.default, content: String, id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
            op: (Heading.() -> Unit)? = null): Heading {
	return makeElement(Heading(level = level, content = content, id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script), op)
}

fun canvas(width: Int = 300, height: Int = 150, content: String = "", id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
           op: (Canvas.() -> Unit)? = null): Canvas {
	return makeElement(Canvas(width = width, height = height, content = content, id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script), op)
}

fun code(content: String, id: CSSID? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), inlineStyle: InlineStyle? = null, script: Script = Script({}),
         op: (Code.() -> Unit)? = null): Code {
	return makeElement(Code(content = content, id = id, cssClass = cssClass, properties = properties, inlineStyle = inlineStyle, script = script), op)
}

fun code(id: CSSID? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), inlineStyle: InlineStyle? = null, script: Script = Script({}), content: () -> String): Code {
	return Code(content = content(), id = id, cssClass = cssClass, properties = properties, inlineStyle = inlineStyle, script = script)
}

fun listItem(content: String, type: ListItem.Type = ListItem.Type.default, id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
             op: (ListItem.() -> Unit)? = null): ListItem {
	return makeElement(ListItem(type = type, content = content, id = id, cssClass = cssClass, properties = properties, inlineStyle = inlineStyle, script = script), op)
}

fun list(items: ArrayList<ListItem> = arrayListOf(), type: List.Type = List.Type.unordered,
         styleType: String = List.StyleType.default, id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null,
         properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
         op: (List.() -> Unit)? = null): List {
	return makeElement(List(items = items, type = type, id = id, cssClass = cssClass, styleType = styleType, properties = properties, inlineStyle = inlineStyle, script = script), op)
}

fun listFrom(vararg items: String,
                   type: List.Type = List.Type.unordered,
                   styleType: String = List.StyleType.Unordered.disc.name,
                   id: CSSID? = null,
                   inlineStyle: InlineStyle? = null,
                   cssClass: CSSClass? = null,
                   op: (List.() -> Unit)? = null): List {
	return makeElement(List.from(items = *items, type = type, styleType = styleType, id = id, inlineStyle = inlineStyle, cssClass = cssClass), op)
}

fun unorderedListFrom(vararg items: String,
             styleType: List.StyleType.Unordered = List.StyleType.Unordered.disc,
             id: CSSID? = null,
             inlineStyle: InlineStyle? = null,
             cssClass: CSSClass? = null,
             op: (List.() -> Unit)? = null): List {
	return listFrom(items = *items, type = List.Type.unordered, styleType = styleType.name, id = id, inlineStyle = inlineStyle, cssClass = cssClass, op = op)
}

fun orderedListFrom(vararg items: String,
                      styleType: List.StyleType.Ordered = List.StyleType.Ordered.decimal,
                      id: CSSID? = null,
                      inlineStyle: InlineStyle? = null,
                      cssClass: CSSClass? = null,
                      op: (List.() -> Unit)? = null): List {
	return listFrom(items = *items, type = List.Type.ordered, styleType = styleType.value, id = id, inlineStyle = inlineStyle, cssClass = cssClass, op = op)
}

fun listFrom(items: Array<String>,
                   type: List.Type = List.Type.unordered,
             styleType: String = List.StyleType.Unordered.disc.name,
                   id: CSSID? = null,
                   inlineStyle: InlineStyle? = null,
                   cssClass: CSSClass? = null,
                   op: (List.() -> Unit)? = null): List {
	return makeElement(List.from(items = *items, type = type, styleType = styleType, id = id, inlineStyle = inlineStyle, cssClass = cssClass), op)
}

fun source(source: Path, type: Source.Type = Source.Type.auto,
           media: String = "", sizes: String = "",
           mimeType: MIMEType = MIMEType(""),
           sourceSet: SourceSet? = null, id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
           op: (Source.() -> Unit)? = null): Source {
	return makeElement(Source(source, type, media, sizes, mimeType, sourceSet, id, inlineStyle, cssClass, properties, script), op)
}

fun audio(sources: ArrayList<Source> = arrayListOf(),
          tracks: ArrayList<Track> = arrayListOf(),
          volumeLevel: Int = 50,
          autoplay: Boolean = false,
          controls: Boolean = true,
          loop: Boolean = false,
          muted: Boolean = false,
          preload: Audio.Preload = Audio.Preload.metadata,
          defaultUnsupportedMessage: String = "", id: CSSID? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), inlineStyle: InlineStyle? = null, script: Script = Script({}),
          op: (Audio.() -> Unit)? = null): Audio {
	return makeElement(Audio(sources, tracks, volumeLevel, autoplay, controls, loop, muted, preload, defaultUnsupportedMessage, id, inlineStyle, cssClass, properties, script), op)
}

fun video(sources: ArrayList<Source> = arrayListOf(),
          tracks: ArrayList<Track> = arrayListOf(),
          poster: String = "",
          width: Int = -1,
          height: Int = -1,
          autoplay: Boolean = false,
          controls: Boolean = true,
          loop: Boolean = false,
          muted: Boolean = false,
          preload: Video.Preload = Video.Preload.metadata,
          defaultUnsupportedMessage: String = "", id: CSSID? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), inlineStyle: InlineStyle? = null, script: Script = Script({}),
          op: (Video.() -> Unit)? = null): Video {
	return makeElement(Video(sources, tracks, poster, width, height, autoplay, controls, loop, muted, preload, defaultUnsupportedMessage, id, inlineStyle, cssClass, properties, script), op)
}

fun picture(defaultSource: Path,
            width: Int = -1,
            height: Int = -1,
            alternativeText: String = "",
            sources: ArrayList<Source> = arrayListOf(), id: CSSID? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), inlineStyle: InlineStyle? = null, script: Script = Script({}),
            op: (Picture.() -> Unit)? = null): Picture {
	return makeElement(Picture(defaultSource, width, height, alternativeText, sources, id, inlineStyle, cssClass, properties, script), op)
}

fun label(content: String, id: CSSID? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), inlineStyle: InlineStyle? = null, script: Script = Script({}),
          op: (Label.() -> Unit)? = null): Label {
	return makeElement(Label(content = content, id = id, inlineStyle = inlineStyle, cssClass = cssClass, properties = properties, script = script), op)
}

fun button(buttonName: String = "",
           type: Button.Type = Button.Type.button,
           autofocus: Boolean = true,
           disabled: Boolean = false,
           formID: String = "", formAction: String = "", formMethod: Form.Method? = null, formNoValidate: Boolean = false, formTarget: Target? = null,
           content: String, id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
           op: (Button.() -> Unit)? = null): Button {
	return makeElement(Button(buttonName = buttonName, type = type, autofocus = autofocus,  disabled = disabled,  formID = formID, formAction = formAction,
			formMethod = formMethod, formTarget = formTarget, formNoValidate = formNoValidate, content = content, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), op)
}

fun inlineStyle(vararg properties: Pair<Css, String>,
                font: Font = Font()): InlineStyle {
	return InlineStyle(properties = *properties, font = font)
}

fun inlineStyle(vararg properties: Pair<Css, String>,
                font: () -> Font): InlineStyle {
	return inlineStyle(properties = *properties, font = font())
}

fun inlineStyleProperties(properties: () -> Array<Pair<Css, String>>): InlineStyle {
	return InlineStyle(properties = *properties(), font = Font())
}

fun font(size: Pair<Number, LengthType>, textColor: String = "", family: Font.Safe, style: FontStyle = FontStyle.default, op: (Font.() -> Unit)? = null): InlineStyle {
	return InlineStyle(properties = *arrayOf(), font = makeElement(Font(size, textColor, family, style), op))
}

fun input(label: Label = Label(),
          placeholder: String = "",
          legend: String = "",
          type: Input.Type = Input.Type.text,
          inputName: String = "",
          readOnly: Boolean = false,
          id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
          op: (Input.() -> Unit)? = null): Input {
	return makeElement(Input(label = label, placeholder = placeholder, legend = legend, type = type, inputName = inputName, readOnly = readOnly,
			id = id, cssClass = cssClass, properties = properties, inlineStyle = inlineStyle, script = script), op)
}

fun track(source: Path,
          kind: Track.Kind = Track.Kind.subtitles,
          language: Languages = Languages.english,
          isDefaultTrack: Boolean = false,
          label: String = "", id: CSSID? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), inlineStyle: InlineStyle? = null, script: Script = Script({}),
          op: (Track.() -> Unit)? = null): Track {
	return makeElement(Track(source, kind, language, isDefaultTrack, label, id, inlineStyle, cssClass, properties, script), op)
}

fun row(vararg columnRowValue: Pair<String, Any> = arrayOf(),
        id: CSSID? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), inlineStyle: InlineStyle? = null, script: Script = Script({}),
        op: (Row.() -> Unit)? = null): Row {
	return makeElement(Row(*columnRowValue, id = id, cssClass = cssClass, properties = properties, inlineStyle = inlineStyle, script = script), op)
}

fun table(columns: MutableSet<String> = mutableSetOf(),
          rows: ArrayList<Row> = arrayListOf(),
          headStyle: InlineStyle? = null,
          bodyStyle: InlineStyle? = null, id: CSSID? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), inlineStyle: InlineStyle? = null, script: Script = Script({}),
          op: (Table.() -> Unit)? = null): Table {
	return makeElement(Table(columns, rows, headStyle, bodyStyle, id, inlineStyle, cssClass, properties, script), op)
}

fun form(action: String = "", method: Form.Method = Form.Method.get, autoComplete: Boolean = true, target: Target = Target.self,
         inputs: ArrayList<Input>, buttons: ArrayList<Button> = arrayListOf(), id: CSSID? = null, inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
         op: (Form.() -> Unit)? = null): Form {
	return makeElement(Form(action, method, autoComplete, target, inputs, buttons, id = id, inlineStyle = inlineStyle, cssClass = cssClass, properties = properties, script = script), op)
}

fun imageHyperLink(image: Picture, link: URL, content: String = "", id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null,
                   properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
                   op: (ImageHyperLink.() -> Unit)? = null): ImageHyperLink {
	return makeElement(ImageHyperLink(image = image, link = link, content = content, id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script), op)
}

fun equation(preContent: String = "", content: String, id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
             op: (Equation.() -> Unit)? = null): Equation {
	return makeElement(Equation(preContent = preContent, content = content, id = id, inlineStyle = inlineStyle, cssClass = cssClass, properties = properties, script = script), op)
}

fun hyperLink(link: URL = URL("#"), content: String = "", target: Target = Target.self, id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
              op: (HyperLink.() -> Unit)? = null): HyperLink {
	return makeElement(HyperLink(link = link, content = content, target = target, id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script), op)
}

fun quote(lengthType: Quote.LengthType, cite: String = "", content: String = "", id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
          op: (Quote.() -> Unit)? = null): Quote {
	return makeElement(Quote(lengthType = lengthType, inlineStyle = inlineStyle, cite = cite, content = content, id = id, cssClass = cssClass, properties = properties, script = script), op)
}

fun span(content: String = "", id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
         op: (Span.() -> Unit)? = null): Span {
	return makeElement(Span(content = content, id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script), op)
}

fun body(content: String = "", id: CSSID? = null, inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}), startScripts: Array<Path> = arrayOf(), endScripts: Array<Path> = arrayOf(),
         op: (Body.() -> ArrayList<Element>)? = null): Body {

	val bodyElement = Body(content = content, id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script, nested = arrayListOf(), startScripts = startScripts, endScripts = endScripts)

	op?.let { validOperation ->
		bodyElement.nested = validOperation(bodyElement)
	}
	return bodyElement
}

fun html(fileName: String = "index", metadata: Metadata = Metadata(), styleSheet: StyleSheet = StyleSheet(), body: Body = Body(nested = arrayListOf()),
         op: (HTML.() -> Unit)? = null): HTML {
	return makeElement(HTML(fileName, metadata, styleSheet, body), op)
}

fun metadata(title: String = "Blank",
             charset: Charset = Charset.UTF_8,
             externalCSS: Array<Path> = arrayOf(),
             externalScripts: Array<Path> = arrayOf(),
             featuresOptions: Set<Metadata.Features> = setOf(),
             iOSIconPath: Path? = null,
             properties: HashMap<String, String> = hashMapOf(),
             op: (Metadata.() -> Unit)? = null): Metadata {
	return makeElement(Metadata(title, charset, externalCSS, externalScripts, iOSIconPath, featuresOptions, properties), op)
}

fun div(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
        elements: (Container.() -> ArrayList<Element>)? = null): Container {
	return makeContainer(Container(id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script), elements)
}

fun address(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
            elements: (Container.() -> ArrayList<Element>)? = null): Container {
	return makeContainer(Container(type = Container.Type.address, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

fun article(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
            elements: (Container.() -> ArrayList<Element>)? = null): Container {
	return makeContainer(Container(type = Container.Type.article, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

fun aside(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
          elements: (Container.() -> ArrayList<Element>)? = null): Container {
	return makeContainer(Container(type = Container.Type.aside, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

fun footer(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
           elements: (Container.() -> ArrayList<Element>)? = null): Container {
	return makeContainer(Container(type = Container.Type.footer, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

fun header(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
           elements: (Container.() -> ArrayList<Element>)? = null): Container {
	return makeContainer(Container(type = Container.Type.header, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

fun hgroup(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
           elements: (Container.() -> ArrayList<Element>)? = null): Container {
	return makeContainer(Container(type = Container.Type.hgroup, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

fun nav(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
        elements: (Container.() -> ArrayList<Element>)? = null): Container {
	return makeContainer(Container(type = Container.Type.nav, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

fun section(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
            elements: (Container.() -> ArrayList<Element>)? = null): Container {
	return makeContainer(Container(type = Container.Type.section, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

//

fun simpleParagraph(content: String, id: CSSID? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), inlineStyle: InlineStyle? = null, script: Script = Script({}),
              op: (Paragraph.() -> Element)? = null): Paragraph {
	return makeElement(Paragraph(content = content, id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script), op)
}

fun simpleHeading(level: Heading.Level = Heading.Level.default, content: String, id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
            op: (Heading.() -> Element)? = null): Heading {
	return makeElement(Heading(level = level, content = content, id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script), op)
}

fun simpleCanvas(width: Int = 300, height: Int = 150, content: String = "", id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
           op: (Canvas.() -> Element)? = null): Canvas {
	return makeElement(Canvas(width = width, height = height, content = content, id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script), op)
}

fun simpleCode(content: String, id: CSSID? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), inlineStyle: InlineStyle? = null, script: Script = Script({}),
         op: (Code.() -> Element)? = null): Code {
	return makeElement(Code(content = content, id = id, cssClass = cssClass, properties = properties, inlineStyle = inlineStyle, script = script), op)
}

fun simpleInput(label: Label = Label(),
          placeholder: String = "",
          legend: String = "",
          type: Input.Type = Input.Type.text,
          inputName: String = "",
          readOnly: Boolean = false,
          id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
          op: (Input.() -> Element)? = null): Input {
	return makeElement(Input(label = label, placeholder = placeholder, legend = legend, type = type, inputName = inputName, readOnly = readOnly,
			id = id, cssClass = cssClass, properties = properties, inlineStyle = inlineStyle, script = script), op)
}

fun simpleRow(vararg columnRowValue: Pair<String, Any> = arrayOf(),
        id: CSSID? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), inlineStyle: InlineStyle? = null, script: Script = Script({}),
        op: (Row.() -> Element)? = null): Row {
	return makeElement(Row(*columnRowValue, id = id, cssClass = cssClass, properties = properties, inlineStyle = inlineStyle, script = script), op)
}

fun simpleQuote(lengthType: Quote.LengthType, cite: String = "", content: String = "", id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
          op: (Quote.() -> Element)? = null): Quote {
	return makeElement(Quote(lengthType = lengthType, inlineStyle = inlineStyle, cite = cite, content = content, id = id, cssClass = cssClass, properties = properties, script = script), op)
}

fun sipmleSpan(content: String = "", id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
         op: (Span.() -> Element)? = null): Span {
	return makeElement(Span(content = content, id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script), op)
}

fun simpleElement(name: ElementType, content: String = "", id: CSSID? = null, inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
            op: (Element.() -> Element)? = null): Element {
	return makeElement(Element(name = name, content = content, id = id, inlineStyle = inlineStyle, cssClass = cssClass, properties = properties, script = script), op)
}

fun simpleHyperLink(link: URL = URL("#"), content: String = "", target: Target = Target.self, id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
              op: (HyperLink.() -> Element)? = null): HyperLink {
	return makeElement(HyperLink(content = content, link = link, target = target, id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script), op)
}

fun simpleAddress(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
                  elements: (Container.() -> Element)? = null): Container {
	return makeContainer(Container(type = Container.Type.address, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

fun simpleArticle(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
                  elements: (Container.() -> Element)? = null): Container {
	return makeContainer(Container(type = Container.Type.article, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

fun simpleAside(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
                elements: (Container.() -> Element)? = null): Container {
	return makeContainer(Container(type = Container.Type.aside, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

fun simpleFooter(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
                 elements: (Container.() -> Element)? = null): Container {
	return makeContainer(Container(type = Container.Type.footer, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

fun simpleHeader(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
                 elements: (Container.() -> Element)? = null): Container {
	return makeContainer(Container(type = Container.Type.header, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

fun simpleHgroup(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
                 elements: (Container.() -> Element)? = null): Container {
	return makeContainer(Container(type = Container.Type.hgroup, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

fun simpleNav(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
              elements: (Container.() -> Element)? = null): Container {
	return makeContainer(Container(type = Container.Type.nav, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

fun simpleDiv(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
              elements: (Container.() -> Element)? = null): Container {
	return makeContainer(Container(id = id, cssClass = cssClass, inlineStyle = inlineStyle, properties = properties, script = script), elements)
}

fun simpleSection(id: CSSID? = null, cssClass: CSSClass? = null, inlineStyle: InlineStyle? = null, properties: HashMap<String, String> = hashMapOf(), script: Script = Script({}),
            elements: (Container.() -> Element)? = null): Container {
	return makeContainer(Container(type = Container.Type.section, inlineStyle = inlineStyle, id = id, cssClass = cssClass, properties = properties, script = script), elements)
}

//

private fun <T> makeElement(element: T, op: (T.() -> Unit)?): T {
	op?.let { validOperation ->
		validOperation(element)
	}
	return element
}

private fun <T> makeElement(element: T, func: (T.() -> Element)? = null): T {
	func?.let { validOperation ->
		(element as Element).nested = arrayListOf(validOperation(element))
	}
	return element
}

private fun makeContainer(container: Container, elements: (Container.() -> ArrayList<Element>)? = null): Container {
	elements?.let { validOperation ->
		val op = validOperation(container)
		container.nested = op
	}
	return container
}

private fun makeContainer(container: Container, elements: (Container.() -> Element)? = null): Container {
	elements?.let { validOperation ->
		val op = validOperation(container)
		container.nested = arrayListOf(op)
	}
	return container
}