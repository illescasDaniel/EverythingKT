/* Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/publicdomain/zero/1.0/ */

import Everything.*

class DBExample(val username: String, val pass: String, val movements: String) {
	fun inHTML(): String {
		return HTML("test", metadata = Metadata("Result"), body = Body(nested = arrayListOf(
				Paragraph("Welcome, $username ", inlineStyle = InlineStyle(font = Font(family = "Arial", size = 2.em))),
				Paragraph("$username password: $pass"),
				Paragraph("Movements: $movements")
		))).inHTML
	}
}
