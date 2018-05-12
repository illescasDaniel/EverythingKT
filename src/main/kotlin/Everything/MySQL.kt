/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

import kotlin.js.Json
import kotlin.js.Promise

class MySQL(var host: String, var user: String, var password: String, var database: String) {

	private var mysql = require("mysql")
	private var connection: dynamic = ""

	class Rows (
			var rows: Array<Json> = arrayOf(),
			var affectedRows: Number = 0
	)

	init {

		val connectionDetails = ConnectionDetails(this.host, this.user, this.password, this.database)

		connection = mysql.createConnection(connectionDetails)

		connection.connect { error ->
			if (error == true) {
				println("Error when connecting to the database")
			}
		}
	}

	fun runAndClose(query: QueryInterface, escapedValues: Array<String> = arrayOf(), action: (result: Rows) -> dynamic) {

		connection.query(query.toString(), escapedValues) { error, result ->

			if (error != true) {

				val jsonString = JSON.stringify(result as Any?)
				val parsedResult = JSON.parse<Array<Json>>(jsonString)
				var affectedRows: Number = 0

				if (result.affectedRows !== undefined) {
					affectedRows = result.affectedRows as Number
				}

				//connection.end(fun() { action(Rows(parsedResult, affectedRows)) })
				action(Rows(parsedResult, affectedRows))
				connection.end()
			}
			else {
				println("Error running the query")
			}
		}
	}

	/// It doesn't close the connection automatically
	fun runPromised(query: QueryInterface, escapedValues: Array<String> = arrayOf()): Promise<Rows> {
		return Promise({ resolve, _ ->
			this.run(query, escapedValues) { result ->
				resolve(result)
			}
		})
	}

	fun runPromised(rawQuery: String, escapedValues: Array<String> = arrayOf()): Promise<Rows> {
		return Promise({ resolve, _ ->
			this.run(rawQuery, escapedValues) { result ->
				resolve(result)
			}
		})
	}

	fun endConnection() {
		connection.end()
	}

	// - Convenience

	fun run(query: QueryInterface, escapedValues: Array<String> = arrayOf(), action: (result: Rows) -> Unit) {
		run(query.toString(), escapedValues, action)
	}

	fun run(rawQuery: String, escapedValues: Array<String> = arrayOf(), action: (result: Rows) -> Unit) {

		connection.query(rawQuery, escapedValues) { error, result ->

			if (error != true) {

				val jsonString = JSON.stringify(result as Any?)
				val parsedResult = JSON.parse<Array<Json>>(jsonString)
				var affectedRows: Number = 0

				if (result.affectedRows !== undefined) {
					affectedRows = result.affectedRows as Number
				}
				action(Rows(parsedResult, affectedRows))
			}
			else {
				println("Error running the query")
			}
		}
	}

	private class ConnectionDetails (
			var host: String = "",
			var user: String = "",
			var password: String = "",
			var database: String = ""
	)
}