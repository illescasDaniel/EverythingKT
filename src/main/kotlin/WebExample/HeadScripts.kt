/* Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/publicdomain/zero/1.0/ */

import org.w3c.dom.HTMLElement
import org.w3c.dom.events.KeyboardEvent
import kotlin.browser.document
import kotlin.browser.window

class HeadScripts {

	companion object {

		private val initializeHightlight = {
			js("hljs.initHighlightingOnLoad();")
		}
		
		val all = arrayOf(initializeHightlight)
	}
}