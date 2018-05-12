/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything.WebDocument

import Everything.*

@Deprecated(message = "This Website class could be replaced or changed in the future, we strongly recommend using WebDocument now.",
		replaceWith = ReplaceWith("WebDocument", "import Everything.WebDocument.WebDocument"))
abstract class Website {

	abstract val webDocument: HTML
	abstract val styleSheet: StyleSheet
	abstract val jsContent: JSContent
	abstract val dbContent: DBContent

	open val inHTML: String by lazy {

		this.webDocument.styleSheet = this.styleSheet

		this.webDocument.metadata.nested.addAll(this.jsContent.head.map { it.asElement })

		val allJSBodyContent = this.jsContent.body.map { it.asElement }
		this.webDocument.body.nested = (allJSBodyContent + this.webDocument.body.nested) as ArrayList<Element>

		this.webDocument.body.nested.addAll(this.jsContent.end.map { it.asElement })

		this.webDocument.inHTML
	}

	val html: HTML
		get() {
			this.inHTML
			return this.webDocument
		}

	override fun toString() = this.inHTML

	fun getJSWith(id: String): String? {
		return this.html.elementWith(id)?.script?.inJS?.beautifiedJS
	}

	fun exportToFile() {
		this.inHTML
		this.webDocument.toFile()
	}
}

abstract class WebDocument {

	abstract val document: HTML
	abstract val styleSheet: StyleSheet
	abstract val dbContent: DBContent

	open val inHTML: String by lazy {

		if (HeadScripts.all.isNotEmpty() || StartScripts.all.isNotEmpty() || EndScripts.all.isNotEmpty()) {

			FileSystem.makeDir("./public")
			FileSystem.makeDir("public/kotlin2JS")
			FileSystem.copyFile("node_modules/kotlin/kotlin.js", "public/kotlin2JS/index.js")

			this.document.metadata.externalScripts += arrayOf(
					Path("kotlin2JS/index.js")
			)
		}

		if (HeadScripts.all.isNotEmpty()) {

			FileSystem.copyFile("./Kotlin2JavaScript/output/Kotlin2jsOutput_HEAD.js", "./public/kotlin2JS/output_HEAD.js")

			this.document.metadata.externalScripts += arrayOf(
					//Path("kotlin2JS/index.js"),
					Path("kotlin2JS/output_HEAD.js")
			)
		}

		if (StartScripts.all.isNotEmpty()) {

			FileSystem.copyFile("./Kotlin2JavaScript/output/Kotlin2jsOutput_START.js", "./public/kotlin2JS/output_START.js")

			this.document.body.startScripts += arrayOf(
					//Path("kotlin2JS/index.js"),
					Path("kotlin2JS/output_START.js")
			)
		}

		if (EndScripts.all.isNotEmpty()) {

			FileSystem.copyFile("./Kotlin2JavaScript/output/Kotlin2jsOutput_END.js", "./public/kotlin2JS/output_END.js")

			this.document.body.endScripts += arrayOf(
					//Path("kotlin2JS/index.js"),
					Path("kotlin2JS/output_END.js")
			)
		}

		this.document.styleSheet = this.styleSheet
		this.document.inHTML
	}

	val html: HTML
		get() {
			this.inHTML
			return this.document
		}

	override fun toString() = this.inHTML

	fun getJSWith(id: String): String? {
		return this.html.elementWith(id)?.script?.inJS?.beautifiedJS
	}

	fun exportToFile() {
		this.inHTML
		this.document.toFile()
	}
}