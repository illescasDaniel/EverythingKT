/* Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/publicdomain/zero/1.0/ */

package WebExample

import Everything.Row
import Everything.WebDocument.WebDocument

class IndexWeb(rows: ArrayList<Row>): WebDocument() {
	override val styleSheet = IndexStyleSheet.value
	override val document = IndexHTML(rows).value
	override val dbContent = IndexDB.value
	// With Website class: override val jsContent = IndexJSContent.value
}