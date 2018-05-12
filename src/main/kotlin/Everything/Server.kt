/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

import kotlin.js.Date

class Server(val port: Number,
             val listenHandler: (port: Number) -> dynamic = {},
             val routeGetHandler: (request: Request, response: Response) -> dynamic) {

	private val express = require("express")
	private val app = express()

	init {

		this.routeGETto("/") { request, response ->
			routeGetHandler(request, response)
		}
		app.use(express.static("public"))

		this.listen {
			listenHandler(this.port)
		}
	}

	// Some status codes
	enum class StatusCode(val value: Int) {
		_100(100), _101(101),
		_200(200), _201(201), _202(202), _203(203),_204(204), _205(205), _206(206),
		_300(300), _301(301), _302(302), _303(303), _304(304), _3054(305), _306(306), _307(307),
		_400(400), _401(401), _402(402), _403(403), _404(404), _405(405), _406(406), _407(407), _408(408),
		_409(409), _410(410), _411(411), _412(412), _413(413), _414(414), _415(415), _416(416), _417(417),
		_500(500), _501(501), _502(502), _503(503), _504(504), _505(505)
	}

	class Request(val request: dynamic) {

		val baseURL = this.request.baseUrl as String?
		val fresh = this.request.fresh as Boolean
		val hostname = this.request.hostname as String?
		val ip = this.request.ip as String?
		val ips = this.request.ips
		enum class Method { GET, POST, PUT; val value: String by lazy { this.toString() } }
		val method = Method.valueOf(this.request.method as String? ?: "GET")
		val originalURL = this.request.originalUrl as String?
		val path = this.request.path as String?
		enum class Protocol { http, https; val value: String by lazy { this.toString() } }
		val protocol = Protocol.valueOf(this.request.protocol as String? ?: "http")
		val app = this.request.app
		val params = this.request.params
		val body = this.request.body
		val query = this.request.query
		val route = this.request.route
		val cookies = this.request.cookies
		val signedCookies = this.request.signedCookies
		val stale = this.request.stale as Boolean
		val subdomains = this.request.subdomains as Array<String>?
		val xhr = this.request.xhr as Boolean
		val secure: Boolean = this.protocol == Protocol.https

		fun accepts(type: String) = this.request.accepts(type) as String?
		fun accepts(type: Array<String>) = this.request.accepts(type) as String?

		fun acceptsCharsets(type: String) = this.request.acceptsCharsets(type) as String?
		fun acceptsCharsets(type: Array<String>) = this.request.acceptsCharsets(type) as String?

		fun acceptsEncodings(type: String) = this.request.acceptsEncodings(type) as String?
		fun acceptsEncodings(type: Array<String>) = this.request.acceptsEncodings(type) as String?

		fun acceptsLanguages(type: String) = this.request.acceptsLanguages(type) as String?
		fun acceptsLanguages(type: Array<String>) = this.request.acceptsLanguages(type) as String?

		fun get(field: String) = this.request.get(field) as String?
		fun `is`(type: String) = this.request.`is`(type) as String?

		fun range(size: Number, combine: Boolean = false): dynamic {
			return this.request.range(size, object { var combine = combine })
		}

		override fun toString() = this.request.toString()
	}

	class Response(val response: dynamic) {

		val app = this.response.app
		val headersSent = this.response.headersSent as Boolean
		val locals = this.response.locals

		fun append(field: String, values: Array<String>) { this.response.append(field, values) }
		fun append(field: String, value: String) { this.response.append(field, value) }

		fun attachment(fileName: String? = null) {
			if (!fileName.isNullOrEmpty()) {
				this.response.attachment(fileName)
			} else {
				this.response.attachment()
			}
		}

		class CookieOptions {
			var domain = String()
			var encode: () -> dynamic = {}
			var expires = Date()
			var httpOnly: Boolean = false
			var maxAge: Number = 0
			var path = "\\"
			var secure: Boolean = false
			var signed: Boolean = false
			var sameSite: dynamic = undefined
		}

		fun setCookie(name: String, value: String, options: CookieOptions) = this.response.cookie(name, value, options)
		fun setCookie(name: String, value: dynamic, options: CookieOptions) = this.response.cookie(name, value, options)
		fun setCookie(name: String, value: String, options: dynamic) = this.response.cookie(name, value, options)
		fun setCookie(name: String, value: dynamic, options: dynamic) = this.response.cookie(name, value, options)

		fun clearCookie(name: String, options: CookieOptions) = this.response.clearCookie(name, options)
		fun clearCookie(name: String, options: dynamic) = this.response.clearCookie(name, options)

		fun download(path: String, fileName: String? = null, errorHandler: ((error: dynamic) -> dynamic)? = null ) {
			if (fileName.isNullOrBlank()) {
				if (errorHandler == null) {
					this.response.download(path)
				} else {
					this.response.download(path) { error ->
						errorHandler(error)
					}
				}
			}
			else if (errorHandler == null) {
				this.response.download(path, fileName)
			}
			else {
				this.response.download(path, fileName) { error ->
					errorHandler(error)
				}
			}
		}

		fun end() = this.response.end()
		fun end(data: dynamic, encoding: String, callback: () -> dynamic = {}) {
			this.response.end(data, encoding, callback)
		}

		fun format(anObject: dynamic) = this.response.format(anObject)
		fun get(field: String) = this.response.get(field) as String?

		fun sendJSONResponse(body: dynamic) = this.response.json(body)
		fun json(body: dynamic) = this.response.json(body)

		fun join(links: dynamic) = this.response.links(links)
		fun links(links: dynamic) = this.response.links(links)

		fun sendLocation(path: String) = this.response.location(path)

		fun redirect(status: Int? = null, path: String) {
			if (status == null) {
				this.response.redirect(path)
			} else {
				this.response.redirect(status, path)
			}
		}

		fun redirect(status: StatusCode, path: String) = this.response.redirect(status.value, path)

		fun render(view: String, locals: dynamic = undefined, callback: (() -> dynamic)? = null) {

			if (locals == null || locals == undefined) {
				if (callback == null) {
					this.response.render(view)
				} else {
					this.response.render(callback)
				}
			} else if (callback == null) {
				this.response.render(view, locals)
			} else {
				this.response.render(view, locals, callback)
			}
		}

		fun send(body: dynamic): dynamic = this.response.send(body)
		fun send(body: Array<dynamic>): dynamic = this.response.send(body)
		fun send(body: String): dynamic = this.response.send(body)

		enum class DotFilesOptions { allow, deny, ignore; val value: String by lazy { this.toString() } }
		class FileOptions {
			var maxAge = 0
			var root = ""
			var lastModified = true
			var headers: dynamic = undefined
			var dotfiles = DotFilesOptions.ignore.value
			var acceptRanges= true
			var cacheControl = true
		}

		fun sendFile(path: String, options: FileOptions = FileOptions(), callback: (() -> dynamic)? = null) {
			if (callback == null) {
				this.response.sendFile(path, options)
			} else {
				this.response.sendFile(path, options, callback)
			}
		}

		fun sendStatus(statusCode: Int) = this.response.sendStatus(statusCode)
		fun sendStatus(statusCode: StatusCode) = this.response.sendStatus(statusCode.value)

		fun setStatus(statusCode: Int) = this.response.status(statusCode)
		fun setStatus(statusCode: StatusCode) = this.response.status(statusCode.value)

		fun set(field: String, value: String) = this.response.set(field, value)
		fun set(field: String, value: dynamic) = this.response.set(field, value)

		fun setType(type: String) = this.response.type(type)
		fun type(type: String) = this.response.type(type)

		fun vary(field: String) = this.response.vary(field)
	}

	/// Note: to retrieve parameters use "request.query.parameterName"
	fun get(path: String, handler: (request: Request, response: Response) -> dynamic) {
		app.get(path) { request, response ->
			response.type("text/html")
			handler(Request(request), Response(response))
		}
	}
	fun routeGETto(path: String, handler: (request: Request, response: Response) -> dynamic) = this.get(path, handler)

	/// Note: to retrieve parameters use "request.body.parameterName"
	fun post(path: String, handler: (request: Request, response: Response) -> dynamic) {
		app.post(path) { request, response ->
			response.type("text/html")
			handler(Request(request), Response(response))
		}
	}
	fun routePOSTto(path: String, handler: (request: Request, response: Response) -> dynamic) = this.post(path, handler)

	fun listen(handler: () -> dynamic) {
		app.listen(this.port) {
			handler()
		}
	}

	init {
		val bodyParser = require("body-parser")
		app.use(bodyParser.json())
		app.use(bodyParser.urlencoded(object { val extended = true }))
	}
}
