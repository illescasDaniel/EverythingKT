/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything.Common

external fun alert(str: String)
external val Infinity: Double
external val Nan: Double
external fun isFinite(number: Number): Boolean
external fun parseFloat(value: dynamic): Float
external fun parseInt(strValue: String, radix: Int): Int
external fun decodeURI(encodedURI: dynamic): String
external fun encodeURIComponent(str: String): String

external class Prototype {
	val constructor: dynamic
	fun hasOwnProperty(property: String): Boolean
	fun isPrototypeOf(obj: Object): Boolean
	fun propertyIsEnumerable(property: String): Boolean
	fun toLocaleString(): String
	//fun toString(): String
	fun valueOf(): dynamic
}

external class Object(obj: dynamic) {

	val length: Int
	val prototype: Prototype

	companion object {
		fun assign(targetObject: Object, sources: dynamic): Object
		fun create(proto: Object, propertiesObject: dynamic = definedExternally): Object
		fun defineProperty(obj: Object, property: String, descriptor: dynamic): Object
		fun defineProperties(obj: Object, properties: dynamic): Object
		fun entries(obj: Object): dynamic
		fun freeze(obj: Object): Object
		fun getOwnPropertyDescriptor(obj: Object, property: String): String?
		fun getPrototypeOf(obj: Object): String?
		fun `is`(value1: dynamic, value2: dynamic): Boolean
		fun isExtensible(obj: Object): Boolean
		fun isFrozen(obj: Object): Boolean
		fun isSealed(obj: Object): Boolean
		fun keys(obj: Object): Array<dynamic>
		fun preventExtensions(obj: Object): Object
		fun seal(obj: Object): Object
		fun setPrototypeOf(obj: Object, prototype: Prototype?): Object
		fun values(obj: Object): Array<dynamic>
	}

	val constructor: dynamic
	fun hasOwnProperty(property: String): Boolean
	fun isPrototypeOf(obj: Object): Boolean
	fun propertyIsEnumerable(property: String): Boolean
	fun toLocaleString(): String
	//fun toString(): String
	fun valueOf(): dynamic
}

external class Function(vararg arguments: String, functionBody: String) {

	val length: Int
	val name: String

	fun apply(thisArgument: dynamic, arguments: Array<dynamic>): dynamic
	fun bind(thisArgument: dynamic, vararg arguments: dynamic): dynamic //
	fun call(thisArgument: dynamic, vararg arguments: dynamic): dynamic
}