/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

class FileSystem {
	companion object {

		private val fileSystem = require("fs")

		fun readFile(fileName: String, action: (String) -> Unit) {
			fileSystem.readFile(fileName, "utf8") { error, data ->
				if (error == true) {
					throw error("Couldn't write HTML file to disk")
				} else {
					action(data as String)
				}
			}
		}

		fun writeFile(fileName: String, data: String) {
			fileSystem.writeFile(fileName, data) { error ->
				if (error == true) {
					throw error("Couldn't write HTML file to disk")
				}
			}
		}

		fun existsSync(path: String): Boolean {
			return fileSystem.existsSync(path) as Boolean
		}

		fun existsSyncHere(path: String): Boolean {
			return this.existsSync("./$path")
		}

		fun makeDir(dirName: String) {
			if (!this.existsSync(dirName)){
				fileSystem.mkdirSync(dirName)
			}
		}

		fun makeDirHere(dirName: String) {
			this.makeDir("./$dirName")
		}

		fun copyFile(source: String, destination: String) {
			fileSystem.copyFile(source, destination) { err ->
				if (err != null && err !== undefined) {throw err;}
			}
		}
	}
}

class Path(val filePath: String) {

	data class FileName(val fileName: String, val extension: String)

	val fullFileName: FileName

	init {
		val fullFileName = filePath.split("/").last()
		val splittedFileName = fullFileName.split(".")

		val fileNameWithDotsExceptForLast =
				if (splittedFileName.size > 1) {
					splittedFileName.dropLast(1).joinToString(".")
				} else splittedFileName.first()

		val extension =
				if (splittedFileName.size > 1) {
					splittedFileName.last()
				} else ""

		this.fullFileName = FileName(fileName = fileNameWithDotsExceptForLast, extension = extension)

		if (this.fullFileName.fileName.isBlank()) {
			throw Exception("No file name detected in path: $filePath")
		}
	}
}

class URL(val url: String) {

	enum class Protocol {
		http, https, other;
		val value: String = this.toString()
	}

	val protocol: Protocol
	val host: String
	val port: Int?

	init {
		val splittedUrl = url.split("//")

		protocol = when(splittedUrl.first()) {
			"http:" -> Protocol.http
			"https:" -> Protocol.https
			else -> Protocol.other
		}

		val splittedProtocol = splittedUrl.last().split(":")
		host = splittedProtocol.first()
		port = splittedProtocol.last().toIntOrNull()

		if (host.isBlank()) {
			throw Exception("Host address is blank for url: $url")
		}
	}
}