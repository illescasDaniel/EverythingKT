/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package WebExample

import Everything.HTML
import Everything.*
import Everything.WebDocument.classListOf
import Everything.WebDocument.classesFrom

class IndexHTML(val allRows: ArrayList<Row>) {

	val value: HTML by lazy {

		html {
			metadata = metadata {
				title = "EverythingKT"
				externalCSS = arrayOf(
						Path("styles/github.css")
				)
				externalScripts = arrayOf(
						Path("scripts/highlight.pack.js")
				)
			}

			body = body {
				arrayListOf(
					simpleHeader(cssClass = IndexWebClass.logoBackgroundGradient) {
						div(cssClass = IndexWebClass.verticalStack) { arrayListOf(
								div(cssClass = IndexWebClass.horizontalStack) { arrayListOf(
										simpleHyperLink(URL("#"), cssClass = IndexWebClass.linkButton) {
											paragraph(id = IndexWebID.testParagraph, content = "Documentation", cssClass = IndexWebClass.elementMargin) {
													inlineStyle = Font(size = 17.px, family = Font.Safe.arial).asStyle
											}
										},
										displayIf(10 > 1) { // This is just an EXAMPLE of conditional inclusion of elements :)
											picture(defaultSource = Path("images/logoWithShadow.png")) {
												cssClass = classListOf(IndexWebClass.logoImage, IndexWebClass.elementMargin)
												alternativeText = "Logo"
											}
										},
										hyperLink(URL("download/Test.txt"), cssClass = IndexWebClass.linkButton) {
											properties = hashMapOf("download" to "")
											nested = arrayListOf( paragraph(cssClass = IndexWebClass.elementMargin) { "Download" } )
										}
								)},
								paragraph(id = IndexWebID.testParagraph2, content = "A library for web development", cssClass = IndexWebClass.elementMargin)
						)}
					},
					section(id = IndexWebID.mainSection, cssClass = IndexWebClass.verticalStack) { arrayListOf(
							section(cssClass = classListOf(IndexWebClass.verticalStack, IndexWebClass.sectionsWidth)) { arrayListOf(
									heading(Heading.Level.higher, content = "Overview:"),
									paragraph {"""
									EverythingKT is a Kotlin library which helps developing websites in a more simple, clear and faster way.
                                    It's aimed to developers with previous programming knowledge that pretend to create quality websites in short time.
									""" }
							)},
							section(cssClass = classListOf(IndexWebClass.verticalStack, IndexWebClass.sectionsWidth)) { arrayListOf(
									heading(Heading.Level.higher, content = "Motivation:"),
									paragraph {"""
									One of the main goals is not to use multiple languages directly (HTML, CSS, JS, PHP...), but instead using one which you can utilise to represent everything (paragraphs,  links, styles, etc) in a way that it will translate from Kotlin to all the other languages.
								""" }
							)},
							section(cssClass = classListOf(IndexWebClass.verticalStack, IndexWebClass.sectionsWidth)) { arrayListOf(
									heading(Heading.Level.higher, content = "Code Example:"),
									code { """
								// STYLE

								classStyles["bodyStyle"] = CssStyle(
								    Css.fontFamily to "sans-serif"
								)

								val flexBoxHeader = Flexbox(flow = Flexbox.rowWrap,
								                    justifyContent = Flexbox.JustifyContent.spaceAround,
								                    alignItems = Flexbox.AlignItems.center)

								classStyles["headerStyle"] = CssStyle(
								        *flexBoxHeader.properties,
								        Css.backgroundColor to Color.lightYellow.hexValue
								)

								// HTML

								this.document =

								html {
								    metadata = Metadata("Page Title")
								    body = body {
								        nested = arrayListOf(

								                header {
								                    arrayListOf(
								                            paragraph("Hello!")
								                    )
								                },

								                section {
								                    id = "bodySection"
								                    inlineStyle = InlineStyle(
								                            Css.margin to "20"
								                    )
								                    arrayListOf(
								                            paragraph("Text here") {
								                                this.script = script {
								                                    console.log("Hi!!")
								                                }
								                            },
								                            heading(Heading.Level.highest, "Title"),
								                            list(
								                                    listItem("Tomatoes"),
								                                    listItem("Pineapple") {
								                                        inlineStyle = InlineStyle (
								                                            Css.color to "red"
								                                        )
								                                    }
								                            )
								                    )
								                }
								        )
								    }
								}

								// SERVER
								val server = Server(indexContent = Index.html, port = 3000)
								""" }
							)},
							section(cssClass = IndexWebClass.verticalStack) { arrayListOf(
									heading(Heading.Level.higher, content = "Changelog"),
									table(id = IndexWebID.changelogTable, columns = mutableSetOf("Version", "Description", "Date")) {
										this.rows = allRows
									}
							)},
							section(cssClass = IndexWebClass.verticalStack) { arrayListOf(
									heading(Heading.Level.higher, content = "PayPal.Me"),
									button(id = IndexWebID.showPayPalOptions, content = "Show Options"),
									section(id = IndexWebID.paypal, cssClass = classesFrom("test", "horizontalStack")) {// ~ IndexWebClass.horizontalStack
										arrayListOf(
												button(id = IndexWebID.twoEurPayPal, cssClass = IndexWebClass.paypalButton, content = "2€"),
												button(id = IndexWebID.fiveEurPaypal, cssClass = IndexWebClass.paypalButton, content = "5€"),
												input(id = IndexWebID.customEurPayPal, cssClass = IndexWebClass.inputPayPal) {
													this.type = Input.Type.number
													this.placeholder = "Custom amount in €"
												}
										)
									}
							)}
					)},
					simpleFooter {
						div(cssClass = IndexWebClass.horizontalStackSpaced) { arrayListOf(
								heading(Heading.Level.lower, content = "© 2017 EverythingKT"),
								paragraph(content = "Designed by Daniel Illescas")
						)}
					}
			)}

		}
	}
}