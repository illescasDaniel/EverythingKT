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

	// This is just an example of routing a POST request (like when using a form)
	// It access the elements it sent like a username of password and it can access data from a database to later show a different website (or not)
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

	// Another page example, remember you can have as many webDocuments as you want to show them in the Web
	// Try: http://localhost:3000/test2.html
	server.routeGETto("/test2.html") { _, response ->
		val simpleHTML =
				HTML(body = body { arrayListOf(
						paragraph {
							"hiii"
						}
				)}).inHTML
		response.send(simpleHTML)
	}
}
