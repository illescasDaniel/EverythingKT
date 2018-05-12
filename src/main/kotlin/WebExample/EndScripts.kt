/* Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/publicdomain/zero/1.0/ */

import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.KeyboardEvent
import kotlin.browser.document
import kotlin.browser.window
import Everything.Common.*

class EndScripts {

	companion object {

		private val paypalScript = {

			val paypalModalSection = document.elementWith(id = IndexWebID.paypal)

			val showPayPalOptionsButton = document.elementWith(id = IndexWebID.showPayPalOptions)

			showPayPalOptionsButton?.onclick = {
				if (paypalModalSection?.style?.display == "" || paypalModalSection?.style?.display == "none") {
					paypalModalSection.style.display = "flex"
					showPayPalOptionsButton?.textContent = "Hide Options"
				} else {
					paypalModalSection?.style?.display = "none"
					showPayPalOptionsButton?.textContent = "Show Options"
				}
			}
			val twoEurPayPal = document.elementWith(id = IndexWebID.twoEurPayPal)
			twoEurPayPal?.onclick = {
				window.open("https://www.paypal.me/illescasDaniel/2")
			}
			val fiveEurPayPal = document.elementWith(id = IndexWebID.fiveEurPaypal)
			fiveEurPayPal?.onclick = {
				window.open("https://www.paypal.me/illescasDaniel/5")
			}

			val customEurPayPal = document.elementWith(id = IndexWebID.customEurPayPal) as HTMLInputElement?

			customEurPayPal?.addEventListener("keydown", callback = {

				val keyboardEvent = it as KeyboardEvent

				if (keyboardEvent.key == "Enter") {
					val moneyValue = customEurPayPal.value
					if (moneyValue == "") {
						alert("Invalid Input")
					} else {
						window.open("https://www.paypal.me/illescasDaniel/$moneyValue")
					}
				}
			})
		}

		val all = arrayOf(paypalScript)
	}
}