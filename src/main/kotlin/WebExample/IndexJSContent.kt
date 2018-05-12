/* Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/publicdomain/zero/1.0/ */

package WebExample

/*
* EXAMPLE CLASS only for the (old) Website class
* Not necessary for a WebDocument
*/

import Everything.WebDocument.JSContent
import Everything.script
import org.w3c.dom.HTMLElement
import kotlin.browser.document

class IndexJSContent {
	companion object {

		val value = JSContent(
				head = arrayListOf(
						script { js("hljs.initHighlightingOnLoad();") }
				)/*,
				body = arrayListOf(
						script { ... }
				),
				end = arrayListOf(
						script { ... }
				)*/
		)

		val paypalScript = script {

			val paypalModalSection = document.getElementById("paypal") as HTMLElement

			val showPayPalOptionsButton = document.getElementById("showPayPalOptions") as HTMLElement

			js("""showPayPalOptionsButton.onclick = function() {
					if (paypalModalSection.style.display == "" || paypalModalSection.style.display == "none") {
						paypalModalSection.style.display = "flex"
						showPayPalOptionsButton.textContent = "Hide Options"
					} else {
						paypalModalSection.style.display = "none"
						showPayPalOptionsButton.textContent = "Show Options"
					}
				}""")
			val twoEurPayPal = document.getElementById("twoEurPayPal") as HTMLElement
			js("""twoEurPayPal.onclick = function() {
					window.open("https://www.paypal.me/illescasDaniel/2")
				}""")
			val fiveEurPayPal = document.getElementById("fiveEurPaypal") as HTMLElement
			js("""fiveEurPayPal.onclick = function() {
					window.open("https://www.paypal.me/illescasDaniel/5")
				}""")

			val customEurPayPal = document.getElementById("customEurPayPal") as HTMLElement//HTMLDataElement
			js("""customEurPayPal.addEventListener('keydown', function(event) {
					if (event.key === "Enter") {
						var moneyValue = customEurPayPal.value
						if (moneyValue == "") {
							alert("Invalid Input")
						} else {
							window.open("https://www.paypal.me/illescasDaniel/" + moneyValue)
						}
					}
			})""")
		}
	}
}