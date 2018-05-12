/* Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/publicdomain/zero/1.0/ */

package WebExample

import Everything.*
import Everything.WebDocument.DBContent
import kotlin.js.Json

class IndexDB {

	class Changelog(row: Json): SQLTable(row) {

		val version: String = this.properties.version as String
		val description: String = this.properties.description as String
		val date: String = this.properties.date as String

		// it could also access de database to get the columns BUT that is async :/
		companion object Columns {
			val tableName = Changelog::class.simpleName
			val version = SQLColumn("version")
			val description = SQLColumn("description")
			val date = SQLColumn("date")
		}
	}

	companion object {

		val connection = MySQL("localhost", "root", "admin", "EverythingKT")

		val changelogQuery =
				SQL.Query.select(Changelog.version, Changelog.description, Changelog.date)
						.from("${connection.database}.${Changelog.tableName}")
						.orderBy(Changelog.version).descendingOrder

		val value = DBContent(connection = connection)
	}
}
