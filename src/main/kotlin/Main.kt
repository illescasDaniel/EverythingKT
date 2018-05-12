/* Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/publicdomain/zero/1.0/ */

import Everything.*
import WebExample.IndexDB
import WebExample.IndexWeb

fun main(args: Array<String>) {

	val server = Server(port = 3000, listenHandler = { portNumber ->
		println("Listening on port: $portNumber")
	}, routeGetHandler = { _, response ->

		IndexDB.connection.run(IndexDB.changelogQuery) { results ->

			val allRows = ArrayList<Row>()

			for (result in results.rows) {

				//val changelog = SQLTable(row = result).properties
				val changelog = IndexDB.Changelog(row = result)

				allRows.add(Row("Version" to changelog.version,
						"Description" to changelog.description,
						"Date" to changelog.date))
			}
			val web = IndexWeb(allRows)
			web.html.writeOutputToPageFolder()

			response.sendFile("/Users/Daniel/Documents/IntelliJ IDEA/EverythingKT/public")
		}
	})

	server.routePOSTto("/test.html") { request, response ->

		val password = request.body.password as String

		val mysql = MySQL(host = "localhost", user = "root", password = "admin", database = "PW")

		val userFromPasswordPromise = mysql.runPromised(SQL.Query.select("*").from("users"), arrayOf(password))

		userFromPasswordPromise.then(onFulfilled = { usersResult ->

			val username = usersResult.rows[0]["user"] as String

			mysql.run(SQL.Query.select("*").from("Movements").where("user = '"), escapedValues = arrayOf(username)) { movementsResult ->
				val resultPage = DBExample(username, password, JSON.stringify(movementsResult))
				response.send(resultPage.inHTML())
			}

		}, onRejected = { error ->
			mysql.endConnection()
			println(error)
		})
	}

	server.routeGETto("/test2.html") { _, response ->
		response.send("hi!!")
	}

	// - Console output
	// inside the server closure or using: server.listen {}
}
