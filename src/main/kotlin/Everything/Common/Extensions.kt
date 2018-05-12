/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything.Common

fun org.w3c.dom.Document.elementWith(id: CSSID): org.w3c.dom.HTMLElement? {
	return kotlin.browser.document.getElementById(id.toString()) as org.w3c.dom.HTMLElement?
}